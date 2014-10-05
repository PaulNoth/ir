package eu.pidanic.ir.util;

/**
 * Utility class for manipulating with dbpedia resource links.
 * 
 * @author Pidanic
 *
 */
public final class LinkUtil
{
    private LinkUtil()
    {
        throw new AssertionError(LinkUtil.class.getName()
                + " cannot be instatiated");
    }

    public static String removeBracket(String link)
    {
        return link.substring(1, link.length() - 1);
    }

    public static String parseWord(String link)
    {
        int lastSlash = link.lastIndexOf("/");
        String word = link.substring(lastSlash + 1, link.length());
        return word;
    }
}
