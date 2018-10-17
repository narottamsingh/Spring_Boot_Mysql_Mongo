package practical.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerDemo2 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Dog dog = new Dog();
		System.out.println("-------Object serialization start ---------------");
		System.out.println(dog.toString());
		FileOutputStream outputStream = new FileOutputStream("/home/manikaran/a.ser");
		ObjectOutputStream stream = new ObjectOutputStream(outputStream);
		stream.writeObject(dog);
		stream.close();
		outputStream.close();
		System.out.println("----------Object de-serialization start --------------");
		FileInputStream inputStream = new FileInputStream("/home/manikaran/a.ser");
		ObjectInputStream stream2 = new ObjectInputStream(inputStream);
		Dog dogO = (Dog) stream2.readObject();
		stream2.close();
		inputStream.close();
		System.out.println(dogO.cat.animal.name);
	}
}
