package myapp.aop_lib;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by yang2 on 2017/7/4.
 * 对presenter对象进行缓存切片
 */
@Aspect
public class MemoryCacheAspect {

    private static final String POINTCUT_METHOD = "execution(myapp.apt_lib.aop.MemoryCache * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodMemoryCache() {
    }

    @Around("methodMemoryCache()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        if (signature instanceof MethodSignature) {

            String name = signature.getName();

            MemoryCacheManager memoryCacheManager = MemoryCacheManager.getInstance();

            StringBuffer sb = new StringBuffer();
            sb.append(name);
            //以方法名字和类名做Key
            for (Object o : joinPoint.getArgs()) {
                if (o instanceof Class) {
                    sb.append(((Class) o).getSimpleName());
                }
            }
            String key = sb.toString();
            Object result = memoryCacheManager.get(key);
            if (result != null) {
                return result;
            }
            //执行方法
            result = joinPoint.proceed();

            if (result != null) {

                if (result instanceof Object) memoryCacheManager.put(key, result);

            }

            return result;
        }
        return null;
    }

}
