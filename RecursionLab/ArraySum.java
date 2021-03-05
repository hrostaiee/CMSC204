package recursionLab;

/**This class containts a method that sum the values in an array of integers
 * @author Hasib
 *
 */
public class ArraySum {

	
	public int sumOfArray (Integer[] a,int index) {

	        if (index <= 0) 
	            return 0; 
	        return (sumOfArray(a, index- 1) + a[index - 1]); 
		}
}