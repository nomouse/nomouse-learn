package nomouse.util.strategy;

/**
 * 基于策略模式的扩展点
 *
 * @author nomouse
 */
public interface StrategyExt {

    /**
     * 默认key
     */
    String DEFAULT_KEY = "__";

    /**
     * 子类+扩展点可以定义一个唯一的业务坐标
     *
     * @return 返回null代表走默认实现
     */
    Object getStrategy();
}
