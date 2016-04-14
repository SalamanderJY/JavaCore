package randomFileTest;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RandomFileTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[3];
		
		staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
		staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);
		
		try {
			// save all employee records to the file employee.dat
			DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat"));
			for (Employee employee : staff) {
				employee.writeData(out);
			}
			out.close();
			
			//retrieve all records into a new array
			RandomAccessFile in = new RandomAccessFile("employee.dat", "r");
			//compute the array size
			int n = (int)(in.length() / Employee.RECORD_SIZE);
			Employee[] newStaff = new Employee[n];
			
			//read employees in reverse order
			for(int i = n - 1; i >= 0; i--) {
				newStaff[i] = new Employee();
				in.seek(i * Employee.RECORD_SIZE);
				newStaff[i].readData(in);
			}
			in.close();
			
			//print the newly read employee records
			for(Employee e : newStaff)
				System.out.println(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Employee {
	
	private String name;
	private double salary;
	private Date hireDay;
	
	public static final int NAME_SIZE = 40;
	public static final int RECORD_SIZE = 2 * NAME_SIZE + 8 + 4 + 4 + 4;
	
	public Employee() {}
	
	public Employee(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
		hireDay = calendar.getTime();
	}
	
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
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", hireDay="
				+ hireDay + "]";
	}
	
	public void writeData(DataOutput out) throws IOException{
		DataIO.writeFixedString(name, NAME_SIZE, out);
		out.writeDouble(salary);
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(hireDay);
		
		out.writeInt(calendar.get(Calendar.YEAR));
		out.writeInt(calendar.get(Calendar.MONTH) + 1);
		out.writeInt(calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	public void readData(DataInput in) throws IOException {
		name = DataIO.readFixedString(NAME_SIZE, in);
		salary = in.readDouble();
		int y = in.readInt();
		int m = in.readInt();
		int d = in.readInt();
		GregorianCalendar calendar = new GregorianCalendar(y, m-1, d);
		hireDay = calendar.getTime();
	}
}

class DataIO {
	
	public static String readFixedString(int size, DataInput in) 
			throws IOException {
		StringBuilder b = new StringBuilder(size);
		int i = 0;
		boolean more = true;
		while(more && i < size) {
			char ch = in.readChar();
			i++;
			if(ch == 0) more = false;
			else b.append(ch);
		}
		
		in.skipBytes(2 * (size - i));
		
		return b.toString();
	}
	
	public static void writeFixedString(String s, int size, DataOutput out) 
	        throws IOException {
		for(int i = 0; i < size; i++) {
			char ch = 0;
			if(i < s.length()) ch = s.charAt(i);
			  out.writeChar(ch);
		}
	}
}
