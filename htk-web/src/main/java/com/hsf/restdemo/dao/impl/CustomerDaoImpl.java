package com.hsf.restdemo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hsf.restdemo.dao.CustomerDao;
import com.hsf.restdemo.entity.Customer;

@Repository(value = "customerDao")
public class CustomerDaoImpl extends BaseHibernateDao<Customer, String> implements CustomerDao {
	/**
	 * 
	 * 若CustomerDao 定义了BaseDAO没有的方法，则可以在这里实现
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	public List<Customer> list(){
		return getSession().createQuery("from Customer").list();
	}
}
