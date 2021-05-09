package cmsc204_Assignment6;

import java.util.ArrayList;
import java.util.List;

/**This class holds name of town and list of its Adjacent towns
 * @author Hasib Rostaiee
 *
 */
public class Town implements Comparable<Town> {

	private String townName;
	private ArrayList<Town> adjTowns;
	
	
	/**Constructor
	 * @param townName name of the town
	 */
	public Town(String townName) {
		this.townName = townName;
	}
	

	/**Copy Constructor
	 * @param templateTown town to be copied
	 */
	public Town(Town templateTown) {
		townName = templateTown.getName();
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Town newTown) {
		return townName.compareTo(newTown.getName());
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Town other = (Town) obj;
		return (getName().equals(other.getName()));
	}
	
	
//Setters and Getters
	public String getName() {
		return townName;
	}

	public void setName(String townName) {
		this.townName = townName;
	}


	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return getName().hashCode();
	}
	
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
