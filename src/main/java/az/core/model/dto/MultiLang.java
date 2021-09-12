package az.core.model.dto;



public class MultiLang {
    private String az;
    private String en;
    private String ru;

    public MultiLang() {
    }

    public MultiLang(String az, String en, String ru) {
        this.az = az;
        this.en = en;
        this.ru = ru;
    }

    public String getAz() {
        return az;
    }

    public void setAz(String az) {
        this.az = az;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}
