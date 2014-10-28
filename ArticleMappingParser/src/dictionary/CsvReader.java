package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple reader for CSV files.
 * 
 * @author Pidanic
 *
 */
public final class CsvReader
{
    /**
     * Parses given csv file and return <code>Map</code> of result data. Parser
     * expect lines with only 2 comma separated values.
     * 
     * @param file
     *            File to parse.
     * @return <code>Map</code> of parsed data.
     * @throws IOException
     *             if problem manipulating file.
     */
    public Map<String, String> readCsv(File file) throws IOException
    {
        Map<String, String> result = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = br.readLine()) != null)
        {
            String[] s = line.split(",");
            result.put(s[0], s[1]);
        }
        br.close();
        return result;
    }
}
