package com.example.demo;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Orders {
	private int ord_id;
	private int cus_id;
	private int ven_id;
	private int men_id;
	private String wal_src;
	private Date ord_date;
	private int ord_qt;
	private int ord_billamt;
	private String ord_status;
	private String ord_comments;
	
	@Id
	public int getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
	}
	public int getCus_id() {
		return cus_id;
	}
	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}
	public int getVen_id() {
		return ven_id;
	}
	public void setVen_id(int ven_id) {
		this.ven_id = ven_id;
	}
	public int getMen_id() {
		return men_id;
	}
	public void setMen_id(int men_id) {
		this.men_id = men_id;
	}
	public String getWal_src() {
		return wal_src;
	}
	public void setWal_src(String wal_src) {
		this.wal_src = wal_src;
	}
	public Date getOrd_date() {
		return ord_date;
	}
	public void setOrd_date(Date ord_date) {
		this.ord_date = ord_date;
	}
	public int getOrd_qt() {
		return ord_qt;
	}
	public void setOrd_qt(int ord_qt) {
		this.ord_qt = ord_qt;
	}
	public int getOrd_billamt() {
		return ord_billamt;
	}
	public void setOrd_billamt(int ord_billamt) {
		this.ord_billamt = ord_billamt;
	}
	public String getOrd_status() {
		return ord_status;
	}
	public void setOrd_status(String ord_status) {
		this.ord_status = ord_status;
	}
	public String getOrd_comments() {
		return ord_comments;
	}
	public void setOrd_comments(String ord_comments) {
		this.ord_comments = ord_comments;
	}
	
	
	
	
	

}
