package com.fulzele.hibernateusingtdd;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernatePersonDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void savePerson(Person person) {

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(person);
		tx.commit();
	}

}
