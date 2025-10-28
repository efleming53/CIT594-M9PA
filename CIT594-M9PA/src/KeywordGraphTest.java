import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class KeywordGraphTest {

    private static KeywordGraph graph;

    @BeforeAll
    public static void setup() {
    }

    @Test
    public void testKeywordGraph() {
    	
    	// load articles
        List<Article> articles = ArticleLoader.loadFromCSV("m9pa_dataset_simplified.csv");
        graph = new KeywordGraph();
        graph.buildGraphFromArticles(articles);
        System.out.println("Articles loaded: " + articles.size());

        // test graph not empty
        List<String> top1 = graph.topKDegree(1);
        assertFalse(top1.isEmpty(), "Graph should contain at least one node.");

        // test contains first keyword from first article
        List<String> allNodes = graph.topKDegree(100); // get many nodes
        String firstArticleKeyword = articles.get(0).getConcepts().get(0).getLabel();
        assertTrue(allNodes.contains(firstArticleKeyword), "Graph should contain first keyword");
        
        // test contains first keyword from thirtieth article
        String thirtiethArticleKeyword = articles.get(29).getConcepts().get(0).getLabel();  
        assertTrue(allNodes.contains(thirtiethArticleKeyword), "Graph should contain fiftieth keyword");
    }
    
    @Test
    public void testBFS() {
        List<Article> articles = ArticleLoader.loadFromCSV("src/m9pa_dataset_simplified.csv");
        graph = new KeywordGraph();
        graph.buildGraphFromArticles(articles);

        // test BFS return contains first node
        String firstNode = articles.get(0).getConcepts().get(0).getLabel();
        List<String> bfsResult = graph.bfs(firstNode);
        assertTrue(bfsResult.contains(firstNode), "BFS result should include first node.");

        // test BFS returns labels for all concepts in same article
        List<Concept> firstArticleConcepts = articles.get(0).getConcepts();
        for (Concept concept : firstArticleConcepts) {
                assertTrue(bfsResult.contains(concept.getLabel()), "BFS should reach all concepts in the same article.");
        }

        List<String> bfsNonExistent = graph.bfs("doesnt_exist");
        assertTrue(bfsNonExistent.isEmpty(), "BFS on non-existent word should return an empty list.");
    }
    
    @Test
    public void testDFS() {
        List<Article> articles = ArticleLoader.loadFromCSV("src/m9pa_dataset_simplified.csv");
        graph = new KeywordGraph();
        graph.buildGraphFromArticles(articles);

        // test DFS return contains first node
        String firstNode = articles.get(0).getConcepts().get(0).getLabel();
        List<String> dfsResult = graph.dfs(firstNode);
        assertTrue(dfsResult.contains(firstNode), "DFS result should include first node.");

        // test DFS returns labels for all concepts in same article
        List<Concept> firstArticleConcepts = articles.get(0).getConcepts();
        for (Concept concept : firstArticleConcepts) {
                assertTrue(dfsResult.contains(concept.getLabel()), "DFS should reach all concepts in the same article.");
        }

        // test non-existent word in dfs
        List<String> dfsNonExistent = graph.dfs("doesnt_exist");
        assertTrue(dfsNonExistent.isEmpty(), "dFS on non-existent word should return an empty list.");
    }
    
    @Test
    public void testTopKDegree() {
    	 List<Article> articles = ArticleLoader.loadFromCSV("src/m9pa_dataset_simplified.csv");
    	 graph = new KeywordGraph();
    	 graph.buildGraphFromArticles(articles);

    	    // test top 1 node is returned
    	    List<String> top1 = graph.topKDegree(1);
    	    assertFalse(top1.isEmpty(), "topKDegree(1) should return at least one node.");

    	    // test top 5 nodes are returned
    	    List<String> top5 = graph.topKDegree(5);
    	    assertEquals(5, top5.size(), "topKDegree(5) should return 5 nodes.");

    	    // test all nodes returned when k > number of nodes
    	    List<String> allNodes = graph.topKDegree(1000);
    	    List<String> graphNodes = graph.topKDegree(graph.topKDegree(1000).size());
    	    assertEquals(allNodes.size(), graphNodes.size(), "Requesting more nodes than exist should return all nodes.");

    }
}
