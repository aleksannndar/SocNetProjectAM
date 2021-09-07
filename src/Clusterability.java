import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.Pair;

public class Clusterability<V, E> {

	private Set<UndirectedSparseGraph<V, Link<E>>> components = new HashSet<UndirectedSparseGraph<V, Link<E>>>();
	private Set<UndirectedSparseGraph<V, Link<E>>> coalitions = new HashSet<UndirectedSparseGraph<V, Link<E>>>();
	private Set<UndirectedSparseGraph<V, Link<E>>> anticoalitions = new HashSet<UndirectedSparseGraph<V, Link<E>>>();

	public UndirectedSparseGraph<V, Link<E>> graph;

	public Clusterability(UndirectedSparseGraph<V, Link<E>> graph) {
		this.graph = graph;
		generateComponentFromSet();
		findAntiCoalitions();
		findCoalitions();
	}

	public boolean clusterable() {
		return anticoalitions.size() == 0;
	}

	public Set<UndirectedSparseGraph<V, Link<E>>> getComponents() {
		return components;
	}

	public Set<UndirectedSparseGraph<V, Link<E>>> getCoalitions() {
		return coalitions;
	}

	public Set<UndirectedSparseGraph<V, Link<E>>> getAnticoalitions() {
		return anticoalitions;
	}

	public List<Link<E>> linksToRemove() {
		List<Link<E>> links = new ArrayList<Link<E>>();
		for (UndirectedSparseGraph<V, Link<E>> ac : anticoalitions) {
			for (Link<E> l : ac.getEdges()) {
				if (!l.isPositive()) {
					links.add(l);
				}
			}
		}
		return links;
	}

	private Set<V> bfsPositiveComponent(V v, Set<V> visited) {
		Queue<V> q = new LinkedList<V>();
		Set<V> compNodes = new HashSet<V>();
		compNodes.add(v);
		q.add(v);
		visited.add(v);
		while (!q.isEmpty()) {
			V current = q.poll();
			for (V n : graph.getNeighbors(current)) {
				if (!visited.contains(n) && graph.findEdge(current, n).isPositive()) {
					compNodes.add(n);
					q.add(n);
					visited.add(n);
				}
			}
		}
		return compNodes;
	}

	private void generateComponentFromSet() {
		Set<V> visited = new HashSet<V>();
		for (V v : graph.getVertices()) {
			if (!visited.contains(v)) {
				Set<V> comp = bfsPositiveComponent(v, visited);
				UndirectedSparseGraph<V, Link<E>> component = new UndirectedSparseGraph<V, Link<E>>();
				for (V n : comp) {
					component.addVertex(n);
				}
				List<Link<E>> links = new ArrayList<Link<E>>(graph.getEdges());
				for (Link<E> l : links) {
					Pair p = graph.getEndpoints(l);
					if (p != null) {
						if (comp.contains(p.getFirst()) && comp.contains(p.getSecond())) {
							component.addEdge(l, (V) p.getFirst(), (V) p.getSecond());
						}
					}
				}
				components.add(component);
			}
		}
	}

	public UndirectedSparseGraph<UndirectedSparseGraph<V, Link<E>>, Link<E>> getWebOfComponents() {
		UndirectedSparseGraph<UndirectedSparseGraph<V, Link<E>>, Link<E>> webOfComponents = new UndirectedSparseGraph<UndirectedSparseGraph<V, Link<E>>, Link<E>>();
		for (UndirectedSparseGraph<V, Link<E>> component : components) {
			webOfComponents.addVertex(component);
		}

		for (Link<E> l : graph.getEdges()) {
			Pair<V> p = graph.getEndpoints(l);
			UndirectedSparseGraph<V, Link<E>> component1 = componentWithNode(p.getFirst());
			UndirectedSparseGraph<V, Link<E>> component2 = componentWithNode(p.getSecond());
			if (!component1.equals(component2)) {
				webOfComponents.addEdge(l, component1, component2);
			}
		}
		return webOfComponents;
	}

	private UndirectedSparseGraph<V, Link<E>> componentWithNode(V n) {
		for (UndirectedSparseGraph<V, Link<E>> component : components) {
			if (component.getVertices().contains(n)) {
				return component;
			}
		}
		return null;
	}

	private void findCoalitions() {
		for (UndirectedSparseGraph<V, Link<E>> component : components) {
			boolean coalition = true;
			for (Link<E> l : component.getEdges()) {
				if (!l.isPositive()) {
					coalition = false;
					break;
				}
			}
			if (coalition) {
				coalitions.add(component);
			}
		}
	}

	private void findAntiCoalitions() {
		for (UndirectedSparseGraph<V, Link<E>> component : components) {
			for (Link<E> l : component.getEdges()) {
				if (!l.isPositive()) {
					anticoalitions.add(component);
					break;
				}
			}
		}
	}

	public double averageDegree(Collection<UndirectedSparseGraph<V, Link<E>>> components) {
		double sum = 0.0;
		for (UndirectedSparseGraph<V, Link<E>> component : components) {
			double localAverage = averageDegree(component);
			sum += localAverage;
		}
		return sum / components.size();
	}

	public double averageDegree(UndirectedSparseGraph<V, Link<E>> component) {
		double sum = 0.0;
		for (V n : component.getVertices()) {
			double dVal = component.degree(n);
			if(!Double.isNaN(dVal)) {
				sum += dVal;
			}
		}
		if(component.getVertexCount() != 0) {
			return sum / component.getVertexCount();
		}
		return 0;
	}
	
	public double averageDensity(Collection<UndirectedSparseGraph<V, Link<E>>> components) {
		double sum = 0.0;
		for(UndirectedSparseGraph<V, Link<E>> component : components) {
			sum += density(component);
		}
		return sum/components.size();
	}
	
	public double density(UndirectedSparseGraph<V, Link<E>> component) {
		double numNodes = component.getVertexCount();
		double maxLinks = numNodes * (numNodes - 1)/2.0;
		if(Double.isNaN(maxLinks) || maxLinks == 0.0) {
			return 0;
		}
		return component.getEdgeCount() / maxLinks;
	}
	
	public double averageDiameter(Collection<UndirectedSparseGraph<V, Link<E>>> components) {
		double sum = 0.0;
		for(UndirectedSparseGraph<V, Link<E>> component : components) {
			sum+= DistanceStatistics.diameter(component); 
		}
		return sum/components.size();
	}
	
	public double averageDiameter(UndirectedSparseGraph<V, Link<E>> component) {
		return DistanceStatistics.diameter(component);
	}
	
	public double averageDistance(Collection<UndirectedSparseGraph<V, Link<E>>> components) {
		double sum = 0.0;
		for(UndirectedSparseGraph<V, Link<E>> component : components) {
			double localAverage = averageDistance(component);
			sum += localAverage;
		}
		
		return sum/components.size();
	}
	
	public double averageDistance(UndirectedSparseGraph<V, Link<E>> component) {
		Transformer<V, Double> t = DistanceStatistics.averageDistances(component);
		double localSum = 0.0;
		for(V n : component.getVertices()) {
			double dist = t.transform(n);
			if(!Double.isNaN(dist)) {
				localSum += dist;
			}
		}
		return localSum/component.getVertexCount();
	}
}
