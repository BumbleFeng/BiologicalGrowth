package edu.neu.csye6200.bg;

/**
 *
 * @author BumbleBee
 */
public class Vector2 {

    private double x;
    private double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 Rotate(double Angle, double Ratio) {
        double Radian = Angle / 180 * Math.PI;
        double x = Ratio * (Math.cos(Radian) * this.x - Math.sin(Radian) * this.y);
        double y = Ratio * (Math.sin(Radian) * this.x + Math.cos(Radian) * this.y);
        return new Vector2(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
