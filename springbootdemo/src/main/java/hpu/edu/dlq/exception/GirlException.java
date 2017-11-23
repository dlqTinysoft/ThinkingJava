package hpu.edu.dlq.exception;

import hpu.edu.dlq.ResultEnum;

/**
 * Created by 董乐强 on 2017/11/23.
 */
//Spring只会对runtimeException进行事物回滚
public class GirlException extends RuntimeException {
    private Integer code;
//    使用枚举，特别方便好用
    public GirlException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
