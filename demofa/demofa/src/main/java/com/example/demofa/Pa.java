package com.example.demofa;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pa {
    String dateTime;

    Double paValue;

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
                "dateTime=" + dateTime +
                ", paValue=" + paValue +
                '}';
    }
}
