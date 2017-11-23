package hpu.edu.dlq.exception;

import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by 董乐强 on 2017/11/23.
 */
public class ResultUtil {
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(0);
        result.setMessge("成功");
        result.setData(object);
        return result;
    }
    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMessge(msg);
        return result;
    }
}
