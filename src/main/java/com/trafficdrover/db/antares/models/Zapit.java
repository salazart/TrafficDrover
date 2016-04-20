package com.trafficdrover.db.antares.models;

import java.sql.Date;

public class Zapit {
	private int numZapit;
	private Date dateZapit;
	private String tezisZapit;
	private int idActive;
	private String fabulaZapit;
	private int idZapitRule;
	private int idLevel;
	private String textZapit;
	private int countBlock;
	private boolean booArh;
	private boolean booSend;
	private String curUser;
	private Date curDate;
	private int idAbonent;
	private int idYes;
	private int yearZapit;
	private boolean booWord;
	
	public int getNumZapit() {
		return numZapit;
	}
	public void setNumZapit(int numZapit) {
		this.numZapit = numZapit;
	}
	public Date getDateZapit() {
		return dateZapit;
	}
	public void setDateZapit(Date dateZapit) {
		this.dateZapit = dateZapit;
	}
	public String getTezisZapit() {
		return tezisZapit;
	}
	public void setTezisZapit(String tezisZapit) {
		this.tezisZapit = tezisZapit;
	}
	public int getIdActive() {
		return idActive;
	}
	public void setIdActive(int idActive) {
		this.idActive = idActive;
	}
	public String getFabulaZapit() {
		return fabulaZapit;
	}
	public void setFabulaZapit(String fabulaZapit) {
		this.fabulaZapit = fabulaZapit;
	}
	public int getIdZapitRule() {
		return idZapitRule;
	}
	public void setIdZapitRule(int idZapitRule) {
		this.idZapitRule = idZapitRule;
	}
	public int getIdLevel() {
		return idLevel;
	}
	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}
	public String getTextZapit() {
		return textZapit;
	}
	public void setTextZapit(String textZapit) {
		this.textZapit = textZapit;
	}
	public int getCountBlock() {
		return countBlock;
	}
	public void setCountBlock(int countBlock) {
		this.countBlock = countBlock;
	}
	public boolean isBooArh() {
		return booArh;
	}
	public void setBooArh(boolean booArh) {
		this.booArh = booArh;
	}
	public boolean isBooSend() {
		return booSend;
	}
	public void setBooSend(boolean booSend) {
		this.booSend = booSend;
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
	public int getIdAbonent() {
		return idAbonent;
	}
	public void setIdAbonent(int idAbonent) {
		this.idAbonent = idAbonent;
	}
	public int getIdYes() {
		return idYes;
	}
	public void setIdYes(int idYes) {
		this.idYes = idYes;
	}
	public int getYearZapit() {
		return yearZapit;
	}
	public void setYearZapit(int yearZapit) {
		this.yearZapit = yearZapit;
	}
	public boolean isBooWord() {
		return booWord;
	}
	public void setBooWord(boolean booWord) {
		this.booWord = booWord;
	}
}
