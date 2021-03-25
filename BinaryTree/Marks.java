package Project2_CS260_DevanshAgrawal;

import java.io.Serializable;

/**
 * @author Devansh Agrawal
 *
 */

public class Marks implements Comparable<Marks>, Serializable {

	private int serialNum;
	private String empName = " ";
	

	/**
	 * Constructor to initialize the data
	 * 
	 * @param emplname 
	 * @param sn
	 */
	public Marks(String emplname, int sn) {
		this.serialNum = sn;
		this.setEmpName(emplname);
	}

	/**
	 * Use to compare values of two marks
	 * 
	 * @param marks
	 * @return -1 if key is less, 1 if key is more and 0 as backup  
	 */
	public int compareTo(Marks marks) {
		if (serialNum < marks.serialNum) {
			return -1;
		}
		if (serialNum > marks.serialNum) {
			return 1;
		}
		return 0;

	}

	/**
	 * Returns the name of the employee
	 * 
	 * @return the name of the employee
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * Sets the Employee's name 
	 * 
	 * @param empName
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/* 
	 * Used to Print the data of the Node
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "SerialNumber: " + serialNum + "\n Employee Name" + empName + "\n";
	}
}
