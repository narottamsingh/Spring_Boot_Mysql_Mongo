package practical.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerDemo1 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Employee employee = new Employee();
		System.out.println("-------Object serialization start ---------------");
		System.out.println(employee.toString());
		FileOutputStream outputStream = new FileOutputStream("/home/manikaran/a.ser");
		ObjectOutputStream stream = new ObjectOutputStream(outputStream);
		stream.writeObject(employee);
		stream.close();
		outputStream.close();
		System.out.println("----------Object de-serialization start --------------");
		FileInputStream inputStream=new FileInputStream("/home/manikaran/a.ser");
		ObjectInputStream stream2=new ObjectInputStream(inputStream);
		Employee employee2=(Employee) stream2.readObject();
		stream2.close();
		inputStream.close();
		System.out.println(employee2.toString()+" "+employee2.lName);
	}
}
