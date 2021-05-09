package cmsc204_Assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**This class implements Graph Interface which is a generic class and we use them for <Town, Road>. 
 * Using Sets to store towns and roads and find shortest path from a town every other town using Dijkstra’s Shortest Path algorithm
 * @author Hasib Rostaiee
 *
 */
public class Graph implements GraphInterface<Town, Road> {

	private Set<Town> towns = new HashSet<>();
	private Set<Road> roads = new HashSet<>();
	Map<Town, Town> townMap = new HashMap<>();
	Map<Town, Integer> weightMap = new HashMap<>();

	/* (non-Javadoc)
	 * @see cmsc204_Assignment6.GraphInterface#getEdge(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		for (Road edge : roads) {
			if (edge.contains(sourceVertex) && edge.contains(destinationVertex))
				return edge;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see cmsc204_Assignment6.GraphInterface#addEdge(java.lang.Object, java.lang.Object, int, java.lang.String)
	 */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (sourceVertex == null || destinationVertex == null)
			throw new NullPointerException();
		Road edge = new Road(sourceVertex, destinationVertex, weight, description);
		roads.add(edge);
		return edge;
	}

	/* (non-Javadoc)
	 * @see cmsc204_Assignment6.GraphInterface#addVertex(java.lang.Object)
	 */
	@Override
	public boolean addVertex(Town vertex) {
		if (vertex == null)
			throw new NullPointerException();
		if (containsVertex(vertex))
			return false;
		return towns.add(vertex);
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for (Road road : roads) {
			if (road.contains(sourceVertex) && road.contains(destinationVertex))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsVertex(Town t) {
		for (Town town : towns) {
			if (town.equals(t))
				return true;
		}
		return false;
	}

	@Override
	public Set<Road> edgeSet() {
		return roads;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		if (vertex == null) {
			throw new NullPointerException();
		}
		Set<Road> edgesOfTown = new HashSet<>();
		for (Road road : roads) {
			if (road.contains(vertex))
				edgesOfTown.add(road);
		}
		return edgesOfTown;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (sourceVertex == null || destinationVertex == null)
			return null;
		for (Road road : roads) {
			if (road.contains(sourceVertex) && road.contains(destinationVertex) && road.getWeight() == weight
					&& road.getName().equals(description)) {
				roads.remove(road);
				return road;
			}
		}
		return null;
	}

	@Override
	public boolean removeVertex(Town t) {
		if (t == null)
			return false;
		return towns.remove(t);
	}

	@Override
	public Set<Town> vertexSet() {
		return towns;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		ArrayList<String> shortestPath = new ArrayList<>();

		boolean destExist = false;
		for (Road road : roads) {
			if (road.contains(destinationVertex))
				destExist = true;
		}

		// boolean soureExist = false;
//			for (Road road: roads) {
//				if(road.contains(sourceVertex))
//					soureExist = true;
//			}
		if (!destExist) {
			return shortestPath;
		}

		dijkstraShortestPath(sourceVertex);
		Town destinationTown = destinationVertex;
		
		while (!destinationTown.equals(sourceVertex)) {
			for (Road road : roads) {
				if (road.contains(destinationTown) && road.contains(townMap.get(destinationTown))) {
					shortestPath.add(0, townMap.get(destinationTown).getName() + " via " + road.getName() + " " + " to "
							+ destinationTown.getName() + " " + road.getWeight() + " mi");
				}
				destinationTown = townMap.get(destinationTown);
			}
		}
		return shortestPath;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		HashSet<Town> temp = new HashSet<>();
		for (Town town : towns) {
			temp.add(town);
		}
		for (Town town : towns) {
			weightMap.put(town, Integer.MAX_VALUE);
		}
		weightMap.put(sourceVertex, 0);
		
		while (!temp.isEmpty()) {
			for (Road road : roads) {
				if (road.contains(sourceVertex)) {
					if (!road.getDestination().equals(sourceVertex) && temp.contains(road.getDestination())) {
						if (weightMap.get(sourceVertex) + road.getWeight() < weightMap.get(road.getDestination())) {
							townMap.put(road.getDestination(), sourceVertex);
							weightMap.put(road.getDestination(), road.getWeight() + weightMap.get(sourceVertex));
						}
					} else if (!road.getSource().equals(sourceVertex) && temp.contains(road.getSource()))
						if (weightMap.get(sourceVertex) + road.getWeight() < weightMap.get(road.getSource())) {
							townMap.put(road.getSource(), sourceVertex);
							weightMap.put(road.getSource(), road.getWeight() + weightMap.get(sourceVertex));
						}
				}
			}
			temp.remove(sourceVertex);

			int min = 100;
			for (Town town : weightMap.keySet()) {
				if (min > weightMap.get(town) && temp.contains(town)) {
					min = weightMap.get(town);
					sourceVertex = town;
				}
			}
		}

	}

}
