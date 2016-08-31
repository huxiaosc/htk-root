package com.hsf.restdemo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsf.restdemo.dao.CustomerDao;
import com.hsf.restdemo.entity.Customer;
import com.hsf.restdemo.service.CustomerService;

@Service("customerService")

@Transactional
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService {

	/**
	 * 
	 * 注入DAO
	 * 
	 */
	@Resource
    private CustomerDao customerDao;

	/**
	 * 
	 * 若CustomerService 定义了BaseService没有的方法，则可以在这里实现
	 * 
	 */
	
	public List<Customer> list(){
		return customerDao.list();
	}

	
	
}
