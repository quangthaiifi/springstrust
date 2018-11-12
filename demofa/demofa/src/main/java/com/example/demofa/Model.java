package com.example.demofa;

import java.time.LocalDateTime;

public class Model {
    private LocalDateTime dateTime;

    private Double pa;

    private Double psp;

    public Model() {

    }

    public Model(LocalDateTime dateTime, Double pa, Double psp) {
        this.dateTime = dateTime;
        this.pa = pa;
        this.psp = psp;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getPa() {
        return pa;
    }

    public void setPa(Double pa) {
        this.pa = pa;
    }

    public Double getPsp() {
        return psp;
    }

    public void setPsp(Double psp) {
        this.psp = psp;
    }

    @Override
    public String toString() {
        return "Model{" +
                "dateTime=" + dateTime +
                ", pa=" + pa +
                ", psp=" + psp +
                '}';
    }
}
