package eu.pidanic.ir.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CsvCreator
{
    public void createCsv(String fileName, Map<String, String> data)
            throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for (Map.Entry<String, String> entry : data.entrySet())
        {
            bw.write(entry.getKey() + "," + entry.getValue() + "\n");
        }
        bw.close();
    }
}
