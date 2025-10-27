import java.util.List;

/**
 * =========================================================
 * WARNING: DO NOT MODIFY THIS CLASS
 *
 * This file is provided as part of the starter code.
 * It is used by the autograder and your implementation
 * will rely on its behavior being consistent.
 * =========================================================
 */
public class Article {
    public String uri;
    public String title;
    public List<Concept> concepts;

    public Article(String uri, String title, List<Concept> concepts) {
        this.uri = uri;
        this.title = title;
        this.concepts = concepts;
    }

    public String getUri() {
        return uri;
    }

    public String getTitle() {
        return title;
    }

    public List<Concept> getConcepts() {
        return concepts;
    }

    @Override
    public String toString() {
        return String.format("Article(uri=%s, title=%s, concepts=%s)", uri, title, concepts);
    }
}