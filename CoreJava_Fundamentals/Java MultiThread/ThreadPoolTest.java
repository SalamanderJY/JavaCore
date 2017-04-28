package ThreadPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest {

	/**
	 * Enter base directory : D:/Java
       Enter keyword : Test
       46 matching files. 
       largest pool size = 42
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory : ");
		String directory = in.nextLine();
		System.out.print("Enter keyword : ");
		String keyword = in.nextLine();
		in.close();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
		Future<Integer> result = pool.submit(counter);
		
		try
		{
			System.out.println(result.get() + " matching files. ");
		}
		catch(ExecutionException e)
		{
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			
		}
		pool.shutdown();
		
		int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
		System.out.println("largest pool size = " + largestPoolSize);
	}
}

class MatchCounter implements Callable<Integer>
{

	public MatchCounter(File directory, String keyword, ExecutorService pool)
	{
		this.directory = directory;
		this.keyword = keyword;
		this.pool = pool;
	}
	
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		count = 0;
		try
		{
			File[] files = directory.listFiles();
			ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
			
			for(File file : files)
			{
				if(file.isDirectory())
				{
					MatchCounter counter = new MatchCounter(file, keyword, pool);
					Future<Integer> result = pool.submit(counter);
					results.add(result);
				}
				else
				{
					if(search(file)) count++;
				}
			}
			
			for(Future<Integer> result : results)
			{
				try
				{
					count += result.get();
				}
				catch(ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(InterruptedException e)
		{
			
		}
		return count;
	}
	
	public boolean search(File file)
	{
		try
		{
			Scanner in = new Scanner(new FileInputStream(file));
			boolean found = false;
			while(!found && in.hasNextLine())
			{
				String line = in.nextLine();
				if(line.contains(keyword)) found = true;
			}
			in.close();
			return found;
		}
		catch(IOException e)
		{
			return false;
		}
	}
	
	private File directory;
	private String keyword;
	private ExecutorService pool;
	private int count;
}
