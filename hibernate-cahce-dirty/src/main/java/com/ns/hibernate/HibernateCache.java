package com.ns.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ns.hibernate.model.Employee;

public class HibernateCache {

	public static void main(String[] args)  {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer id = 2;
		printLog("--------Step 1--------");
		Employee emp = (Employee) session.load(Employee.class,id);
		printLog(emp, 1);
		// Waiting for sometime to change the data in backend
		printLog("Waiting for 10000 milliseconds...");

		// Fetch same data again, and no query fired
		Employee emp1 = (Employee) session.load(Employee.class, id);
		printLog(emp1, 2);
		emp.setAge(54);
		printLog("--------Step 2--------");
		// Create new session
		printLog("Create new session and get Employee by the same id");
		Session newSession = HibernateUtil.getSessionFactory().openSession();
		// Get Employee with id=1, query is fired because hibernate get it from
		// database
		Employee emp2 = (Employee) newSession.load(Employee.class, id);
//		printLog(emp2, 3);

		tx.commit();
//		newSession.close();
		printLog(emp2, 3);
		System.exit(0);
		
	}

	private static void printLog(Employee emp, int count) {
		System.out.println("Get Employee => Name=" + emp.getName() + ", Age=" + emp.getAge());
		System.out.println(emp.getAddress().toString());
	}

	private static void printLog(String msg) {
		System.out.println(msg);
	}
}
