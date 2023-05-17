package org.sunc.talkig;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.sunc.talkig.common.Constants;
import org.sunc.talkig.entity.Course;
import org.sunc.talkig.entity.Messages;
import org.sunc.talkig.entity.TComment;
import org.sunc.talkig.entity.User;
import org.sunc.talkig.exception.CustomException;
import org.sunc.talkig.mapper.CourseMapper;
import org.sunc.talkig.mapper.TCommentMapper;
import org.sunc.talkig.mapper.UserMapper;
import org.sunc.talkig.service.IMessagesService;
import org.sunc.talkig.service.IUserService;

import java.util.List;

/**
 * @author cc
 * @version V1.0
 * @Package org.sunc.talkig
 * @date 2023/5/3 21:17
 */

@Slf4j
@SpringBootTest
public class MyTest {

    @Autowired
    IUserService userService;

    @Autowired
    IMessagesService messagesService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    TCommentMapper commentMapper;

    @Test
    public void testSaveOrUpdate() {
        User user = new User();
        user.setUsername("bujurui");
        user.setPassword("123123");

        System.out.println(userService.saveOrUpdate(user));
    }
    
    @Test
    public void testMessage() {
        List<Messages> list = messagesService.list();
    }

    @Test
    public void testInsert() {
        Course course =new Course();
        course.setTitle("niuzi");
        courseMapper.insert(course);
        // courseMapper.insertCourse(course);
        Long id = course.getId(); // 获取自增主键值
        log.info("雪花生成的id:{}",id);
    }

    // @Test
    // public void testFindId() {
    //     CourseDTO courseDTO = new CourseDTO();
    //     courseDTO.setTitle("wagnlaowu");
    //     courseDTO.set
    //     courseMapper.insert(course);
    //     // courseMapper.insertCourse(course);
    //     Long id = course.getId(); // 获取自增主键值
    //     log.info("雪花生成的id:{}",id);
    // }

    /*
    * 使用lam法则
    * */
    @Test
    public void testGetByPId() {
        LambdaQueryWrapper<TComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TComment::getPid,32);
        TComment tComment = commentMapper.selectOne(queryWrapper);
        if (tComment == null) {
            throw new CustomException(Constants.CODE_500, "null");
        }
        log.info("查询到的评论是：{}", tComment);
        System.out.println(tComment.toString());


    }
}
