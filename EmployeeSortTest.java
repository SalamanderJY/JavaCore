import java.util.*;
public class EmployeeSortTest 
{
    public static void main(String[] args)
    {
    	Employee[] staff = new Employee[3];
    	
    	staff[0] = new Employee("Harry Hacker", 35000);
    	staff[1] = new Employee("Carl Cracker", 75000);
    	staff[2] = new Employee("Tony Tester", 38000);
    	
    	Arrays.sort(staff);//MergeSort,要求数组中的元素必须属于实现了Comparable接口的类，并且元素之间是可比较的
    	
    	//print out information about all Employee objects.
    	for(Employee e: staff)
    	{
    		System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());
    	}
    }
}

class Employee implements Comparable<Employee>
{
    public Employee(String n, double s)
    {
    	name = n;
    	salary = s;
    }
    
    public String getName()
    {
    	return name;
    }
    
    public double getSalary()
    {
    	return salary;
    }
    
    public void raiseSalary(double byPercent)
    {
    	double raise = salary * byPercent/100;
    	salary += raise;
    }
	
	@Override
	public int compareTo(Employee other) 
	{
		// TODO Auto-generated method stub
		if(salary < other.salary) return -1;
		if(salary > other.salary) return 1;
		return 0;
	}
	
	private String name;
	private double salary;
}
