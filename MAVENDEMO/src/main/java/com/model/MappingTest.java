package com.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MappingTest {
	public static void main(String[] args) {
		oneToOne();
//		oneToMany();
	}

	static void oneToOne() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		Address a = new Address();
		a.setCity("Butwal");
		a.setCountry("Nepal");
		a.setState("Lumbini");
		
		session.save(a);
		
		Employee e = new Employee();
		e.setFname("Shankhar");
		e.setLname("Pokhrel");
		e.setSalary("25000");
		e.setAddress(a);
		a.setEmployee(e);
		
		session.save(e);
		
		session.close();
	}
	
	// oneToMany
	static void oneToMany() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		Address a = new Address();
		a.setCity("Butwal");
		a.setCountry("Nepal");
		a.setState("Lumbini");
		session.save(a);
		
		Employee e = new Employee();
		e.setFname("Shankhar");
		e.setLname("Pokhrel");
		e.setSalary("25000");
		e.setAddress(a);
		
		session.close();
	}
}
