package eu.pidanic.ir.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import eu.pidanic.ir.util.LinkUtil;

public class Testahsdasibp
{
    private static final File[] FILES;
    static
    {
        FILES = new File[4];
        FILES[0] = new File(
                "C:\\Users\\Paul P\\Desktop\\New Folder\\interlanguage_links_fr.ttl");
        FILES[1] = new File(
                "C:\\Users\\Paul P\\Desktop\\New Folder\\interlanguage_links_de.ttl");
        FILES[2] = new File(
                "C:\\Users\\Paul P\\Desktop\\New Folder\\interlanguage_links_en.ttl");
        FILES[3] = new File(
                "C:\\Users\\Paul P\\Desktop\\New Folder\\interlanguage_links_sk.ttl");
    }

    private static final File[] FILES_WIKILINKS;
    static
    {
        FILES_WIKILINKS = new File[4];
        FILES_WIKILINKS[0] = new File(
                "C:\\Users\\Paul P\\Desktop\\New Folder\\wikipedia_links_fr.ttl");
        FILES_WIKILINKS[1] = new File(
                "C:\\Users\\Paul P\\Desktop\\New Folder\\wikipedia_links_de.ttl");
        FILES_WIKILINKS[2] = new File(
                "C:\\Users\\Paul P\\Desktop\\New Folder\\wikipedia_links_en.ttl");
        FILES_WIKILINKS[3] = new File(
                "C:\\Users\\Paul P\\Desktop\\New Folder\\wikipedia_links_sk.ttl");
    }

    public static void main(String[] args) throws IOException
    {
        for (int i = 0; i < FILES.length; i++)
        {
            Parser parser = new InterlanguageLinksParser();
            BufferedReader br = new BufferedReader(new FileReader(FILES[i]));
            String line = null;
            String lastResource = "";
            BufferedWriter bw = new BufferedWriter(new FileWriter("interl" + i
                    + ".csv"));
            while ((line = br.readLine()) != null)
            {
                if(!line.startsWith("#"))
                {
                    String[] resources = line.split("\\s+");
                    String resource = LinkUtil.removeBracket(resources[0]);
                    String idResource = LinkUtil.removeBracket(resources[2]);
                    if(!lastResource.equals(resource))
                    {
                        String id = LinkUtil.parseWord(idResource);
                        // result.put(id, resource);
                        resource = resource.replaceAll(",", "_");
                        String outline = id + "," + resource + "\n";
                        // System.out.println(outline);
                        bw.write(outline);
                        lastResource = resource;
                    }
                }
            }
            bw.flush();
            bw.close();
            br.close();
        }
        for (int i = 0; i < FILES_WIKILINKS.length; i++)
        {
            Parser parser = new WikipediaLinksParser();
            BufferedReader br = new BufferedReader(new FileReader(
                    FILES_WIKILINKS[i]));
            String line = null;
            String lastResource = "";
            BufferedWriter bw = new BufferedWriter(new FileWriter("wiki" + i
                    + ".csv"));
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
                            dbpediaUrl = dbpediaUrl.replaceAll(",", "_");
                            wikiUrl = wikiUrl.replaceAll(",", "_");
                            String outline = dbpediaUrl + "," + wikiUrl + "\n";
                            // System.out.println(outline);
                            bw.write(outline);
                            // result.put(dbpediaUrl, wikiUrl);
                        }
                    }
                }
            }
            bw.flush();
            bw.close();
            br.close();
        }
    }
}
