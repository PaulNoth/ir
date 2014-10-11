package eu.pidanic.ir.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import eu.pidanic.ir.util.Language;
import eu.pidanic.ir.util.LinkUtil;

public final class WikilinksParser
{
    // private static final File[] FILES = new File("data" + File.separator
    // + "wikipedia_links").listFiles();

    private static final File[] FILES;
    static
    {
        FILES = new File[4];
        FILES[0] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_fr.ttl");
        FILES[1] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_de.ttl");
        FILES[2] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_en.ttl");
        FILES[3] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_sk.ttl");
    }

    public WikilinksParser()
    {
    }

    // TODO rethrow exception and log to some file
    public Map<String, String> parseWikipediaLinks(Language lang)
            throws IOException
    {
        Map<String, String> result = new HashMap<>();
        for (File file : FILES)
        {
            if(file.getName().contains(lang.toString().toLowerCase()))
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = null;
                String lastResource = "";
                while ((line = br.readLine()) != null)
                {
                    if(!line.startsWith("#"))
                    {
                        String[] links = line.split("\\s+");
                        if(links[0].contains("dbpedia"))
                        {
                            String dbpediaUrl = LinkUtil
                                    .removeBracket(links[0]);
                            if(!lastResource.equals(dbpediaUrl))
                            {
                                String wikiUrl = LinkUtil
                                        .removeBracket(links[2]);
                                // String word = LinkUtil.parseWord(dbpediaUrl);
                                // word = StringUtil.makeWords(word);
                                result.put(dbpediaUrl, wikiUrl);
                            }
                        }
                    }
                }
                br.close();
            }
        }
        return result;
    }
}
