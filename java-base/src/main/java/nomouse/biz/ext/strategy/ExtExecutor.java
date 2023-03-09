package nomouse.biz.ext.strategy;

import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 策略扩展执行器
 *
 * @author wuchunhao
 */
@Component
public class ExtExecutor implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    private ExtFactory extFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        extFactory.init(applicationContext);
    }

    public <R, C> R apply(Class<C> targetClz, ExtCondition condition, Function<C, R> exeFunction) {
        C ext = extFactory.getExt(targetClz, condition);
        return exeFunction.apply(ext);
    }

    public <C> void accept(Class<C> targetClz, ExtCondition condition, Consumer<C> exeFunction) {
        C ext = extFactory.getExt(targetClz, condition);
        exeFunction.accept(ext);
    }
}
