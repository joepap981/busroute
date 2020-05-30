package com.joepap.busroute.model.openroute;

public class CoordinatesVO {
    private Double[] pair = new Double[2];
    private Double x;
    private Double y;

    public Double[] getPair () {
        return pair;
    }

    public void setPair (Double x, Double y) {
        this.x = this.pair[0] = x;
        this.y = this.pair[1] = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
        this.pair[0] = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
        this.pair[1] = y;
    }
}
