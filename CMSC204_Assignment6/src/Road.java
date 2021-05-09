package cmsc204_Assignment6;

/**
 * This class represent the edges of a Graph of Towns. It stores references to
 * two Town endpoints (Soure and Destination), Distance between them and the
 * reoad name.
 * 
 * @author Hasib Rostaiee
 *
 */
public class Road implements Comparable<Road> {

	private String roadName;
	private Town source;
	private Town destination;
	int distance;

	/**
	 * Constructor
	 * 
	 * @param source      Source Town
	 * @param destination Destination Town
	 * @param distance    the distance btween two Towns
	 * @param roadName    name of the road
	 */
	public Road(Town source, Town destination, int distance, String roadName) {
		this.roadName = roadName;
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}

	/**
	 * Constructor, default distance is 1 mile
	 * 
	 * @param source      name of source town
	 * @param destination name of destination town
	 * @param roadName    name of the road
	 */
	public Road(Town source, Town destination, String roadName) {
		this.roadName = roadName;
		this.source = source;
		this.destination = destination;
		distance = 1;
	}

	/**
	 * This methods checks if a Road contain a town either on its source or
	 * destination points
	 * 
	 * @param town name of the given town
	 * @return true if the given town is part of the Road, false otherwise.
	 */
	public boolean contains(Town town) {

		if (getSource().getName().equals(town.getName()))
			return true;
		if (getDestination().getName().equals(town.getName()))
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Road otherRoad) {
		return (this.getName().compareTo(otherRoad.getName()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Road)) {
			return false;
		}
		Road other = (Road) obj;
		if (other.getSource().equals(getSource()) && other.getDestination().equals(getDestination()))
			return true;
		if (other.getDestination().equals(getSource()) && other.getSource().equals(getDestination()))
			return true;

		return false;
	}

	/**
	 * Get name of the road
	 * 
	 * @return roadName name of the road
	 */
	public String getName() {
		return roadName;
	}

	/**
	 * Get Source town
	 * 
	 * @return source which is source town
	 */
	public Town getSource() {
		return source;
	}

	/**
	 * Get Destination Town
	 * 
	 * @return destination
	 */
	public Town getDestination() {
		return destination;
	}

	/**
	 * Get distance between to vertices of the road
	 * 
	 * @return distance
	 */
	public int getWeight() {
		return distance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return source.getName() + " via " + roadName + " to " + destination.getName() + " " + distance + " mi";
	}

}
