package cn.effine.lab.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.*;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.GroupParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 */
public class SearchJobs {

    private static Logger logger = LoggerFactory.getLogger(SearchJobs.class);

    public static int JobsId = 219443;
    private static String solr_url = "http://localhost:8010/solr/product";
    private static String url = "jdbc:mysql://192.168.2.106:1433;DatabaseName=JobsOtherweb51jobDB";
    private static String user = "sa";
    private static String password = "sa";
    public SolrServer solrServer = null;
    private String corenum;
    // HttpSolrServer("http://192.168.2.100:8080/solr/JobsOtherWeb1");

    /**
     *  1、 创建solrserver对象：
     */
    public SolrServer createSolrServer() {
        HttpSolrServer solr = null;
        try {
            solr = new HttpSolrServer(solr_url);
            solr.setConnectionTimeout(100);
            solr.setDefaultMaxConnectionsPerHost(100);
            solr.setMaxTotalConnections(100);
        } catch (Exception e) {
            System.out.println("请检查tomcat服务器或端口是否开启!");
            e.printStackTrace();
        }
        return solr;
    }

    /**
     *  简单的查询，取出二十个
     */
    public void querytop20() {
        solrServer = createSolrServer();

        System.out.println("简单查询取出前二十个");
        String dtStart = new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date());
        System.out.println("开始时间：" + dtStart + "\n");
        try {
            // 查询
            SolrQuery query = new SolrQuery();
            query.setQuery("jobsName:计算机");
            query.setRows(20);
            SolrDocumentList docs = solrServer.query(query).getResults();
            for (SolrDocument sd : docs) {
                System.out.println(sd.getFieldValue("jobsName"));
                System.out.println(sd.getFieldValue("publishDate"));
            }
            solrServer.shutdown();
            String dtEnd = new SimpleDateFormat("yyyyMMddHHmmssSSS")
                    .format(new Date());
            System.out.println(query);
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 删除索引(据查询结果删除)
     */
    public void deleteByQuery() {
        solrServer = createSolrServer();
        try {
            // 删除所有的索引
            solrServer.deleteByQuery("jobsName:高级技术支持");
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据索引号删除索引
     */
    public void deleteByQueryJobsId() {
        solrServer = createSolrServer();
        try {
            solrServer.deleteById("515792");
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询
    // SolrJ提供的查询功能比较强大，可以进行结果中查询、范围查询、排序等。
    // 补充一下范围查询的格式：[star t TO end]，start与end是相应数据格式的值的字符串形式，“TO” 一定要保持大写！
    /**
     * field 查询的字段名称数组 key 查询的字段名称对应的值 start 查询的起始位置 count 一次查询出来的数量 sortfield
     * 需要排序的字段数组 flag 需要排序的字段的排序方式如果为true 升序 如果为false 降序 hightlight 是否需要高亮显示
     */
    public QueryResponse search(String[] field, String[] key, int start,
                                int count, String[] sortfield, Boolean[] flag, Boolean hightlight) {
        solrServer = createSolrServer();
        // 检测输入是否合法
        if (null == field || null == key || field.length != key.length) {
            return null;
        }
        if (null == sortfield || null == flag
                || sortfield.length != flag.length) {
            return null;
        }

        SolrQuery query = null;
        try {
            // 初始化查询对象
            query = new SolrQuery(field[0] + ":" + key[0]);
            for (int i = 0; i < field.length; i++) {
                query.addFilterQuery(field[i] + ":" + key[i]);
            }
            // 设置起始位置与返回结果数
            query.setStart(start);
            query.setRows(count);
            // 设置排序
            for (int i = 0; i < sortfield.length; i++) {
                if (flag[i]) {
                    query.addSort(sortfield[i], SolrQuery.ORDER.asc);
                } else {
                    query.addSort(sortfield[i], SolrQuery.ORDER.desc);
                }
            }
            // 设置高亮
            if (null != hightlight) {
                // 开启高亮组件
                query.setHighlight(true);
                // 高亮字段
                query.addHighlightField("jobsName");
                // 标记
                query.setHighlightSimplePre("<font color=\"red\">");
                query.setHighlightSimplePost("</font>");
                // 结果分片数，默认为1
                query.setHighlightSnippets(1);
                // 每个分片的最大长度，默认为100
                query.setHighlightFragsize(1000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        QueryResponse rsp = null;
        try {
            rsp = solrServer.query(query);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // 返回查询结果
        return rsp;
    }

    // Facet的一个应用：自动补全
    // prefix为前缀，min为最大返回结果数
    // field需要查询并返回不全的字段，prefix需要查询并返回的字段不全值

    public String[] autoComplete(String field, String prefix, int min) {
        /*------------第一处标记------------------------*/
        solrServer = createSolrServer();
        String[] words = null;
        StringBuffer sb = new StringBuffer("");
        SolrQuery query = new SolrQuery(field + ":" + prefix);
        QueryResponse rsp = new QueryResponse();
        // Facet为solr中的层次分类查询
        /*------------第二处标记：程序从第一处标记执行到这里需要300ms所以将上面的代码进行实例化最好------------------------*/
        try {
            query.setFacet(true);
            query.setQuery("*:*");
            query = new SolrQuery(field + ":" + prefix);
            query.setFacetPrefix(prefix);
            query.addFacetField(field);
            rsp = solrServer.query(query);
            /*------------第三处标记：程序从第二处标记执行到这里需要200ms但此处很难再进行优化，由于查询的复杂性------------------------*/
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
        if (null != rsp) {
            FacetField ff = rsp.getFacetField(field);
            List<FacetField.Count> countList = ff.getValues();
            if (null == countList) {
                return null;
            }
            for (int i = 0; i < countList.size(); i++) {
                String[] tmp = countList.get(i).toString().split(" ");
                // 排除单个字
                if (tmp[0].length() < 2) {
                    continue;
                }
                sb.append(tmp[0] + " ");
                min--;
                if (min == 0) {
                    break;
                }
            }
            words = sb.toString().split(" ");
        } else {
            return null;
        }
        return words;
    }

    /**
     * @param queryContent 查询内容
     * @param queryRows    查找的数量,默认是10
     * @param group        true or false 是否按group查询
     * @param groupField   查询field
     * @param groupLimit   The number of results (documents) to return for each group. Defaults to 1
     * @Author fjsh
     * @Title searchGroup
     * @Description 按group进行查找
     * @Return void
     * @Throws
     * @Date 2014-5-7
     * 输出结果的时候，由于定义的数据索引没有做很好是调整，显示的结果并不理想，不过此方法可以作为参考
     */
    public void searchGroup(String queryContent, int queryRows, Boolean group, String groupField, String groupLimit) {
        SolrServer server = createSolrServer();
        SolrQuery param = new SolrQuery();
        param.setQuery("jobsName:" + queryContent);
        param.setRows(queryRows);
        param.setParam(GroupParams.GROUP, group);
        param.setParam(GroupParams.GROUP_FIELD, groupField);
        param.setParam(GroupParams.GROUP_LIMIT, groupLimit);
        QueryResponse response = null;
        try {
            response = server.query(param);
        } catch (SolrServerException e) {
             logger.error(e.getMessage(), e);
        }
        Map<String, Integer> info = new HashMap<String, Integer>(64);
        GroupResponse groupResponse = response.getGroupResponse();
        if (groupResponse != null) {
            List<GroupCommand> groupList = groupResponse.getValues();
            for (GroupCommand groupCommand : groupList) {
                List<Group> groups = groupCommand.getValues();
                for (Group item : groups) {
                    info.put(item.getGroupValue(), (int) item.getResult().getNumFound());
                    System.out.println(item.getGroupValue() + "---" + item.getResult().getNumFound());
                }
            }
        }
    }

    /**
     * 介绍了一下facet之后，来说说怎么实现facet。facet的实现其实很简单，主要在搜索参数上带上就OK。
     *
     * facet=on/true #代表开启facet facet.field=cate #代表要统计的面（分组），比如上面的分类，品牌，可以多次出现
     * facet.limit =20 #每个分组最多返回条数 facet.mincount = 1 #这个表示分组下某一条目的最小数据量
     * facet.missing = on/true #统计null的值 facet.method = #默认为fc, fc表示Field Cache
     * 比如
     * ：http://localhost/product/select/?q=铁观音&facet=on&facet.field=category&facet
     * .field=brand&facet.mincount=1在搜索结果中返回xml的facet结果
     *
     *
     * view sourceprint? 01 <lst name="facet_counts"> 02 <lst
     * name="facet_queries"/> 03 <lst name="facet_fields"> 04 <lst
     * name="category"> 05 <int name="2742">64</int> 06 <int name="793">48</int>
     * 07 <int name="2741">12</int> 08 <int name="801">6</int> 09 <int
     * name="1087">1</int> 10 </lst> 11 <lst name="brand"> 12 <int
     * name="229">74</int> 13 <int name="227">16</int> 14 <int
     * name="270">13</int> 15 <int name="317">10</int> 16 <int name="0">4</int>
     * 17 <int name="165">4</int> 18 <int name="203">3</int> 19 <int
     * name="147">2</int> 20 <int name="166">2</int> 21 <int name="217">1</int>
     * 22 <int name="342">1</int> 23 <int name="343">1</int> 24 </lst> 25 </lst>
     * <lst name="category"> 分组名 <int name="2742">64</int>
     * 分组内条目，name表示条目，64是统计结果数。
     *
     *
     *
     *
     * Date Facet 日期类型的字段在文档中很常见 , 如商品上市时间 , 货物出仓时间 , 书籍上架时间等等 . 某些情况下需要针对这些字段进行
     * Facet. 不过时间字段的取值有无限性 , 用户往往关心的不是某个时间点而是某个时间段内的查询统计结果 . Solr
     * 为日期字段提供了更为方便的查询统计方式 . 当然 , 字段的类型必须是 DateField( 或其子类型 ). 需要注意的是 , 使用 Date
     * Facet 时 , 字段名 , 起始时间 , 结束时间 , 时间间隔这 4 个参数都必须提供 . 与 Field Facet 类似 ,Date
     * Facet 也可以对多个字段进行 Facet. 并且针对每个字段都可以单独设置参数 . 2.1 facet.date 该参数表示需要进行 Date
     * Facet 的字段名 , 与 facet.field 一样 , 该参数可以被设置多次 , 表示对多个字段进行 Date Facet. 2.2
     * facet.date.start 起始时间 , 时间的一般格式为 ” 1995-12-31T23:59:59Z”, 另外可以使用
     * ”NOW”,”YEAR”,”MONTH” 等等 , 具体格式可以参考 org.apache.solr.schema. DateField 的
     * java doc. 2.3 facet.date.end 结束时间 . 2.4 facet.date.gap 时间间隔 . 如果 start 为
     * 2009-1-1,end 为 2010-1-1.gap 设置为 ”+1MONTH” 表示间隔 1 个月 , 那么将会把这段时间划分为 12
     * 个间隔段 . 注意 ”+” 因为是特殊字符所以应该用 ”%2B” 代替 . 2.5 facet.date.hardend 取值可以为
     * true|false, 默认为 false. 它表示 gap 迭代到 end 处采用何种处理 . 举例说明 start 为
     * 2009-1-1,end 为 2009-12-25,gap 为 ”+1MONTH”,hardend 为 false 的话最后一个时间段为
     * 2009-12-1 至 2010-1-1;hardend 为 true 的话最后一个时间段为 2009-12-1 至 2009-12-25.
     * 2.6 facet.date.other 取值范围为 before|after|between|none|all, 默认为 none.
     * before 会对 start 之前的值做统计 . after 会对 end 之后的值做统计 . between 会对 start 至 end
     * 之间所有值做统计 . 如果 hardend 为 true 的话 , 那么该值就是各个时间段统计值的和 . none 表示该项禁用 . all 表示
     * before,after,all 都会统计 . 举例 : &facet=on &facet.date=date
     * &facet.date.start=2009-1-1T0:0:0Z &facet.date.end=2010-1-1T0:0:0Z
     * &facet.date.gap=%2B1MONTH &facet.date.other=all
     */
    public void facetFieldQuery() throws Exception {
        solrServer = createSolrServer();
        // 建立一个新的查询
        SolrQuery query = new SolrQuery();
        query.setQuery("jobsName:计算机维护");
        // 设置facet=on
        query.setFacet(true);
        // 分类信息分为：薪水，发布时间，教育背景，工作经验，公司类型，工作类型
        // 设置需要facet的字段
        query.addFacetField(new String[]{"salary", "publishDate",
                "educateBackground", "jobExperience", "companytype", "jobsType"});
        // 限制facet返回的数量
        query.setFacetLimit(10);
        // 不统计null的值
        query.setFacetMissing(false);
        // 设置返回的数据中每个分组的数据最小值，比如设置为1，则统计数量最小为1，不然不显示
        query.setFacetMinCount(1);
        query.addFacetQuery("publishDate:[2014-04-11T00:00:00Z TO 2014-04-13T00:00:00Z]");

        QueryResponse response = solrServer.query(query);
        System.out.println("查询时间：" + response.getQTime());
        // 返回的facet列表
        List<FacetField> facets = response.getFacetFields();
        for (FacetField facet : facets) {
            System.out.println(facet.getName());
            System.out.println("----------------");
            List<FacetField.Count> counts = facet.getValues();
            for (FacetField.Count count : counts) {
                System.out.println(count.getName() + ":" + count.getCount());
            }
            System.out.println();
        }

    }

    /**
     * 时间片使用方法
     * @throws Exception
     */
    public void facetFieldQueryDate() throws Exception {
        solrServer = createSolrServer();
        // 建立一个新的查询
        SolrQuery query = new SolrQuery();
        query.setQuery("jobsName:计算");
        // 设置facet=on
        query.setFacet(true);
        // 限制facet返回的数量
        query.setFacetLimit(10);
        // 不统计null的值
        query.setFacetMissing(false);
        // 设置返回的数据中每个分组的数据最小值，比如设置为1，则统计数量最小为1，不然不显示
        query.setFacetMinCount(1);
        // 设置需要facet的字段
        query.addFacetField(new String[]{"salary", "educateBackground", "jobExperience", "companytype", "jobsType"});
        query.addFacetQuery("publishDate:[2014-04-11T00:00:00Z TO 2014-04-13T00:00:00Z]");

        SimpleDateFormat time0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat time1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time2 = new SimpleDateFormat("HH:mm:ss");

        Calendar c = Calendar.getInstance();
        c.setTime(time0.parse(time1.format(c.getTime()) + " 23:59:59"));
        Date date = c.getTime();
        String dateNow = time1.format(date) + "T" + time2.format(date) + "Z";
        c.setTime(time0.parse(time1.format(c.getTime()) + " 23:59:59"));
        c.add(Calendar.DATE, -1);
        date = c.getTime();
        // 当天
        query.addFacetQuery("publishDate:[" + time1.format(date) + "T"
                + time2.format(date) + "Z" + " TO " + dateNow + "]");
        c.add(Calendar.DATE, -2);
        date = c.getTime();
        // 前三天
        query.addFacetQuery("publishDate:[" + time1.format(date) + "T"
                + time2.format(date) + "Z" + " TO " + dateNow + "]");
        c.add(Calendar.DATE, -4);
        date = c.getTime();
        // 前一周
        query.addFacetQuery("publishDate:[" + time1.format(date) + "T"
                + time2.format(date) + "Z" + " TO " + dateNow + "]");
        c.add(Calendar.DATE, -7);
        date = c.getTime();
        // 前两周
        query.addFacetQuery("publishDate:[" + time1.format(date) + "T"
                + time2.format(date) + "Z" + " TO " + dateNow + "]");
        c.add(Calendar.DATE, -16);
        date = c.getTime();
        // 前一个月
        query.addFacetQuery("publishDate:[" + time1.format(date) + "T"
                + time2.format(date) + "Z" + " TO " + dateNow + "]");
        c.add(Calendar.DATE, -30);
        date = c.getTime();
        // 前两个月
        query.addFacetQuery("publishDate:[" + time1.format(date) + "T"
                + time2.format(date) + "Z" + " TO " + dateNow + "]");

        QueryResponse response = solrServer.query(query);
        System.out.println("查询时间：" + response.getQTime());
        // 返回的facet列表
        List<FacetField> facets = response.getFacetFields();
        for (FacetField facet : facets) {
            System.out.println(facet.getName());
            System.out.println("----------------");
            List<FacetField.Count> counts = facet.getValues();
            for (FacetField.Count count : counts) {
                System.out.println(count.getName() + ":" + count.getCount());
            }
            System.out.println();
        }
        // 根据时间段来获取数据
        Map<String, Integer> maps = response.getFacetQuery();
        for (Map.Entry<String, Integer> entry : maps.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }

    // 最终使用的查询方式
    // SolrJ提供的查询功能比较强大，可以进行结果中查询、范围查询、排序等。
    // 补充一下范围查询的格式：[star t TO end]，start与end是相应数据格式的值的字符串形式，“TO” 一定要保持大写！
    /**
     * field 查询的字段名称数组 key 查询的字段名称对应的值 start 查询的起始位置 count 一次查询出来的数量 sortfield
     * 需要排序的字段数组 flag 需要排序的字段的排序方式如果为true 升序 如果为false 降序 hightlight 是否需要高亮显示
     */
    public QueryResponse searchResult(String[] field, String[] key, int start,
                                      int count, String[] sortfield, Boolean[] flag, Boolean hightlight)
            throws Exception {
        solrServer = createSolrServer();
        // 检测输入是否合法
        if (null == field || null == key || field.length != key.length) {
            return null;
        }


        SolrQuery query = null;
        try {
            // 初始化查询对象
            query = new SolrQuery(field[0] + ":" + key[0]);
            for (int i = 0; i < field.length; i++) {
                query.addFilterQuery(field[i] + ":" + key[i]);
            }
            // 设置起始位置与返回结果数
            query.setStart(start);
            query.setRows(count);
            // 设置排序
            if (!(null == sortfield || null == flag
                    || sortfield.length != flag.length)) {
                for (int i = 0; i < sortfield.length; i++) {
                    if (flag[i]) {
                        query.addSort(sortfield[i], SolrQuery.ORDER.asc);
                    } else {
                        query.addSort(sortfield[i], SolrQuery.ORDER.desc);
                    }
                }
            }

            // 设置高亮
            if (null != hightlight) {
                // 开启高亮组件
                query.setHighlight(true);
                // 高亮字段
                query.addHighlightField("jobsName");
                // 标记
                query.setHighlightSimplePre("<font color=\"red\">");
                query.setHighlightSimplePost("</font>");
                // 结果分片数，默认为1
                query.setHighlightSnippets(1);
                // 每个分片的最大长度，默认为100
                query.setHighlightFragsize(1000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 设置facet=on
        query.setFacet(true);
        // 限制facet返回的数量
        query.setFacetLimit(10);
        // 不统计null的值
        query.setFacetMissing(false);
        // 设置返回的数据中每个分组的数据最小值，比如设置为1，则统计数量最小为1，不然不显示
        query.setFacetMinCount(1);
        // 设置需要facet的字段
        query.addFacetField(new String[]{"salary", "educateBackground", "jobExperience", "companytype", "jobsType"});
        query.addFacetQuery("publishDate:[2014-04-21T00:00:00Z TO 2014-04-23T00:00:00Z]");

        SimpleDateFormat time0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat time1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time2 = new SimpleDateFormat("HH:mm:ss");

        Calendar c = Calendar.getInstance();
        c.setTime(time0.parse(time1.format(c.getTime()) + " 23:59:59"));
        Date date = c.getTime();
        String dateNow = time1.format(date) + "T" + time2.format(date) + "Z";
        c.setTime(time0.parse(time1.format(c.getTime()) + " 23:59:59"));
        c.add(Calendar.DATE, -1);
        date = c.getTime();
        // 当天
        query.addFacetQuery("publishDate:[" + time1.format(date) + "T"
                + time2.format(date) + "Z" + " TO " + dateNow + "]");
        c.add(Calendar.DATE, -2);
        date = c.getTime();
        // 前三天
        query.addFacetQuery("publishDate:[" + time1.format(date) + "T"
                + time2.format(date) + "Z" + " TO " + dateNow + "]");
        c.add(Calendar.DATE, -4);
        date = c.getTime();
        // 前一周
        query.addFacetQuery("publishDate:[" + time1.format(date) + "T"
                + time2.format(date) + "Z" + " TO " + dateNow + "]");
        c.add(Calendar.DATE, -7);
        date = c.getTime();
        // 前两周
        query.addFacetQuery("publishDate:[" + time1.format(date) + "T"
                + time2.format(date) + "Z" + " TO " + dateNow + "]");
        c.add(Calendar.DATE, -16);
        date = c.getTime();
        // 前一个月
        query.addFacetQuery("publishDate:[" + time1.format(date) + "T"
                + time2.format(date) + "Z" + " TO " + dateNow + "]");
        c.add(Calendar.DATE, -30);
        date = c.getTime();
        // 前两个月
        query.addFacetQuery("publishDate:[" + time1.format(date) + "T"
                + time2.format(date) + "Z" + " TO " + dateNow + "]");

        QueryResponse rsp = null;
        try {
            rsp = solrServer.query(query);
            System.out.println("此次查询时间qtime :" + rsp.getQTime());
            // 返回的facet列表
            List<FacetField> facets = rsp.getFacetFields();
            for (FacetField facet : facets) {
                System.out.println(facet.getName());
                System.out.println("----------------");
                List<FacetField.Count> counts = facet.getValues();
                for (FacetField.Count countitem : counts) {
                    System.out.println(countitem.getName() + ":"
                            + countitem.getCount());
                }
                System.out.println();
            }
            // 根据时间段来获取数据
            Map<String, Integer> maps = rsp.getFacetQuery();
            for (Map.Entry<String, Integer> entry : maps.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
            // 获取返回的结果
            SolrDocumentList docs = rsp.getResults();
            for (SolrDocument doc : docs) {
                System.out.println("-----");
                System.out.println(doc.getFieldValue("jobsName"));
                System.out.println(doc.getFieldValue("publishDate"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // 返回查询结果
        return rsp;
    }
}
