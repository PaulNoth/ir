package eu.pidanic.ir.app;

import eu.pidanic.ir.util.Language;

/**
 * 
 * @author Pidanic
 *
 */
public final class Resource
{
    private final String id;

    private final String url;

    private final Language lang;

    public Resource(String id, Language lang, String url)
    {
        this.lang = lang;
        this.id = id;
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

    public String getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return id + " | " + lang + " | " + url;
    }
}
