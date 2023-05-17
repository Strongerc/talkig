package org.sunc.talkig.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.sunc.talkig.dto.DiscussionDTO;
import org.sunc.talkig.entity.Discussion;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author talk
 * @since 2023-05-06
 */
public interface IDiscussionService extends IService<Discussion> {

    void saveWithUserName(DiscussionDTO discussionDTO);

    DiscussionDTO getByIdWithUsername(Long id);

    void updateLike(Discussion discussion);
}
