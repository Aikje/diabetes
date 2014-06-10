package com.coderzheaven.gettablefromphp;

public class Users {

	int mid;
	String datum;
	String commentaar;
	
	public int getId() {
		return mid;
	}
	public void setId(int id) {
		this.mid = id;
	}
	public String getName() {
		return datum;
	}
	public void setName(String name) {
		this.datum = name;
	}
	public String getPlace() {
		return commentaar;
	}
	public void setPlace(String place) {
		this.commentaar = place;
	}
	
}
