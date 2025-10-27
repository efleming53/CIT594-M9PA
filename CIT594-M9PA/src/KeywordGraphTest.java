import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KeywordGraphTest {

    private static KeywordGraph graph;

    @BeforeAll
    public static void setup() {
        // uncomment the lines below and provide correct path to your dataset file.
        // List<Article> articles = ArticleLoader.loadFromCSV("m9pa_dataset_simplified.csv");
        // graph = new KeywordGraph();
        // graph.buildGraphFromArticles(articles);
    }

    @Test
    public void testKeywordGraph() {
        // TODO: write at least 3 test cases for each method to test functionality

        // example
        // List<String> top = graph.topKDegree(1);
        // assertFalse(top.isEmpty(), "Graph should contain nodes.");
    }
}
