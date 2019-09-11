/**
 * 
 */
package chapter9.rsa;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;

import chapter9.aes.Util;

/**
 * 生成秘钥：java chapter9.rsa.RSATest -genkey public.key private.key
 * 加密：java chapter9.rsa.RSATest -encrypt plaintextFile encryptedFile public.key
 * 解密：java chapter9.rsa.RSATest -decrypt encryptedFile decryptedFile private.key
 * @author bowen
 *	2018-08-30
 */
public class RSATest {
	private static final int KEYSIZE=512;
	
	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws IllegalBlockSizeException 
	 * @throws BadPaddingException 
	 * @throws ShortBufferException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException, ClassNotFoundException, 
	NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, ShortBufferException, BadPaddingException {
		if(args[0].equals("-genkey")){
			KeyPairGenerator pairgen=KeyPairGenerator.getInstance("RSA");
			SecureRandom random=new SecureRandom();
			pairgen.initialize(KEYSIZE, random);
			KeyPair keyPair=pairgen.generateKeyPair();
			try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(args[1]))){
				out.writeObject(keyPair.getPublic());
			}
			try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(args[2]))){
				out.writeObject(keyPair.getPrivate());
			}
		}else if(args[0].equals("-encrypt")){
			KeyGenerator keygen=KeyGenerator.getInstance("AES");
			SecureRandom random=new SecureRandom();
			keygen.init(random);
			SecretKey key=keygen.generateKey();
			try(ObjectInputStream keyIn=new ObjectInputStream(new FileInputStream(args[3]));
				  DataOutputStream out=new DataOutputStream(new FileOutputStream(args[2]));
				  InputStream in=new FileInputStream(args[1])){
				Key publicKey=(Key)keyIn.readObject();
				Cipher cipher=Cipher.getInstance("RSA");
				cipher.init(Cipher.WRAP_MODE,publicKey);
				byte[] wrappedKey=cipher.wrap(key);
				out.writeInt(wrappedKey.length);
				out.write(wrappedKey);
				
				cipher=Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, key);
				Util.crypt(in,out,cipher);
			}
		}else{
			try(DataInputStream in=new DataInputStream(new FileInputStream(args[1]));
					ObjectInputStream keyIn=new ObjectInputStream(new FileInputStream(args[3]));
					OutputStream out=new FileOutputStream(args[2])){
				int length=in.readInt();
				byte[] wrappedKey=new byte[length];
				in.read(wrappedKey,0,length);
				
				Key privateKey=(Key)keyIn.readObject();
				
				Cipher cipher=Cipher.getInstance("RSA");
				cipher.init(Cipher.UNWRAP_MODE, privateKey);
				Key key=cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);
				
				cipher=Cipher.getInstance("AES");
				cipher.init(Cipher.DECRYPT_MODE, key);
				Util.crypt(in, out, cipher);
			}
		}
	}

}
