package cn.effine.lab.solr;

import cn.effine.filter.Token;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author effine  Email: zhangyafei#co-mall.com
 * @version 3.2.1
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @since 2017-05-10 14:53
 */
public class QueryParserDemo {

    public static void main(String[] args) throws ParseException, IOException {

        String field = "title";
        QueryParser parser = new QueryParser(Version.LUCENE_47, field, new WhitespaceAnalyzer(Version.LUCENE_47));

        // 查找同时包含Persist及Google的文档；修改field为"Persist AND Google"也可以实现
        parser.setDefaultOperator(QueryParser.AND_OPERATOR);
        //parser.setDefaultOperator(QueryParser.Operator.AND);



        Query query = parser.parse("-blackword:牛奶 进口");

        System.out.println(query.getClass().getName());


        StringReader sr = new StringReader(field);
        Analyzer analyzer = new WhitespaceAnalyzer(Version.LUCENE_47);

        TokenStream tokenStream = analyzer.tokenStream("t", sr);


        Token token = null;
        // 开始分词


    }

}
