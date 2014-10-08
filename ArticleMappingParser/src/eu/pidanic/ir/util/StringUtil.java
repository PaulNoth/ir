package eu.pidanic.ir.util;

public final class StringUtil
{
    public static String makeWords(String dbWord)
    {
        return dbWord.replaceAll("_", " ");
    }
}
