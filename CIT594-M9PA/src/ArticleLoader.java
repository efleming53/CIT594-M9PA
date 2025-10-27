import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * =========================================================
 * WARNING: DO NOT MODIFY THIS CLASS
 *
 * This file is provided as part of the starter code.
 * It is used by the autograder and your implementation
 * will rely on its behavior being consistent.
 * =========================================================
 */
public class ArticleLoader {

    /**
     * Load articles from a simplified CSV dataset.
     * Each line contains: uri, title, and concepts (as JSON-like string).
     */
    public static List<Article> loadFromCSV(String filename) {
        List<Article> articles = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                Article article = parseArticle(line);
                if (article != null) {
                    articles.add(article);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return articles;
    }

    /**
     * Parse a CSV line into an Article object.
     */
    private static Article parseArticle(String line) {
        try {
            // Use comma split with limit = 3 to prevent breaking inside concept field
            String[] parts = line.split(",", 3);
            if (parts.length < 3) return null;

            String uri = parts[0].trim();
            String title = parts[1].trim();
            String conceptField = parts[2].trim();

            List<Concept> concepts = parseConcepts(conceptField);
            return new Article(uri, title, concepts);
        } catch (Exception e) {
            System.err.println("Failed to parse line: " + line);
            return null;
        }
    }

    /**
     * Extract Concept objects from a simplified JSON-like string.
     * Example: [{'label': 'AI', 'type': 'tech'}, {'label': 'OpenAI', 'type': 'company'}]
     */
    private static List<Concept> parseConcepts(String conceptField) {
        List<Concept> concepts = new ArrayList<>();

        // Regex to match each concept object
        Pattern pattern = Pattern.compile("\\{\\s*'label'\\s*:\\s*'([^']+)'\\s*,\\s*'type'\\s*:\\s*'([^']+)'\\s*}");
        Matcher matcher = pattern.matcher(conceptField);

        while (matcher.find()) {
            String label = matcher.group(1);
            String type = matcher.group(2);
            concepts.add(new Concept(label.toLowerCase(), type));
        }

        return concepts;
    }
}
