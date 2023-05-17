package org.sunc.talkig.service.impl;

import org.sunc.talkig.entity.TComment;
import org.sunc.talkig.mapper.TCommentMapper;
import org.sunc.talkig.service.ITCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author talk
 * @since 2023-05-08
 */
@Service
public class TCommentServiceImpl extends ServiceImpl<TCommentMapper, TComment> implements ITCommentService {

    @Resource
    private TCommentMapper commentMapper;

    @Override
    public List<TComment> findCommentDetail(Long articleId) {
        return commentMapper.findCommentDetail(articleId);
    }
}
