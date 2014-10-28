package dictionary;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Class that tests {@link WikipediaLinksParser} against sample output.
 * 
 * @author Pidanic
 * 
 */
public class WikipediaLinksParserTest
{
    private static final File OUTPUT_EN = new File("data" + File.separator
            + "sample_output_wikipedia_links_en.csv");

    private static final File INPUT_EN = new File("data" + File.separator
            + "sample_wikipedia_links_en.ttl");

    private static final File OUTPUT_DE = new File("data" + File.separator
            + "sample_output_wikipedia_links_de.csv");

    private static final File INPUT_DE = new File("data" + File.separator
            + "sample_wikipedia_links_de.ttl");

    private static final File OUTPUT_FR = new File("data" + File.separator
            + "sample_output_wikipedia_links_fr.csv");

    private static final File INPUT_FR = new File("data" + File.separator
            + "sample_wikipedia_links_fr.ttl");

    private static final File OUTPUT_SK = new File("data" + File.separator
            + "sample_output_wikipedia_links_sk.csv");

    private static final File INPUT_SK = new File("data" + File.separator
            + "sample_wikipedia_links_sk.ttl");

    private Parser wikipediaLinks;

    private CsvReader reader;

    @Before
    public void init()
    {
        wikipediaLinks = new WikipediaLinksParser();
        reader = new CsvReader();
    }

    @Test
    public void testParseWikipediaLinksEn() throws IOException
    {
        Map<String, String> expected = reader.readCsv(OUTPUT_EN);
        Map<String, String> actual = wikipediaLinks.parse(INPUT_EN);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testParseWikipediaLinksDe() throws IOException
    {
        Map<String, String> expected = reader.readCsv(OUTPUT_DE);
        Map<String, String> actual = wikipediaLinks.parse(INPUT_DE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testParseWikipediaLinksFr() throws IOException
    {
        Map<String, String> expected = reader.readCsv(OUTPUT_FR);
        Map<String, String> actual = wikipediaLinks.parse(INPUT_FR);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testParseWikipediaLinksSk() throws IOException
    {
        Map<String, String> expected = reader.readCsv(OUTPUT_SK);
        Map<String, String> actual = wikipediaLinks.parse(INPUT_SK);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IOException.class)
    public void testFileNotFound() throws IOException
    {
        Map<String, String> expected = reader.readCsv(new File("wrong_file"));
        Map<String, String> actual = wikipediaLinks.parse(INPUT_SK);
        Assert.assertEquals(expected, actual);
    }
}
