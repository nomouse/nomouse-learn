package nomouse.biz.util.logic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

/**
 * 内部逻辑灰度切换开关
 *
 * @author wuchunhao on 2022/2/13
 */
@Slf4j
public class LogicSwitch {

    /**
     * 全量开关
     */
    private boolean full = false;

    /**
     * 组织白名单
     */
    private Set<Integer> orgIdWhitelist;

    /**
     * 用户白名单
     */
    private Set<String> employeeIdWhitelist;

    private static final String DATA_KEY = "logicSwitchMap";

    private static Map<String, LogicSwitch> logicSwitchMap;

//    static {
//        ConfigRepository repository = Lion.getConfigRepository(Environment.getAppName());
//
//        String initValue = repository.get(DATA_KEY);
//        log.info("|Lion.init|Y||key={}|value={}", DATA_KEY, initValue);
//        parseConfig(initValue);
//        // 动态变更
//        repository.addConfigListener(DATA_KEY, (configEvent) -> {
//            String value = configEvent.getValue();
//            log.info("|Lion.update|Y||key={}|value={}", DATA_KEY, value);
//            parseConfig(value);
//        });
//    }


    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public Set<Integer> getOrgIdWhitelist() {
        return orgIdWhitelist;
    }

    public void setOrgIdWhitelist(Set<Integer> orgIdWhitelist) {
        this.orgIdWhitelist = orgIdWhitelist;
    }

    public Set<String> getEmployeeIdWhitelist() {
        return employeeIdWhitelist;
    }

    public void setEmployeeIdWhitelist(Set<String> employeeIdWhitelist) {
        this.employeeIdWhitelist = employeeIdWhitelist;
    }

    private static void parseConfig(String value) {
        // 初始化
        try {
            logicSwitchMap = JSON.parseObject(value,
                new TypeReference<Map<String, LogicSwitch>>() {
                });
        } catch (Exception e) {
            log.error("|Lion.parse|N|jsonParseError|key={}|value={}", DATA_KEY, value, e);
        }
    }

    private static Map<String, LogicSwitch> getLogicSwitchConfig() {
        if (logicSwitchMap == null) {
            return Collections.emptyMap();
        }
        return logicSwitchMap;
    }

    private static LogicSwitch getLogicSwitch(String logicKey) {
        return getLogicSwitchConfig().get(logicKey);
    }

    public static boolean checkOrg(String loginKey, List<Integer> orgIdList) {
        LogicSwitch logicSwitch = getLogicSwitch(loginKey);
        if (logicSwitch == null) {
            return false;
        }

        // 已全量
        if (logicSwitch.isFull()) {
            return true;
        }

        Set<Integer> orgIdWhitelist = logicSwitch.getOrgIdWhitelist();
        return (!CollectionUtils.isEmpty(orgIdWhitelist)
            && orgIdWhitelist.containsAll(orgIdList));
    }

    public static boolean checkEmployee(String loginKey, List<String> employeeIdList) {
        LogicSwitch logicSwitch = getLogicSwitch(loginKey);
        if (logicSwitch == null) {
            return false;
        }

        // 已全量
        if (logicSwitch.isFull()) {
            return true;
        }

        Set<String> employeeIdWhitelist = logicSwitch.getEmployeeIdWhitelist();
        return (!CollectionUtils.isEmpty(employeeIdWhitelist)
            && employeeIdWhitelist.containsAll(employeeIdList));
    }

}
