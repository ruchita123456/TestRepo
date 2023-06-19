package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
private int cus_id;
private String cus_name;
private String cus_ph_no;
private String cus_username;
private String pwd;
private String cus_email;
@Id
public int getCus_id() {
	return cus_id;
}
public void setCus_id(int cus_id) {
	this.cus_id = cus_id;
}
public String getCus_name() {
	return cus_name;
}
public void setCus_name(String cus_name) {
	this.cus_name = cus_name;
}
public String getCus_ph_no() {
	return cus_ph_no;
}
public void setCus_ph_no(String cus_ph_no) {
	this.cus_ph_no = cus_ph_no;
}
public String getCus_username() {
	return cus_username;
}
public void setCus_username(String cus_username) {
	this.cus_username = cus_username;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getCus_email() {
	return cus_email;
}
public void setCus_email(String cus_email) {
	this.cus_email = cus_email;
}


}
