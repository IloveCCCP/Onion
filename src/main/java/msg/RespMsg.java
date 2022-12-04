package msg;

import java.nio.charset.StandardCharsets;

public class RespMsg extends Msg{


    MsgType type=MsgType.RESP;


    ErrorCode errorCode;

    String msg;

    int length;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {

        length=msg.getBytes(StandardCharsets.UTF_8).length;
        this.msg = msg;
    }

    public MsgType getType() {
        return type;
    }

    public int getLength() {
        return length;
    }
}
