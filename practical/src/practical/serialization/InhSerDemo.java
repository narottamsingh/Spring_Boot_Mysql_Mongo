package practical.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InhSerDemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Child parent = new Child();
		parent.j=500;
		System.out.println("-------Object serialization start ---------------");
		System.out.println(parent.j);
		FileOutputStream outputStream = new FileOutputStream("/home/manikaran/a.ser");
		ObjectOutputStream stream = new ObjectOutputStream(outputStream);
		stream.writeObject(parent);
		stream.close();
		outputStream.close();
		System.out.println("----------Object de-serialization start --------------");
		FileInputStream inputStream=new FileInputStream("/home/manikaran/a.ser");
		ObjectInputStream stream2=new ObjectInputStream(inputStream);
		Child parent2=(Child) stream2.readObject();
		stream2.close();
		inputStream.close();
		System.out.println(parent2.j+" "+parent2.i);
	}
}
