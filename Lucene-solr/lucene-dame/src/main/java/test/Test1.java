package test;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Field.Store;
import java.io.File;
import java.io.IOException;
import org.junit.Test;

public class Test1 {
    String[] str={
            "3, 华为 - 华为电脑, 爆款",
            "4, 华为手机, 旗舰",
            "5, 联想 - Thinkpad, 商务本",
            "6, 联想手机, 自拍神器"
    };

    @org.junit.Test
    public void test1() throws IOException {
        //配置存放索引文件夹
        FSDirectory open = FSDirectory.open(
                new File("E:/solr").toPath());
        //配置中文分词器
        IndexWriterConfig config =
                new IndexWriterConfig(new SmartChineseAnalyzer());
        //索引输出工具
        IndexWriter writer=new IndexWriter(open,config);
        //遍历文档，输出索引
        for (String s:str){
            String[] stringArr=s.split(",");
            //创建文档,文档中包含的是要索引的字段
            Document doc = new Document();
            doc.add(new LongPoint("id", Long.parseLong(stringArr[0])));
            doc.add(new StoredField("id", Long.parseLong(stringArr[0])));
            //是否作为摘要
            doc.add(new TextField("title", stringArr[1], Store.YES));
            doc.add(new TextField("sellPoint", stringArr[2], Store.YES));
            //将文档写入磁盘索引文件
            writer.addDocument(doc);
        }
        writer.close();
    }


    @Test
    public void test2() throws IOException {
        //搜索索引中的数据
        //索引数据的保存目录
        File path = new File("E:/solr");
        FSDirectory d = FSDirectory.open(path.toPath());
        //创建搜索工具对象
        DirectoryReader reader = DirectoryReader.open(d);
        IndexSearcher searcher = new IndexSearcher(reader);

        //关键词搜索器,我们搜索 "title:华为"
        TermQuery q = new TermQuery(new Term("title", "华为"));
        //执行查询,并返回前20条数据
        TopDocs docs = searcher.search(q, 20);

        //遍历查询到的结果文档并显示（获取有得分的摘要）
        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            int id=scoreDoc.doc;
            float score=scoreDoc.score;     //根据文档索引获取相关度得分
            Document doc = searcher.doc(scoreDoc.doc);//根据文档编号获取文档
            System.out.println(id);
            System.out.println(score);
            System.out.println(doc.get("id"));
            System.out.println(doc.get("title"));
            System.out.println(doc.get("sellPoint"));
            System.out.println("--------------");
        }
    }
}
