package msg;

public enum ErrorCode {


    Success(0), Fail(1);

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;
    ErrorCode(int value) {
        this.value=value;
    }

    public static ErrorCode getEnumByValue(int code){
        for(ErrorCode e : ErrorCode.values()){
            if(e.value==code) return e;
        }
        return null;
    }
}
