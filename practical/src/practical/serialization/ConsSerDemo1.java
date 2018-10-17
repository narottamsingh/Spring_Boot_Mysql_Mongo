package practical.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ConsSerDemo1 {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		EmployeeCons employee = new EmployeeCons(250);
		System.out.println("-------Object serialization start ---------------");
		System.out.println(employee.toString());
		try (FileOutputStream outputStream = new FileOutputStream("/home/manikaran/a.ser")) {
			ObjectOutputStream stream = new ObjectOutputStream(outputStream);
			stream.writeObject(employee);

		}
		System.out.println("-------Object de-serialization start ------------");
		try (FileInputStream inputStream = new FileInputStream("/home/manikaran/a.ser")) {
			ObjectInputStream stream2 = new ObjectInputStream(inputStream);
			EmployeeCons employee2 = (EmployeeCons) stream2.readObject();
			System.out.println(employee2.toString());
		}
	}
	
}
