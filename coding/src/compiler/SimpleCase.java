/**
 * 
 */
package compiler;

import java.io.InputStream;
import java.io.OutputStream;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * @author bowen
 *
 */
public class SimpleCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
		OutputStream out=null;
		OutputStream err=null;
		int result=compiler.run(null, out, err, "-sourcepath","src/compiler","Main.java");
		if(result==0){
			System.out.println("compile success.");
		}else{
			System.out.println("compile failure.");
		}
		
		
	}

}
