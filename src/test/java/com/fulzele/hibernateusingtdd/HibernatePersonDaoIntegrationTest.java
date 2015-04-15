package com.fulzele.hibernateusingtdd;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.fulzele.hibernateusingtdd.HibernatePersonDao;
import com.fulzele.hibernateusingtdd.Person;

public class HibernatePersonDaoIntegrationTest {

	@Test
	public void persistedObjectExistInDB() throws HibernateException, IOException {
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

	private SessionFactory getSessionFactory() throws HibernateException, IOException {
		return createConfiguration().buildSessionFactory();
		
//		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
//	    configuration.getProperties()).build();
//		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//	    return sessionFactory;
	    
	}

	private Configuration createConfiguration() throws IOException {
		
		Configuration cfg = loadProductionConfiguration();
		loadTestConfigInto(cfg,"/hibernate.test.properties");
		cfg.addAnnotatedClass(Person.class);
		return cfg;
	}

	private void loadTestConfigInto(Configuration cfg, String path) throws IOException {
		
		Properties properties = loadPropertiesFrom(path);
		Enumeration<Object> keys = properties.keys();
		while(keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = properties.getProperty(key);
			cfg.setProperty(key, value);
		}
		
	}

	private Properties loadPropertiesFrom(String path) throws IOException {
		
		InputStream stream = getClass().getResourceAsStream(path);
		assertNotNull("Resource Not FOund:" + path,stream);
		Properties prop = new Properties();
		prop.load(stream);
		stream.close();
		return prop;
		
	}

	private Configuration loadProductionConfiguration() {
		
		return new Configuration().configure();
	}

}
