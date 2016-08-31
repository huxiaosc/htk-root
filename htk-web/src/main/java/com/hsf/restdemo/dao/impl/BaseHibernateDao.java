package com.hsf.restdemo.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hsf.restdemo.dao.BaseDao;

public class BaseHibernateDao<T, PK extends Serializable> implements BaseDao<T, PK>{


	// 泛型反射类

	private Class<T> entityClass;

	// 通过反射获取子类确定的泛型类



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseHibernateDao() {

		Type genType = getClass().getGenericSuperclass();

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		entityClass = (Class) params[0];

	}

	/*
	 * 
	 * 注入sessionFactory
	 * 
	 */

	@Autowired

	@Qualifier("sessionFactory")

	private SessionFactory sessionFactory;

	public Session getSession() {

		// 事务必须是开启的(Required)，否则获取不到

		return sessionFactory.getCurrentSession();

	}

	@SuppressWarnings("unchecked")
	@Override
	public Serializable save(Object entity) {
		// TODO Auto-generated method stub
		return getSession().save(entity);
	}

	@Override
	public void update(Object entity) {
		// TODO Auto-generated method stub
		getSession().update(entity);
	}

	@Override
	public void delete(PK id) {
		// TODO Auto-generated method stub
		getSession().delete(this.get(id));
	}

	@Override
	public void deleteObject(Object entity) {
		// TODO Auto-generated method stub
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(PK id) {
		// TODO Auto-generated method stub
		return (T) getSession().load(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(PK id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(this.entityClass, id);
	}



}
