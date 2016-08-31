package com.hsf.restdemo.dao;

import java.util.List;

import com.hsf.restdemo.entity.Customer;


public interface CustomerDao extends BaseDao<Customer, String> {
	  /** 

     * 若BaseDAO 没有定义的方法，可以在这里添加 

     */  
	public List<Customer> list();
}
