package eu.pidanic.ir.app;

import eu.pidanic.ir.util.Language;

public class WikilinkResource
{
    private final Language lang;

    private final String dbpediaUrl;

    private final String wikiUrl;

    public WikilinkResource(Language lang, String dbpediaUrl, String wikiUrl)
    {
        this.lang = lang;
        this.dbpediaUrl = dbpediaUrl;
        this.wikiUrl = wikiUrl;
    }

    public String getDbpediaUrl()
    {
        return dbpediaUrl;
    }

    public Language getLanguage()
    {
        return lang;
    }

    public String getWikiUrl()
    {
        return wikiUrl;
    }

    @Override
    public String toString()
    {
        return lang + " | " + dbpediaUrl + " | " + wikiUrl;
    }
}
