import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class LoadNetwork {

	public UndirectedSparseGraph<Integer, Link<String>> loadNetworks(String src) {
		UndirectedSparseGraph<Integer, Link<String>> bitCoin = new UndirectedSparseGraph<Integer, Link<String>>();
		try {
			Scanner sc = new Scanner(new FileReader(src));
			int count = 0;
			for(int i = 0; i < 4; i++) {
				sc.nextLine();
			}
			while (sc.hasNext()) {
				count++;
				String[] lines = sc.nextLine().trim().split("\\s+");
				int v1 = Integer.parseInt(lines[0].trim());
				int v2 = Integer.parseInt(lines[1].trim());
				int sign = Integer.parseInt(lines[2].trim());
				boolean signB;
				if (sign >= 0) {
					signB = true;
				} else {
					signB = false;
				}
				Link<String> l = new Link(signB, v1 + "-" + v2);
				Set<Integer> n = null;
				if(bitCoin.getVertices().contains(n)) {
					n = new HashSet<Integer>(bitCoin.getNeighbors(v1));
					if(n.contains(v2)) {
						if(!signB) {
							bitCoin.addEdge(l, v1, v2);
						}
					}
				}else {
					bitCoin.addEdge(l, v1, v2);
				}
			}
			System.out.println("Counter: " + count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return bitCoin;
	}
	
	//links with 1 node are discarded
	public UndirectedSparseGraph<String, Link<String>> loadWiki(){
		UndirectedSparseGraph<String, Link<String>> wiki = new UndirectedSparseGraph<String, Link<String>>();
		int count = 0;
		try {
			Scanner sc = new Scanner(new FileReader("wiki-RfA.txt"));
			while(sc.hasNext()) {
				count++;
				String[] vline1 = sc.nextLine().split(":");
				String[] vline2 = sc.nextLine().split(":");
				if(vline1.length < 2 || vline2.length < 2) {
					for(int i = 0; i<6; i++) {
						sc.nextLine();
					}	
					continue;
				}
				String v1 = vline1[1].trim();
				String v2 = vline2[1].trim();
				int sign = Integer.parseInt(sc.nextLine().split(":")[1].trim());
				for(int i = 0; i<5; i++) {
					sc.nextLine();
				}
				boolean signB;
				if (sign >= 0) {
					signB = true;
				} else {
					signB = false;
				}
				Link<String> l = new Link(signB, v1 + "-" + v2);
				Set<String> n = null;
				if(wiki.getVertices().contains(n)) {
					n = new HashSet<String>(wiki.getNeighbors(v1));
					if(n.contains(v2)) {
						if(!signB) {
							wiki.addEdge(l, v1, v2);
						}
					}
				}else {
					wiki.addEdge(l, v1, v2);
				}
			}
			System.out.println("Counter: " + count);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return wiki;
	}
}
