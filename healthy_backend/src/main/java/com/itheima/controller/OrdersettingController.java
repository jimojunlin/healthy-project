package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.POIUtile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 预约设置
 */
@RestController
@RequestMapping("/ordersetting")
public class OrdersettingController {
    @Reference
    private OrderSettingService orderSettingService;

    //文件上传
    @PostMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){
        try {
            //读取用户上传的文件
            List<String[]> list = POIUtile.readExcel(excelFile);
            
            //转换读取的数据格式
            List<OrderSetting> data = new ArrayList<OrderSetting>();
            for (String[] strings : list) {
                //读取数据
                String date = strings[0];
                String number = strings[1];
                //将数据封装为对象
                OrderSetting orderSetting = new OrderSetting(new Date(date), Integer.parseInt(number));
                //将封装后的对象添加到list列表中
                data.add(orderSetting);
            }

            //向数据库中添加数据
            orderSettingService.add(data);

            return new Result(true, MessageConstant.UPLOAD_SUCCESS);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.UPLOAD_FAIL);
        }
    }

    //根据日期获取预约数据
    @PostMapping("/selectByDate")
    public Result selectByDate(String date){
        try {
            List<Map> list = orderSettingService.selectByDate(date);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, list);
        } catch (Exception e){
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    //根据日期修改可预约人数
    @PostMapping("/updateNumberByDate")
    public Result updateNumberByDate(@RequestBody OrderSetting orderSetting){
        try {
            orderSettingService.updateNumberByDate(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e){
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
