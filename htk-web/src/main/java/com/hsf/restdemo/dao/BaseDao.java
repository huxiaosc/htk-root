package com.hsf.restdemo.dao;

import java.io.Serializable;

/**
 * 
 * BaseDAO 定义DAO的通用操作
 * 
 * 
 * 
 * @author Monday
 * 
 */

public interface BaseDao<T,PK extends Serializable> {

	public PK save(T entity);

	public void update(T entity);

	public void delete(PK id);
	
	public void deleteObject(T entity);
	
	public T load(PK id);
	
	public T get(PK id);
	

}