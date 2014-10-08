package eu.pidanic.ir.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.pidanic.ir.util.Language;
import eu.pidanic.ir.util.LinkUtil;

/**
 * 
 * Main class.
 * 
 * @author Pidanic
 *
 */
public class App
{

    private static final List<String> LOCAL_FILES;
    static
    {
        List<String> temp = new ArrayList<>();
        // temp.add("C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_en.ttl");
        temp.add("C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_en.ttl");
        // temp.add("C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_de.ttl");
        temp.add("C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_de.ttl");
        // temp.add("C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_sk.ttl");
        temp.add("C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_sk.ttl");
        temp.add("C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_fr.ttl");
        LOCAL_FILES = Collections.unmodifiableList(temp);
    }

    public static void main(String[] args) throws IOException
    {
        File dir = new File(
        // "C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_en.ttl");
        // "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_en.ttl");
        // "C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_de.ttl");
        // "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_de.ttl");
        // "C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_sk.ttl");
        // "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_sk.ttl");
        // "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_fr.ttl");
        // "data" + File.separator + "sample_interlanguage_links_sk.ttl");
                "data" + File.separator + "interlanguage_links");
        for (File f : dir.listFiles())
        // for (String localPath : LOCAL_FILES)
        {
            // BufferedReader br = new BufferedReader(new FileReader(f));
            // File f = new File(localPath);
            BufferedReader br = new BufferedReader(new FileReader(f));

            String line = null;
            String lastResource = "";
            List<Resource> result = new ArrayList<>();
            while ((line = br.readLine()) != null)
            {
                // System.out.println(line);
                if(!line.startsWith("#"))
                {
                    String[] resources = line.split("\\s+");
                    String resource = LinkUtil.removeBracket(resources[0]);
                    String translResource = LinkUtil
                            .removeBracket(resources[2]);

                    if(!lastResource.equals(resource))
                    {
                        String id = LinkUtil.parseWord(translResource);
                        Language searchLang = Language
                                .valueOf(f
                                        .getName()
                                        .substring(
                                                f.getName().lastIndexOf("_") + 1,
                                                f.getName().indexOf("."))
                                        .toUpperCase());
                        Resource r = new Resource(id, searchLang, resource);
                        // System.out.println(r);
                        result.add(r);
                        lastResource = resource;
                    }
                }
            }
            System.out.println(result);
            br.close();
        }

    }
}
