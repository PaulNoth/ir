package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

public class Main
{

    private static final File[] FILES_INTERLANGUAGE;
    static
    {
        FILES_INTERLANGUAGE = new File[4];
        FILES_INTERLANGUAGE[0] = new File(
                "C:\\Users\\Paul P\\Desktop\\New Folder\\interlanguage_links_fr.ttl");
        FILES_INTERLANGUAGE[1] = new File(
                "C:\\Users\\Paul P\\Desktop\\New Folder\\interlanguage_links_de.ttl");
        FILES_INTERLANGUAGE[2] = new File(
                "C:\\Users\\Paul P\\Desktop\\New Folder\\interlanguage_links_en.ttl");
        FILES_INTERLANGUAGE[3] = new File(
                "C:\\Users\\Paul P\\Desktop\\New Folder\\interlanguage_links_sk.ttl");
    }

    public static void main(String[] args) throws IOException, ParseException
    {
        parseToFile(FILES_INTERLANGUAGE[0], new File("fr.csv"));
        parseToFile(FILES_INTERLANGUAGE[1], new File("de.csv"));
        parseToFile(FILES_INTERLANGUAGE[2], new File("en.csv"));
        parseToFile(FILES_INTERLANGUAGE[3], new File("sk.csv"));
    }

    public static void parseToFile(File from, File to) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(from));
        String line = null;
        String lastResource = "";
        BufferedWriter bw = new BufferedWriter(new FileWriter(to));
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
                    String outline = id
                            + ","
                            + StringUtil
                                    .makeWords(LinkUtil.parseWord(resource))
                            + "\n";
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
}
