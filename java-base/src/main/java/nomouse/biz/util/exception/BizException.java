package nomouse.biz.util.exception;

/**
 * @author wuchunhao on 2023/2/23
 */
public class BizException extends RuntimeException {

    private String code;

    public String getCode() {
        return code;
    }

    public BizException(String code, String msg, Throwable t) {
        super(msg, t);
        this.code = code;
    }

    public BizException(String code, String msg) {
        this(code, msg, null);
    }

    public BizException(String code) {
        this(code, null, null);
    }

}
