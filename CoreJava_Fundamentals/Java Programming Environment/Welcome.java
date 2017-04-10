/**
 *This program displays a greeting from the authors.
 *@version 1.20 2004-02-28
 *@author Wang
 */
 public class Welcome
 {
     public static void main(String[] args)
	{
		 String[] greeting = new String[3];
         greeting[0] = "Welcome to Core Java";
		 greeting[1] = "by Wang";
		 greeting[2] = "and Salamander";
		 
		 for (String g : greeting)
		    System.out.println(g);
			
	}
}
	