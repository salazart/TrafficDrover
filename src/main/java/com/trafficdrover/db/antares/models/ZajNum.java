package com.trafficdrover.db.antares.models;

import java.sql.Date;

import com.trafficdrover.db.models.Entity;



public class ZajNum extends Entity{
	private int idZajDet;
	private Date dateB;
	private Date dateE;
	private String number;
	private int idTypAnaliz = 0;
	private int idNiv;
	private String textNumZaj = "";
	private int idZapitNum;
	private int idZloch;
	
	public ZajNum(){
		
	}
	
	public ZajNum(int idZajDet, int idZloch) {
		setIdZajDet(idZajDet);
		setIdZloch(idZloch);
	}

	public String toString(){
		return getId() + " " + getDateB() + " " + getDateE() + " " + getIdNiv() + " " + getNumber();
	}
	
	public void setData(Date start, Date end, int type, String number){
		setDateB(start);
		setDateE(end);
		setIdNiv(type);
		setNumber(number);
	}
	
	public int getIdZajDet() {
		return idZajDet;
	}
	public void setIdZajDet(int idZajDet) {
		this.idZajDet = idZajDet;
	}
	public Date getDateB() {
		return dateB;
	}
	public void setDateB(Date dateB) {
		this.dateB = dateB;
	}
	public Date getDateE() {
		return dateE;
	}
	public void setDateE(Date dateE) {
		this.dateE = dateE;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getIdTypAnaliz() {
		return idTypAnaliz;
	}
	public void setIdTypAnaliz(int idTypAnaliz) {
		this.idTypAnaliz = idTypAnaliz;
	}
	public int getIdNiv() {
		return idNiv;
	}
	public void setIdNiv(int idNiv) {
		this.idNiv = idNiv;
	}
	public String getTextNumZaj() {
		return textNumZaj;
	}
	public void setTextNumZaj(String textNumZaj) {
		this.textNumZaj = textNumZaj;
	}
	public int getIdZapitNum() {
		return idZapitNum;
	}
	public void setIdZapitNum(int idZapitNum) {
		this.idZapitNum = idZapitNum;
	}
	public int getIdZloch() {
		return idZloch;
	}
	public void setIdZloch(int idZloch) {
		this.idZloch = idZloch;
	}
	
}
