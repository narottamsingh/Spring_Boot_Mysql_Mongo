package com.ns.hibernate;

import org.hibernate.Session;

import com.ns.hibernate.model.Animal;
import com.ns.hibernate.model.Elephant;
import com.ns.hibernate.model.Lion;

public class EmbededTest {
	public static void main(String... args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Animal animal = new Animal("Lion A", "Africa");
		Lion lion = new Lion(1, animal);
		animal = new Animal("Elephnat A", "Asia");
		Elephant elephant = new Elephant(1, animal);
		session.save(lion);
		session.save(elephant);
		session.getTransaction().commit();
		session.close();
	}
}
