package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO {
	@Autowired  
    JdbcTemplate jdbc;  

	public int generateId()
	{
		String cmd="select max(cus_id) from Customer";
		List li=jdbc.query(cmd , new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1)+1;
			}
			
		});
		return (int) li.get(0);
	}
	
	public String addCustomer(Customer cus)
	{
		int id=generateId();
		String cmd = "insert into Customer(cus_id,cus_name,cus_ph_no,cus_username,pwd,cus_email)  "
				+ " values(?,?,?,?,?,?)";
		jdbc.update(cmd, new Object[] {id, 
				 cus.getCus_name(),cus.getCus_ph_no(),cus.getCus_username(),
				 cus.getPwd(),cus.getCus_email()
		});
		String msg="Customer Account Created,"
				  +"\n Customer id is:"+id
        		+"\n\n\n thank you......";
		
		  MailAlert.sendmail(cus.getCus_email(),msg);

		return "inserted";
	}
    public String authenticate(String user,String pwd) {
        String cmd = "select count(*) cnt from Customer where cus_username=? "
                + " AND pwd=?";
        List str=jdbc.query(cmd,new Object[] {user,pwd}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                return rs.getInt("cnt");
            }
            
        });
        return str.get(0).toString();
    }
    public Customer showonecustomer(String user) {
		String cmd = "select * from Customer where cus_UserName=? ";
        List<Customer> str=jdbc.query(cmd,new Object[] {user}, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
            	Customer customer = new Customer();
        		
        			customer.setCus_id(rs.getInt("cus_id"));
        			customer.setCus_name(rs.getString("cus_name"));
        			customer.setCus_ph_no(rs.getString("cus_ph_no"));
        			customer.setCus_username(rs.getString("cus_username"));
        			customer.setCus_email(rs.getString("cus_email"));
        			
        		
        		return customer;
            }
            
        });
        return str.get(0);
	}
    public Customer customersearchid(int id) {
		String cmd = "select * from Customer where cus_id=? ";
        List<Customer> str=jdbc.query(cmd,new Object[] {id}, new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
            	Customer customer = new Customer();
        		
        			customer.setCus_id(rs.getInt("cus_id"));
        			customer.setCus_name(rs.getString("cus_name"));
        			customer.setCus_ph_no(rs.getString("cus_ph_no"));
        			customer.setCus_username(rs.getString("cus_username"));
        			customer.setCus_email(rs.getString("cus_email"));
        			
        		
        		return customer;
            }
            
        });
        return str.get(0);
	}
    

}