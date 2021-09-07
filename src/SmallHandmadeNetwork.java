import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class SmallHandmadeNetwork {

	UndirectedSparseGraph<Integer, Link<String>> graph;

	public SmallHandmadeNetwork(boolean clusterable) {
		graph = new UndirectedSparseGraph<Integer, Link<String>>();

		for (int i = 1; i <= 15; i++) {
			graph.addVertex(i);
		}
		graph.addEdge(new Link<String>(true, 1 + "-" + 2), 1, 2);
		graph.addEdge(new Link<String>(true, 1 + "-" + 3), 1, 3);
		graph.addEdge(new Link<String>(true, 2 + "-" + 3), 2, 3);
		graph.addEdge(new Link<String>(true, 2 + "-" + 5), 2, 5);
		graph.addEdge(new Link<String>(false, 2 + "-" + 4), 2, 4);
		graph.addEdge(new Link<String>(false, 4 + "-" + 9), 4, 9);
		graph.addEdge(new Link<String>(false, 4 + "-" + 7), 4, 7);
		graph.addEdge(new Link<String>(true, 9 + "-" + 12), 9, 12);
		graph.addEdge(new Link<String>(true, 7 + "-" + 12), 7, 12);
		graph.addEdge(new Link<String>(true, 10 + "-" + 12), 10, 12);
		graph.addEdge(new Link<String>(true, 12 + "-" + 13), 12, 13);
		graph.addEdge(new Link<String>(false, 3 + "-" + 6), 3, 6);
		graph.addEdge(new Link<String>(false, 5 + "-" + 6), 5, 6);
		graph.addEdge(new Link<String>(true, 6 + "-" + 8), 6, 8);
		graph.addEdge(new Link<String>(false, 8 + "-" + 11), 8, 11);
		graph.addEdge(new Link<String>(false, 6 + "-" + 11), 6, 11);
		graph.addEdge(new Link<String>(false, 10 + "-" + 11), 10, 11);
		graph.addEdge(new Link<String>(false, 11 + "-" + 13), 11, 13);
		graph.addEdge(new Link<String>(false, 13 + "-" + 15), 13, 15);
		graph.addEdge(new Link<String>(false, 11 + "-" + 14), 11, 14);
		graph.addEdge(new Link<String>(false, 14 + "-" + 15), 14, 15);
		
		if(!clusterable) {
			graph.addEdge(new Link<String>(false, 7 + "-" + 10), 7, 10);
		}
	}

	public UndirectedSparseGraph<Integer, Link<String>> getGraph() {
		return graph;
	}
	
}
