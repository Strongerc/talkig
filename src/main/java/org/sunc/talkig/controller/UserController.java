package org.sunc.talkig.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.sunc.talkig.common.Result;
import org.sunc.talkig.entity.User;
import org.sunc.talkig.service.IUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author talk
 * @since 2023-05-02
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService userService;

    /*
    * 登录
    * */
    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody User user) {
        log.info(user.toString());

        String password = null;

        if(user.getPassword() !=null) {
            password = user.getPassword();
            password = DigestUtils.md5DigestAsHex(password.getBytes());
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User user1 = userService.getOne(queryWrapper);

        if(user1 ==null) {
            user1 = new User();
            user1.setUsername(user.getUsername());
            user1.setPassword(password);
            if(user1.getHeadPicture() == null) {
                user1.setHeadPicture("http://localhost:9091/file/bf73abf35c144ecd9d95023a8b5648aa.JPG");
            }
            userService.save(user1);
        }
        if(user1 != null || user1.getRole() == "ROLE_ADMIN") {
            return Result.success(user1);
        }
        // 管理员的数据
        request.setAttribute("user", user1.getUsername());
        return Result.success(user1);
    }

    /*
    * 注销登录
    * */
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return Result.success("退出成功");
    }

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody User user) {
        log.info(user.toString());
        userService.saveOrUpdate(user);
        return Result.success();
    }
    // 根据id删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.removeById(id);
        return Result.success();
    }
    // 多个id删除
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        userService.removeByIds(ids);
        return Result.success();
    }
    // 查找所有
    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }
    // 根据id找一个
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
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
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),User::getUsername,name);
        //添加排序条件
        queryWrapper.orderByDesc(User::getUpdateTime);

        //执行查询
        userService.page(pageInfo,queryWrapper);

        return Result.success(pageInfo);
    }
    @GetMapping("/username/{username}")
    public Result findUsernameOne(@PathVariable String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return Result.success(userService.getOne(queryWrapper));
    }

    /*
    * admin login
    * */




}

