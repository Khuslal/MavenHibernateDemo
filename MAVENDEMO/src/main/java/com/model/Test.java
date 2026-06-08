package com.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Test {
	public static void main(String[] args) {
//		add();
		delete();
	}

	// add student
	static void add() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		Student s = new Student();
		s.setFname("Sandip");
		s.setLname("Chhetri");
		s.setAge(22);
		s.setCity("New Baneshwor");
		s.setCollege("New Summit College");

		session.save(s); // insert sql
		session.getTransaction().commit();
		session.close();

	}

	// delete data
	static void delete() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		Student s = session.get(Student.class, 4); // 4 is the id you want to delete

		session.delete(s);
		session.getTransaction().commit();
		session.close();
	}
}
