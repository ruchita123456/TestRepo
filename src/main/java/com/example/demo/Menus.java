package com.example.demo;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Menus {
	private int men_id;
	private String men_item;
	private int men_price;
	private int men_cal;
	private String men_speciality;
	
	@Id
	public int getMen_id() {
		return men_id;
	}
	public void setMen_id(int men_id) {
		this.men_id = men_id;
	}
	public String getMen_item() {
		return men_item;
	}
	public void setMen_item(String men_item) {
		this.men_item = men_item;
	}
	public int getMen_price() {
		return men_price;
	}
	public void setMen_price(int men_price) {
		this.men_price = men_price;
	}
	public int getMen_cal() {
		return men_cal;
	}
	public void setMen_cal(int men_cal) {
		this.men_cal = men_cal;
	}
	public String getMen_speciality() {
		return men_speciality;
	}
	public void setMen_speciality(String men_speciality) {
		this.men_speciality = men_speciality;
	}
	

}
