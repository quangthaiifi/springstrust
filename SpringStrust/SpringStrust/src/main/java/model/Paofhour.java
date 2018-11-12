package model;

public class Paofhour {

    private Double paValues;

    private String hours;

    private  Double psValue;

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Double getPsValue() {
        return psValue;
    }

    public void setPsValue(Double psValue) {
        this.psValue = psValue;
    }

    public Double getPaValues() {
        return paValues;
    }

    public Paofhour() {
    }


    public void setPaValues(Double paValues) {
        this.paValues = paValues;
    }
 @Override
    public String toString() {
        return "Paofhour{" +
                "paValues=" + paValues +
                ", hours=" + hours +
                ", psValue=" + psValue +
                '}';
    }
}
