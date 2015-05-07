import java.lang.reflect.*;

public class MethodPointerTest 
{
    public static void main(String[] args) throws Exception
    {
    	//get method pointers to the square and square root methods.
    	Method square = MethodPointerTest.class.getMethod("square", double.class);
    	Method sqrt = Math.class.getMethod("sqrt", double.class);
    	
    	//print tables of x- and y-values.
    	printTable(1,10,10,square);
    	printTable(1,10,10,sqrt);
    }
    
    
    //Return the square of a number.
    public static double square(double x)
    {
    	return x * x;
    }
    
    //Print a table with x- and y-values for a method
    public static void printTable(double from, double to, int n, Method f)
    {
    	//print out the method as table header
    	System.out.println(f);
    	
    	double dx = (to - from) / (n-1);
    	
    	for(double x = from; x <= to; x += dx)
    	{
    		try
    		{
    			double y = (Double) f.invoke(null, x);
    			System.out.printf("%10.4f | %10.4f%n", x, y);
    		}
    		catch (Exception e)/*The class Exception and its subclasses are a form of Throwable that indicates conditions 
    		                     that a reasonable application might want to catch. */
    		{
    			e.printStackTrace();
    		}
    	}
    }    
}
