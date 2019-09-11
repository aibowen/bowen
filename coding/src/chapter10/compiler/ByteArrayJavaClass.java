/**
 * 
 */
package chapter10.compiler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * @author bowen
 *	2018-08-27
 */
public class ByteArrayJavaClass extends SimpleJavaFileObject{
	
	private ByteArrayOutputStream stream;
	
	public ByteArrayJavaClass(String name) {
		super(URI.create("bytes:///"+name),Kind.CLASS);
		stream=new ByteArrayOutputStream();
	}
	
	public OutputStream openOutputStream() throws IOException{
		return stream;
	}
	
	public byte[] getBytes(){
		return stream.toByteArray();
	}

}
