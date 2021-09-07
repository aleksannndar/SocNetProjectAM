import java.awt.Dimension;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class Main {

	public static void main(String[] args) {

		int option;
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println(
					"Unesit broj pored opcije koju zelite da odaberete: \n 1. Mala klasterabilna mreza \n 2. Mala neklasterabilna mreza \n 3. Velika klasterabilna random generisana mreza \n "
					+ "4. Velika neklasterabilna random generisana mreza \n 5. Epinions mreza \n 6. Slashdot mreza \n 7. Wikipedia mreza \n 8. Zatvoriti program ");
			option = sc.nextInt();
			
			if (option == 1) {
				SmallHandmadeNetwork sn = new SmallHandmadeNetwork(true);
				UndirectedSparseGraph<Integer, Link<String>> g = sn.getGraph();
				System.out.println("Broj cvorova u grafu: " + g.getVertexCount());
				System.out.println("Broj grana u grafu: " + g.getEdgeCount());
				Clusterability c = new Clusterability(g);
				if(c.clusterable()) {
					System.out.println("Mreza je klasterabilna");
				}else {
					System.out.println("Mreza nije klasterabilna");
					List<String> linksToRemove = c.linksToRemove();
					System.out.println("Treba izbaciti " + linksToRemove.size() + " grana");
				}
				System.out.println("Broj komponenti: " + c.getComponents().size());
				System.out.println("Broj koalicija: " + c.getCoalitions().size());
				System.out.println("Broj antikoalicija: " + c.getAnticoalitions().size());
				UndirectedSparseGraph<UndirectedSparseGraph<Integer, Link<String>>, Link<String>> web = c.getWebOfComponents();
				System.out.println("Broj grana u mrezi komponenti: " + web.getEdgeCount());
				System.out.println("Prosecan stepen cvorova grafa: " + (double) Math.round(c.averageDegree(g) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u koalicijama: " + (double) Math.round(c.averageDegree(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u antikoalicijama: " + (double) Math.round(c.averageDegree(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina grafa: " + (double) Math.round(c.density(g) * 100) / 100);
				System.out.println("Prosecna gustina koalicija: " + (double) Math.round(c.averageDensity(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina antikoalicija: " + (double) Math.round(c.averageDensity(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Prosecan dijametar u grafu: " + (double) Math.round(c.averageDiameter(g) * 100) / 100);
				System.out.println("Prosecan dijametar u koalicijama: " + (double) Math.round(c.averageDiameter(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecan dijametar u antikoalicijama: " + (double) Math.round(c.averageDiameter(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Prosecna distanca u grafu: " + (double) Math.round(c.averageDistance(g) * 100) / 100);
				System.out.println("Prosecna distanca u koalicijama: " + (double) Math.round(c.averageDistance(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecna distanca u antikoalicijama: " + (double) Math.round(c.averageDistance(c.getAnticoalitions()) * 100) / 100);
			}else if (option == 2) {
				SmallHandmadeNetwork sn = new SmallHandmadeNetwork(false);
				UndirectedSparseGraph<Integer, Link<String>> g = sn.getGraph();
				System.out.println("Broj cvorova u grafu: " + g.getVertexCount());
				System.out.println("Broj grana u grafu: " + g.getEdgeCount());
				Clusterability c = new Clusterability(g);
				if(c.clusterable()) {
					System.out.println("Mreza je klasterabilna, zbog toga nije moguce izracunati metrike za antikoalicije, jer ne postoje");
				}else {
					System.out.println("Mreza nije klasterabilna");
					List<String> linksToRemove = c.linksToRemove();
					System.out.println("Treba izbaciti " + linksToRemove.size() + " grana");
				}
				System.out.println("Broj komponenti: " + c.getComponents().size());
				System.out.println("Broj koalicija: " + c.getCoalitions().size());
				System.out.println("Broj antikoalicija: " + c.getAnticoalitions().size());
				UndirectedSparseGraph<UndirectedSparseGraph<Integer, Link<String>>, Link<String>> web = c.getWebOfComponents();
				System.out.println("Broj grana u mrezi komponenti: " + web.getEdgeCount());
				System.out.println("Prosecan stepen cvorova grafa: " + (double) Math.round(c.averageDegree(g) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u koalicijama: " + (double) Math.round(c.averageDegree(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u antikoalicijama: " + (double) Math.round(c.averageDegree(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina grafa: " + (double) Math.round(c.density(g) * 100) / 100);
				System.out.println("Prosecna gustina koalicija: " + (double) Math.round(c.averageDensity(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina antikoalicija: " + (double) Math.round(c.averageDensity(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Prosecan dijametar u grafu: " + (double) Math.round(c.averageDiameter(g) * 100) / 100);
				System.out.println("Prosecan dijametar u koalicijama: " + (double) Math.round(c.averageDiameter(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecan dijametar u antikoalicijama: " + (double) Math.round(c.averageDiameter(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Prosecna distanca u grafu: " + (double) Math.round(c.averageDistance(g) * 100) / 100);
				System.out.println("Prosecna distanca u koalicijama: " + (double) Math.round(c.averageDistance(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecna distanca u antikoalicijama: " + (double) Math.round(c.averageDistance(c.getAnticoalitions()) * 100) / 100);
			}else if (option == 3) {
				BigRandomNetwork bn = new BigRandomNetwork(true,5000);
				UndirectedSparseGraph<Integer, Link<String>> g = bn.getGraph();
				System.out.println("Broj cvorova u grafu: " + g.getVertexCount());
				System.out.println("Broj grana u grafu: " + g.getEdgeCount());
				Clusterability c = new Clusterability(g);
				if(c.clusterable()) {
					System.out.println("Mreza je klasterabilna, zbog toga nije moguce izracunati metrike za antikoalicije, jer ne postoje");
				}else {
					System.out.println("Mreza nije klasterabilna");
					List<String> linksToRemove = c.linksToRemove();
					System.out.println("Treba izbaciti " + linksToRemove.size() + " grana");
				}
				System.out.println("Broj komponenti: " + c.getComponents().size());
				System.out.println("Broj koalicija: " + c.getCoalitions().size());
				System.out.println("Broj antikoalicija: " + c.getAnticoalitions().size());
				UndirectedSparseGraph<UndirectedSparseGraph<Integer, Link<String>>, Link<String>> web = c.getWebOfComponents();
				System.out.println("Prosecan stepen cvorova grafa: " + (double) Math.round(c.averageDegree(g) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u koalicijama: " + (double) Math.round(c.averageDegree(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u antikoalicijama: " + (double) Math.round(c.averageDegree(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina grafa: " + (double) Math.round(c.density(g) * 100) / 100);
				System.out.println("Prosecna gustina koalicija: " + (double) Math.round(c.averageDensity(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina antikoalicija: " + (double) Math.round(c.averageDensity(c.getAnticoalitions()) * 100) / 100);
//				System.out.println("Prosean dijametar u grafu: " + (double) Math.round(c.averageDiameter(g) * 100) / 100);
//				System.out.println("Prosean dijametar u koalicijama: " + (double) Math.round(c.averageDiameter(c.getCoalitions()) * 100) / 100);
//				System.out.println("Prosean dijametar u antikoalicijama: " + (double) Math.round(c.averageDiameter(c.getAnticoalitions()) * 100) / 100);
//				System.out.println("Prosecna distanca u grafu: " + (double) Math.round(c.averageDistance(g) * 100) / 100);
//				System.out.println("Prosecna distanca u koalicijama: " + (double) Math.round(c.averageDistance(c.getCoalitions()) * 100) / 100);
//				System.out.println("Prosecna distanca u antikoalicijama: " + (double) Math.round(c.averageDistance(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Ova mreza je previse zahtevna za racunanje distance i dijametra");
			}else if (option == 4) {
				BigRandomNetwork bn = new BigRandomNetwork(false,5000);
				UndirectedSparseGraph<Integer, Link<String>> g = bn.getGraph();
				System.out.println("Broj cvorova u grafu: " + g.getVertexCount());
				System.out.println("Broj grana u grafu: " + g.getEdgeCount());
				Clusterability c = new Clusterability(g);
				if(c.clusterable()) {
					System.out.println("Mreza je klasterabilna, zbog toga nije moguce izracunati metrike za antikoalicije, jer ne postoje");
				}else {
					System.out.println("Mreza nije klasterabilna");
					List<String> linksToRemove = c.linksToRemove();
					System.out.println("Treba izbaciti " + linksToRemove.size() + " grana");
				}
				System.out.println("Broj komponenti: " + c.getComponents().size());
				System.out.println("Broj koalicija: " + c.getCoalitions().size());
				System.out.println("Broj antikoalicija: " + c.getAnticoalitions().size());
				UndirectedSparseGraph<UndirectedSparseGraph<Integer, Link<String>>, Link<String>> web = c.getWebOfComponents();
				System.out.println("Prosecan stepen cvorova grafa: " + (double) Math.round(c.averageDegree(g) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u koalicijama: " + (double) Math.round(c.averageDegree(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u antikoalicijama: " + (double) Math.round(c.averageDegree(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina grafa: " + (double) Math.round(c.density(g) * 100) / 100);
				System.out.println("Prosecna gustina koalicija: " + (double) Math.round(c.averageDensity(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina antikoalicija: " + (double) Math.round(c.averageDensity(c.getAnticoalitions()) * 100) / 100);
//				System.out.println("Prosean dijametar u grafu: " + (double) Math.round(c.averageDiameter(g) * 100) / 100);
//				System.out.println("Prosean dijametar u koalicijama: " + (double) Math.round(c.averageDiameter(c.getCoalitions()) * 100) / 100);
//				System.out.println("Prosean dijametar u antikoalicijama: " + (double) Math.round(c.averageDiameter(c.getAnticoalitions()) * 100) / 100);
//				System.out.println("Prosecna distanca u grafu: " + (double) Math.round(c.averageDistance(g) * 100) / 100);
//				System.out.println("Prosecna distanca u koalicijama: " + (double) Math.round(c.averageDistance(c.getCoalitions()) * 100) / 100);
//				System.out.println("Prosecna distanca u antikoalicijama: " + (double) Math.round(c.averageDistance(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Ova mreza je previse zahtevna za racunanje distance i dijametra");
			}else if (option == 5) {
				LoadNetwork ln = new LoadNetwork();
				UndirectedSparseGraph<Integer, Link<String>> g = ln.loadNetworks("soc-sign-epinions.txt");
				System.out.println("Broj cvorova u grafu: " + g.getVertexCount());
				System.out.println("Broj grana u grafu: " + g.getEdgeCount());
				Clusterability c = new Clusterability(g);
				if(c.clusterable()) {
					System.out.println("Mreza je klasterabilna, zbog toga nije moguce izracunati metrike za antikoalicije, jer ne postoje");
				}else {
					System.out.println("Mreza nije klasterabilna");
					List<String> linksToRemove = c.linksToRemove();
					System.out.println("Treba izbaciti " + linksToRemove.size() + " grana");
				}
				System.out.println("Broj komponenti: " + c.getComponents().size());
				System.out.println("Broj koalicija: " + c.getCoalitions().size());
				System.out.println("Broj antikoalicija: " + c.getAnticoalitions().size());
				UndirectedSparseGraph<UndirectedSparseGraph<Integer, Link<String>>, Link<String>> web = c.getWebOfComponents();
				System.out.println("Broj grana u mrezi komponenti: " + web.getEdgeCount());
				System.out.println("Prosecan stepen cvorova grafa: " + (double) Math.round(c.averageDegree(g) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u koalicijama: " + (double) Math.round(c.averageDegree(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u antikoalicijama: " + (double) Math.round(c.averageDegree(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina grafa: " + (double) Math.round(c.density(g) * 100) / 100);
				System.out.println("Prosecna gustina koalicija: " + (double) Math.round(c.averageDensity(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina antikoalicija: " + (double) Math.round(c.averageDensity(c.getAnticoalitions()) * 100) / 100);
//				System.out.println("Prosean dijametar u grafu: " + (double) Math.round(c.averageDiameter(g) * 100) / 100);
//				System.out.println("Prosean dijametar u koalicijama: " + (double) Math.round(c.averageDiameter(c.getCoalitions()) * 100) / 100);
//				System.out.println("Prosean dijametar u antikoalicijama: " + (double) Math.round(c.averageDiameter(c.getAnticoalitions()) * 100) / 100);
//				System.out.println("Prosecna distanca u grafu: " + (double) Math.round(c.averageDistance(g) * 100) / 100);
//				System.out.println("Prosecna distanca u koalicijama: " + (double) Math.round(c.averageDistance(c.getCoalitions()) * 100) / 100);
//				System.out.println("Prosecna distanca u antikoalicijama: " + (double) Math.round(c.averageDistance(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Ova mreza je previse zahtevna za racunanje distance i dijametra");
			}else if (option == 6) {
				LoadNetwork ln = new LoadNetwork();
				UndirectedSparseGraph<Integer, Link<String>> g = ln.loadNetworks("soc-sign-Slashdot090221.txt");
				System.out.println("Broj cvorova u grafu: " + g.getVertexCount());
				System.out.println("Broj grana u grafu: " + g.getEdgeCount());
				Clusterability c = new Clusterability(g);
				if(c.clusterable()) {
					System.out.println("Mreza je klasterabilna, zbog toga nije moguce izracunati metrike za antikoalicije, jer ne postoje");
				}else {
					System.out.println("Mreza nije klasterabilna");
					List<String> linksToRemove = c.linksToRemove();
					System.out.println("Treba izbaciti " + linksToRemove.size() + " grana");
				}
				System.out.println("Broj komponenti: " + c.getComponents().size());
				System.out.println("Broj koalicija: " + c.getCoalitions().size());
				System.out.println("Broj antikoalicija: " + c.getAnticoalitions().size());
				UndirectedSparseGraph<UndirectedSparseGraph<Integer, Link<String>>, Link<String>> web = c.getWebOfComponents();
				System.out.println("Prosecan stepen cvorova grafa: " + (double) Math.round(c.averageDegree(g) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u koalicijama: " + (double) Math.round(c.averageDegree(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u antikoalicijama: " + (double) Math.round(c.averageDegree(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina grafa: " + (double) Math.round(c.density(g) * 100) / 100);
				System.out.println("Prosecna gustina koalicija: " + (double) Math.round(c.averageDensity(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina antikoalicija: " + (double) Math.round(c.averageDensity(c.getAnticoalitions()) * 100) / 100);
//				System.out.println("Prosean dijametar u grafu: " + (double) Math.round(c.averageDiameter(g) * 100) / 100);
//				System.out.println("Prosean dijametar u koalicijama: " + (double) Math.round(c.averageDiameter(c.getCoalitions()) * 100) / 100);
//				System.out.println("Prosean dijametar u antikoalicijama: " + (double) Math.round(c.averageDiameter(c.getAnticoalitions()) * 100) / 100);
//				System.out.println("Prosecna distanca u grafu: " + (double) Math.round(c.averageDistance(g) * 100) / 100);
//				System.out.println("Prosecna distanca u koalicijama: " + (double) Math.round(c.averageDistance(c.getCoalitions()) * 100) / 100);
//				System.out.println("Prosecna distanca u antikoalicijama: " + (double) Math.round(c.averageDistance(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Ova mreza je previse zahtevna za racunanje distance i dijametra");
			}else if (option == 7) {
				LoadNetwork ln = new LoadNetwork();
				UndirectedSparseGraph<String, Link<String>> g = ln.loadWiki();
				System.out.println("Broj cvorova u grafu: " + g.getVertexCount());
				System.out.println("Broj grana u grafu: " + g.getEdgeCount());
				Clusterability c = new Clusterability(g);
				if(c.clusterable()) {
					System.out.println("Mreza je klasterabilna, zbog toga nije moguce izracunati metrike za antikoalicije, jer ne postoje");
				}else {
					System.out.println("Mreza nije klasterabilna");
					List<String> linksToRemove = c.linksToRemove();
					System.out.println("Treba izbaciti " + linksToRemove.size() + " grana");
				}
				System.out.println("Broj komponenti: " + c.getComponents().size());
				System.out.println("Broj koalicija: " + c.getCoalitions().size());
				System.out.println("Broj antikoalicija: " + c.getAnticoalitions().size());
				UndirectedSparseGraph<UndirectedSparseGraph<String, Link<String>>, Link<String>> web = c.getWebOfComponents();
				System.out.println("Prosecan stepen cvorova grafa: " + (double) Math.round(c.averageDegree(g) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u koalicijama: " + (double) Math.round(c.averageDegree(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecan stepen cvorova u antikoalicijama: " + (double) Math.round(c.averageDegree(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina grafa: " + (double) Math.round(c.density(g) * 100) / 100);
				System.out.println("Prosecna gustina koalicija: " + (double) Math.round(c.averageDensity(c.getCoalitions()) * 100) / 100);
				System.out.println("Prosecna gustina antikoalicija: " + (double) Math.round(c.averageDensity(c.getAnticoalitions()) * 100) / 100);
//				System.out.println("Prosean dijametar u grafu: " + (double) Math.round(c.averageDiameter(g) * 100) / 100);
//				System.out.println("Prosean dijametar u koalicijama: " + (double) Math.round(c.averageDiameter(c.getCoalitions()) * 100) / 100);
//				System.out.println("Prosean dijametar u antikoalicijama: " + (double) Math.round(c.averageDiameter(c.getAnticoalitions()) * 100) / 100);
//				System.out.println("Prosecna distanca u grafu: " + (double) Math.round(c.averageDistance(g) * 100) / 100);
//				System.out.println("Prosecna distanca u koalicijama: " + (double) Math.round(c.averageDistance(c.getCoalitions()) * 100) / 100);
//				System.out.println("Prosecna distanca u antikoalicijama: " + (double) Math.round(c.averageDistance(c.getAnticoalitions()) * 100) / 100);
				System.out.println("Ova mreza je previse zahtevna za racunanje distance i dijametra");
			}
		} while (option != 8);
	}

}
