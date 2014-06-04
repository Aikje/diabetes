package com.example.sqlite;

/*
 * LET OP!
 * Deze class wordt gebruikt bij het ophalen van metinggegevens uit de database NIET om een nieuwe meting aan te maken.
 * Datamanipulatie van de originele gegevens via deze class is niet mogelijk.
 * 
 * EDIT(3-6-2014)
 * Deze class is nu overbodig maar wordt voor de zekerheid in het project gehouden.
 */
public class Meting {
	//Variabelen
	private int 	_mid;
	private int 	_pid;
	private String 	_datum;
	private int 	_status;
	private int 	_bloedsuiker;
	private String 	_commentaar;
	
	/**
	 * Lege constructor Meting
	 * Voor wanneer de gegevens later pas ingevult kunnen worden.
	 */
	public Meting()
	{
		//Leeg
	}
	
	/**
	 * Algemene constructor Meting
	 * 
	 * @param mid			Meting ID, AUTO_INCREMENT in database
	 * @param pid			Patiënt ID, staat vast aan de ingelogde persoon
	 * @param datum			Timestamp van de aangemaakte meting
	 * @param status		Status van de patiënt
	 * @param bloedsuiker	Bloedsuikerwaarde
	 * @param commentaar	Commentaar van de patiënt tijdens de meting
	 */
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
	public void setMid(int mid)
	{
		_mid = mid;
	}
	
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
