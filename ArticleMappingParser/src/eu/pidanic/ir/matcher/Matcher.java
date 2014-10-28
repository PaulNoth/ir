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

/**
 * 
 * Class for matching parsed data.
 * 
 * @author Pidanic
 *
 */
final class Matcher
{
    /**
     * Creates temporary mapping for given files.
     * 
     * @param dbPediaMappingPath
     *            path for DBPedia mapping.
     * @param wikipediaMappingPath
     *            path for Wikipedialinks mapping.
     * @throws IOException
     *             if problem with manipulating files.
     * 
     */
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

        // File skWiki = new File(wikipediaMappingPath);
        // Map<String, String> skWikipedia = reader.readCsv(skWiki);

        String lang = dbPediaMappingPath.substring(
                dbPediaMappingPath.lastIndexOf("_") + 1,
                dbPediaMappingPath.indexOf("."));
        File output = new File("temp\\sample_output_dbpedia_wikipedia_links_"
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

        outWr.flush();
        outWr.close();
    }
}
