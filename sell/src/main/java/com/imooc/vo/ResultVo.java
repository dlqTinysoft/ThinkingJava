package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**http请求返回的最外层对象
 * Created by 董乐强 on 2017/12/8.
 */
@Data
//在文件中使用了全局配置
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVo<T> {
    /**错误码**/
    private Integer code;
    //提示信息
    private String msg;
    //具体内容
    private T data ;
}
