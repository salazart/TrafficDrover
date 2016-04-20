package com.trafficdrover.db.antares.models;

import java.util.Date;

import com.trafficdrover.db.models.Entity;

public class Monitoring extends Entity{
	private int idViddil;
	private Date dateMonitor;
	private String curUser;
	private Date curDate;
	private int idZloch;
	private int countNums;
	private Date dateB;
	private Date dateE;
	private String textMonitor;
	private boolean booArh;
	private int idOperator;
	private int idZlochPlace;
	private boolean booCas;
	private int idZapitMon;
	
	public Monitoring() {
	}
	
	public Monitoring(int idSection, Date dateMonitor, int idZloch, Date startDate, Date endDate) {
		setIdSection(idSection);
		setDateMonitor(dateMonitor);
		setIdZloch(idZloch);
		setDateB(startDate);
		setDateE(endDate);
	}
	
	public Monitoring(Date startDate, Date endDate){
		setDateB(startDate);
		setDateE(endDate);
		setDateMonitor(new Date());
	}
	
	public int getIdViddil() {
		return idViddil;
	}
	public void setIdSection(int idViddil) {
		this.idViddil = idViddil;
	}
	public Date getDateMonitor() {
		return dateMonitor;
	}
	public void setDateMonitor(Date dateMonitor) {
		this.dateMonitor = dateMonitor;
	}
	public String getCurUser() {
		return curUser;
	}
	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}
	public Date getCurDate() {
		return curDate;
	}
	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}
	public int getIdZloch() {
		return idZloch;
	}
	public void setIdZloch(int idZloch) {
		this.idZloch = idZloch;
	}
	public int getCountNums() {
		return countNums;
	}
	public void setCountNums(int countNums) {
		this.countNums = countNums;
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
	public String getTextMonitor() {
		return textMonitor;
	}
	public void setTextMonitor(String textMonitor) {
		this.textMonitor = textMonitor;
	}
	public boolean isBooArh() {
		return booArh;
	}
	public void setBooArh(boolean booArh) {
		this.booArh = booArh;
	}
	public int getIdOperator() {
		return idOperator;
	}
	public void setIdOperator(int idOperator) {
		this.idOperator = idOperator;
	}
	public int getIdZlochPlace() {
		return idZlochPlace;
	}
	public void setIdZlochPlace(int idZlochPlace) {
		this.idZlochPlace = idZlochPlace;
	}
	public boolean isBooCas() {
		return booCas;
	}
	public void setBooCas(boolean booCas) {
		this.booCas = booCas;
	}
	public int getIdZapitMon() {
		return idZapitMon;
	}
	public void setIdZapitMon(int idZapitMon) {
		this.idZapitMon = idZapitMon;
	}
}
