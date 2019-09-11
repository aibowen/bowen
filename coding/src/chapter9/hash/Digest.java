/**
 * 
 */
package chapter9.hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author bowen
 * 2018-08-30
 */
public class Digest {

	/**
	 * @param args	 args[0] is filename,args[1] is optionally algname (SHA-1 or MD5)
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException,IOException {
		String algname=args.length>=2?args[1]:"SHA-1";
		MessageDigest alg=MessageDigest.getInstance(algname);
		byte[] input=Files.readAllBytes(Paths.get(args[0]));
		byte[] hash=alg.digest(input);
		String d="";
		for (int i = 0; i < hash.length; i++) {
			int v=hash[i]&0xFF;
			if(v<16)
				d+="0";
			d+=Integer.toString(v,16).toUpperCase();
			
		}
		System.out.println(d);
	}

}
