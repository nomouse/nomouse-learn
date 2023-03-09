package nomouse.biz.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author nomouse
 */
public class StrategyExecutor {

    @Resource
    private List<StrategyExt> strategyExtList;

    private Map<Class<?>, Map<Object, StrategyExt>>
        strategyInterfaceMap;

    @PostConstruct
    private void init() {
        int size = strategyExtList.size();
        strategyInterfaceMap = new HashMap<>(size);

        strategyExtList.forEach(strategyExt -> {

            Class<?> interfaceClass = InterfaceUtils.getSecondaryInterface(
                strategyExt.getClass(), StrategyExt.class);
            Object strategy = strategyExt.getStrategy();
            if (strategy == null) {
                // 设置为null的时候，作为默认实现
                strategy = StrategyExt.DEFAULT_KEY;
            }

            Map<Object, StrategyExt> strategyExtMap;
            if (strategyInterfaceMap.containsKey(interfaceClass)) {
                strategyExtMap = strategyInterfaceMap.get(interfaceClass);
            } else {
                strategyExtMap = new HashMap<>(16);
                strategyInterfaceMap.put(interfaceClass, strategyExtMap);
            }

            if (strategyExtMap.containsKey(strategy)) {
                throw new IllegalArgumentException(
                    "|命令冲突|class=" + strategyExt.getClass().getSimpleName()
                        + "|command=" + strategy);
            }

            strategyExtMap.put(strategy, strategyExt);
        });

    }

    /**
     * 根据接口类和command获取实现类
     *
     * @param targetClz 对象类
     * @param command   命令
     * @param <C>       范型
     * @return 实现类
     */
    @SuppressWarnings("unchecked")
    private <C> C getStrategy(Class<C> targetClz,
        Object command) {

        if (!strategyInterfaceMap.containsKey(targetClz)) {
            return null;
        }

        Map<Object, StrategyExt> strategyExtMap
            = strategyInterfaceMap.get(targetClz);

        // 根据command来路由实现类
        C c = (C) strategyExtMap.get(command);
        if (c == null) {
            // 如果没有查到command，尝试拿默认值
            c = (C) strategyExtMap.get(StrategyExt.DEFAULT_KEY);
        }

        return c;
    }

    public <R, C> R apply(Class<C> targetClz,
        Object strategy,
        Function<C, R> exeFunction) {
        C ext = getStrategy(targetClz, strategy);
        return exeFunction.apply(ext);
    }

    public <C> void accept(Class<C> targetClz,
        Object strategy,
        Consumer<C> exeFunction) {
        C ext = getStrategy(targetClz, strategy);
        exeFunction.accept(ext);
    }
}
