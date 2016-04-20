package com.trafficdrover.db.antares.models;

import java.sql.Date;

public class ZapitDet {
	private int idZapit;
	private int idNiv;
	private Date dateB;
	private Date dateE;
	private boolean pk;
	private boolean sn;
	private boolean bs;
	private int idOperator;
	
	public int getIdZapit() {
		return idZapit;
	}
	public void setIdZapit(int idZapit) {
		this.idZapit = idZapit;
	}
	public int getIdNiv() {
		return idNiv;
	}
	public void setIdNiv(int idNiv) {
		this.idNiv = idNiv;
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
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	public boolean isSn() {
		return sn;
	}
	public void setSn(boolean sn) {
		this.sn = sn;
	}
	public boolean isBs() {
		return bs;
	}
	public void setBs(boolean bs) {
		this.bs = bs;
	}
	public int getIdOperator() {
		return idOperator;
	}
	public void setIdOperator(int idOperator) {
		this.idOperator = idOperator;
	}
}
