package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

/**
 * 套餐
 */
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    //文件上传
    @PostMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
        //原始文件名
        String originalFilename = imgFile.getOriginalFilename();

        //获取原始文件名的后缀
        int index = originalFilename.lastIndexOf(".");
        String postfix = originalFilename.substring(index - 1);

        //定义文件名
        String fileName = UUID.randomUUID().toString() + postfix;

        //将文件上传到七牛云
        try {
            String fileUrl = QiniuUtile.uploadByBytes(imgFile.getBytes(), fileName);
            //将url保存到redis
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_IMG_URL, fileUrl);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    //添加套餐
    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try {
            setmealService.add(setmeal, checkgroupIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }

        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    //分页查询
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            return setmealService.findPage(queryPageBean);
        } catch (Exception e) {
            return new PageResult(0L, null);
        }
    }
}
