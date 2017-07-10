package cn.effine.lab.solr;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * @author effine
 * @Date 2017-05-14 14:36
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 * @sine 0.1
 */
public class LuceneHelloWord {


    public static final String INDEX_DIR = "/sources/temp/lucene/index";

    public static void main(String[] args) throws IOException, ParseException {

        File file = new File(INDEX_DIR);
        // 建立
        Directory directory = FSDirectory.open(file);

        // 建立内存索引对象
        // Directory ramdDirectory = new RAMDirectory();

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_47, new WhitespaceAnalyzer(Version.LUCENE_47));
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        System.out.println("lucene first demo: 开始建立索引 ... ");
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        // 建立索引(可以扩展：创建多个文档加入索引)
        Document document = new Document();
        document.add(new TextField("content", "this is my first lucene demo", Field.Store.YES));
        indexWriter.addDocument(document);


        // 提交并关闭
        indexWriter.commit();
        indexWriter.close();

        System.out.println("lucene first demo: 索引建立完成");



        System.out.println("lucene first demo: 开始查询....");
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // "centent"表示查询的标题
        QueryParser queryParser = new QueryParser(Version.LUCENE_47, "content", new WhitespaceAnalyzer(Version.LUCENE_47));

        // "lucene"： 在name为"content"的值value中搜索文本"lucene"
        Query query = queryParser.parse("lucene");
        query.setBoost(2f);

        // 可以增加排序功能
        Sort sort = new Sort(new SortField("content", SortField.Type.SCORE), new SortField("date", SortField.Type.INT, true) );

        // 100 表示前多少条记录，可以做分页使用
        TopDocs topDocs = indexSearcher.search(query, 100);
        // TopDocs topDocs1 = indexSearcher.search(query, 100, sort);

        System.out.println("总记录数：" + topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            Document doc = indexSearcher.doc(scoreDoc.doc);
            System.out.println("得分：" + scoreDoc.score);
            System.out.println("field: " + doc.getField("content"));
            System.out.println("得分算法：" + indexSearcher.explain(query, scoreDoc.doc));

        }

    }
}
