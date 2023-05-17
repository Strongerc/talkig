package org.sunc.talkig.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunc.talkig.common.Result;
import org.sunc.talkig.entity.TActivity;
import org.sunc.talkig.service.ITActivityService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author talk
 * @since 2023-05-06
 */
@Slf4j
@RestController
@RequestMapping("/t-activity")
public class TActivityController {

    @Autowired
    ITActivityService activityService;

    // 根据具体活动添加相应信息，需要提供课程id
    @PostMapping
    public Result saveOrUpdate(@RequestBody TActivity acitvity) {
        log.info(acitvity.toString());
        activityService.saveOrUpdate(acitvity);
        return Result.success();
    }
    /*
    * 请求相应文章，为了获得文章id
    * */
}

