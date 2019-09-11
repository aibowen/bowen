/**
 * 
 */
package stream.objectStream;

import java.util.Date;

/**
 * @author bowen
 *
 */
public class Manager extends Employee {
	private Employee secretary;
	public Manager(String name, double salary, Date hiredate) {
		super(name, salary, hiredate);
	}
	
	public void setSecretary(Employee secretary){
		this.secretary=secretary;
	}
	
}
