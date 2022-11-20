package msg;

public class ResponseMsg extends Msg{
    private int intValue;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    @Override
    public String toString() {
        return "ResponseData{" + "intValue=" + intValue + '}';
    }
}