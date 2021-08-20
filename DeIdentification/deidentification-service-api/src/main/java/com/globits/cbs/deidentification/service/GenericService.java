package com.globits.cbs.deidentification.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;

public interface GenericService<T, Idt extends Serializable> {
	T delete(Idt id);

	T save(T t);

	Page<T> getList(int pageIndex, int pageSize);

	T findById(Idt id);
}
