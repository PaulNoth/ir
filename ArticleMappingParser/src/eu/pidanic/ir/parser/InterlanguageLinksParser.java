package eu.pidanic.ir.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import eu.pidanic.ir.util.LinkUtil;

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
}
