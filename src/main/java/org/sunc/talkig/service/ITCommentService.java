package org.sunc.talkig.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.sunc.talkig.entity.TComment;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author talk
 * @since 2023-05-08
 */
public interface ITCommentService extends IService<TComment> {

    List<TComment> findCommentDetail(Long articleId);
}
