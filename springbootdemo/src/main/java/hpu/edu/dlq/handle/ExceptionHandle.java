package hpu.edu.dlq.handle;

import hpu.edu.dlq.exception.GirlException;
import hpu.edu.dlq.exception.Result;
import hpu.edu.dlq.exception.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

/**
 * Created by 董乐强 on 2017/11/23.
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger  logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        //定义的异常是否是自己定义的exception
        if(e instanceof GirlException){
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(),girlException.getMessage());
        }
        logger.info("系统异常={}",e);
        return ResultUtil.error(-1,"未知错误");
    }
}
