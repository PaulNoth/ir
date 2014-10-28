package eu.pidanic.ir.parser;

/**
 * 
 * Factory for creating concrete parsers.
 * 
 * @author Pidanic
 *
 */
public final class ParserFactory
{
    private ParserFactory()
    {
        throw new AssertionError(ParserFactory.class.getName()
                + " can not be instatiated");
    }

    /**
     * Creates new instance of <tt>interlanguagelinks</tt> Parser.
     * 
     * @return {@link Parser} instance.
     */
    public static Parser createInterlanguageLinksParser()
    {
        return new InterlanguageLinksParser();
    }

    /**
     * Creates new instance of <tt>interlanguagelinks</tt> Parser.
     * 
     * @return {@link Parser} instance.
     */
    public static Parser createWikipediaLinksParser()
    {
        return new WikipediaLinksParser();
    }
}
