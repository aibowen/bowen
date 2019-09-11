/**
 * 
 */
package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Random;
import java.util.Scanner;

/**
 * @author bowen
 *	2018-09-06
 */
public class ReflectionTest {
	
	public static void testClass(){
		Random random=new Random();
		System.out.println(random.getClass().getName());
		
		String className="java.util.Random";
		Class ran = null;
		try {
			ran=Class.forName(className);
			System.out.println(ran.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(Random.class==random.getClass());
		try {
			System.out.println(random==ran.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name;
		if(args.length>0)
			name=args[0];
		else{
			Scanner in=new Scanner(System.in);
			System.out.println("enter class name (e.g. java.util.Date): ");
			name=in.nextLine();
		}
		
		try {
			Class cl=Class.forName(name);
			Class supercl=cl.getSuperclass();
			String modifiers=Modifier.toString(cl.getModifiers());
			if(modifiers.length()>0)
				System.out.print(modifiers+" ");
			System.out.println("class "+name);
			if(supercl!=null&&supercl!=Object.class)
				System.out.print(" extends "+supercl.getName());
			System.out.print("\n{\n");
			printConstructors(cl);
			System.out.println();
			printMethods(cl);
			System.out.println();
			printFields(cl);
			System.out.println("}");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public static void printConstructors(Class cl){
		Constructor[] constructors=cl.getDeclaredConstructors();
		for(Constructor c:constructors){
			String name=c.getName();
			System.out.println("  ");
			String modifiers=Modifier.toString(c.getModifiers());
			if(modifiers.length()>0)
				System.out.print(modifiers+" ");
			System.out.print(name+"(");
			
			Class[] paramTypes=c.getParameterTypes();
			for (int i = 0; i < paramTypes.length; i++) {
				if(i>0)
					System.out.print(", ");
				System.out.print(paramTypes[i].getName());
			}
			System.out.println(");");
		}
	}
	
	public static void printMethods(Class cl){
		Method[] methods=cl.getDeclaredMethods();
		for(Method m:methods){
			Class retType=m.getReturnType();
			String name=m.getName();
			
			System.out.println("  ");
			String modifiers=Modifier.toString(m.getModifiers());
			if(modifiers.length()>0)
				System.out.print(modifiers+" ");
			System.out.print(retType.getName()+" "+name+"(");
			Class[] paramTypes=m.getParameterTypes();
			for (int i = 0; i < paramTypes.length; i++) {
				if(i>0)
					System.out.print(", ");
				System.out.print(paramTypes[i].getName());
			}
			System.out.println(")");
		}
	}
	
	public static void printFields(Class cl){
		Field[] fields=cl.getDeclaredFields();
		
		for(Field f:fields){
			Class type=f.getType();
			String name=f.getName();
			System.out.print("  ");
			String modifiers=Modifier.toString(f.getModifiers());
			if(modifiers.length()>0)
				System.out.print(modifiers+" ");
			System.out.println(type.getName()+"  "+name+";");
		}
	}
}
