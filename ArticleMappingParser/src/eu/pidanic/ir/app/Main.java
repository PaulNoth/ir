package eu.pidanic.ir.app;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.lucene.queryparser.classic.ParseException;

public class Main
{
    public static void main(String[] args) throws IOException, ParseException
    {
        // System.out.println(new WikilinksParser()
        // .parseWikipediaLinks(Language.SK));
        List<Set<Resource>> resources = new InterlanguageParser()
                .getTranslatedPairs();
        // System.out.println(resources);
        new Dictionary().createDictionary(resources);

        // Search search = new Search();
        // System.out.println("Vitajte v aplikácii slovník");
        // System.out.println("Podporované jazyky SK, EN, DE, FR");
        // while (true)
        // {
        // System.out.println("-> pre koniec zadajte \"q\"");
        // System.out.println("-> pre hľadanie zadajte \"h\"");
        // System.out.println(">");
        // Scanner scanner = new Scanner(System.in);
        // String action = scanner.nextLine();
        // if("q".equalsIgnoreCase(action))
        // {
        // break;
        // }
        // System.out.println("Zadajte jazyk (EN, SK, FR, DE):");
        // String searchLang = scanner.nextLine().toUpperCase();
        // Language lang = Language.valueOf(searchLang);
        // System.out.println("Zadajte hľadané slovo:");
        //
        // String query = scanner.nextLine();
        // List<String> result = search.search(lang, query);
        // System.out.println("Zobrazujem " + result.size()
        // + " výsledkov zoradených podľa najviac vyhovujúceho");
        // for (String s : result)
        // {
        // System.out.println(s);
        // }
        // scanner.close();
        // }
    }
}
