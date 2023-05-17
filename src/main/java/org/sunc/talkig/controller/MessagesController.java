package org.sunc.talkig.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sunc.talkig.common.Result;
import org.sunc.talkig.entity.Messages;
import org.sunc.talkig.service.IMessagesService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author talk
 * @since 2023-05-04
 */
@Slf4j
@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    IMessagesService messagesService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Messages messages) {
        log.info(messages.toString());
        messagesService.saveOrUpdate(messages);
        return Result.success();
    }
    // 根据id删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        messagesService.removeById(id);
        return Result.success();
    }
    // 多个id删除
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        messagesService.removeByIds(ids);
        return Result.success();
    }
    // 查找所有
    @GetMapping
    public Result findAll() {
        return Result.success(messagesService.list());
    }
    // 根据id找一个
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(messagesService.getById(id));
    }
    /**
     * 用户信息分页查询
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result page(int pageNum,int pageSize,String name){
        log.info("page = {},pageSize = {},name = {}" ,pageNum,pageSize,name);

        //构造分页构造器
        Page pageInfo = new Page(pageNum,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Messages> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件



        queryWrapper.like(StringUtils.isNotEmpty(name),Messages::getId,name);





        //执行查询
        messagesService.page(pageInfo,queryWrapper);

        return Result.success(pageInfo);
    }

}

