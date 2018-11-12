package model;

import com.fasterxml.jackson.annotation.JsonView;
import sun.util.resources.LocaleData;

public class Pa {
    private String dateTime;
    private Double paValue;

    public Pa(String dateTime, Double paValue) {
        this.dateTime = dateTime;
        this.paValue = paValue;
    }

    public Pa() {
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Double getPaValue() {
        return paValue;
    }

    public void setPaValue(Double paValue) {
        this.paValue = paValue;
    }

    @Override
    public String toString() {
        return "Pa{" +
                "localeData=" + dateTime +
                ", paValue=" + paValue +
                '}';
    }
}
