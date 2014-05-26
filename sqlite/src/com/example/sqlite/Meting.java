package com.example.sqlite;

public class Meting {
	//Variabelen
	private int 	_mid;
	private int 	_pid;
	private String 	_datum;
	private int 	_status;
	private int 	_bloedsuiker;
	private String 	_commentaar;
	
	//Lege Constructor
	public Meting()
	{
		//leeg
	}
	
	//Constructor
	public Meting(int mid, int pid, String datum, int status, int bloedsuiker, String commentaar)
	{
		_mid = mid;
		_pid = pid;
		_datum = datum;
		_status = status;
		_bloedsuiker = bloedsuiker;
		_commentaar = commentaar;
	}
	
	//Return methods
	public int getMid()
	{
		return _mid;
	}
	
	public int getPid()
	{
		return _pid;
	}
	
	public String getDatum()
	{
		return _datum;
	}
	
	public int getStatus()
	{
		return _status;
	}
	
	public int getBloedsuiker()
	{
		return _bloedsuiker;
	}
	
	public String getCommentaar()
	{
		return _commentaar;
	}
	
	//Set methods
	public void setPid(int pid)
	{
		_pid = pid;
	}
	
	public void setDatum(String datum)
	{
		_datum = datum;
	}
	
	public void setStatus(int status)
	{
		_status = status;
	}
	
	public void setBloedsuiker(int bloedsuiker)
	{
		_bloedsuiker = bloedsuiker;
	}
	
	public void setCommentaar(String commentaar)
	{
		_commentaar = commentaar;
	}
}
