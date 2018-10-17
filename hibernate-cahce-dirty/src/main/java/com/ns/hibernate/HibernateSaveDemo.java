package com.ns.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ns.hibernate.model.Address;
import com.ns.hibernate.model.Employee;

public class HibernateSaveDemo {

	public static void main(String[] args) {
		System.out.println("save and saveUpdate ");
		Employee e = new Employee();
//		e.setId(1);
		e.setEmpNo("EMP001");
		e.setName("RAM SINGH");
		e.setEmpSalary(125f);
		e.setAge(25);
		e.setAddress(new Address());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.persist(e);
		tx.commit();
		System.out.println(e.getId());
		

		System.out.println("merge fetch first if not in session");
		
		Employee e1 = new Employee();
		e1.setId(2);
		e1.setEmpNo("EMP003");
		Address address=new Address();
		address.setCity("Noida");
		address.setId(1);
		e1.setName("RAM SINGH RAM SINGH");
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		session1.merge(e1);
		tx1.commit();
		System.out.println(e1.getId());
		
		// merge does not return any identifier while new object save
		Employee e2 = new Employee();
//		e2.setId(2);
		e2.setEmpNo("EMP003");
		e2.setName("RAM SINGH");
		Session session2 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx2 = session2.beginTransaction();
		session2.merge(e2);
		tx2.commit();
		System.out.println(e2.getId());
		
		System.exit(0);

	}

}
