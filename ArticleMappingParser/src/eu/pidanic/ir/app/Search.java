package eu.pidanic.ir.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import eu.pidanic.ir.util.Language;

public class Search
{
    private static Analyzer analyzer;

    private static Directory directory;

    static
    {
        try
        {
            analyzer = new StandardAnalyzer();
            directory = new RAMDirectory();
            IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,
                    analyzer);
            IndexWriter iwriter = new IndexWriter(directory, config);

            File dictionary = new File("temp\\dictionary_full.txt");
            BufferedReader br = new BufferedReader(new FileReader(dictionary));

            String line = null;
            while ((line = br.readLine()) != null)
            {
                Document doc = new Document();
                String[] words = line.split("\\|");
                doc.add(new Field(Language.SK.toString(), words[0],
                        TextField.TYPE_STORED));
                doc.add(new Field(Language.EN.toString(), words[1],
                        TextField.TYPE_STORED));
                doc.add(new Field(Language.DE.toString(), words[2],
                        TextField.TYPE_STORED));
                doc.add(new Field(Language.FR.toString(), words[3],
                        TextField.TYPE_STORED));
                iwriter.addDocument(doc);
            }
            br.close();

            iwriter.close();
        }
        catch (IOException io)
        {
            // TODO handle ex
        }
        finally
        {
            // TODO close stream
        }
    }

    // TODO rethrow an log exception
    public List<String> search(Language lang, String searchText)
            throws IOException, ParseException
    {
        // search index
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // parse simple query that searches for "text"
        QueryParser qp = new QueryParser(lang.toString(), analyzer);
        Query query = qp.parse(searchText);
        TopDocs topDocs = isearcher.search(query, 20);
        ScoreDoc[] hits = topDocs.scoreDocs;
        // ScoreDoc[] hits = isearcher.search(query, 1000).scoreDocs; // moze
        // filtrovat,
        // sortovat
        List<String> result = new ArrayList<>();
        for (int i = 0; i < hits.length; i++)
        {
            Document hitDoc = isearcher.doc(hits[i].doc);
            // System.out.println(hitDoc);
            // System.out.println(hits[i].score);
            // assertEquals("This is the text to be indexed.",
            // hitDoc.get("fieldname"));
            StringBuilder resultLine = new StringBuilder();
            resultLine.append(hitDoc.get(Language.SK.toString()));
            resultLine.append("|");
            resultLine.append(hitDoc.get(Language.EN.toString()));
            resultLine.append("|");
            resultLine.append(hitDoc.get(Language.DE.toString()));
            resultLine.append("|");
            resultLine.append(hitDoc.get(Language.FR.toString()));
            resultLine.append("|");
            resultLine.append(hits[i].score);
            result.add(resultLine.toString());
        }
        ireader.close();
        directory.close();
        return result;
    }
}
