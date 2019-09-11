/**
 * 
 */
package stream.serialClone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author bowen
 *	2018-08-30
 */
public class SerialCloneTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee emp=new Employee("mary",5000,2016,11,11);
		Employee emp1=(Employee) emp.clone();
		emp.raiseSalary(10);
		
		System.out.println(emp);
		System.out.println(emp1);
	}

}

class SerialCloneable implements Cloneable,Serializable{
	public Object clone(){
		try{
			ByteArrayOutputStream bout=new ByteArrayOutputStream();
			ObjectOutputStream out=new ObjectOutputStream(bout);
			out.writeObject(this);
			out.close();
			
			ByteArrayInputStream bin=new ByteArrayInputStream(bout.toByteArray());
			ObjectInputStream in=new ObjectInputStream(bin);
			Object obj=in.readObject();
			in.close();
			return obj;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

class Employee extends SerialCloneable{
	private String name;
	private double salary;
	private Date hiredate;
	
	public Employee(String name,double salary,int year,int month,int day){
		this.name=name;
		this.salary=salary;
		GregorianCalendar calendar=new GregorianCalendar(year,month-1,day);
		hiredate=calendar.getTime();
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public Date getHiredate() {
		return hiredate;
	}
	
	public void raiseSalary(double byPercent){
		double raise=salary*byPercent/100;
		salary=salary+raise;
	}
	
	public String toString(){
		return getClass().getName()
				+"[name="+name
				+",salary="+salary
				+",hiredate="+hiredate+"]";
	}
}
