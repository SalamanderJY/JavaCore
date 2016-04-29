package serialCloneTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class SerialCloneTest {
    public static void main(String[] args) {
    	Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
    	//clone harry
    	Employee harry2 = (Employee) harry.clone();
    	
    	//mutate harry
    	harry.raiseSalary(10);
    	
    	//no harry and the clone are different
        System.out.println(harry);
        System.out.println(harry2);
	}
}

/**
 * A class whose clone method uses serialization
 */
class SerialCloneable implements Cloneable, Serializable {

	/**
	 * generate serialVersionUID
	 */
	private static final long serialVersionUID = -1345549641134543656L;
	
	public Object clone() {
	    try {
	    	//save a object to a byte array.
	    	ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    	ObjectOutputStream out = new ObjectOutputStream(bout);
	    	out.writeObject(this);
	    	out.close();
	    	
	    	//read a clone of the object from the byte array.
	    	ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
	    	ObjectInputStream in = new ObjectInputStream(bin);
	    	Object ret = in.readObject();
	    	in.close();
	    	
	    	return ret;
	    } catch (Exception e) {
	    	return null;
	    }
		
	}
	
}

class Employee extends SerialCloneable {
	/**
	 * generate serialVersionUID
	 */
	private static final long serialVersionUID = -437169406668922624L;
	
	private String name;
	private double salary;
	private Date hireDay;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getHireDay() {
		return hireDay;
	}

	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}

	public Employee() {}
	
	public Employee(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
		hireDay = calendar.getTime();
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[name=" + name + ", salary=" + salary + ", hireDay="
				+ hireDay + "]";
	}	
}
