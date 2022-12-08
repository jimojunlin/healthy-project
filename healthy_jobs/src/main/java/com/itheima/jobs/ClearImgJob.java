package com.itheima.jobs;

import com.itheima.constant.RedisConstant;
import com.itheima.utils.QiniuUtile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 定时清除图片
 */
@Slf4j
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    //清除图片
    public void clearImg(){
        //获取没用保存的imgUrl
        Set<String> imgUrlSet = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_IMG_URL, RedisConstant.SETMEAL_IMG_DB_URL);
        //进行删除操作
        if(imgUrlSet.size() > 0) {
            for (String imgUrl : imgUrlSet) {
                //删除七牛云中的数据
                //获取文件名
                String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
                QiniuUtile.delete(fileName);

                //删除redis中的数据
                jedisPool.getResource().srem(RedisConstant.SETMEAL_IMG_URL, imgUrl);

            }
            log.info("清除图片任务完成：{}", imgUrlSet.toString());
        }
    }
}
