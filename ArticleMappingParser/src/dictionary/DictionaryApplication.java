package dictionary;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.lucene.queryparser.classic.ParseException;

/**
 * 
 * Main class.
 * 
 * @author Pidanic
 *
 */
public class DictionaryApplication
{
    public static void main(String[] args) throws IOException, ParseException
    {
        DictionaryCorpusCreator dict = new DictionaryCorpusCreator();
        dict.createEnhancedDictionary();

        LuceneDbpediaDictionary dictionarySearch;
        System.out.println("Vitajte v aplikácii slovník\n");
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("-> Pre obohatený slovník zadajte 1");
            System.out.println("-> Pre jednoduchý slovník zadajte 2");
            System.out.println("-> Pre rozšírené informácie zadajte \"h\"");
            System.out
                    .println("-> Pre koniec zadajte \"q\" (funguje aj pri výbere jazyka)");
            System.out.print(">");
            String option = scanner.nextLine();
            if("1".equals(option))
            {
                dictionarySearch = new LuceneEnhancedDictionary();
                break;
            }
            else if("2".equals(option))
            {
                dictionarySearch = new LuceneSimpleDictionary();
                break;
            }
            else if("q".equalsIgnoreCase(option))
            {
                scanner.close();
                System.exit(0);
            }
            else if("h".equalsIgnoreCase(option))
            {
                showHelp();
            }
            else
            {
                System.out.println("Neznáma voľba");
            }
        }
        System.out.println("Slovník sa inicializuje, prosím čakajte");
        System.out.println();

        System.out.println("Podporované jazyky SK, EN, DE, FR");
        while (true)
        {
            try
            {
                System.out.print("Zadajte preklad z (EN, SK, FR, DE): ");
                String searchLang = scanner.nextLine().toUpperCase();
                if("q".equalsIgnoreCase(searchLang))
                {
                    break;
                }
                Language from = Language.valueOf(searchLang);
                System.out.print("Zadajte preklad do (EN, SK, FR, DE): ");
                String toString = scanner.nextLine().toUpperCase();
                if("q".equalsIgnoreCase(toString))
                {
                    break;
                }
                Language to = Language.valueOf(toString);
                System.out.print("Zadajte hľadané slovo: ");

                String query = scanner.nextLine();
                List<String> result = dictionarySearch.search(from, to, query);
                System.out.println("\nZobrazujem " + result.size()
                        + " výsledkov zoradených podľa najviac vyhovujúceho ");
                for (String s : result)
                {
                    System.out.println(s);
                }
                System.out.println();
                System.out.println();
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("neznámy jazyk");
            }
        }
        scanner.close();

        dictionarySearch.close();
    }

    private static void showHelp()
    {
        // TODO Auto-generated method stub
    }
}
