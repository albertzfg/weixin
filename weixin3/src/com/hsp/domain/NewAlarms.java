package com.hsp.domain;

import java.sql.Timestamp;

/**
 * NewAlarms entity. @author MyEclipse Persistence Tools
 */

public class NewAlarms implements java.io.Serializable {

	// Fields

	private Long id;
	private Timestamp localDtsNewOn;
	private Timestamp localDtsAck;
	private Timestamp localDtsGoneOff;
	private Short shiftNr;
	private String ionode;
	private String ioitem;
	private String textId;
	private Integer priority;
	private String plantType;
	private String cclass;
	private String areakey;
	private Short sendCounter;
	private String alarmtext;

	// Constructors

	/** default constructor */
	public NewAlarms() {
	}

	/** minimal constructor */
	public NewAlarms(Timestamp localDtsNewOn, String ionode, String ioitem, Integer priority) {
		this.localDtsNewOn = localDtsNewOn;
		this.ionode = ionode;
		this.ioitem = ioitem;
		this.priority = priority;
	}

	/** full constructor */
	public NewAlarms(Timestamp localDtsNewOn, Timestamp localDtsAck, Timestamp localDtsGoneOff, Short shiftNr,
			String ionode, String ioitem, String textId, Integer priority, String plantType, String cclass,
			String areakey, Short sendCounter, String alarmtext) {
		this.localDtsNewOn = localDtsNewOn;
		this.localDtsAck = localDtsAck;
		this.localDtsGoneOff = localDtsGoneOff;
		this.shiftNr = shiftNr;
		this.ionode = ionode;
		this.ioitem = ioitem;
		this.textId = textId;
		this.priority = priority;
		this.plantType = plantType;
		this.cclass = cclass;
		this.areakey = areakey;
		this.sendCounter = sendCounter;
		this.alarmtext = alarmtext;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getLocalDtsNewOn() {
		return this.localDtsNewOn;
	}

	public void setLocalDtsNewOn(Timestamp localDtsNewOn) {
		this.localDtsNewOn = localDtsNewOn;
	}

	public Timestamp getLocalDtsAck() {
		return this.localDtsAck;
	}

	public void setLocalDtsAck(Timestamp localDtsAck) {
		this.localDtsAck = localDtsAck;
	}

	public Timestamp getLocalDtsGoneOff() {
		return this.localDtsGoneOff;
	}

	public void setLocalDtsGoneOff(Timestamp localDtsGoneOff) {
		this.localDtsGoneOff = localDtsGoneOff;
	}

	public Short getShiftNr() {
		return this.shiftNr;
	}

	public void setShiftNr(Short shiftNr) {
		this.shiftNr = shiftNr;
	}

	public String getIonode() {
		return this.ionode;
	}

	public void setIonode(String ionode) {
		this.ionode = ionode;
	}

	public String getIoitem() {
		return this.ioitem;
	}

	public void setIoitem(String ioitem) {
		this.ioitem = ioitem;
	}

	public String getTextId() {
		return this.textId;
	}

	public void setTextId(String textId) {
		this.textId = textId;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getPlantType() {
		return this.plantType;
	}

	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}

	public String getCclass() {
		return this.cclass;
	}

	public void setCclass(String cclass) {
		this.cclass = cclass;
	}

	public String getAreakey() {
		return this.areakey;
	}

	public void setAreakey(String areakey) {
		this.areakey = areakey;
	}

	public Short getSendCounter() {
		return this.sendCounter;
	}

	public void setSendCounter(Short sendCounter) {
		this.sendCounter = sendCounter;
	}

	public String getAlarmtext() {
		return this.alarmtext;
	}

	public void setAlarmtext(String alarmtext) {
		this.alarmtext = alarmtext;
	}

}