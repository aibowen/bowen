/**
 * 
 */
package chapter10.compiler;

import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * @author bowen
 *	2018-08-27
 */
public class StringBuilderJavaSource extends SimpleJavaFileObject{
	
	private StringBuilder code;

	public StringBuilderJavaSource(String name) {
		super(URI.create("string:///"+name.replace(".", "/")+Kind.SOURCE.extension),Kind.SOURCE);
		code=new StringBuilder();
	}
	
	public CharSequence getContent(boolean ignoreEncodingErrors){
		return code;
	}
	
	public void append(String str){
		code.append(str);
		code.append("\n");
	}
	
	public static void main(String[] args) {
		String name="chapter10.compiler.ButtonFrame";
		URI uri=URI.create("string:///"+name.replace(".", "/")+Kind.SOURCE.extension);
		System.out.println(uri);
	}
}
