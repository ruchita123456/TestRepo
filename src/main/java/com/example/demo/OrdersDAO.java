package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersDAO {
	@Autowired  
    JdbcTemplate jdbc; 
	public int generateId()
	{
		String cmd="select max(ord_id) from Orders";
		List li=jdbc.query(cmd , new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1)+1;
			}
			
		});
		return (int) li.get(0);
	}
	public List<Orders> showVendorPendingOrders(int ven_id) {
		String cmd = "select * from Orders where ven_id=? AND ORD_STATUS='PENDING' ";
		List<Orders> ordersList=null;
		ordersList=jdbc.query(cmd,new Object[] {ven_id}, new RowMapper<Orders>() {

			@Override
			public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
				Orders orders = new Orders();
				orders.setOrd_id(rs.getInt("ord_id"));
    			orders.setCus_id(rs.getInt("cus_id"));
    			orders.setVen_id(rs.getInt("ven_id"));
    			orders.setMen_id(rs.getInt("men_id"));
    			orders.setWal_src(rs.getString("wal_src"));
    			orders.setOrd_date(rs.getDate("ord_date"));
    			orders.setOrd_qt(rs.getInt("ord_qt"));
    			orders.setOrd_billamt(rs.getInt("ord_billamt"));
    			orders.setOrd_status(rs.getString("ord_status"));
    			orders.setOrd_comments(rs.getString("ord_comments"));
				return orders;
			}
			
		});
		return ordersList;
	}
	
	public List<Orders> showVendorOrders(int ven_id) {
		String cmd = "select * from Orders where ven_id=?";
		List<Orders> ordersList=null;
		ordersList=jdbc.query(cmd,new Object[] {ven_id}, new RowMapper<Orders>() {

			@Override
			public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
				Orders orders = new Orders();
				orders.setOrd_id(rs.getInt("ord_id"));
    			orders.setCus_id(rs.getInt("cus_id"));
    			orders.setVen_id(rs.getInt("ven_id"));
    			orders.setMen_id(rs.getInt("men_id"));
    			orders.setWal_src(rs.getString("wal_src"));
    			orders.setOrd_date(rs.getDate("ord_date"));
    			orders.setOrd_qt(rs.getInt("ord_qt"));
    			orders.setOrd_billamt(rs.getInt("ord_billamt"));
    			orders.setOrd_status(rs.getString("ord_status"));
    			orders.setOrd_comments(rs.getString("ord_comments"));
				return orders;
			}
			
		});
		return ordersList;
	}
	
	 public Orders[] customerorder(int cus_id) {
			String cmd = "select * from Orders where cus_id=? ";
			List<Orders> c=null;
			c=jdbc.query(cmd,new Object[] {cus_id}, new RowMapper<Orders>() {
	            @Override
	            public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
	                // TODO Auto-generated method stub
	            	Orders orders = new Orders();
	        		
	        			orders.setOrd_id(rs.getInt("ord_id"));
	        			orders.setCus_id(rs.getInt("cus_id"));
	        			orders.setVen_id(rs.getInt("ven_id"));
	        			orders.setMen_id(rs.getInt("men_id"));
	        			orders.setWal_src(rs.getString("wal_src"));
	        			orders.setOrd_date(rs.getDate("ord_date"));
	        			orders.setOrd_qt(rs.getInt("ord_qt"));
	        			orders.setOrd_billamt(rs.getInt("ord_billamt"));
	        			orders.setOrd_status(rs.getString("ord_status"));
	        			orders.setOrd_comments(rs.getString("ord_comments"));
						return orders;
	        
	            }
	            
	        });
			return c.toArray(new Orders[c.size()]);
	     
	 }
		public List<Orders> showCustomerPendingOrders(int cus_id) {
			String cmd = "select * from Orders where  cus_id=? AND ORD_STATUS='PENDING' ";
			List<Orders> ordersList=null;
			ordersList=jdbc.query(cmd,new Object[] {cus_id}, new RowMapper<Orders>() {

				@Override
				public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
					Orders orders = new Orders();
					orders.setOrd_id(rs.getInt("ord_id"));
        			orders.setCus_id(rs.getInt("cus_id"));
        			orders.setVen_id(rs.getInt("ven_id"));
        			orders.setMen_id(rs.getInt("men_id"));
        			orders.setWal_src(rs.getString("wal_src"));
        			orders.setOrd_date(rs.getDate("ord_date"));
        			orders.setOrd_qt(rs.getInt("ord_qt"));
        			orders.setOrd_billamt(rs.getInt("ord_billamt"));
        			orders.setOrd_status(rs.getString("ord_status"));
        			orders.setOrd_comments(rs.getString("ord_comments"));
					return orders;
        
				}
				
			});
			return ordersList;
		}
			
		public Orders searchByOrderId(int ord_id) {
	        String cmd = "select * from Orders where ord_id=?";
	        List<Orders> ordersList=null;
	        ordersList=jdbc.query(cmd,new Object[] {ord_id}, new RowMapper<Orders>() {
	            @Override
	            public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	Orders ord=new Orders();
					ord.setOrd_id(rs.getInt("ord_id"));
					ord.setCus_id(rs.getInt("cus_id"));
					ord.setVen_id(rs.getInt("ven_id"));
					ord.setWal_src(rs.getString("wal_src"));
					ord.setMen_id(rs.getInt("men_id"));
					ord.setOrd_date(rs.getDate("ord_date"));
					ord.setOrd_qt(rs.getInt("ord_qt"));
					ord.setOrd_billamt(rs.getInt("ord_billamt"));
					ord.setOrd_status(rs.getString("ord_status"));
					ord.setOrd_comments(rs.getString("ord_comments"));
					return ord;
	            }
	            
	        });
	        return ordersList.get(0);
	    }   
		
		public String updateStatus(int ord_id,String ord_status) {
			String cmd = "Update Orders set ORD_STATUS=? WHERE ord_Id=?";
			jdbc.update(cmd, new Object[] {ord_status,ord_id});
			return "Order Accepted...";
		}

	
}
