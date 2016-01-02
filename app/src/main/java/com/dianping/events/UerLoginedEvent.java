package com.dianping.events;

/**
 * Created by Administrator on 2016/1/1.
 */
public  class UerLoginedEvent {

    public  String userName;
    public  String cityName;

    public UerLoginedEvent(String userName, String cityName) {
        this.userName = userName;
        this.cityName = cityName;
    }
}
