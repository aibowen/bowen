/**
 * 
 */
package stream.textFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import stream.objectStream.Employee;
import util.DateUtil;

/**
 * @author bowen
 *	2018-08-29
 */
public class TextFileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		Employee[] staff=new Employee[3];
		staff[0]=new Employee("carl cracker",7500,DateUtil.parseString("1987-12-15"));
		staff[1]=new Employee("harry hacker",5000,DateUtil.parseString("1989-10-01"));
		staff[2]=new Employee("tony tester",4000,DateUtil.parseString("1990-03-15"));
		
		try(PrintWriter out=new PrintWriter("employee.dat","utf-8")){
			writeData(staff,out);
		}
		
		try(Scanner in=new Scanner(new FileInputStream("employee.dat"),"utf-8")){
			Employee[] newStaff=readData(in);
			for (int i = 0; i < newStaff.length; i++) {
				System.out.print(newStaff[i]);
			}
		}
	}
	
	private static void writeData(Employee[] employees,PrintWriter out)throws IOException{
		out.println(employees.length);
		
		for(Employee emp:employees)
			writeEmployee(out,emp);
	}
	
	private static void writeEmployee(PrintWriter out,Employee e){
		GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(e.getHiredate());
		out.println(e.getName()+"|"+e.getSalary()+"|"+
						calendar.get(Calendar.YEAR)+"|"+calendar.get(Calendar.MONTH)+"|"+calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	private static Employee[] readData(Scanner in){
		int n=in.nextInt();
		in.nextLine();
		Employee[] emps=new Employee[n];
		for (int i = 0; i < emps.length; i++) {
			emps[i]=readEmployee(in);
		}
		return emps;
	}
	
	private static Employee readEmployee(Scanner in){
		String line=in.nextLine();
		String[] tokens=line.split("\\|");
		String name=tokens[0];
		double salary=Double.parseDouble(tokens[1]);
		int year=Integer.parseInt(tokens[2]);
		int month=Integer.parseInt(tokens[3]);
		int day=Integer.parseInt(tokens[4]);
		return new Employee(name,salary,DateUtil.parseString(year+"-"+month+"-"+day));
	}

}
