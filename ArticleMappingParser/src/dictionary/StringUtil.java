package dictionary;

/**
 * Utility class for String operations.
 * 
 * @author Pidanic
 *
 */
public final class StringUtil
{
    /**
     * Creates string with words separated with spaces.
     * 
     * @param dbWord
     * @return word
     */
    public static String makeWords(String dbWord)
    {
        return dbWord.replaceAll("_", " ");
    }
}
