package org.sunc.talkig.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sunc.talkig.dto.DiscussionDTO;
import org.sunc.talkig.entity.Discussion;
import org.sunc.talkig.entity.User;
import org.sunc.talkig.mapper.DiscussionMapper;
import org.sunc.talkig.mapper.UserMapper;
import org.sunc.talkig.service.IDiscussionService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author talk
 * @since 2023-05-06
 */
@Service
public class DiscussionServiceImpl extends ServiceImpl<DiscussionMapper, Discussion> implements IDiscussionService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void saveWithUserName(DiscussionDTO discussionDTO) {
        //用户名
        String username = discussionDTO.getUsername();
        // 根据用户名查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotEmpty(username),User::getUsername,username);
        User user = userMapper.selectOne(queryWrapper);
        discussionDTO.setUid(user.getId());
        // 直接保存相应字段
        this.save(discussionDTO);
    }

    @Override
    public DiscussionDTO getByIdWithUsername(Long id) {
        // 拿到后台的discussion信息
        Discussion discussionAndId = this.getById(id);
        DiscussionDTO discussionDTO = new DiscussionDTO();
        BeanUtils.copyProperties(discussionAndId,discussionDTO);
        // 拿到username的id
        Long uid = discussionAndId.getUid();
        // 根据用户id查用户表
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(uid != null ,User::getId, uid);
        User user = userMapper.selectOne(queryWrapper);
        // 设置dto
        String username = user.getUsername();
        discussionDTO.setUsername(username);
        return discussionDTO;

    }

    @Override
    public void updateLike(Discussion discussion) {
        UpdateWrapper<Discussion> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(discussion.getId() != null,"id", discussion.getId());
        updateWrapper.set(discussion.getLike()!=null, "like",discussion.getLike());
        updateWrapper.set(discussion.getUnlike()!=null, "unlike",discussion.getUnlike());
        this.update(null, updateWrapper);
    }
}
