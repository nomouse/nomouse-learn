package nomouse.biz.ext.strategy;

import nomouse.biz.ext.util.InterfaceUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * @author wuchunhao on 2022/9/29
 */
@Component
public class ExtFactory {

    private Map<Class<?>, ExtPointWrapper> extPointMap;

    public void init(ApplicationContext applicationContext) {
        Map<String, Object> extMap = applicationContext.getBeansWithAnnotation(Ext.class);

        extPointMap = new HashMap<>(32);
        extMap.values().forEach(this::doInitExt);
    }

    private void doInitExt(Object ext) {
        Class<?> extClass = ext.getClass();
        if (AopUtils.isAopProxy(ext)) {
            extClass = ClassUtils.getUserClass(ext);
        }

        Ext extAnnotation = AnnotationUtils.findAnnotation(extClass, Ext.class);
        if (extAnnotation == null) {
            throw new IllegalArgumentException("|ExtFactory.init|N|注解为空|class=" + extClass.getSimpleName());
        }

        // 初始化ExtPoint
        Class<?> extPointClass = InterfaceUtils.getSecondaryInterface(extClass, ExtPoint.class);
        ExtPointWrapper extPointBean;
        if (extPointMap.containsKey(extPointClass)) {
            extPointBean = extPointMap.get(extPointClass);
        } else {
            extPointBean = new ExtPointWrapper();
            extPointBean.setExtPointClass(extPointClass);
            extPointMap.put(extPointClass, extPointBean);
        }

        // 初始化Ext
        ExtWrapper extBean = new ExtWrapper(extPointClass, ext, extAnnotation);
        extPointBean.setExtBean(extBean);

    }

    /**
     * 根据扩展点和扩展点条件获取扩展实例对象
     *
     * @param targetClz 对象类
     * @param condition 条件
     * @param <C>       扩展实例范型
     * @return 扩展实例对象
     */
    @SuppressWarnings("unchecked")
    public <C> C getExt(Class<C> targetClz, ExtCondition condition) {
        ExtPointWrapper extPointBean = extPointMap.get(targetClz);
        if (extPointBean == null) {
            return null;
        }

        return (C) extPointBean.getExt(condition);

    }

    @Getter
    @Setter
    public class ExtPointWrapper {

        private Class<?> extPointClass;

        private List<ExtWrapper> extList = new ArrayList<>(8);

        private Map<String, ExtWrapper> extMap = new HashMap<>(16);

        public void setExtBean(ExtWrapper extBean) {
            extList.add(extBean);

            for (String uniqueId : extBean.getUniqueIdList()) {
                if (extMap.containsKey(uniqueId)) {
                    throw new IllegalArgumentException("|ExtFactory.init|N|策略冲突|class=" + extBean.getExt().getClass().getSimpleName() + "|uniqueId=" + uniqueId);
                }

                extMap.put(uniqueId, extBean);
            }
        }

        public Object getExt(ExtCondition condition) {
            // 根据uniqueId来路由实现类
            String uniqueId = condition.getUniqueId();

            Object c = extMap.get(uniqueId);
            if (c == null) {
                // 如果没有查到扩展实例，尝试拿默认实例
                c = extMap.get(ExtConstants.DEFAULT_UNIQUE_ID);
            }
            return c;
        }
    }

    @Getter
    @Setter
    public class ExtWrapper {

        private Class<?> extPointClass;

        private Object ext;

        private String bizInfo;

        private int[] tenantIds;

        private String[] bizIds;

        private List<String> uniqueIdList;

        public ExtWrapper(Class<?> extPointClass, Object ext, Ext extAnnotation) {
            this.extPointClass = extPointClass;
            this.ext = ext;

            this.bizInfo = extAnnotation.bizInfo();

            this.tenantIds = extAnnotation.tenantIds();
            this.bizIds = extAnnotation.bizIds();

            this.uniqueIdList = ExtCondition.generateUniqueIdList(this.tenantIds, this.bizIds);
        }

        public List<String> getUniqueIdList() {
            return uniqueIdList;
        }

    }
}
