package com.dianping.model;

public class ResponseWrapper<T> {
	private int mResult_State;//=1
	private T mData;
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getResult_State() {
		return mResult_State;
	}
	public void setResult_State(int mResult_State) {
		this.mResult_State = mResult_State;
	}
	public T getData() {
		return mData;
	}
	public void setData(T mData) {
		this.mData = mData;
	}
}
