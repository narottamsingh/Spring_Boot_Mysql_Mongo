package com.ns.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ns.hibernate.model.Employee;

public class HibernateCacheLoadEvictTest {

	public static void main(String[] args)  {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer id = 2;
		printLog("--------Step 1--------");
		// Get Employee with id=1
		Employee emp = (Employee) session.load(Employee.class, id);
		printLog(emp, 1);

		// Fetch same data again, and no query fired
		Employee emp1 = (Employee) session.load(Employee.class, id);
		printLog(emp1, 2);

		printLog("--------Step 2--------");
		// Evict the Employee object with id=1 from first level cache
		printLog("Evict the Employee object id=1 from first level cache");
		session.evict(emp);
		printLog("Session contains Employee with id=1? " + session.contains(emp));

		// You will see query in logs because Hibernate get it from database.
		printLog("--------Step 3--------");
		Employee emp4 = (Employee) session.load(Employee.class, id);
		printLog(emp4, 3);

		tx.commit();
		System.exit(0);
	}

	private static void printLog(Employee emp, int count) {
		System.out.println("Employee => Name=" + emp.getName() + ", Age=" + emp.getAge());
	}

	private static void printLog(String msg) {
		System.out.println(msg);
	}
}
