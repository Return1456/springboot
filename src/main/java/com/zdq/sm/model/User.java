package com.zdq.sm.model;

import java.util.Date;

public class User {
private Long id;
private String name;
private String password;
private byte[] headImg;
private String email;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public byte[] getHeadImg() {
	return headImg;
}
public void setHeadImg(byte[] headImg) {
	this.headImg = headImg;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
private Date createTime;
}
