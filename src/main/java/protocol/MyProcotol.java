package protocol;


/**
 * 协议定义
 */

public class MyProcotol {

    private short header;
    private byte version;
    private int cmd;
    private int code;
    private byte encryp;
    private long contentLength;
    private String content;

    public MyProcotol(short header, byte version, byte cmd, int code, byte encryp, long contentLength, String content) {
        this.header = header;
        this.version = version;
        this.cmd = cmd;
        this.code = code;
        this.encryp = encryp;
        this.contentLength = contentLength;
        this.content = content;
    }

    @Override
    public String toString() {
        return "MyProcotol{" +
                "header=" + header +
                ", version=" + version +
                ", cmd=" + cmd +
                ", code=" + code +
                ", encryp=" + encryp +
                ", contentLength=" + contentLength +
                ", content='" + content + '\'' +
                '}';
    }

    public short getHeader() {
        return header;
    }

    public void setHeader(short header) {
        this.header = header;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public byte getEncryp() {
        return encryp;
    }

    public void setEncryp(byte encryp) {
        this.encryp = encryp;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyProcotol() {
    }
}
