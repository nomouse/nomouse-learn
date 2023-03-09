package nomouse.biz.ext.util;

/**
 * @author nomouse
 */
public class InterfaceUtils {

    public static Class<?> getSecondaryInterface(
            Class<?> clz, Class<?> rootInterface) {
        if (clz == null) {
            return null;
        }
        Class<?>[] fatherInterfaces = clz.getInterfaces();
        if (fatherInterfaces.length == 0) {
            return null;
        }
        for (Class<?> fatherInterface : fatherInterfaces) {
            if (fatherInterface == rootInterface) {
                return null;
            }

            for (Class<?> grandInterface : fatherInterface.getInterfaces()) {
                if (grandInterface == rootInterface) {
                    return fatherInterface;
                }
            }
        }

        return getSecondaryInterface(clz.getSuperclass(), rootInterface);
    }

}
