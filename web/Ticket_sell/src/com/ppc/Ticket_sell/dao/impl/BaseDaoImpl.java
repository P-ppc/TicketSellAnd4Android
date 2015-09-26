package com.ppc.Ticket_sell.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import com.ppc.Ticket_sell.dao.BaseDao;

public class BaseDaoImpl implements BaseDao {

    Configuration config=new Configuration().configure();
	
	SessionFactory factory= config.buildSessionFactory();
	
	
	
		
	
	
	@Override
	public ArrayList<?> getQueryByPage(int pageNum, int pageSize, String hql) {
		
		Session session= factory.openSession();
		Query query=session.createQuery(hql);
		query.setFirstResult((pageNum-1)*pageSize);
		query.setMaxResults(pageSize);
		ArrayList<?> result=(ArrayList<?>) query.list();
		session.close();
		return result;
	}

	@Override
	public void delete(Object object) {
		Session session= factory.openSession();
		Transaction tx= session.beginTransaction();
		session.delete(object);
		
		tx.commit();
		session.close();
	}

	@Override
	public void savaOrUpdate(Object object) {
		//clear()可能出现问题
		//session.clear();
		Session session= factory.openSession();
		Transaction tx= session.beginTransaction();
		session.saveOrUpdate(object);
		tx.commit();
		session.close();
		
		
	}

	@Override
	public Long getTotal(String hql) {
		Session session= factory.openSession();
		Query query=session.createQuery(hql);
		Long total=(Long) query.list().get(0);
		session.close();
		return total;
	}

	@Override
	public ArrayList<?> getQueryAll(String hql) {
		Session session=factory.openSession();
		Query query=session.createQuery(hql);
		ArrayList<?> result= (ArrayList<?>) query.list();
		return result;
	}

	@Override
	public String getSaveResultId(Object object) {
		Session session= factory.openSession();
		Transaction tx= session.beginTransaction();
		Serializable pKey = session.save(object);
		tx.commit();
		session.close();
		System.out.println("主键"+pKey.toString());
		return pKey.toString();
	}

	@Override
	public Boolean updateBySql(String sql) {
		Session session=factory.openSession();
		Transaction tx= session.beginTransaction();
		Query query=session.createSQLQuery(sql);
		try {
			query.executeUpdate();
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}



}
