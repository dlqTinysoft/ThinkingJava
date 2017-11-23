package hpu.edu.dlq.exception;

/**http请求返回的最外层对象
 * Created by 董乐强 on 2017/11/23.
 */
public class Result<T> {
    //错误码
    private Integer code;
    //提示信息
    private String messge;
    //具体的内容
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
