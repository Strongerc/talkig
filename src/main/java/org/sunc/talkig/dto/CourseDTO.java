package org.sunc.talkig.dto;

import lombok.Data;
import org.sunc.talkig.entity.Course;
import org.sunc.talkig.entity.TActivity;

import java.util.List;

/**
 * @author cc
 * @version V1.0
 * @Package org.sunc.talkig.dto
 * @date 2023/5/6 0:23
 */

@Data
public class CourseDTO extends Course {
    private List<TActivity> activity;
}
