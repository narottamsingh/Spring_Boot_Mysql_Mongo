package practical.collection;

import java.util.HashMap;
import java.util.WeakHashMap;

import practical.serialization.Employee;

public class HashMapVsWeekHashMap {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hash map testing start .........");
		HashMap<Employee, String> hashMap = new HashMap<>();
		Employee employee = new Employee();
		hashMap.put(employee, "HELLO");
		System.out.println(hashMap);
		employee = null;
		// garbage collector is called
		System.gc();
		// thread sleeps for 4 sec
		Thread.sleep(4000);
		System.out.println(hashMap);
		
		System.out.println("Week Hash map testing start .........");
		WeakHashMap<Employee, String> hashMapW = new WeakHashMap<>();
		Employee employee1 = new Employee();
		hashMapW.put(employee1, "HELLO");
		System.out.println(hashMapW);
		employee1 = null;
		// garbage collector is called
		System.gc();
		// thread sleeps for 4 sec
		Thread.sleep(4000);
		System.out.println(hashMapW);

	}
}
