package eu.pidanic.ir.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class CsvReader
{
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
