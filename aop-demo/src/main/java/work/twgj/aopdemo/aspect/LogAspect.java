package work.twgj.aopdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import work.twgj.aopdemo.annotation.Log;
import work.twgj.aopdemo.entity.SysLogEntity;
import work.twgj.aopdemo.mapper.SysLogMapper;
import work.twgj.aopdemo.utils.HttpContextUtils;
import work.twgj.aopdemo.utils.IPUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author weijie.zhu
 * @date 2023/10/8 9:30
 */
@Aspect
@Component
public class LogAspect {

    @Resource
    private SysLogMapper sysLogMapper;

    @Pointcut("@annotation(work.twgj.aopdemo.annotation.Log)")
    private void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point){
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try{
            result = point.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point,time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint point,long time){
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        SysLogEntity sysLog = new SysLogEntity();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null){
            sysLog.setOperation(logAnnotation.value());
        }
        // 类名
        String className = point.getTarget().getClass().getName();
        // 方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求方法参数
        Object[] args = point.getArgs();
        // 请求方法参数名称
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = discoverer.getParameterNames(method);
        if (args != null && paramNames != null){
            String params = "";
            for(int i = 0;i < args.length;i++){
                params += " " + paramNames[i] + ": " + args[i];
            }
            sysLog.setParams(params);
        }
        // 提前request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 设置用户名
        sysLog.setUsername("");
        sysLog.setTime(time);
        sysLog.setCreateTime(new Date());
        sysLogMapper.insert(sysLog);
    }
}
