package com.itheima.dao;

import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 预约设置
 */
public interface OrderSettingDao {
    /**
     * 根据日期获取记录数
     * @param date
     * @return
     */
    long getCountByDate(Date date);

    /**
     * 添加记录
     * @param orderSetting
     */
    void add(OrderSetting orderSetting);

    /**
     * 根据日期修改可预约人数
     * @param ordersetting
     */
    void updateNumberByDate(OrderSetting ordersetting);

    /**
     * 根据日期获取预约数据
     * @param dateMap
     * @return
     */
    List<OrderSetting> selectByDate(Map<String, String> dateMap);
}
