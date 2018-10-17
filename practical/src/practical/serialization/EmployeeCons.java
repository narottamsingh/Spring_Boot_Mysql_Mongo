package practical.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EmployeeCons implements Serializable {

	private static final long serialVersionUID = -4782101598434477168L;

	private static transient String fName = "Ram";
	private static final String empCode = "R001";
	transient private int age = 25;
	static private int marks = 600;
//	transient volatile private int marks = 600;

	@Override
	public String toString() {
		return "Employee [fName=" + fName + ", empCode=" + empCode + ", age=" + age + ", marks=" + marks + "]";
	}

	public EmployeeCons(int age) {
		super();
		this.age = age;
	}

	private void writeObject(ObjectOutputStream obj) throws IOException {
		obj.defaultWriteObject();
		obj.writeObject(age * 4);
	}

	private void readObject(ObjectInputStream obj) throws ClassNotFoundException, IOException {
		obj.defaultReadObject();
		age = ((Integer) obj.readObject() / 4);
	}

}
