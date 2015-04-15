package com.fulzele.testprodconfig;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String password;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void main(String[] args) {
		Configuration config = new Configuration();
		config.addAnnotatedClass(User.class);
		config.configure();
		new SchemaExport(config).create(true, true);
	}
}
