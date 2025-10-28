/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/ >
 * Signed,
 * Author: Eric Fleming
 * Penn email: eflem53@seas.upenn.edu
 * Date: 2025-10-23
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
    	
    	// loop over articles
    	for (Article article : articles) {
    		
    		List<Concept> concepts = article.getConcepts(); // get concepts for current article
    		
    		// skip empty or invalid lists of concepts
    		if (concepts == null || concepts.size() < 2) {
    			continue;
    		}
    		
    	
    		List<String> keywords = new ArrayList<>(); // list to hold keywords for this article
    		// loop over articles concepts
    		for (Concept concept : concepts) {
    			keywords.add(concept.getLabel());
    		}
    		
    		// loop over articles keywords
    		for (String keyword1 : keywords) {
    			adjList.putIfAbsent(keyword1, new TreeSet<>()); // put keyword in graphs adjList if not already present, value is treeset to hold neighbor keywords
    			
    			// nested loop to add edges both ways
    			for (String keyword2 : keywords) {
    				
    				// skip if same word as outer loop
    				if (!keyword2.equals(keyword1)) {
    					adjList.putIfAbsent(keyword2, new TreeSet<>()); // put keyword if not already in adjList
    					adjList.get(keyword1).add(keyword2); // ensures every keyword in this aritcle has every other keyword included in its set of neighbors
    					adjList.get(keyword2).add(keyword1);
    				}
    			}
    		}
    	}
    }


    // perform Breadth-First Search to explore related keywords in lexicographic order.
    public List<String> bfs(String rawKeyword) {
        //TODO: follow instructions to complete this method
    	
    	String word = rawKeyword.toLowerCase().trim(); // process raw keyword
    	
    	// if word not in adjList, return empty list
    	if (!adjList.containsKey(word)) {
    		return new ArrayList<>();
    	}
    	
    	Set<String> visited = new HashSet<>(); // set to hold visited nodes
    	Queue<String> visiting = new LinkedList<>(); // queue to hold nodes in order they will be processed
    	
    	visited.add(word); // add given word to visited set
    	visiting.add(word); // add given word to visiting queue
    	
    	// loop until queue of nodes to visit is empty
    	while (!visiting.isEmpty()) {
    		
    		String currWord = visiting.remove(); // get next node to process from queue
    		
    		// for each neighbor of the word currently being processed
    		for (String neighbor : adjList.get(currWord)) {
    			
    			// if the neighbor is not in visited, add to visited AND visiting
    			if (!visited.contains(neighbor)) {
    				visited.add(neighbor);
    				visiting.add(neighbor);
    			}
    		}
    	}

        return new ArrayList<>(visited); // return visited set as new ArrayList
    }


    // perform Depth-First Search to explore keyword chains in lexicographic order.
    public List<String> dfs(String rawKeyword) {
        //TODO: follow instructions to complete this method
    	
    	String word = rawKeyword.toLowerCase().trim(); // process given word
    	// return empty list if word not found
    	if (!adjList.containsKey(word)) {
    		return new ArrayList<>();
    	}
    	
    	Set<String> visited = new HashSet<>(); // create set to hold visited nodes
    	
    	doDFS(word, visited); // call recursive function to do DFS

        return new ArrayList<>(visited); // return set of visited nodes as new ArrayList
    }
    
    private Set<String> doDFS(String currWord, Set<String> visited) {
    	
    	// base case = currWord is already in visited, return
    	if (visited.contains(currWord)) {
    		return visited;
    	}
    	
    	visited.add(currWord); // add word to visited set
    	
    	// loop over words neighbors, if not in visited, recursively call this function
    	for (String neighbor : adjList.get(currWord)) {
    		if (!visited.contains(neighbor)) {
    			doDFS(neighbor, visited);
    		}
    	}  	
    	return visited; // return visited set
    }


    // returns top-K most connected keywords based on degree.
    public List<String> topKDegree(int k) {
        //TODO: follow instructions to complete this method
    	
    	// return empty list if adjList empty or invalid k
    	if (adjList.isEmpty() || k <= 0) {
    		return new ArrayList<>();
    	}
    	
    	List<String> words = new ArrayList<>(adjList.keySet()); // create list will all words
    	
    	// sort words using Comparator function
    	words.sort((String w1, String w2) -> {
    		
    		// get degrees of words being compared (size of neighbor set)
    		int w1Degree = adjList.get(w1).size();
    		int w2Degree = adjList.get(w2).size();
    		
    		if (w1Degree > w2Degree) {return -1;} // if w1 has higher degree
    		if (w1Degree < w2Degree) {return 1;} // if w2 has higher degree
    		else {return w1.compareTo(w2);} // if words have same degree, make comparison based on lexicographical order
    	});
    	
    	List<String> topKWords = new ArrayList<>();
    	
    	// adjust k if bigger than number of words
    	if (k > words.size()) {
    		k = words.size();
    	}
    	
    	// add all top k words to list to be returned
    	for (int i = 0; i < k; i++) {
    		topKWords.add(words.get(i));
    	}

        return topKWords;
    }
}
