package com.example.demo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@Transactional
public class CustomerService {
	@Autowired
    private CustomerRepository repo;
	@Autowired
	private CustomerDAO dao;
	
	public void insert(Customer cus) {
		 dao.addCustomer(cus);
	 }
    
    public String authenticate(String user,String pwd) {
        return dao.authenticate(user, pwd);
    }
	
	public List<Customer> showCustomer() {
		return repo.findAll();
	}
	 public Customer get(Integer cus_id) {
         return repo.findById(cus_id).get();
     }
	 public Customer showonecustomer(String user) {
	        return dao.showonecustomer(user);
	    }
		public void save(Customer customer) {
	        repo.save(customer);
	    }
	   
}

