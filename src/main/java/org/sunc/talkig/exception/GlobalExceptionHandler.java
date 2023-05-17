package org.sunc.talkig.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sunc.talkig.common.Result;

/**
 * @author cc
 * @version V1.0
 * @Package org.sunc.talkig.exception
 * @date 2023/5/4 16:14
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 如果抛出的的是CustomException，则调用该方法
     * @param se 业务异常
     * @return Result
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result handle(CustomException se){
        return Result.error(se.getCode(), se.getMessage());
    }
}
