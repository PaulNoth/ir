package dictionary;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;

public abstract class LuceneDbpediaDictionary
{
    protected Analyzer analyzer;

    protected Directory directory;

    protected abstract void initializeDictionaryData();

    public abstract List<String> search(Language from, Language to,
            String searchText) throws IOException, ParseException;

    public void close() throws IOException
    {
        directory.close();
    }

    public LuceneDbpediaDictionary()
    {
        this.initializeDictionaryData();
    }
}
