package com.syn.dao;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class BaseDao {
	private Configuration configuration;
	private ServiceRegistry serviceRegistry;
	protected SessionFactory sessionFactory;
	protected Transaction ts;

	public BaseDao(){
		configuration = new Configuration();
		configuration.configure();

		serviceRegistry = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).buildServiceRegistry();

		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}	
}
