package eu.pidanic.ir.app;

import eu.pidanic.ir.util.Language;

/**
 * 
 * @author Pidanic
 *
 */
public final class Resource implements Comparable<Resource>
{
    private final String url;

    private final Language lang;

    private final String wikiUrl;

    public Resource(Language lang, String url, String wikiLink)
    {
        this.lang = lang;
        this.url = url;
        this.wikiUrl = wikiLink;
    }

    public Language getLanguage()
    {
        return lang;
    }

    public String getUrl()
    {
        return url;
    }

    public String getWikiUrl()
    {
        return wikiUrl;
    }

    @Override
    public String toString()
    {
        return lang + " | " + url + " | " + wikiUrl;
    }

    @Override
    public int compareTo(Resource o)
    {
        // TODO check for exception?
        return lang.compareTo(o.lang);
    }
}
