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

import eu.pidanic.ir.util.LinkUtil;
import eu.pidanic.ir.util.StringUtil;

public class Dictionary
{
    private static final String SK_PATH = "temp\\output_dbpedia_wikipedia_links_sk.csv";

    private static final String EN_PATH = "temp\\output_dbpedia_wikipedia_links_en.csv";

    private static final String DE_PATH = "temp\\output_dbpedia_wikipedia_links_de.csv";

    private static final String FR_PATH = "temp\\output_dbpedia_wikipedia_links_fr.csv";

    public static void main(String[] args) throws IOException
    {
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
            if(tokens.length < 3)
            {
                System.out.println();
            }
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
                "temp\\sample_dictonary.csv")));
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
}
