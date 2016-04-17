package objectStreamTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class ObjectStreamTest {
    public static void main(String[] args) throws ClassNotFoundException {
		Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		Manager carl = new Manager("Carl Cracker", 75000, 1987, 12, 15);
		carl.setSecretary(harry);
		Manager tony = new Manager("Tony Tester", 40000, 1990, 3, 15);
		tony.setSecretary(harry);
		
		Employee[] staff = new Employee[3];
		staff[0] = carl;
		staff[1] = harry;
		staff[2] = tony;
		
		try {
			// save all records into a new array.
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("employee.dat"));
			out.writeObject(staff);
			out.close();
			
			// retrieve all records into a new array.
			ObjectInputStream in = new ObjectInputStream
					(new FileInputStream("employee.dat"));
			Employee[] newStaff = (Employee[]) in.readObject();
			in.close();
			
			// raise secretary's salary
			newStaff[1].raiseSalary(10);
			
			// print the newly read employee records
			for (Employee employee : newStaff) {
				System.out.println(employee);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
 	}
}

class Employee implements Serializable {

	/**
	 * Employee serializable
	 */
	private static final long serialVersionUID = 4108736674576421543L;
	
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

class Manager extends Employee {

	/**
	 * Manager serializable
	 */
	private static final long serialVersionUID = 6383248006137012466L;
	
	private Employee secretary;
	
	public Manager(String n, double s, int year, int month, int day) {
		super(n, s, year, month, day);
		secretary = null;
	}
	
	public void setSecretary(Employee e) {
		secretary = e;
	}

	@Override
	public String toString() {
		return super.toString() + "[secretary=" + secretary + "]";
	}	
}