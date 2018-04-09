package cn.effine.lab.solr;

import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 */
public class QueryParserDemo {

    public static void main(String[] args) throws ParseException, IOException {

//        String field = "title";
//        QueryParser parser = new QueryParser(Version.LUCENE_47, field, new WhitespaceAnalyzer(Version.LUCENE_47));
//
//        // 查找同时包含Persist及Google的文档；修改field为"Persist AND Google"也可以实现
//        parser.setDefaultOperator(QueryParser.AND_OPERATOR);
//        //parser.setDefaultOperator(QueryParser.Operator.AND);
//
//
//
//        QUERY query = parser.parse("-blackword:牛奶 进口");
//
//        System.out.println(query.getClass().getName());
//
//
//        StringReader sr = new StringReader(field);
//        Analyzer analyzer = new WhitespaceAnalyzer(Version.LUCENE_47);
//
//        TokenStream tokenStream = analyzer.tokenStream("t", sr);
//
//
//        Token token = null;
//        // 开始分词


    }

}
