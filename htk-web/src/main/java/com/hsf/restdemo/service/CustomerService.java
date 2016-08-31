package com.hsf.restdemo.service;

import java.util.List;

import com.hsf.restdemo.entity.Customer;

public interface CustomerService{
	/**
	 * 
	 * 若BaseService 没有定义的方法，可以在这里添加
	 * 
	 */
	public List<Customer> list();
}
