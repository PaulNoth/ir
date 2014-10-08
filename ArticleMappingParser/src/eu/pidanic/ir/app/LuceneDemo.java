package eu.pidanic.ir.app;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class LuceneDemo
{
    public static void main(String[] args) throws IOException, ParseException
    {
        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,
                analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        Document doc = new Document();
        Document doc2 = new Document();
        String text = "This is the text to be indexed.";
        String text2 = "This is the text to be indexed.";
        doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
        doc2.add(new Field("fieldname", text2, TextField.TYPE_STORED));
        iwriter.addDocument(doc);
        iwriter.addDocument(doc2);
        iwriter.close();

        // search index
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // parse simple query that searches for "text"
        QueryParser qp = new QueryParser("fieldname", analyzer);
        Query query = qp.parse("text");
        ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs; // moze
                                                                   // filtrovat,
                                                                   // sortovat
        for (int i = 0; i < hits.length; i++)
        {
            Document hitDoc = isearcher.doc(hits[i].doc);
            System.out.println(hitDoc);
            // assertEquals("This is the text to be indexed.",
            // hitDoc.get("fieldname"));
        }
        ireader.close();
        directory.close();
    }
}
