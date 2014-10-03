package eu.pidanic.ir.util;

/**
 * 
 * Enumeration for Dbpedia Languages used in this program.
 * 
 * @author Pidanic
 *
 */
public enum Language
{
	SK("sk."), EN(""), DE("de."), FR("fr.");

	/**
	 * value of dbpedia prefix.
	 */
	private final String prefix;

	private Language(String prefix)
	{
		this.prefix = prefix;
	}

	/**
	 * <p>
	 * Returns prefix used in dbpedia (<tt>http://[prefix]dbpedia.org/...</tt>).
	 * </p>
	 * <p>
	 * E.g. <tt>http://sk.dbpedia.org/resource/Blog</tt>
	 * <p>
	 * 
	 * @return prefix
	 */
	public String getPrefix()
	{
		return prefix;
	}
}
