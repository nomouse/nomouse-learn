package nomouse.biz.filter;

import nomouse.biz.strategy.InterfaceUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 基于责任链模式的执行器
 *
 * @author nomouse on 19/1/10.
 */
public class FilterExecutor {

    @Resource
    private List<FilterExt> filterExtList;

    private Map<Class<?>, List<FilterExt>> filterChainClassMap;

    @PostConstruct
    private void init() {

        int size = filterExtList.size();
        filterChainClassMap = new HashMap<>(size);

        filterExtList.forEach(ext -> {
            Class<?> clz = InterfaceUtils.getSecondaryInterface(
                ext.getClass(), FilterExt.class);

            if (clz == null) {
                return;
            }

            List<FilterExt> filterChainList;
            if (filterChainClassMap.containsKey(clz)) {
                filterChainList = filterChainClassMap.get(clz);
            } else {
                filterChainList = new ArrayList<>();
                filterChainClassMap.put(clz, filterChainList);
            }

            filterChainList.add(ext);
        });

//        filterChainClassMap.forEach((clz, list) -> {
//            List<FilterExt> orderedList = list.stream()
//                .sorted((ext1, ext2) -> {
//                    Integer order1 = OrderUtils.getOrder(ext1.getClass(), Ordered.LOWEST_PRECEDENCE);
//                    Integer order2 = OrderUtils.getOrder(ext2.getClass(), Ordered.LOWEST_PRECEDENCE);
//                    if (order1.equals(order2)) {
//                        return 0;
//                    } else if (order1 > order2) {
//                        return 1;
//                    } else {
//                        return -1;
//                    }
//                }).collect(Collectors.toList());
//
//            // 重新排序
//            filterChainClassMap.put(clz, orderedList);
//        });

    }

    @SuppressWarnings("unchecked")
    private <C> List<C> getFilterChainList(Class<C> targetClz) {

        if (!filterChainClassMap.containsKey(targetClz)) {
            return null;
        }

        return (List<C>)filterChainClassMap.get(targetClz);
    }

    @SuppressWarnings("unchecked")
    private <C> List<C> getFilterChainList(Class<C> targetClz, Object context) {

        if (!filterChainClassMap.containsKey(targetClz)) {
            return null;
        }

        return (List<C>)filterChainClassMap.get(targetClz);
    }

    public <C> void execute(Class<C> targetClz,
                            Function<C, Boolean> matchFunction,
                            Consumer<C> exeFunction) {
        List<C> extList = getFilterChainList(targetClz);
        if (extList != null && extList.size() > 0) {
            for (C ext : extList) {
                if (matchFunction == null || matchFunction.apply(ext)) {
                    exeFunction.accept(ext);
                }
            }
        }
    }

    public <C> void accept(Class<C> targetClz,
                           Consumer<C> exeFunction) {
        List<C> extList = getFilterChainList(targetClz);
        if (extList != null && extList.size() > 0) {
            for (C ext : extList) {
                if (ext != null) {
                    exeFunction.accept(ext);
                }
            }
        }
    }

    public <C> void accept(Class<C> targetClz,
                           Object context,
                           Consumer<C> exeFunction) {
        List<C> extList = getFilterChainList(targetClz, context);
        if (extList != null && extList.size() > 0) {
            for (C ext : extList) {
                if (ext != null) {
                    exeFunction.accept(ext);
                }
            }
        }
    }

}
