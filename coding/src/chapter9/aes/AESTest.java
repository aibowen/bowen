/**
 * 
 */
package chapter9.aes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;

/**
 * 生成秘钥：java aes.AESTest -genkey secret.key
 * 加密：java chapter9.aes.AESTest -encrypt plaintextFile encryptedFile secret.key
 * 解密：java chapter9.aes.AESTest -decrypt encryptedFile decryptedFile secret.key
 * @author bowen
 *	2018-08-30
 */
public class AESTest {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws ShortBufferException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ClassNotFoundException,
	NoSuchPaddingException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException{
		if(args[0].equals("-genkey")){
			KeyGenerator keygen= KeyGenerator.getInstance("AES");
			SecureRandom random=new SecureRandom();
			keygen.init(random);
			SecretKey key=keygen.generateKey();
			try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(args[1]))){
				out.writeObject(key);
			}
		}else{
			int mode;
			if(args[0].equals("-encrypt"))
				mode=Cipher.ENCRYPT_MODE;
			else
				mode=Cipher.DECRYPT_MODE;
			
			try(ObjectInputStream keyIn=new ObjectInputStream(new FileInputStream(args[3]));
				  InputStream in=new FileInputStream(args[1]);
				  OutputStream out=new FileOutputStream(args[2])){
				Key key=(Key)keyIn.readObject();
				Cipher cipher=Cipher.getInstance("AES");
				cipher.init(mode, key);
				Util.crypt(in, out, cipher);
			}
		}
	}

}
