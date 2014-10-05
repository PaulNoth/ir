package eu.pidanic.ir.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import eu.pidanic.ir.util.Language;
import eu.pidanic.ir.util.LinkUtil;

public class WikilinksParser
{
    public static void main(String[] args) throws IOException
    {
        String path = "data" + File.separator + "wikipedia_links"
                + File.separator + "sample_wikipedia_links_sk.ttl";
        File f = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(f));
        String line = null;
        String lastResource = "";
        while ((line = br.readLine()) != null)
        {
            // System.out.println(line);
            String[] links = line.split("\\s+");
            if(links[0].contains("dbpedia"))
            {
                String dbpediaUrl = LinkUtil.removeBracket(links[0]);
                String wikiUrl = LinkUtil.removeBracket(links[2]);
                if(!lastResource.equals(dbpediaUrl))
                {
                    WikilinkResource wr = new WikilinkResource(Language.SK,
                            dbpediaUrl, wikiUrl);
                    System.out.println(wr);
                }
            }
        }
        br.close();
    }
}
