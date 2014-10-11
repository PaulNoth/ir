package eu.pidanic.ir.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import eu.pidanic.ir.util.LinkUtil;
import eu.pidanic.ir.util.StringUtil;

public class Dictionary
{
    private static final String PATH = "temp" + File.separator
            + "dictionary.txt";

    // TODO rethrow exception and log
    public void createDictionary(List<Set<Resource>> pairs) throws IOException
    {
        File dictionary = new File(PATH);
        BufferedWriter bw = new BufferedWriter(new FileWriter(dictionary));

        for (Set<Resource> set : pairs)
        {
            StringBuilder line = new StringBuilder();
            for (Resource resource : set)
            {
                String word = StringUtil.makeWords(LinkUtil.parseWord(resource
                        .getUrl()));
                line.append(word);
                line.append("|");
                String wikiLink = resource.getWikiUrl();
                line.append(wikiLink);
                line.append("|");
            }
            // removes last '|' and replaces with \n
            line.setCharAt(line.length() - 1, '\n');
            bw.write(line.toString());
        }
        bw.close();
    }
}
