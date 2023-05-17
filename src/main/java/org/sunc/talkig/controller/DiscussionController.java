package org.sunc.talkig.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sunc.talkig.common.Result;
import org.sunc.talkig.dto.DiscussionDTO;
import org.sunc.talkig.entity.Discussion;
import org.sunc.talkig.entity.User;
import org.sunc.talkig.service.IDiscussionService;
import org.sunc.talkig.service.IUserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author talk
 * @since 2023-05-06
 */
@Slf4j
@RestController
@RequestMapping("/discussion")
public class DiscussionController {

    @Autowired
    private IDiscussionService discussionService;

    @Autowired
    private IUserService userService;

    /**
     * 根据用户名新增讨论
     * @param discussionDTO
     * @return
     */
    @PostMapping
    public Result save(@RequestBody DiscussionDTO discussionDTO){
        log.info(discussionDTO.toString());
        discussionService.saveWithUserName(discussionDTO);
        return Result.success("新增菜品成功");
    }

    /**
     * 讨论分页查询
     * @param pageNum
     * @param pageSize
     * @param Message
     * @return
     */
    @GetMapping("/page")
    public Result page(int pageNum,int pageSize,String Message){

        //构造分页构造器对象
        Page<Discussion> pageInfo = new Page<>(pageNum,pageSize);
        Page<DiscussionDTO> discussionDTOPage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Discussion> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(Message != null,Discussion::getMessage,Message);
        //添加排序条件
        queryWrapper.orderByDesc(Discussion::getUpdateTime);

        //执行分页查询
        discussionService.page(pageInfo,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo,discussionDTOPage,"records");

        List<Discussion> records = pageInfo.getRecords();

        List<DiscussionDTO> list = records.stream().map((item) -> {
            DiscussionDTO discussionDTO = new DiscussionDTO();

            BeanUtils.copyProperties(item,discussionDTO);

            Long uid = item.getUid();
            //根据用户名id查询用户的姓名并存入DiscussionDto
            User userById = userService.getById(uid);

            if(userById != null){
                String username = userById.getUsername();
                discussionDTO.setUsername(username);
            }
            return discussionDTO;
        }).collect(Collectors.toList());

        discussionDTOPage.setRecords(list);

        return Result.success(discussionDTOPage);
    }

    /**
     * 根据id查询讨论信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id){
        DiscussionDTO byIdWithUsername = discussionService.getByIdWithUsername(id);
        return Result.success(byIdWithUsername);
    }

    /**
     * 修改点赞数
     * @param discussion
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Discussion discussion){
        log.info(discussion.toString());
        discussionService.updateLike(discussion);
        return Result.success("修改点赞成功成功");
    }
}

