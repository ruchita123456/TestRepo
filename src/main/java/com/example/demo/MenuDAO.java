package com.example.demo;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDAO {
	@Autowired  
    JdbcTemplate jdbc;
	public int generateId()
	{
		String cmd="select max(men_id) from Menus";
		List li=jdbc.query(cmd , new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1)+1;
			}
			
		});
		return (int) li.get(0);
	}
	
	public String addMenu(Menus men)
	{
		int id=generateId();
		String cmd = "insert into Menus(men_id,men_item,men_price,men_cal,men_speciality)  "
				+ " values(?,?,?,?,?)";
		jdbc.update(cmd, new Object[] {id, 
				 men.getMen_item(),men.getMen_price(),men.getMen_cal(),
				 men.getMen_speciality()
		});

		

		return "inserted";
	}
	
	
	 public Menus searchMenu(int men_id) {
	        String cmd = "select * from Menus where men_id=?";
	        List<Menus> menuList=jdbc.query(cmd,new Object[] {men_id}, new RowMapper<Menus>() {
	            @Override
	            public Menus mapRow(ResultSet rs, int arg1) throws SQLException {
	                Menus menu = new Menus();
	                menu.setMen_id(rs.getInt("men_id"));
	                menu.setMen_item(rs.getString("men_item"));
	                menu.setMen_price(rs.getInt("men_price"));
	                menu.setMen_cal(rs.getInt("men_cal"));
	                menu.setMen_speciality(rs.getString("men_speciality"));
	                return menu;
	            }
	            
	        });
	        return menuList.get(0);
	    }
	 

}
