package com.trafficdrover.db.antares.models;

import java.sql.Date;
import java.util.Calendar;

import com.trafficdrover.db.models.Entity;

public class Zaj extends Entity{
	private int idSection;
	private Date inDate;
	private int inNum;
	private Date inDateV;
	private int inNumV;
	private String nameInit = "";
	private String textZay = "";
	private int stanZay;
	private int zapitStan;
	private int idActive;
	private int idInit;
	private int idTypZajRule;
	private String textViddil;
	private boolean booCas;
	
	public Zaj() {
	}
	
	public Zaj(int numberZaj, int idSection, int idInitiator){
		setInNum(numberZaj);
		setInNumV(numberZaj);
		setIdSection(idSection);
		setIdInit(idInitiator);
		idSection = 22;
		inDate = new Date(Calendar.getInstance().getTimeInMillis());
		inNum = 1;
		inDateV = new Date(Calendar.getInstance().getTimeInMillis());
		inNumV = 1;
		idTypZajRule = 1;
		idActive = 1;
		
	}
	
	public String toString(){
		return getId() + " " + getInNum() + " " + getInDate() + " " + getInNumV() + " " + getInDateV();
	}
	
	public int getIdSection() {
		return idSection;
	}
	public void setIdSection(int idSection) {
		this.idSection = idSection;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public int getInNum() {
		return inNum;
	}
	public void setInNum(int inNum) {
		this.inNum = inNum;
	}
	public Date getInDateV() {
		return inDateV;
	}
	public void setInDateV(Date inDateV) {
		this.inDateV = inDateV;
	}
	public int getInNumV() {
		return inNumV;
	}
	public void setInNumV(int inNumV) {
		this.inNumV = inNumV;
	}
	public String getNameInit() {
		return nameInit;
	}
	public void setNameInit(String nameInit) {
		this.nameInit = nameInit;
	}
	public String getTextZay() {
		return textZay;
	}
	public void setTextZay(String textZay) {
		this.textZay = textZay;
	}
	public int getStanZay() {
		return stanZay;
	}
	public void setStanZay(int stanZay) {
		this.stanZay = stanZay;
	}
	public int getZapitStan() {
		return zapitStan;
	}
	public void setZapitStan(int zapitStan) {
		this.zapitStan = zapitStan;
	}
	public int getIdActive() {
		return idActive;
	}
	public void setIdActive(int idActive) {
		this.idActive = idActive;
	}
	public int getIdInit() {
		return idInit;
	}
	public void setIdInit(int idInit) {
		this.idInit = idInit;
	}
	public int getIdTypZajRule() {
		return idTypZajRule;
	}
	public void setIdTypZajRule(int idTypZajRule) {
		this.idTypZajRule = idTypZajRule;
	}
	public String getTextViddil() {
		return textViddil;
	}
	public void setTextViddil(String textViddil) {
		this.textViddil = textViddil;
	}
	public boolean getBooCas() {
		return booCas;
	}
	public void setBooCas(boolean booCas) {
		this.booCas = booCas;
	}
}
