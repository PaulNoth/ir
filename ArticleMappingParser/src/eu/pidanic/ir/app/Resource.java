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

    public Resource(Language lang, String url)
    {
        this.lang = lang;
        this.url = url;
    }

    public Language getLanguage()
    {
        return lang;
    }

    public String getUrl()
    {
        return url;
    }

    @Override
    public String toString()
    {
        return lang + " | " + url;
    }

    @Override
    public int compareTo(Resource o)
    {
        // TODO check for exception?
        return lang.compareTo(o.lang);
    }
}
