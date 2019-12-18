package util;

public enum MethodEnum {


    SERVER_DATA_TO_CLIENT(0x11, "服务端封装数据发送到客户端"),
    CLIENT_DATA_SAVE_IN_DB(0x12, "客户端发送数据保存到服务端");

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
