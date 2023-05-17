package org.sunc.talkig.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.sunc.talkig.dto.CourseDTO;
import org.sunc.talkig.entity.Course;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author talk
 * @since 2023-05-05
 */

public interface ICourseService extends IService<Course> {

    CourseDTO getByIdWithActivity(Integer id);

    Long saveWithActivity(CourseDTO courseDTO);
}
