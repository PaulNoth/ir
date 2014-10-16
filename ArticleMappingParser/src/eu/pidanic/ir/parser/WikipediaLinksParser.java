package eu.pidanic.ir.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import eu.pidanic.ir.util.LinkUtil;

final class WikipediaLinksParser implements Parser
{
    @Override
    public Map<String, String> parse(File file) throws IOException
    {
        Map<String, String> result = new HashMap<>();
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
                    String dbpediaUrl = LinkUtil.removeBracket(links[0]);
                    if(!lastResource.equals(dbpediaUrl))
                    {
                        String wikiUrl = LinkUtil.removeBracket(links[2]);
                        result.put(dbpediaUrl, wikiUrl);
                    }
                }
            }
        }
        br.close();
        return result;
    }

}
