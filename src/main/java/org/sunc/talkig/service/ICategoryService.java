package org.sunc.talkig.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.sunc.talkig.entity.Category;

/**
 * <p>
 *
 * </p>
 *
 * @author talk
 * @since 2023-05-05
 */
public interface ICategoryService extends IService<Category> {

    public void remove(Long id);
}
