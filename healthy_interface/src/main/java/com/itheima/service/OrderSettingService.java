package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * 预约设置
 */
public interface OrderSettingService {
    /**
     * 添加预约设置数据
     * @param data
     */
    void add(List<OrderSetting> data);

    /**
     * 根据日期获取预约数据
     * @param date
     * @return
     */
    List<Map> selectByDate(String date);

    /**
     * 根据日期修改可预约人数
     * @param orderSetting
     */
    void updateNumberByDate(OrderSetting orderSetting);
}
