package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预约设置
 */
@Service
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao ordersettingDao;

    /**
     * 添加预约设置数据
     *
     * @param data
     */
    public void add(List<OrderSetting> data) {
        for (OrderSetting orderSetting : data) {
            //判断当前日期是否在数据库中存在数据
            long count = ordersettingDao.getCountByDate(orderSetting.getOrderDate());
            if (count > 0) {
                //当前日期已存在，进行更新操作
                ordersettingDao.updateNumberByDate(orderSetting);
            } else {
                //当前日期未存在，进行添加操作
                ordersettingDao.add(orderSetting);
            }

        }
    }

    /**
     * 根据日期获取预约数据
     *
     * @param date
     * @return
     */
    public List<Map> selectByDate(String date) {
        Map<String, String> stringMap = this.editDate(date);

        Map<String, String> dateMap = new HashMap<String, String>();
        dateMap.put("begin", stringMap.get("begin"));
        dateMap.put("end", stringMap.get("end"));

        //查询数据
        List<OrderSetting> orderSettingList = ordersettingDao.selectByDate(dateMap);

        //设置返回值
        List<Map> list = new ArrayList<Map>();
        for (OrderSetting orderSetting : orderSettingList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("date", orderSetting.getOrderDate().getDate());
            map.put("number", orderSetting.getNumber());
            map.put("reservations", orderSetting.getReservations());

            list.add(map);
        }

        return list;
    }

    /**
     * 根据日期修改可预约人数
     *
     * @param orderSetting
     */
    public void updateNumberByDate(OrderSetting orderSetting) {
        //根据日期查询记录数
        long count = ordersettingDao.getCountByDate(orderSetting.getOrderDate());
        if (count > 0) {
            //当前日期已存在，进行更新操作
            ordersettingDao.updateNumberByDate(orderSetting);
        } else {
            //当前日期不存在，进行添加操作
            ordersettingDao.add(orderSetting);
        }
    }

    /**
     * 转换日期参数
     * @param date
     * @return
     */
    public Map<String, String> editDate(String date){
        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]) + 1;
        if(month > 12){
            year = year + 1;
            month = 1;
        }

        Map<String, String> dateMap = new HashMap<String, String>();
        dateMap.put("begin", date + "-1");
        dateMap.put("end", year + "-" + month + "-1");

        return dateMap;
    }

    public static void main(String[] args) {
        OrderSettingServiceImpl orderSettingService = new OrderSettingServiceImpl();
        Map<String, String> stringStringMap = orderSettingService.editDate("2012-12");
        System.out.println(stringStringMap);
    }
}
