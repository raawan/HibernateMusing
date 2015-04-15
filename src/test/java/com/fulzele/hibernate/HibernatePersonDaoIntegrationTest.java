package com.fulzele.hibernate;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class HibernatePersonDaoIntegrationTest {

	@Test
	public void persistedObjectExistInDB() {
		SessionFactory sf = getSessionFactory();
		HibernatePersonDao dao = new HibernatePersonDao();
		dao.setSessionFactory(sf);
		
		Person person = new Person("Minal","Fulzele");
		dao.savePerson(person);
		
		assertNotNull(person.getId());
		
		Session s =sf.openSession();
		Object copy = s.get(Person.class, (Serializable) person.getId());
		assertEquals(person,copy);
		
	}

	private SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
