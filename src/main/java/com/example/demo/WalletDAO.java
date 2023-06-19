package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WalletDAO {
	@Autowired  
    JdbcTemplate jdbc;
	
	public Wallet[] customerWallets(int cus_id)
	{
		String cmd="select * from Wallet where cus_id=?";
		List<Wallet> w=null;
		w=jdbc.query(cmd, new Object[] {cus_id}, new RowMapper<Wallet>() {

			@Override
			public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException {
				Wallet wal=new Wallet();
				wal.setCus_id(rs.getInt("cus_id"));
				wal.setWal_id(rs.getInt("wal_id"));
				wal.setWal_amount(rs.getDouble("wal_amount"));
				wal.setWal_src(rs.getString("wal_src"));
				return wal;
			}
			
		});
		return w.toArray(new Wallet[w.size()]);
	}
	
	public Wallet cusWalletSearch(int cus_id,String wal_src) {
    	String cmd="select * from Wallet where cus_id=? and wal_src=?";
        List<Wallet> str=jdbc.query(cmd,new Object[] {cus_id,wal_src}, new RowMapper<Wallet>() {
            @Override
            public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Wallet wal=new Wallet();
				wal.setCus_id(rs.getInt("cus_id"));
				wal.setWal_id(rs.getInt("wal_id"));
				wal.setWal_amount(rs.getDouble("wal_amount"));
				wal.setWal_src(rs.getString("wal_src"));
				return wal;
            }
            
        });
        return str.get(0);
    }

	public String updateWallet(int cus_id, String wal_src, double wal_amount) {
        String cmd = "Update Wallet set wal_amount=wal_amount-? "
                + " WHERE cus_id=? AND wal_src=?";
        jdbc.update(cmd, new Object[] {wal_amount,cus_id,wal_src});
        return "Amount Debited from Wallet...";
    }
	public String refundWallet(int cus_id, String wal_src, double wal_amount) {
		String cmd = "Update Wallet set wal_amount=wal_amount+? "
				+ " WHERE cus_id=? AND wal_src=?";
		jdbc.update(cmd, new Object[] {wal_amount,cus_id,wal_src});
		return "Amount Debited from Wallet...";
	}
	public String addWallet(Wallet wal)
	{ 
		
		String cmd = "insert into wallet(cus_id,wal_id,wal_src,wal_amount)  "
				+ " values(?,?,?,?)";
		jdbc.update(cmd, new Object[] {
				 wal.getCus_id(),wal.getWal_id(),wal.getWal_src(),wal.getWal_amount()
		});
		return "inserted";
	}

}
