/**
 * 
 */
package objectAnalyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bowen
 *	2018-09-07
 */
public class ObjectAnalyzer {
	private List<Object> visited=new ArrayList<>();
	
	public String toString(Object obj){
		if(obj==null)
			return "null";
		if(visited.contains(obj))
			return "...";
		visited.add(obj);
		Class cl=obj.getClass();
		if(cl==String.class)
			return (String)obj;
		if(cl.isArray()){
			String r=cl.getComponentType()+"[]{";
			for (int i = 0; i < Array.getLength(obj); i++) {
				if(i>0)
					r+=",";
				Object val=Array.get(obj, i);
				if(cl.getComponentType().isPrimitive())
					r+=val;
				else
					r+=toString(val);
			}
			return r+="}";
		}
		
		String r=cl.getName();
		do{
			r+="]";
			Field[] fields=cl.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
			for (int i = 0; i < fields.length; i++) {
				Field field=fields[i];
				if(!Modifier.isStatic(field.getModifiers())){
					if(!r.endsWith("["))
						r+=",";
					r+=field.getName();
					try {
						Class t=field.getType();
						Object val=field.get(obj);
						if(t.isPrimitive())
							r+=val;
						else
							r+=toString(val);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			r+="]";
			cl=cl.getSuperclass();
		}while(cl!=null);
		return r;
	}
}
