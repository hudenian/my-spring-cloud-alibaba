package com.huma.cloud.exception;

import com.huma.cloud.enums.RespCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hudenian
 * @date 2021/12/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private int code;
    private String msg;

    public BusinessException() {
        super();
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(RespCodeEnum responseEnum) {
        super(responseEnum.getMsg());
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
    }

    public BusinessException(RespCodeEnum responseEnum, String msg) {
        super(msg);
        this.code = responseEnum.getCode();
        this.msg = msg;
    }
}
