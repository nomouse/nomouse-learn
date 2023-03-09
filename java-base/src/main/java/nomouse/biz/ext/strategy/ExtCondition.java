package nomouse.biz.ext.strategy;

import static nomouse.biz.ext.strategy.ExtConstants.DEFAULT_INTEGER;
import static nomouse.biz.ext.strategy.ExtConstants.DEFAULT_SPLIT;
import static nomouse.biz.ext.strategy.ExtConstants.DEFAULT_STRING;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

/**
 * @author wuchunhao on 2022/9/29
 */
@Getter
public class ExtCondition {

    private int tenantId;

    private String bizId;

    private ExtCondition() {

    }

    public String getUniqueId() {
        return generateUniqueId(this.tenantId, this.bizId);
    }

    public static ExtCondition init(int tenantId, String bizId) {
        ExtCondition cond = new ExtCondition();
        cond.tenantId = tenantId;
        cond.bizId = bizId;
        return cond;
    }

    public static ExtCondition init(int tenantId) {
        return init(tenantId, DEFAULT_STRING);
    }

    public static ExtCondition init(String bizId) {
        ExtCondition cond = new ExtCondition();
        return init(DEFAULT_INTEGER, bizId);
    }

    public static String generateUniqueId(int tenantId, String bizId) {
        String bizIdStr = Optional.ofNullable(bizId).map(String::valueOf)
            .filter(s -> s.length() != 0).filter(s -> !"null".equals(s))
            .orElse(DEFAULT_STRING);
        String tenantIdStr = String.valueOf(tenantId <= 0 ? DEFAULT_INTEGER : tenantId);
        return bizIdStr + DEFAULT_SPLIT + tenantIdStr;
    }

    public static List<String> generateUniqueIdList(int[] tenantIds, String[] bizIds) {
        List<String> uniqueIdList = new ArrayList<>(tenantIds.length * bizIds.length);
        for (int tenantId : tenantIds) {
            for (String bizId : bizIds) {
                uniqueIdList.add(ExtCondition.generateUniqueId(tenantId, bizId));
            }
        }

        return uniqueIdList;
    }
}
