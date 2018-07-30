package com.syn.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.Session;
import org.hibernate.Query;
import com.syn.entities.TestProcTable;

public class TestProcTableDaoImpl implements TestProcTableDao{
	private Configuration configuration;
	private ServiceRegistry serviceRegistry;
	private SessionFactory sessionFactory;
	private Transaction ts;

	public TestProcTableDaoImpl(){
		configuration = new Configuration();
		configuration.configure();

		serviceRegistry = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).buildServiceRegistry();

		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}	

	@Override
	public List getAllProcUsers(){
		System.out.println("in dao getAll function");
		List result = null;
		Session session = sessionFactory.openSession();
		try {
			ts = session.beginTransaction();
			Query query = session.getNamedQuery("sp_getTestProc")
					.setParameter("t_id", 20);
			result = query.list();
			ts.commit();

		} catch (Exception e) {
			if (ts != null) 
				ts.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}		

		return result;
	}

	@Override
	public boolean addProcUser(TestProcTable procUser) {
		System.out.println("in dao insert function");
		boolean success = true;
		Session session = sessionFactory.openSession();
		try {
			ts = session.beginTransaction();
			Query query = session.getNamedQuery("sp_insertUser")
					.setParameter("t_name", procUser.getName());
			List result = query.list();

			this.showProcUsers(result);
			ts.commit();

		} catch (Exception e) {
			if (ts != null) 
				ts.rollback();
			success = false;
			e.printStackTrace();
		} finally {
			session.close();
		}		
		return success;
	}

	@Override
	public void showProcUsers(List userList) {		
		TestProcTable tb = new TestProcTable();		
		for(int i = 0; i < userList.size(); i++){
			tb = (TestProcTable)userList.get(i);
			System.out.println(tb.getId() + " " + tb.getName());
		}
	}
	
	// testing purpose, delete when done 
	public boolean getDaoTestResult(){
		return false;
	}

}
