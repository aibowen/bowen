/**
 * 
 */
package chapter10.compiler;

import java.util.Map;

/**
 * @author bowen
 *	2018-08-27
 */
public class MapClassLoader extends ClassLoader{
	private Map<String,byte[]> classes;
	
	public MapClassLoader(Map<String,byte[]> classes){
		this.classes=classes;
	}
	
	protected Class<?> findClass(String name)throws ClassNotFoundException{
		byte[] classBytes=classes.get(name);
		if(classBytes==null){
			throw new ClassNotFoundException(name);
		}
		Class<?> cl=defineClass(name,classBytes,0,classBytes.length);
		if(cl==null)
			throw new ClassNotFoundException(name);
		return cl;
	}
}
