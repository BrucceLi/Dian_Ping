package com.dianping.model;

import java.io.Serializable;

public class City implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mId;
	private String mCityName;
	private String mShotKey;
	public int getId() {
		return mId;
	}
	public void setId(int mId) {
		this.mId = mId;
	}
	public String getCityName() {
		return mCityName;
	}
	public void setCityName(String mCityName) {
		this.mCityName = mCityName;
	}
	public String getShotKey() {
		return mShotKey;
	}
	public void setShotKey(String mShotKey) {
		this.mShotKey = mShotKey;
	}
}
