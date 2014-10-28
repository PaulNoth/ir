package eu.pidanic.ir.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Simple CSV file creator.
 * 
 * @author Pidanic
 *
 */
public class CsvCreator
{
    /**
     * Creates <tt>csv</tt> file using given data.
     * 
     * @param fileName
     *            File to create.
     * @param data
     *            Data.
     * @throws IOException
     *             if problem manipulatin file.
     */
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
