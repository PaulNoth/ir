package dictionary;

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

    /**
     * Removes beginning and end angle brackets from input url
     * 
     * @param link
     * @return
     */
    public static String removeBracket(String link)
    {
        return link.substring(1, link.length() - 1);
    }

    /**
     * Parses a word from URL. Word means string after last slash.
     * 
     * @param link
     * @return word.
     */
    public static String parseWord(String link)
    {
        int lastSlash = link.lastIndexOf("/");
        String word = link.substring(lastSlash + 1, link.length());
        return word;
    }
}
