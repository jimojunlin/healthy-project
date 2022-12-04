package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//检查项
@RestController
@RequestMapping("/checkitem")
@Slf4j
public class CheckItemController {

    //远程注入
    @Reference
    private CheckItemService checkItemService;

    //添加检查项
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        log.info("add_checkItem:{}", checkItem);

        try{
            checkItemService.add(checkItem);
        } catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    //分页查询
    @PostMapping("/queryPage")
    public PageResult queryPage(@RequestBody QueryPageBean queryPageBean){
        log.info("检查项分页查询条件：{}", queryPageBean);
        //进行查询
        PageResult pageResult = checkItemService.queryPage(queryPageBean);
        return pageResult;
    }

    //删除检查项
    @DeleteMapping("/deleteCheckItem")
    public Result deleteCheckItem(Long id){
        log.info("删除检查项id：{}", id);

        try {
            checkItemService.deleteCheckItem(id);
        } catch (Exception e){
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    //根据id查询检查项
    @GetMapping("/selectById")
    public Result selectById(Long id){
        try{
            CheckItem checkItem = checkItemService.selectById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
        } catch (Exception e){
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    //根据id修改检查项
    @PostMapping("/updateCheckItem")
    public Result updateCheckItem(@RequestBody CheckItem checkItem){
        try{
            checkItemService.updateCheckItem(checkItem);
        } catch (Exception e){
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    //获取所有检查项数据
    @GetMapping("/getAll")
    public Result getAll(){
        try{
            List<CheckItem> list = checkItemService.getAll();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, list);
        } catch (Exception e){
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}
