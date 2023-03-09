package nomouse.biz.ext.strategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

/**
 * 策略扩展实例注解，实际的业务扩展实例必须实现此注解，代表一个业务扩展点下的一个"策略"
 *
 * @author wuchunhao
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface Ext {

    /**
     * 扩展点业务说明，用来说明扩展点业务目的
     *
     * @return 业务目的
     */
    String bizInfo() default "";

    /**
     * 扩展点租户列表，用来做扩展实例路由
     *
     * @return 租户列表
     */
    int[] tenantIds() default ExtConstants.DEFAULT_INTEGER;

    /**
     * 扩展点业务bizId，用来做扩展路由
     *
     * @return 业务id列表
     */
    String[] bizIds() default ExtConstants.DEFAULT_STRING;

}