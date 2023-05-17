package org.sunc.talkig.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.sunc.talkig.entity.Course;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author talk
 * @since 2023-05-05
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    Long insertCourse(Course course);
}
