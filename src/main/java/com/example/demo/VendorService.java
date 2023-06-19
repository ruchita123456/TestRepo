package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VendorService {
	@Autowired
	private VendorRepository repos;
	@Autowired
    private VendorDAO dao;
	
	public void insert(Vendor ven) {
		 dao.addVendor(ven);
	 }
    
    public String authenticate(String user,String pwd) {
        return dao.authenticate(user, pwd);
    }
	
	public List<Vendor> showall()
	{
		return repos.findAll();
	}
	
	public Vendor search(int id)
	{
		return repos.findById(id).get();
	}
	
	public Vendor showonevendor(String user) {
		// TODO Auto-generated method stub
		 return dao.showonevendor(user);
	}

}
