package cmsc_204_assignment4;

/**This Class implements Comparable and the object of the class consist of five attributes which are:
 * Course ID, CRN, Number of Credits, Room Number and Instructor name
 * @author Hasib Rostaiee
 *
 */
public class CourseDBElement implements Comparable{

	//Fields
	private String courseID;
	private int CRN;
	private int numOfCredit;
	private String roomNumber;
	private String instructorName;
	
	//Constructors 
	public CourseDBElement (String courseID, int crn, int numOfCredits, String roomNum, String instructorName ) {
		this.courseID = courseID;
		this.CRN = crn;
		this.numOfCredit = numOfCredits;
		this.roomNumber =roomNum;
		this.instructorName = instructorName;
	}
	
	public CourseDBElement() {
		courseID = "";
		CRN = 0;
		numOfCredit = 0;
		roomNumber = "";
		instructorName = "";
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		String crnText = Integer.toString(CRN);
		int result = crnText.hashCode();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		CourseDBElement newCourse = (CourseDBElement) obj;
		return (this.getCRN() == newCourse.getCRN());
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object obj) {
		if (obj ==null)
			return 0;
		if(this.getClass() != obj.getClass())
			return 0;
		CourseDBElement newCourse = (CourseDBElement) obj;
		if(this.getCRN() == newCourse.getCRN())
			return 1;
		return 0;
	}
	
	//Getters and Setters
	
	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public int getCRN() {
		return CRN;
	}

	public void setCRN(int crn) {
		this.CRN = crn;
	}

	public int getNumOfCredit() {
		return numOfCredit;
	}

	public void setNumOfCredit(int numOfCredit) {
		this.numOfCredit = numOfCredit;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nCourse:" + courseID + " CRN:" + CRN + " Credits:" + numOfCredit
				+ " Instructor:" + instructorName +" Room:" + roomNumber;
	}






}
