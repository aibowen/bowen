/**
 * 
 */
package stream.objectStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.DateUtil;

/**
 * @author bowen
 *
 */
public class ObjectStreamTest {
	public static void main(String[] args) throws IOException,ClassNotFoundException{
		Employee harry=new Employee("harry hacker",5000,DateUtil.parseString("1989-10-01"));
		Manager carl=new Manager("carl cracker",8000,DateUtil.parseString("1987-12-15"));
		carl.setSecretary(harry);
		Manager tony=new Manager("tony tester",4000,DateUtil.parseString("1990-03-15"));
		tony.setSecretary(harry);
		
		Employee[] staff=new Employee[3];
		staff[0]=harry;
		staff[1]=carl;
		staff[2]=tony;
		
		try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("employee.dat"))){
			out.writeObject(staff);
		}
		
		try(ObjectInputStream in=new ObjectInputStream(new FileInputStream("employee.dat"))){
			Employee[] newStaff=(Employee[])in.readObject();
			newStaff[1].raiseSalary(10);
			
			for (Employee employee : newStaff) {
				System.out.println(employee.getSalary());
			}
		}
	}
}
