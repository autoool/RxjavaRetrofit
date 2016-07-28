package com.techidea.domain.entity;

import java.util.List;

/**
 * Created by zchao on 2016/7/27.
 */
public class HFCityInfo {

    public HFCityInfo() {
    }

    private List<CityInfo> city_info;

    public List<CityInfo> getCity_info() {
        return city_info;
    }

    public void setCity_info(List<CityInfo> city_info) {
        this.city_info = city_info;
    }
}
