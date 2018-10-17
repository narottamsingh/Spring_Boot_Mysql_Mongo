package practical.serialization;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = -4782101598434477168L;

	private static transient String fName = "Ram";
	private static final String empCode = "R001";
	transient private int age = 25;
	static private int marks = 600;
	 String  lName="Shyam1";
//	transient volatile private int marks = 600;

	@Override
	public String toString() {
		return "Employee [fName=" + fName + ", empCode=" + empCode + ", age=" + age + ", marks=" + marks + "]";
	}
	

}
