package cmsc204_Assignment6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**This class hold an Object of the Grpah and pefrom operation such as adding, checking, retriving and removing town and road into and from the graph.
 * It also represents all towns and all roads in array represntation
 * @author Hasib Rostaiee
 *
 */
public class TownGraphManager implements TownGraphManagerInterface {

	Graph graph;

	/**Constructor which creates a new graph
	 * 
	 */
	public TownGraphManager() {
		graph = new Graph();
	}

	/**Populates a graph using provided file
	 * @param file file to read data from 
	 * @throws FileNotFoundException
	 */
	public void populateTownGraph(File file) throws FileNotFoundException {
		String[] data;
		String line;
		Scanner reader = new Scanner(file);

		while (reader.hasNextLine()) {
			line = reader.nextLine();
			data = line.split(",|;");
			graph.addVertex(new Town(data[2]));
			graph.addVertex(new Town(data[3]));
			graph.addEdge(new Town(data[2]), new Town(data[3]), Integer.parseInt(data[1]), data[0]);

		}
		reader.close();
	}

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {

		try {
			graph.addEdge(new Town(town1), new Town(town2), weight, roadName);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public String getRoad(String town1, String town2) {
		String road = graph.getEdge(new Town(town1), new Town(town2)).getName();
		return road;
	}

	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));

	}

	@Override
	public Town getTown(String name) {

		for (Town town : graph.vertexSet()) {
			if (town.equals(new Town(name)))
				return town;
		}

		return null;
	}

	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));

	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roads = new ArrayList<>();

		for (Road road : graph.edgeSet()) {
			roads.add(road.getName());
		}
		Collections.sort(roads);
		return roads;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String roadName) {
		int distance = 0;

		for (Road road : graph.edgeSet()) {
			if (road.getName().equals(getRoad(town1, town2)))
				distance = road.getWeight();
		}
		return graph.removeEdge(new Town(town1), new Town(town2), distance, roadName) != null;
	}

	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> towns = new ArrayList<>();

		for (Town town : graph.vertexSet()) {
			towns.add(town.getName());
		}
		Collections.sort(towns);
		return towns;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {

		return graph.shortestPath(new Town(town1), new Town(town2));
	}

}
