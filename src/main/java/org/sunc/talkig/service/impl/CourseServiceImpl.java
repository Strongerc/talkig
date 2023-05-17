package org.sunc.talkig.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sunc.talkig.dto.CourseDTO;
import org.sunc.talkig.entity.Course;
import org.sunc.talkig.entity.TActivity;
import org.sunc.talkig.mapper.CourseMapper;
import org.sunc.talkig.service.ICourseService;
import org.sunc.talkig.service.ITActivityService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author talk
 * @since 2023-05-05
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private ITActivityService activityService;

    @Autowired
    CourseMapper courseMapper;


    /*
    * 根据id查询课程的
    *
    * */
    @Override
    public CourseDTO getByIdWithActivity(Integer id) {
        // 根据id把课程查询出来
        Course course = this.getById(id);
        // 将信息复制给courseDTO
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        // 查询当前课程对应的活动
        LambdaQueryWrapper<TActivity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TActivity::getCourseId,course.getId());
        List<TActivity> activity = activityService.list(queryWrapper);
        courseDTO.setActivity(activity);
        // 保存到dto
        return courseDTO;
    }

    /*
    * 保存课程相关活动信息，并返回课程活动
    *
    * */
    @Transactional
    @Override
    public Long saveWithActivity(CourseDTO courseDTO) {
        courseMapper.insert(courseDTO);
        Long id = courseDTO.getId();
        List<TActivity> activity = courseDTO.getActivity();
        activity = activity.stream().map((item) -> {
            item.setCourseId(id);
            return item;
        }).collect(Collectors.toList());
        activityService.saveBatch(activity);
        return id;

    }
}
