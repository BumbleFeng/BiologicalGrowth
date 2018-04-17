package edu.neu.csye6200.bg;

import java.awt.Point;

/**
 *
 * @author BumbleBee
 */
public class Stem {

    private Point A;
    private Point B;
    private Vector2 AB;

    public Stem(Point O, Vector2 ab) {
        this.A = O;
        this.AB = ab;
        int x = (int) Math.round(A.x + ab.getX());
        int y = (int) Math.round(A.y + ab.getY());
        B = new Point(x, y);
    }

    public Point getA() {
        return A;
    }

    public Point getB() {
        return B;
    }

    public Vector2 getAB() {
        return AB;
    }

}
