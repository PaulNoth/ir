package eu.pidanic.ir.app;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        // System.out.println(new WikilinksParser()
        // .parseWikipediaLinks(Language.SK));
        List<Set<Resource>> resources = new InterlanguageParser()
                .getTranslatedPairs();
        // System.out.println(resources);
        new Dictionary().createDictionary(resources);
    }
}
