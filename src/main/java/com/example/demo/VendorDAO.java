package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class VendorDAO {
	@Autowired  
    JdbcTemplate jdbc;  
	public int generateId()
	{
		String cmd="select max(ven_id) from Vendor";
		List li=jdbc.query(cmd , new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1)+1;
			}
			
		});
		return (int) li.get(0);
	}
	
	public String addVendor(Vendor ven)
	{
		int id=generateId();
		String cmd = "insert into Vendor(ven_id,ven_name,ven_phone,ven_username,ven_password,ven_email)  "
				+ " values(?,?,?,?,?,?)";
		jdbc.update(cmd, new Object[] {id, 
				 ven.getVen_name(),ven.getVen_phone(),ven.getVen_username(),ven.getVen_password(),
				 ven.getVen_email()
		});
		  String msg="Vendor Account Created,"
				  +"\n Vendor id is:"+id
          		+"\n\n\n thank you......";
		  MailAlert.sendmail(ven.getVen_email(),msg);
		return "inserted";
	}
    
    public String authenticate(String user,String pwd) {
        String cmd = "select count(*) cnt from Vendor where Ven_UserName=? "
                + " AND Ven_Password=?";
        List str=jdbc.query(cmd,new Object[] {user,pwd}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                return rs.getInt("cnt");
            }
            
        });
        return str.get(0).toString();
    }
    public Vendor showonevendor(String user) {
		String cmd = "select * from Vendor where ven_UserName=? ";
        List<Vendor> str=jdbc.query(cmd,new Object[] {user}, new RowMapper<Vendor>() {
            @Override
            public Vendor mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
            	Vendor vendor = new Vendor();
        		
        			vendor.setVen_id(rs.getInt("ven_id"));
        			vendor.setVen_name(rs.getString("ven_name"));
        			vendor.setVen_phone(rs.getString("ven_phone"));
        			vendor.setVen_username(rs.getString("ven_username"));
        			vendor.setVen_email(rs.getString("ven_email"));
        			
        		
        		return vendor;
            }
            
        });
        return str.get(0);
	}

}
