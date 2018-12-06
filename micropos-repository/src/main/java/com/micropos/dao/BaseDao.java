package com.micropos.dao;

public interface BaseDao<T> {

	void create(T type);

	Long merge(T type);

	T read(Integer id);

	void update(T type);

	void delete(T type);




}
