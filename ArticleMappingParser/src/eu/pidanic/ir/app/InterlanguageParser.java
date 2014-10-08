package eu.pidanic.ir.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import eu.pidanic.ir.util.Language;
import eu.pidanic.ir.util.LinkUtil;

public class InterlanguageParser
{
    // private static final File[] FILES = new File("data" + File.separator
    // + "interlanguage_links").listFiles();

    private static final File[] FILES;
    static
    {
        FILES = new File[3];
        FILES[0] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_fr.ttl");
        FILES[1] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_de.ttl");
        FILES[2] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_en.ttl");
        // FILES[3] = new File(
        // "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_sk.ttl");
    }

    // static
    // {
    // FILES = new File[3];
    // FILES[0] = new File(
    // "data\\interlanguage_links\\sample_interlanguage_links_fr.ttl");
    // FILES[1] = new File(
    // "data\\interlanguage_links\\sample_interlanguage_links_de.ttl");
    // FILES[2] = new File(
    // "data\\interlanguage_links\\sample_interlanguage_links_en.ttl");
    // }

    private static final File SK = new File(
            "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_sk.ttl");

    // private static final File SK = new File(
    // "data\\interlanguage_links\\sample_interlanguage_links_sk.ttl");

    // TODO rethrow exception and log to some file
    public List<Set<Resource>> getTranslatedPairs() throws IOException
    {
        Map<String, Set<Resource>> pairs = preParseSk();
        for (int i = 0; i < FILES.length; i++)
        {
            String filename = FILES[i].getName();
            Language lang = Language.valueOf(filename.substring(
                    filename.lastIndexOf("_") + 1, filename.indexOf("."))
                    .toUpperCase());
            String line = null;
            BufferedReader br = new BufferedReader(new FileReader(FILES[i]));
            String lastResource = "";
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
                        Resource r = new Resource(lang, resource);
                        Set<Resource> set = pairs.get(id);
                        if(set != null)
                        {
                            set.add(r);
                            pairs.put(id, set);
                        }
                        lastResource = resource;
                    }
                }
            }
            Set<String> keySet = new HashSet<>(pairs.keySet());
            for (String id : keySet)
            {
                if(pairs.get(id).size() <= i + 1)
                {
                    pairs.remove(id);
                    // System.out.println("removed: " + id);
                }
            }
            br.close();
        }
        System.out.println(pairs.size());
        return new ArrayList<>(pairs.values());
    }

    private Map<String, Set<Resource>> preParseSk() throws IOException
    {
        Map<String, Set<Resource>> pairs = new HashMap<>();
        String filename = SK.getName();
        Language lang = Language.valueOf(filename.substring(
                filename.lastIndexOf("_") + 1, filename.indexOf("."))
                .toUpperCase());
        String line = null;
        BufferedReader br = new BufferedReader(new FileReader(SK));
        String lastResource = "";
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
                    Resource r = new Resource(lang, resource);
                    Set<Resource> set = pairs.get(id);
                    if(set == null)
                    {
                        set = new TreeSet<>();
                        set.add(r);
                        pairs.put(id, set);
                    }
                    lastResource = resource;
                }
            }
        }
        System.out.println(pairs.size());
        br.close();
        return pairs;
    }
}
