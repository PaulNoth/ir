package eu.pidanic.ir.app;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.lucene.queryparser.classic.ParseException;

import eu.pidanic.ir.matcher.Dictionary;
import eu.pidanic.ir.util.Language;

public class Main
{
    public static void main(String[] args) throws IOException, ParseException
    {
        Dictionary dict = new Dictionary();
        dict.createDictionary();

        Search search = new Search();
        System.out.println("Vitajte v aplikácii slovník");
        System.out.println("Podporované jazyky SK, EN, DE, FR");
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("-> pre koniec zadajte \"q\"");
            System.out.println("-> pre hľadanie stlačte ľubovoľnú klávesu");
            System.out.print(">");
            String action = scanner.nextLine();
            if("q".equalsIgnoreCase(action))
            {
                break;
            }
            System.out.print("Zadajte preklad z (EN, SK, FR, DE): ");
            String searchLang = scanner.nextLine().toUpperCase();
            Language from = Language.valueOf(searchLang);
            System.out.print("Zadajte preklad do (EN, SK, FR, DE): ");
            String toString = scanner.nextLine().toUpperCase();
            Language to = Language.valueOf(toString);
            System.out.print("Zadajte hľadané slovo: ");

            String query = scanner.nextLine();
            List<String> result = search.search(from, to, query);
            System.out.println("\nZobrazujem " + result.size()
                    + " výsledkov zoradených podľa najviac vyhovujúceho ");
            for (String s : result)
            {
                System.out.println(s);
            }
            System.out.println();
            System.out.println();
        }
        scanner.close();

        search.close();
    }
}
