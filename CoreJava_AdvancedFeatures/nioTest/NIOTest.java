package nioTest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;

public class NIOTest {
    public static long checksumInputStream(String filename) throws IOException {
		InputStream in = new FileInputStream(filename);
		CRC32 crc = new CRC32();
		
		int c;
		while( (c = in.read()) != -1 )
		  crc.update(c);
		in.close();
    	   	
    	return crc.getValue();  	
    }
    
    public static long checksumBufferedInputStream(String filename) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(filename));
		CRC32 crc = new CRC32();
		
		int c;
		while( (c = in.read()) != -1 )
		  crc.update(c);
		in.close();
    	   	
    	return crc.getValue();  	
    }
    
    public static long checksumRandomAccessFile(String filename) throws IOException {
    	RandomAccessFile file = new RandomAccessFile(filename, "r");
    	long length = file.length();
		CRC32 crc = new CRC32();
		
		for(long p = 0; p < length; p++) {
			file.seek(p);
			int c = file.readByte();
			crc.update(c);
		}
		
		file.close();
		return crc.getValue();	
    }
    
    public static long checksumMappedFile(String filename) throws IOException {
    	FileInputStream in = new FileInputStream(filename);
    	FileChannel channel = in.getChannel();
    	
    	CRC32 crc = new CRC32();
    	int length = (int) channel.size();
    	MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
    	
    	for(int p = 0; p < length; p++) {
    		int c = buffer.get(p);
    		crc.update(c);
    	}
    	
    	in.close();
    	return crc.getValue();
    }
    // 获取命令行参数
    public static void main(String[] args) throws IOException {
    	
    	System.out.println(args.length);
        for(int i=0;i<args.length;i++)
        {
            System.out.println(args[i]);
        }
    	
    	String filename = "D:\\TEMP\\eclipse\\Advanced\\employee.dat";
		System.out.println("Input Stream:");
		long start = System.currentTimeMillis();
		long crcValue = checksumInputStream(filename);
		long end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + " milliseconds");
		
		System.out.println("Buffered Input Stream:");
		start = System.currentTimeMillis();
		crcValue = checksumBufferedInputStream(filename);
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + " milliseconds");
		
		System.out.println("Random Access File:");
		start = System.currentTimeMillis();
		crcValue = checksumRandomAccessFile(filename);
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + " milliseconds");
		
		System.out.println("Mapped File:");
		start = System.currentTimeMillis();
		crcValue = checksumMappedFile(filename);
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + " milliseconds");
	} 
}
