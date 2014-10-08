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

import eu.pidanic.ir.util.LinkUtil;

public class Pairs
{
    public static void main(String[] args) throws IOException
    {
        File en = new File(
                "C:\\Users\\Pavol\\Desktop\\Nový priečinok\\interlanguage_links_en.ttl");
        File de = new File(
                "C:\\Users\\Pavol\\Desktop\\Nový priečinok\\interlanguage_links_de.ttl");
        File fr = new File(
                "C:\\Users\\Pavol\\Desktop\\Nový priečinok\\interlanguage_links_fr.ttl");
        File sk = new File(
                "C:\\Users\\Pavol\\Desktop\\Nový priečinok\\interlanguage_links_sk.ttl");
        List<File> files = new ArrayList<>();
        // files.add(de);
        files.add(fr);
        files.add(en);
        // files.add(sk);
        Map<String, Integer> pairs = new HashMap<>();
        String line = null;
        BufferedReader skBr = new BufferedReader(new FileReader(de));
        String lastResource = "";
        while ((line = skBr.readLine()) != null)
        {
            if(!line.startsWith("#"))
            {
                String[] resources = line.split("\\s+");
                String resource = LinkUtil.removeBracket(resources[0]);
                String translResource = LinkUtil.removeBracket(resources[2]);

                if(!lastResource.equals(resource))
                {
                    String id = LinkUtil.parseWord(translResource);
                    pairs.put(id, 1);
                    lastResource = resource;
                }
            }
        }
        skBr.close();
        // ---------------------------------
        for (int i = 1; i <= files.size(); i++)
        {
            System.out.println("parsing " + files.get(i - 1).getName());
            BufferedReader br = new BufferedReader(new FileReader(
                    files.get(i - 1)));

            while ((line = br.readLine()) != null)
            {
                if(!line.startsWith("#"))
                {
                    String[] resources = line.split("\\s+");
                    String resource = LinkUtil.removeBracket(resources[0]);
                    String translResource = LinkUtil
                            .removeBracket(resources[2]);

                    if(!lastResource.equals(resource))
                    {
                        String id = LinkUtil.parseWord(translResource);
                        if(pairs.get(id) != null)
                        {
                            int count = pairs.get(id);
                            pairs.put(id, ++count);
                        }
                        lastResource = resource;
                    }
                }
            }
            br.close();
            Set<String> keySet = new HashSet<>(pairs.keySet());
            for (String id : keySet)
            {
                if(pairs.get(id).intValue() <= i)
                {
                    pairs.remove(id);
                    // System.out.println("removed: " + id);
                }
            }
        }
        System.out.println(pairs.size());

    }
}
