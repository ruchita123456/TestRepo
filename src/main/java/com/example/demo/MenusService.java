package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenusService {
	@Autowired
	private MenusRepository repos;
	@Autowired
	private MenuDAO dao;
	
	
	public List<Menus> showall()
	{
		return repos.findAll();
	}
	
	public Menus search(int id)
	{
		return repos.findById(id).get();
	}
	public void insert(Menus men) {
		 dao.addMenu(men);
	 }

}
