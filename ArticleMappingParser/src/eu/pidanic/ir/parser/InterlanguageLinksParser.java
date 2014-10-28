package eu.pidanic.ir.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import eu.pidanic.ir.util.LinkUtil;

/**
 * Class for parsing <tt>interlanguage_links_*.ttl</tt> from DBpedia dump.
 * 
 * @author Pidanic
 *
 */
final class InterlanguageLinksParser implements Parser
{

    @Override
    public Map<String, String> parse(File file) throws IOException
    {
        Map<String, String> result = new HashMap<>();

        String line = null;
        BufferedReader br = new BufferedReader(new FileReader(file));
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
                    result.put(id, resource);
                    lastResource = resource;
                }
            }
        }
        br.close();
        return result;
    }

    @Override
    public void parseToFile(File from, File to) throws IOException
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

    @Override
    public Map<String, String> parse(String path) throws IOException
    {
        return parse(new File(path));
    }

    @Override
    public void parseToFile(String pathFrom, String pathTo) throws IOException
    {
        parseToFile(new File(pathFrom), new File(pathTo));
    }
}
