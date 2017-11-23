package com.hsp.domain;

import java.sql.Timestamp;

/**
 * DsAlsAlarmsTab entity. @author MyEclipse Persistence Tools
 */

public class DsAlsAlarmsTab implements java.io.Serializable {

	// Fields

	private Long id;
	private String msreplTranVersion;
	private Timestamp utcDtsNewOn;
	private Timestamp localDtsNewOn;
	private Timestamp utcDtsAck;
	private Timestamp localDtsAck;
	private Timestamp utcDtsGoneOff;
	private Timestamp localDtsGoneOff;
	private Short shiftNr;
	private String computer;
	private String ionode;
	private String ioitem;
	private String textId;
	private String message;
	private String useridAck;
	private String useridEdit;
	private String optionField;
	private Integer priority;
	private Integer diffHours;
	private Short diffMinutes;
	private Short diffSeconds;
	private Integer diffSumSeconds;
	private String areakey;
	private String plantType;
	private String class_;

	// Constructors

	/** default constructor */
	public DsAlsAlarmsTab() {
	}

	/** minimal constructor */
	public DsAlsAlarmsTab(String msreplTranVersion, Timestamp utcDtsNewOn, Timestamp localDtsNewOn, String computer,
			String ionode, String ioitem, Integer priority) {
		this.msreplTranVersion = msreplTranVersion;
		this.utcDtsNewOn = utcDtsNewOn;
		this.localDtsNewOn = localDtsNewOn;
		this.computer = computer;
		this.ionode = ionode;
		this.ioitem = ioitem;
		this.priority = priority;
	}

	/** full constructor */
	public DsAlsAlarmsTab(String msreplTranVersion, Timestamp utcDtsNewOn, Timestamp localDtsNewOn, Timestamp utcDtsAck,
			Timestamp localDtsAck, Timestamp utcDtsGoneOff, Timestamp localDtsGoneOff, Short shiftNr, String computer,
			String ionode, String ioitem, String textId, String message, String useridAck, String useridEdit,
			String optionField, Integer priority, Integer diffHours, Short diffMinutes, Short diffSeconds,
			Integer diffSumSeconds, String areakey, String plantType, String class_) {
		this.msreplTranVersion = msreplTranVersion;
		this.utcDtsNewOn = utcDtsNewOn;
		this.localDtsNewOn = localDtsNewOn;
		this.utcDtsAck = utcDtsAck;
		this.localDtsAck = localDtsAck;
		this.utcDtsGoneOff = utcDtsGoneOff;
		this.localDtsGoneOff = localDtsGoneOff;
		this.shiftNr = shiftNr;
		this.computer = computer;
		this.ionode = ionode;
		this.ioitem = ioitem;
		this.textId = textId;
		this.message = message;
		this.useridAck = useridAck;
		this.useridEdit = useridEdit;
		this.optionField = optionField;
		this.priority = priority;
		this.diffHours = diffHours;
		this.diffMinutes = diffMinutes;
		this.diffSeconds = diffSeconds;
		this.diffSumSeconds = diffSumSeconds;
		this.areakey = areakey;
		this.plantType = plantType;
		this.class_ = class_;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsreplTranVersion() {
		return this.msreplTranVersion;
	}

	public void setMsreplTranVersion(String msreplTranVersion) {
		this.msreplTranVersion = msreplTranVersion;
	}

	public Timestamp getUtcDtsNewOn() {
		return this.utcDtsNewOn;
	}

	public void setUtcDtsNewOn(Timestamp utcDtsNewOn) {
		this.utcDtsNewOn = utcDtsNewOn;
	}

	public Timestamp getLocalDtsNewOn() {
		return this.localDtsNewOn;
	}

	public void setLocalDtsNewOn(Timestamp localDtsNewOn) {
		this.localDtsNewOn = localDtsNewOn;
	}

	public Timestamp getUtcDtsAck() {
		return this.utcDtsAck;
	}

	public void setUtcDtsAck(Timestamp utcDtsAck) {
		this.utcDtsAck = utcDtsAck;
	}

	public Timestamp getLocalDtsAck() {
		return this.localDtsAck;
	}

	public void setLocalDtsAck(Timestamp localDtsAck) {
		this.localDtsAck = localDtsAck;
	}

	public Timestamp getUtcDtsGoneOff() {
		return this.utcDtsGoneOff;
	}

	public void setUtcDtsGoneOff(Timestamp utcDtsGoneOff) {
		this.utcDtsGoneOff = utcDtsGoneOff;
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

	public String getComputer() {
		return this.computer;
	}

	public void setComputer(String computer) {
		this.computer = computer;
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

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUseridAck() {
		return this.useridAck;
	}

	public void setUseridAck(String useridAck) {
		this.useridAck = useridAck;
	}

	public String getUseridEdit() {
		return this.useridEdit;
	}

	public void setUseridEdit(String useridEdit) {
		this.useridEdit = useridEdit;
	}

	public String getOptionField() {
		return this.optionField;
	}

	public void setOptionField(String optionField) {
		this.optionField = optionField;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getDiffHours() {
		return this.diffHours;
	}

	public void setDiffHours(Integer diffHours) {
		this.diffHours = diffHours;
	}

	public Short getDiffMinutes() {
		return this.diffMinutes;
	}

	public void setDiffMinutes(Short diffMinutes) {
		this.diffMinutes = diffMinutes;
	}

	public Short getDiffSeconds() {
		return this.diffSeconds;
	}

	public void setDiffSeconds(Short diffSeconds) {
		this.diffSeconds = diffSeconds;
	}

	public Integer getDiffSumSeconds() {
		return this.diffSumSeconds;
	}

	public void setDiffSumSeconds(Integer diffSumSeconds) {
		this.diffSumSeconds = diffSumSeconds;
	}

	public String getAreakey() {
		return this.areakey;
	}

	public void setAreakey(String areakey) {
		this.areakey = areakey;
	}

	public String getPlantType() {
		return this.plantType;
	}

	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

}