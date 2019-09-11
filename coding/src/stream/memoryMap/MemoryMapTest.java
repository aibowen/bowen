/**
 * 
 */
package stream.memoryMap;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**
 * @author bowen
 *	2018-08-29
 */
public class MemoryMapTest{
	public static void main(String[] args) throws IOException{
		String filepath="D:/Java/jdk1.7.0_75/jre/lib/rt.jar";
		Path filename=Paths.get(filepath);
		
		System.out.println("input stream: ");
		long start=System.currentTimeMillis();
		long crcValue=checksumInputStream(filename);
		long end=System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end-start)+" milliseconds");
		
		
		System.out.println("buffered input stream: ");
		long start1=System.currentTimeMillis();
		long crvValue1=checksumBufferedInputStream(filename);
		long end1=System.currentTimeMillis();
		System.out.println(Long.toHexString(crvValue1));
		System.out.println((end1-start1)+" milliseconds");
		
		System.out.println("random access file: ");
		long start2=System.currentTimeMillis();
		long crvValue2=checksumBufferedInputStream(filename);
		long end2=System.currentTimeMillis();
		System.out.println(Long.toHexString(crvValue2));
		System.out.println((end2-start2)+" milliseconds");
		
		System.out.println("map file: ");
		long start3=System.currentTimeMillis();
		long crvValue3=checksumBufferedInputStream(filename);
		long end3=System.currentTimeMillis();
		System.out.println(Long.toHexString(crvValue3));
		System.out.println((end3-start3)+" milliseconds");
	}
	
	public static long checksumInputStream(Path filename)throws IOException{
		try(InputStream in=Files.newInputStream(filename)){
			CRC32 crc=new CRC32();
			int c;
			while((c=in.read())!=-1)
				crc.update(c);
			return crc.getValue();
		}
	}
	
	public static long checksumBufferedInputStream(Path filename)throws IOException{
		try(InputStream in=new BufferedInputStream(Files.newInputStream(filename))){
			CRC32 crc=new CRC32();
			int c;
			while((c=in.read())!=-1)
				crc.update(c);
			return crc.getValue();
		}
	}
	
	public static long checksumRandomAccessFile(Path filename)throws IOException{
		try(RandomAccessFile file=new RandomAccessFile(filename.toFile(),"r")){
			long length=file.length();
			CRC32 crc=new CRC32();
			for (long i = 0; i < length; i++) {
				file.seek(i);
				int c=file.readByte();
				crc.update(c);
			}
			return crc.getValue();
		}
	}
	
	public static long checksumMappedFile(Path filename)throws IOException{
		try(FileChannel channel=FileChannel.open(filename)){
			CRC32 crc=new CRC32();
			int length=(int)channel.size();
			MappedByteBuffer buffer=channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
			
			for (int i = 0; i < length; i++) {
				int c=buffer.getInt(i);
				crc.update(c);
			}
			return crc.getValue();
		}
	}
	
}
