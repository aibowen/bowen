/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author bowen
 *
 */
public class Test {
	
	public void sort(List list){
		System.out.println("===排序前===");
		PrintUtil.showList(list);
		Collections.sort(list);
		System.out.println("===排序后===");
		PrintUtil.showList(list);
	}
	
	public void compareAndSort(List list){
		Collections.sort(list, new Comparator<Integer>(){
			@Override
			public int compare(Integer i1,Integer i2){
				return i1-i2;
			}
		});
		System.out.println("===排序后===");
		PrintUtil.showList(list);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		List numsList=Arrays.asList(2,1,3);
//		new Test().compareAndSort(numsList);
		Test test=new Test();
		Emp e1=test.new Emp(2,"guan yunchang");
		Emp e2=test.new Emp(3,"zhang fei");
		Emp e3=test.new Emp(1,"liu bei");
		List<Emp> empList=Arrays.asList(e1,e2,e3);
		Company com=test.new Company();
		com.getEmpList().addAll(empList);
		/*
		 * 使用此方法须实现Comparable接口
		 */
//		Collections.sort(empList);
		
		Collections.sort(com.getEmpList(),new Comparator<Emp>(){
			@Override
			public int compare(Emp e,Emp ee){
				return e.getEmpno()-ee.getEmpno();
			}
		});
		PrintUtil.showListDetail(com.getEmpList());
	}
	
	class Company{
		private int comno;
		private String comname;
		private String address;
		private List<Emp> empList=new ArrayList<Emp>();
		
		public int getComno() {
			return comno;
		}
		public void setComno(int comno) {
			this.comno = comno;
		}
		public String getComname() {
			return comname;
		}
		public void setComname(String comname) {
			this.comname = comname;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public List<Emp> getEmpList() {
			return empList;
		}
		public void setEmpList(List<Emp> empList) {
			this.empList = empList;
		}
		
	}
	
	class Emp implements Comparable<Emp>{
		private int empno;
		private String empname;
		
		public Emp(int empno,String empname){
			this.empno=empno;
			this.empname=empname;
		}
		
		public int getEmpno(){
			return empno;
		}
		
		public void setEmpno(int empno){
			this.empno=empno;
		}
		
		public String getEmpname(){
			return empname;
		}
		
		public void setEmpname(String empname){
			this.empname=empname;
		}
		
		@Override
		public String toString(){
			return "empno: "+empno+"\tempname: "+empname;
		}
		
		@Override
		public int compareTo(Emp emp){
			return emp.getEmpname().compareTo(this.getEmpname());
		}
		
	}

}
