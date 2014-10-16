package eu.pidanic.ir.parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import eu.pidanic.ir.dumper.CsvCreator;

public class Testahsdasibp
{
    private static final File[] FILES;
    static
    {
        FILES = new File[4];
        FILES[0] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_fr.ttl");
        FILES[1] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_de.ttl");
        FILES[2] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_en.ttl");
        FILES[3] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\interlanguage_links_sk.ttl");
    }

    private static final File[] FILES_WIKILINKS;
    static
    {
        FILES_WIKILINKS = new File[4];
        FILES_WIKILINKS[0] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_fr.ttl");
        FILES_WIKILINKS[1] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_de.ttl");
        FILES_WIKILINKS[2] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_en.ttl");
        FILES_WIKILINKS[3] = new File(
                "C:\\Users\\Paul P\\Desktop\\New folder\\wikipedia_links_sk.ttl");
    }

    public static void main(String[] args) throws IOException
    {
        for (int i = 0; i < FILES_WIKILINKS.length; i++)
        {
            Parser parser = new WikipediaLinksParser();
            Map<String, String> data = parser.parse(FILES_WIKILINKS[i]);
            System.out
                    .println(FILES_WIKILINKS[i].getName() + " " + data.size());
            CsvCreator creator = new CsvCreator();
            creator.createCsv("output" + i + ".csv", data);
        }
    }
}
