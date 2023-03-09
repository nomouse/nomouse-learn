package nomouse.biz.util.log;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuchunhao on 2023/2/21
 */
public class BizLog {

    public static final String DEFAULT_STRING = "";

    public static final String BIZ_RESULT_SUCCESS = "Y";

    public static final String BIZ_RESULT_FAILED = "N";

    public static final String SPLIT = "|";

    public static final int LOG_TYPE_BIZ = 0;

    public static final String PROTOCOL_BIZ = "biz";

    public static final int BIZ_BUCKET_CONTROL = 0;

    public static final int BIZ_BUCKET_EXP = 1;

    public static final String PROTOCOL_LION = "lion";

    public static final String PROTOCOL_CRANE = "crane";

    public static final String PROTOCOL_THRIFT = "thrift";

    public static final String TAG_LOCAL_APP = "localApp";
    public static final String TAG_LOCAL_IP = "localIp";
    public static final String TAG_REMOTE_APP = "remoteApp";
    public static final String TAG_REMOTE_IP = "remoteIp";
    public static final String TAG_SWIMLANE = "swimlane";

    public static final String TAG_LOG_TYPE = "logType";
    public static final String TAG_PROTOCOL = "protocol";

    public static final String TAG_BIZ_DOMAIN = "bizDomain";
    public static final String TAG_BIZ_SERVICE = "bizService";
    public static final String TAG_BIZ_METHOD = "bizMethod";
    public static final String TAG_BIZ_RESULT = "bizResult";
    public static final String TAG_BIZ_CODE = "bizCode";

    public static final String TAG_BIZ_BUCKET = "bizBucket";

    // prefix
    private String traceId;

    private String spanId;


    private String localApp;

    private String localIp;

    private String remoteApp;

    private String remoteIp;

    private String swimlane;


    private long timestamp;

    private long cost;


    private int logType;

    private String protocol;


    private String bizDomain;

    private String bizService;

    private String bizMethod;

    private String bizResult;

    private String bizCode;

    private int bizBucket = BIZ_BUCKET_CONTROL;

    // message
    private String bizMessage;

    private Object input;

    private Object output;

    /**
     * 自定义检索标签
     */
    private final Map<String, String> tagMap = new HashMap<>();

    /**
     * 自定义扩展标签
     */
    private final Map<String, String> extendMap = new HashMap<>();


    public String getTraceId() {
        return traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public String getLocalApp() {
        return localApp;
    }

    public String getLocalIp() {
        return localIp;
    }

    public String getRemoteApp() {
        return remoteApp;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public String getSwimlane() {
        return swimlane;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getCost() {
        return cost;
    }

    public int getLogType() {
        return logType;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getBizDomain() {
        return bizDomain;
    }

    public String getBizService() {
        return bizService;
    }

    public String getBizMethod() {
        return bizMethod;
    }

    public String getBizResult() {
        return bizResult;
    }

    public String getBizCode() {
        return bizCode;
    }

    public int getBizBucket() {
        return bizBucket;
    }


    public String getBizMessage() {
        return bizMessage;
    }

    public Object getInput() {
        return input;
    }

    public Object getOutput() {
        return output;
    }

    public Map<String, String> getTagMap() {
        return tagMap;
    }

    public Map<String, String> getExtendMap() {
        return extendMap;
    }

    public BizLog() {
    }

    public BizLog tag(String key, String value) {
        tagMap.put(key, value);
        return this;
    }

    public BizLog extend(String key, String value) {
        extendMap.put(key, value);
        return this;
    }

    public BizLog hit() {
        this.bizBucket = BIZ_BUCKET_EXP;
        return this;
    }

    public BizLog input(Object input) {
        this.input = input;
        return this;
    }

    public BizLog output(Object output) {
        this.output = output;
        return this;
    }

    public static BizLog init(String bizDomain, String bizService, String bizMethod) {
        BizLog bizLog = new BizLog();
        bizLog.timestamp = System.currentTimeMillis();
//        bizLog.localApp = Tracer.getAppKey();
//        bizLog.remoteApp = Tracer.getRemoteAppKey();
//        bizLog.swimlane = Tracer.getSwimlane();

//        Optional.ofNullable(Tracer.getServerTracer())
//            .map(ServerTracer::getSpan)
//            .ifPresent(span -> {
//                bizLog.traceId = span.getTraceId();
//                bizLog.spanId = span.getSpanId();
//                bizLog.localIp = span.getLocalIp();
//                bizLog.remoteIp = span.getRemoteIp();
//            });

        bizLog.logType = (LOG_TYPE_BIZ);
        bizLog.protocol = (PROTOCOL_BIZ);

        bizLog.bizDomain = (bizDomain);
        bizLog.bizService = (bizService);
        bizLog.bizMethod = (bizMethod);

        return bizLog;
    }

    public String success() {
        this.bizResult = (BIZ_RESULT_SUCCESS);
        this.cost = calCost();
        return format();
    }

    public String failed(String bizCode, String bizMessage) {
        this.bizResult = (BIZ_RESULT_FAILED);
        this.cost = calCost();

        this.bizCode = (bizCode);
        this.bizMessage = (bizMessage);
        return format();
    }

    private String format() {
        String message = formatMessage();

        Map<String, String> tags = formatTag();

        String log = "";
//            XMDLogFormat.build()
//            .putTags(tags).message(message);
        return log;
    }

    private Map<String, String> formatTag() {
        Map<String, String> tagMap = new HashMap<>(32);

        tagMap.putAll(this.tagMap);

        tagMap.put(TAG_REMOTE_APP, remoteApp);
        tagMap.put(TAG_REMOTE_IP, remoteIp);
        tagMap.put(TAG_SWIMLANE, swimlane);

        tagMap.put(TAG_LOG_TYPE, formatInt(logType));
        tagMap.put(TAG_PROTOCOL, protocol);

        tagMap.put(TAG_BIZ_DOMAIN, bizDomain);
        tagMap.put(TAG_BIZ_SERVICE, bizService);
        tagMap.put(TAG_BIZ_METHOD, bizMethod);
        tagMap.put(TAG_BIZ_RESULT, bizResult);
        tagMap.put(TAG_BIZ_CODE, bizCode);

        tagMap.put(TAG_BIZ_BUCKET, formatInt(bizBucket));

        return tagMap;
    }

    private String formatMessage() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append(SPLIT).append(formatString(this.getTraceId()))
            .append(SPLIT).append(formatString(this.getSpanId()))
            .append(SPLIT).append(formatString(this.getLocalApp()))
            .append(SPLIT).append(formatString(this.getLocalIp()))
            .append(SPLIT).append(formatString(this.getRemoteApp()))
            .append(SPLIT).append(formatString(this.getRemoteIp()));

        sb.append(SPLIT).append(formatInt(this.getLogType()))
            .append(SPLIT).append(formatString(this.getProtocol()));

        sb.append(SPLIT).append(formatLong(this.getTimestamp()))
            .append(SPLIT).append(formatLong(this.getCost()));

        sb.append(SPLIT).append(formatString(this.getBizDomain()))
            .append(SPLIT).append(formatString(this.getBizService()))
            .append(SPLIT).append(formatString(this.getBizMethod()))
            .append(SPLIT).append(formatString(this.getBizResult()))
            .append(SPLIT).append(formatString(this.getBizCode()));

        sb.append(SPLIT).append(formatInt(this.getBizBucket()));

        sb.append(SPLIT).append(formatString(this.getBizMessage()));
        sb.append(SPLIT).append(formatJson(this.getInput()));
        sb.append(SPLIT).append(formatJson(this.getOutput()));

        return sb.toString();
    }


    private String formatString(String value) {
        if (value == null || value.length() == 0) {
            return DEFAULT_STRING;
        } else {
            return value;
        }
    }

    private String formatInt(int value) {
        if (value == 0) {
            return DEFAULT_STRING;
        } else {
            return String.valueOf(value);
        }
    }

    private String formatLong(long value) {
        if (value == 0) {
            return DEFAULT_STRING;
        } else {
            return String.valueOf(value);
        }
    }

    private String formatJson(Object value) {
        if (value == null) {
            return DEFAULT_STRING;
        } else {
            return JSON.toJSONString(value);
        }
    }


    private long calCost() {
        long timestamp = this.timestamp;
        if (timestamp == 0L) {
            return 0;
        } else {
            return System.currentTimeMillis() - timestamp;
        }
    }

}
