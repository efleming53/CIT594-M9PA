/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/ >
 * Signed,
 * Author: YOUR NAME HERE
 * Penn email: <YOUR-EMAIL-HERE@seas.upenn.edu>
 * Date: YYYY-MM-DD
 */

import java.util.*;

// starter code for students to complete; see instructions for details
public class KeywordGraph {
    // adj list representation: keyword -> set of connected keywords
    private final Map<String, Set<String>> adjList;

    public KeywordGraph(){
        this.adjList=new HashMap<>();
    }


    // build a graph that models keyword co-occurrence relationships from tech news articles.
    public void buildGraphFromArticles(List<Article> articles) {
        //TODO: follow instructions to complete this method
    }


    // perform Breadth-First Search to explore related keywords in lexicographic order.
    public List<String> bfs(String rawKeyword) {
        //TODO: follow instructions to complete this method

        return null;
    }


    // perform Depth-First Search to explore keyword chains in lexicographic order.
    public List<String> dfs(String rawKeyword) {
        //TODO: follow instructions to complete this method

        return null;
    }


    // returns top-K most connected keywords based on degree.
    public List<String> topKDegree(int k) {
        //TODO: follow instructions to complete this method

        return null;
    }
}
