import java.util.*;

public class TreeSetTest 
{
    public static void main(String[] args)
    {
    	SortedSet<Item> parts = new TreeSet<Item>();
    	parts.add(new Item("Toaster", 1234));
    	parts.add(new Item("Widget", 4562));
    	parts.add(new Item("Modem", 9912));
    	System.out.println(parts);
    	
    	SortedSet<Item> sortByDescription = new TreeSet<Item>(new 
    			Comparator<Item>()
    			{

					@Override
					public int compare(Item a, Item b) {
						// TODO Auto-generated method stub
						String descrA = a.getDescription();
						String descrB = b.getDescription();
						return descrA.compareTo(descrB);
					}
    		
    			});
    	sortByDescription.addAll(parts);
    	System.out.println(sortByDescription);
    }
}

/**
 * An item with a description and a part number.
 */
class Item implements Comparable<Item>
{

	public Item(String aDescription, int aPartNumber)
	{
		description = aDescription;
		partNumber = aPartNumber;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String toString()
	{
		return "[description=" + description + ", partNumber=" + partNumber + "]";
	}
	
	public boolean equals(Object otherObject)
	{
		if(this == otherObject) return true;
		if(otherObject == null) return false;
		if(getClass() != otherObject.getClass()) return false;
		Item other = (Item) otherObject;
		return description.equals(other.description)
				&& partNumber == other.partNumber;
	}
	
	public int hashCode()
	{
		return 13 * description.hashCode() + 17 * partNumber;
	}
	
	@Override
	public int compareTo(Item other) 
	{
		// TODO Auto-generated method stub
		return partNumber - other.partNumber;
	}
	
	private String description;
	private int partNumber;
	
}