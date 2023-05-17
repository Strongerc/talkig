package org.sunc.talkig.exception;

import lombok.Getter;

/**
 * @author cc
 * @version V1.0
 * @Package org.sunc.talkig.exception
 * @date 2023/5/4 16:13
 */


// 自定义异常
// 使用方法 ：try{ throw new Exception（“msg）} catch
@Getter
public class CustomException extends RuntimeException {
    private String code;

    public CustomException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
