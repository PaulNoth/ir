package eu.pidanic.ir.matcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import eu.pidanic.ir.parser.Parser;
import eu.pidanic.ir.parser.ParserFactory;
import eu.pidanic.ir.util.LinkUtil;
import eu.pidanic.ir.util.StringUtil;

/**
 * Class for creating input dictionary from all parsed files.
 * 
 * @author Pidanic
 *
 */
public class Dictionary
{
    private static final String SK_PATH = "temp" + File.separator
            + "sample_output_dbpedia_wikipedia_links_sk.csv";

    private static final String EN_PATH = "temp" + File.separator
            + "sample_output_dbpedia_wikipedia_links_en.csv";

    private static final String DE_PATH = "temp" + File.separator
            + "sample_output_dbpedia_wikipedia_links_de.csv";

    private static final String FR_PATH = "temp" + File.separator
            + "sample_output_dbpedia_wikipedia_links_fr.csv";

    private static final File[] FILES_INTERLANGUAGE;
    // static
    // {
    // FILES_INTERLANGUAGE = new File[4];
    // FILES_INTERLANGUAGE[0] = new File(
    // "C:\\Users\\Paul P\\Desktop\\New Folder\\interlanguage_links_fr.ttl");
    // FILES_INTERLANGUAGE[1] = new File(
    // "C:\\Users\\Paul P\\Desktop\\New Folder\\interlanguage_links_de.ttl");
    // FILES_INTERLANGUAGE[2] = new File(
    // "C:\\Users\\Paul P\\Desktop\\New Folder\\interlanguage_links_en.ttl");
    // FILES_INTERLANGUAGE[3] = new File(
    // "C:\\Users\\Paul P\\Desktop\\New Folder\\interlanguage_links_sk.ttl");
    // }
    static
    {
        FILES_INTERLANGUAGE = new File[4];
        FILES_INTERLANGUAGE[0] = new File("data" + File.separator
                + "sample_interlanguage_links_fr.ttl");
        FILES_INTERLANGUAGE[1] = new File("data" + File.separator
                + "sample_interlanguage_links_de.ttl");
        FILES_INTERLANGUAGE[2] = new File("data" + File.separator
                + "sample_interlanguage_links_en.ttl");
        FILES_INTERLANGUAGE[3] = new File("data" + File.separator
                + "sample_interlanguage_links_sk.ttl");
    }

    private static final File[] FILES_WIKILINKS;
    // static
    // {
    // FILES_WIKILINKS = new File[4];
    // FILES_WIKILINKS[0] = new File(
    // "C:\\Users\\Paul P\\Desktop\\New Folder\\wikipedia_links_fr.ttl");
    // FILES_WIKILINKS[1] = new File(
    // "C:\\Users\\Paul P\\Desktop\\New Folder\\wikipedia_links_de.ttl");
    // FILES_WIKILINKS[2] = new File(
    // "C:\\Users\\Paul P\\Desktop\\New Folder\\wikipedia_links_en.ttl");
    // FILES_WIKILINKS[3] = new File(
    // "C:\\Users\\Paul P\\Desktop\\New Folder\\wikipedia_links_sk.ttl");
    // }
    static
    {
        FILES_WIKILINKS = new File[4];
        FILES_WIKILINKS[0] = new File("data" + File.separator
                + "sample_wikipedia_links_fr.ttl");
        FILES_WIKILINKS[1] = new File("data" + File.separator
                + "sample_wikipedia_links_de.ttl");
        FILES_WIKILINKS[2] = new File("data" + File.separator
                + "sample_wikipedia_links_en.ttl");
        FILES_WIKILINKS[3] = new File("data" + File.separator
                + "sample_wikipedia_links_sk.ttl");
    }

    public void createDictionary() throws IOException
    {
        parseAll();

        matchAll();

        Map<String, Map<String, String>> result = new HashMap<>();

        BufferedReader sk = new BufferedReader(
                new FileReader(new File(SK_PATH)));
        String line = null;
        while ((line = sk.readLine()) != null)
        {
            String[] tokens = line.split(",");
            String key = tokens[0];
            Map<String, String> value = new LinkedHashMap<>();
            value.put(tokens[1], tokens[2]);
            result.put(key, value);
        }
        sk.close();

        BufferedReader fr = new BufferedReader(
                new FileReader(new File(FR_PATH)));
        String frLine = null;
        while ((frLine = fr.readLine()) != null)
        {
            String[] tokens = frLine.split(",");
            String key = tokens[0];
            if(result.containsKey(key))
            {
                result.get(key).put(tokens[1], tokens[2]);
            }
        }
        fr.close();

        BufferedReader en = new BufferedReader(
                new FileReader(new File(EN_PATH)));
        String enLine = null;
        while ((enLine = en.readLine()) != null)
        {
            String[] tokens = enLine.split(",");
            String key = tokens[0];
            if(result.containsKey(key))
            {
                result.get(key).put(tokens[1], tokens[2]);
            }
        }
        en.close();

        BufferedReader de = new BufferedReader(
                new FileReader(new File(DE_PATH)));
        String deLine = null;
        while ((deLine = de.readLine()) != null)
        {
            String[] tokens = deLine.split(",");
            String key = tokens[0];
            if(result.containsKey(key))
            {
                result.get(key).put(tokens[1], tokens[2]);
            }
        }
        de.close();

        BufferedWriter dictionary = new BufferedWriter(new FileWriter(new File(
                "temp" + File.separator + "sample_dictonary.csv")));
        for (Map.Entry<String, Map<String, String>> map : result.entrySet())
        {
            if(map.getValue().size() == 4)
            {
                StringBuilder dictLine = new StringBuilder();
                for (Map.Entry<String, String> entry : map.getValue()
                        .entrySet())
                {
                    dictLine.append(LinkUtil.parseWord(StringUtil
                            .makeWords(entry.getKey())));
                    dictLine.append(",");
                    dictLine.append(entry.getValue());
                    dictLine.append(",");
                }
                dictLine.replace(dictLine.lastIndexOf(","), dictLine.length(),
                        "\n");
                dictionary.write(dictLine.toString());
            }
        }
        dictionary.flush();
        dictionary.close();
    }

    private static void parseAll() throws IOException
    {
        Parser parser = ParserFactory.createInterlanguageLinksParser();
        parser.parseToFile(FILES_INTERLANGUAGE[0], new File("temp"
                + File.separator + "sample_output_interlanguage_links_fr.csv"));
        parser.parseToFile(FILES_INTERLANGUAGE[1], new File("temp"
                + File.separator + "sample_output_interlanguage_links_de.csv"));
        parser.parseToFile(FILES_INTERLANGUAGE[2], new File("temp"
                + File.separator + "sample_output_interlanguage_links_en.csv"));
        parser.parseToFile(FILES_INTERLANGUAGE[3], new File("temp"
                + File.separator + "sample_output_interlanguage_links_sk.csv"));

        parser = ParserFactory.createWikipediaLinksParser();
        parser.parseToFile(FILES_WIKILINKS[0], new File("temp" + File.separator
                + "sample_output_wikipedia_links_fr.csv"));

        parser.parseToFile(FILES_WIKILINKS[1], new File("temp" + File.separator
                + "sample_output_wikipedia_links_de.csv"));

        parser.parseToFile(FILES_WIKILINKS[2], new File("temp" + File.separator
                + "sample_output_wikipedia_links_en.csv"));

        parser.parseToFile(FILES_WIKILINKS[3], new File("temp" + File.separator
                + "sample_output_wikipedia_links_sk.csv"));
    }

    private static void matchAll() throws IOException
    {
        Matcher match = new Matcher();
        match.createDbpediaWikipediaMapping("temp" + File.separator
                + "sample_output_interlanguage_links_fr.csv", "temp"
                + File.separator + "sample_output_wikipedia_links_fr.csv");
        // System.out.println("fr");
        match.createDbpediaWikipediaMapping("temp" + File.separator
                + "sample_output_interlanguage_links_fr.csv", "temp"
                + File.separator + "sample_output_wikipedia_links_fr.csv");
        // System.out.println("de");
        match.createDbpediaWikipediaMapping("temp" + File.separator
                + "sample_output_interlanguage_links_de.csv", "temp"
                + File.separator + "sample_output_wikipedia_links_de.csv");
        // System.out.println("sk");
        match.createDbpediaWikipediaMapping("temp" + File.separator
                + "sample_output_interlanguage_links_sk.csv", "temp"
                + File.separator + "sample_output_wikipedia_links_sk.csv");
        // System.out.println("en");
        match.createDbpediaWikipediaMapping("temp" + File.separator
                + "sample_output_interlanguage_links_en.csv", "temp"
                + File.separator + "sample_output_wikipedia_links_en.csv");
    }
}
