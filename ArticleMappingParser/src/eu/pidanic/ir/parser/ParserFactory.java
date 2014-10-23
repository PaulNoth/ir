package eu.pidanic.ir.parser;

public final class ParserFactory
{
    private ParserFactory()
    {
        throw new AssertionError(ParserFactory.class.getName()
                + " can not be instatiated");
    }

    public static Parser createInterlanguageLinksParser()
    {
        return new InterlanguageLinksParser();
    }

    public static Parser createWikipediaLinksParser()
    {
        return new WikipediaLinksParser();
    }
}
