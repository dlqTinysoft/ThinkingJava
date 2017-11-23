package hpu.edu.dlq;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 董乐强 on 2017/11/22.
 * 定义一个切面
 */
@Aspect
@Component
public class HttpAspect {
    //   使用日志，来进行打印
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);


    //    定义一个切点
    @Pointcut("execution(public * hpu.edu.dlq.GirlController.girlList(..))")
    public void log() {
    }

    //    拦截girlList方法,方法执行之前就这个方法拦截
//     @Before("execution(public * hpu.edu.dlq.GirlController.*(..))")拦截所有
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {

//        url

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url={}", request.getRequestURL());

//        method
        logger.info("method={}", request.getMethod());

//        ip
        logger.info("ip={}", request.getRemoteAddr());
//        类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getDeclaringType());

//参数
        logger.info("args={}", joinPoint.getArgs());
//        使用日志打印
        logger.info("1111111111111");
        //System.out.println("11111111");
    }

    //    girlList方法执行后执行
    @After("log()")
    public void doAfter() {
        logger.info("doAfter");
        System.out.println("doAfter");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void afterRunning(Object object) {
        logger.info("response={}", object.toString());
    }

}
