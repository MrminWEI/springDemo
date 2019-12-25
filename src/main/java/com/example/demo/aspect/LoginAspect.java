package com.example.demo.aspect;

import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * loginAspect before login after login
 *
 * @author minwei
 * @version 1.0
 * @since 2019/12/10
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class LoginAspect {

  private Logger logger = LoggerFactory.getLogger(LoginAspect.class);

  /**
   * @return : void
   * @author : minwei
   * @date : 2019/12/10 15:39
   */
  @Pointcut("@annotation(com.example.demo.annotation.AutoLog)")
  public void loginLog() {
  }

  /**
   * 前置通知，方法调用前被调用
   *
   * @param joinPoint :
   * @return : void
   * @author : minwei
   * @date : 2019/12/10 16:08
   */
  @Before("loginLog()")
  public void doBefore(JoinPoint joinPoint) {
    logger.info("login request :" + new Date());
    // 接收到请求，记录请求内容
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    // 记录下请求内容
    logger.info("URL : " + request.getRequestURL().toString());
    logger.info("HTTP_METHOD : " + request.getMethod());
    logger.info("IP : " + request.getRemoteAddr());
    logger.info(
        "CLASS_METHOD : "
            + joinPoint.getSignature().getDeclaringTypeName()
            + "."
            + joinPoint.getSignature().getName());
    logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
  }

  /**
   * 后置返回通知
   *
   * @param ret :
   * @return : void
   * @author : minwei
   * @date : 2019/12/10 16:08
   */
  @AfterReturning(returning = "ret", pointcut = "loginLog()")
  public void doAfter(Object ret) {
    logger.info("RESPONSE :" + ret);
  }

  /**
   * 后置异常通知
   *
   * @param joinPoint :
   * @param exception :
   * @return : void
   * @author : minwei
   * @date : 2019/12/10 16:09
   */
  @AfterThrowing(pointcut = "loginLog()", throwing = "exception")
  public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
    // 目标方法名：
    logger.info(joinPoint.getSignature().getName());
    if (exception instanceof NullPointerException) {
      logger.info("发生了空指针异常!!!!!");
    }
  }

  /**
   * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
   *
   * @param joinPoint :
   * @return : void
   * @author : minwei
   * @date : 2019/12/10 16:10
   */
  @After(value = "loginLog()")
  public void doAfterAdvice(JoinPoint joinPoint) {
    logger.info("login end:" + new Date());
  }

  /**
   * 环绕通知
   *
   * @param proceedingJoinPoint :
   * @return : java.lang.Object
   * @author : minwei
   * @date : 2019/12/10 16:12
   */
  //@Around(value = "loginLog()")
  public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
    logger.info("环绕通知的目标方法名：" + proceedingJoinPoint.getSignature().getName());
    try {
      Object obj = proceedingJoinPoint.proceed();
      return obj;
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return null;
  }
}
