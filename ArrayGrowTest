import java.lang.reflect.*;

public class ArrayGrowTest 
{
    public static void main(String[] args)
    {
    	int[] a = { 1, 2, 3 };
    	a = (int[]) goodArrayGrow(a);
    	arrayPrint(a);
    	
    	String[] b = { "Tom", "Dick", "Harry" };
    	b = (String[]) goodArrayGrow(b);
    	arrayPrint(b);
    	
    	System.out.println("The following call will generate an exception.");
    	//b = (String[]) badArrayGrow(b);
    }
    
    //This method attempts to grow an array by allocating a new array and copying all elements.
    static Object[] badArrayGrow(Object[] a)//not useful
    {
    	int newLength = a.length * 11 / 10 + 10;
    	Object[] newArray = new Object[newLength];
    	System.arraycopy(a, 0, newArray, 0, a.length);
    	return newArray;
    }
    
    //This method grows an array by allocating a new array of the same type and copying all elements.
    static Object goodArrayGrow(Object a)
    {
    	Class<?> c1 = a.getClass();
    	if (!c1.isArray())
    		return null;
    	Class<?> componentType = c1.getComponentType();
    	int length = Array.getLength(a);
    	int newLength = length * 11 / 10 + 10;
    	
    	//The Array class provides static methods to dynamically create and access Java arrays. 
    	Object newArray = Array.newInstance(componentType, newLength);
    	System.arraycopy(a, 0, newArray, 0, length);
    	return newArray;   	
    }
    
    //A convenience method to print all elements in an array.
    static void arrayPrint(Object a)
    {
    	Class<?> c1 = a.getClass();
    	if(!c1.isArray())
    		return ;
    	Class<?> componentType = c1.getComponentType();
    	int length = Array.getLength(a);
    	System.out.print(componentType.getName() + "[" + length + "] = { ");
    	for(int i = 0; i < Array.getLength(a); i++)
    	//The Array class provides static methods to dynamically create and access Java arrays.
    		System.out.print(Array.get(a, i));
    	System.out.println("}");
    }    
}
