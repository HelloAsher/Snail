package fastjson;

public class MyObject {
    private String myJson;
    private String otherString;

    public MyObject() {
    }

    public String getMyJson() {
        return myJson;
    }

    public void setMyJson(String myJson) {
        this.myJson = myJson;
    }

    public String getOtherString() {
        return otherString;
    }

    public void setOtherString(String otherString) {
        this.otherString = otherString;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "myJson='" + myJson + '\'' +
                ", otherString='" + otherString + '\'' +
                '}';
    }
}
