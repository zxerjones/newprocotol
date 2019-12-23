package constant;

public enum MethodEnum {


    SERVER_DATA_TO_CLIENT(0x11, "服务端封装数据发送到客户端"),
    CLIENT_DATA_SAVE_IN_DB(0x12, "客户端发送数据保存到服务端"),
    CLIENT_CYCLE_SENG_MESSAGE(0x13, "客户端周期向服务端发送请求"),
    SHAKE_HAND(0x14, "客户端服务端握手");

    private int code;
    private String des;

    MethodEnum(int i, String des) {
        this.code = i;
        this.des = des;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
