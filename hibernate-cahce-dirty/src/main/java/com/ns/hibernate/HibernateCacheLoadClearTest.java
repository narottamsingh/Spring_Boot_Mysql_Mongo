package com.ns.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ns.hibernate.model.Employee;

public class HibernateCacheLoadClearTest {
	
	public static void main(String[] args) throws InterruptedException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer id = 2;
		printLog("--------Step 1--------");
		Employee emp = (Employee) session.load(Employee.class, id);
		printLog(emp, 1);		

		// Fetch same data again, check logs that no query fired
		Employee emp1 = (Employee) session.load(Employee.class, id);
		printLog(emp1, 2);

		printLog("--------Step 2--------");
		// Clear example to remove everything from first level cache
		printLog("Clear everything from first level cache");
		session.clear();
		Employee emp2 = (Employee) session.load(Employee.class, id);
		printLog(emp2, 7);
		Employee emp3 = (Employee) session.load(Employee.class, id);
		printLog(emp3, 8);

		printLog("--------Step 3--------");
		printLog("Session contains Employee with id=24?" + session.contains(emp3));

		tx.commit();
		System.exit(0);
	}

	private static void printLog(Employee emp, int count) {
		System.out.println("Get Employee => Name=" + emp.getName() + ", Age=" + emp.getAge());
	}
	
	private static void printLog(String msg) {
		System.out.println(msg);
	}	
}
