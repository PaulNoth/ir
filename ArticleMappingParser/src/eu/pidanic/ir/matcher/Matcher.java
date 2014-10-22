package eu.pidanic.ir.matcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import eu.pidanic.ir.util.CsvReader;

public class Matcher
{
    public static void main(String[] args) throws IOException
    {
        Matcher match = new Matcher();
        match.createDbpediaWikipediaMapping(
                "data\\sample_output_interlanguage_links_fr.csv",
                "data\\sample_output_wikipedia_links_fr.csv");
        System.out.println("fr");
        match.createDbpediaWikipediaMapping(
                "temp\\output_interlanguage_links_fr.csv",
                "temp\\output_wikipedia_links_fr.csv");
        System.out.println("de");
        match.createDbpediaWikipediaMapping(
                "temp\\output_interlanguage_links_de.csv",
                "temp\\output_wikipedia_links_de.csv");
        System.out.println("sk");
        match.createDbpediaWikipediaMapping(
                "temp\\output_interlanguage_links_sk.csv",
                "temp\\output_wikipedia_links_sk.csv");
        System.out.println("en");
        match.createDbpediaWikipediaMapping(
                "temp\\output_interlanguage_links_en.csv",
                "temp\\output_wikipedia_links_en.csv");
    }

    public void createDbpediaWikipediaMapping(String dbPediaMappingPath,
            String wikipediaMappingPath) throws IOException
    {
        File slovak = new File(dbPediaMappingPath);
        CsvReader reader = new CsvReader();
        Map<String, String> slovakDbpedia = reader.readCsv(slovak);
        Map<String, String> slovakDbpediaReverse = new HashMap<>();
        for (Map.Entry<String, String> entry : slovakDbpedia.entrySet())
        {
            slovakDbpediaReverse.put(entry.getValue(), entry.getKey());
        }
        slovakDbpedia = null;

        File skWiki = new File(wikipediaMappingPath);
        // Map<String, String> skWikipedia = reader.readCsv(skWiki);

        String lang = dbPediaMappingPath.substring(
                dbPediaMappingPath.lastIndexOf("_") + 1,
                dbPediaMappingPath.indexOf("."));
        File output = new File(
                "C:\\Users\\Pavol\\Desktop\\Nový priečinok\\output_dbpedia_wikipedia_links_"
                        + lang + ".csv");
        BufferedReader br = new BufferedReader(new FileReader(
                wikipediaMappingPath));
        String line = null;
        BufferedWriter outWr = new BufferedWriter(new FileWriter(output));
        while ((line = br.readLine()) != null)
        {
            String[] tokens = line.split(",");
            if(slovakDbpediaReverse.containsKey(tokens[0]))
            {
                String id = slovakDbpediaReverse.get(tokens[0]);
                String dbpediaLink = tokens[0];
                String wikiLink = tokens[1];
                String lineOut = id + "," + dbpediaLink + "," + wikiLink + "\n";
                outWr.write(lineOut);
            }
        }
        br.close();

        // for (Map.Entry<String, String> entry : slovakDbpedia.entrySet())
        // {
        // String id = entry.getKey();
        // String dbpediaLink = entry.getValue();
        // if(skWikipedia.containsKey(dbpediaLink))
        // {
        // String wikiLink = skWikipedia.get(dbpediaLink);
        // String line = id + "," + dbpediaLink + "," + wikiLink + "\n";
        // lines.add(line);
        // // outWr.write(line);
        // // outWr.flush();
        // }
        // }

        outWr.flush();
        outWr.close();
    }
}
