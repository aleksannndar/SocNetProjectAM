import java.util.List;
import java.util.Set;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class BigRandomNetwork {
	
	UndirectedSparseGraph<Integer, Link<String>> graph;
	
	public BigRandomNetwork(boolean clusterable, int numNodes) {
		graph = new UndirectedSparseGraph<Integer, Link<String>>();
		
		for(int i = 1; i<= numNodes; i++) {
			graph.addVertex(i);
		}
		for(int i = 1; i<= numNodes; i++) {
			for(int j = i + 1; j<= numNodes; j++) {
				if(Math.random() * 1000 < 10) {
					if(Math.random() * 1000 < 900) {
						Link<String> l = new Link<String>(false, i +"-"+ j);
						graph.addEdge(l, i, j);
					}else {
						Link<String> l = new Link<String>(true, i +"-"+ j);
						graph.addEdge(l, i, j);
					}
				}
			}
		}
		
		Clusterability c = new Clusterability(graph);
		if(clusterable && !c.clusterable()) {
			List<Link<String>> links = c.linksToRemove();
			for(Link<String> l : links) {
				graph.removeEdge(l);
			}
		}else if(!clusterable && c.clusterable()) {
			Set<UndirectedSparseGraph<Integer, Link<String>>> coalitions = c.getCoalitions();
			boolean negativeAdded = false;
			for(UndirectedSparseGraph<Integer, Link<String>> coalition : coalitions) {
				for(Integer i : coalition.getVertices()) {
					for(Integer j : coalition.getVertices()) {
						if(i != j) {
							Link<String> findLink = coalition.findEdge(i, j);
							if(findLink == null) {
								if(!negativeAdded) {
									Link<String> l = new Link<String>(false,i+"-"+j);
									graph.addEdge(l, i, j);
									negativeAdded = true;
								}else {
									if(Math.random()*1000 < 10) {
										Link<String> l = new Link<String>(false,i+"-"+j);
										graph.addEdge(l, i, j);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public UndirectedSparseGraph<Integer, Link<String>> getGraph() {
		return graph;
	}
	
}
