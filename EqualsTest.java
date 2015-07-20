import java.util.*;

public class EqualsTest 
{
    public static void main(String[] args)
    {
    	Employee alice1 = new Employee("Alice Adams", 75000, 1987, 12, 5);
    	Employee alice2 = alice1;
    	Employee alice3 = new Employee("Alice Adams", 75000, 1987, 12, 5);
    	Employee bob = new Employee("Bob Brandson", 50000, 1989, 10, 1);
    	
    	System.out.println("alice1 == alice2: " + (alice1 == alice2) );
    	
    	System.out.println("alice1 == alice3: " + (alice1 == alice3) );
    	
    	System.out.println("alice1.equals(alice3): " + alice1.equals(alice3));
    	
    	System.out.println("alice1.equals(bob): " + alice1.equals(bob));
    	
    	System.out.println("bob.toStirng(): " + bob);
    	
    	Manager car1 = new Manager("Carl Cracker", 80000, 1987, 12, 15);
    	Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
    	//boss.setBonus(5000);
    	System.out.println("boss.toString(): " + boss);
    	System.out.println("carl.equals(boss): " + car1.equals(boss));
    	System.out.println("alice1.hashCode(): " + alice1.hashCode());
    	System.out.println("alice3.hashCode(): " + alice3.hashCode());
    	System.out.println("bob.hashCode(): " + bob.hashCode());
    	System.out.println("car1.hashCode(): " + car1.hashCode());
    }
}

class Employee
{
	public Employee(String n, double s, int year, int month, int day)
	{
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
		hireDay = calendar.getTime();
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public Date getHireDay()
	{
		return hireDay;
	}
	
	public void raiseSalary(double byPercent)
	{
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public boolean equals(Object otherObject)
	{
		//a quick test to see if the objects are identical
		if(this == otherObject)
			return true;
		
		//must return false if the explicit parameter is null
		if(otherObject == null)
			return false;
		
		//if the classes don't match, they can't be equal
		if(getClass() != otherObject.getClass())
			return false;
		
		//now we know otherObject is a non-null Employee
		Employee other = (Employee) otherObject;
		
		//test whether the fields have identical values
		return name.equals(other.name)  && salary == other.salary && hireDay.equals(other.hireDay);
	}
	
	public int hashCode()
	{
		return 7 * name.hashCode() + 11 * new Double(salary).hashCode() + 13 * hireDay.hashCode();
	}
	
	public String toString()
	{
		return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
	}
	
	private String name;
	private double salary;
	private Date hireDay;
}

class Manager extends Employee
{

	public Manager(String n, double s, int year, int month, int day) 
	{
		super(n, s, year, month, day);
		bonus = 0;
		// TODO Auto-generated constructor stub
	}
	
	public double getSalary()
	{
		double baseSalary = super.getSalary();
		return baseSalary + bonus;
	}
	
	public void setBonus(double b)
	{
		bonus = b;
	}
	
	public boolean equals(Object otherObject)
	{
		if (!super.equals(otherObject))
			return false;
		
		Manager other = (Manager) otherObject;
		
		//super.equals checked that this and other belong to the same class
		return bonus == other.bonus;
	}
	
	public int hashCode()
	{
		return super.hashCode() + 17 * new Double(bonus).hashCode();
	}
	
	public String toString()
	{
		return super.toString() + "[bonus=" + bonus + "]" ;
	}
	
	private double bonus;
	
}



































































