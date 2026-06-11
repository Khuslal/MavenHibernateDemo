package com.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MappingTest {
	public static void main(String[] args) {
		// Initialize Hibernate session
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		try {
			// Create and Save Address (OneToOne Target)
			Address a = new Address();
			a.setCity("Butwal");
			a.setCountry("Nepal");
			a.setState("Lumbini");
			session.save(a);

			// Create and Save Departments (ManyToMany Targets)
			Department d1 = new Department();
			d1.setDeptName("IT");
			session.save(d1);

			Department d2 = new Department();
			d2.setDeptName("HR");
			session.save(d2);

			List<Department> depts = new ArrayList<>();
			depts.add(d1);
			depts.add(d2);

			// 3. Create and Save Employee (Owner side of Relationships)
			Employee e = new Employee();
			e.setFname("Shankhar");
			e.setLname("Pokhrel");
			e.setSalary("25000");
			e.setAddress(a); // Set OneToOne
			e.setDeptList(depts); // Set ManyToMany
			a.setEmployee(e); // Bidirectional alignment
			session.save(e);

			// Create and Save Phones (ManyToOne / OneToMany)
			Phone p1 = new Phone();
			p1.setNumber("9800000012");
			p1.setType("Smartphone");
			p1.setEmployee(e); // Set ManyToOne relationship
			session.save(p1);

			Phone p2 = new Phone();
			p2.setNumber("071-540000");
			p2.setType("Landline");
			p2.setEmployee(e); // Set ManyToOne relationship
			session.save(p2);

			// Commit the transaction to save all items into database
			tx.commit();
			System.out.println("All mappings successfully saved!");

		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
			sf.close();
		}
	}
}
