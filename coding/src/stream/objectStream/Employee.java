/**
 * 
 */
package stream.objectStream;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bowen
 * 2018-08-29
 */
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double salary;
	private Date hiredate;
	
	public Employee(String name,double salary,Date hiredate){
		this.name=name;
		this.salary=salary;
		this.hiredate=hiredate;
	}
	
	public void setSalary(double salary){
		this.salary=salary;
	}
	
	public double getSalary(){
		return salary;
	}

	public void raiseSalary(int i) {
		this.salary=this.getSalary()+i;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
}
