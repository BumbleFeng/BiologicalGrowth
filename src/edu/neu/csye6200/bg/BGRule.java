package edu.neu.csye6200.bg;

/**
 *
 * @author BumbleBee
 */
public class BGRule {

    private int layer;
    private int branch;
    private double[] branchAngle;
    private double[] growingRatio;

    public BGRule(int layer, int branch, double[] branchAngle, double[] growingRatio) {
        this.layer = layer;
        this.branch = branch;
        this.branchAngle = branchAngle;
        this.growingRatio = growingRatio;

    }

    public BGRule(int layer, int branch, double Angle, double Ratio) {
        this.layer = layer;
        this.branch = branch;

        branchAngle = new double[branch];
        double d = (branch - 1) * Angle / 2;
        for (int i = 0; i < branch; i++) {
            branchAngle[i] = i * Angle - d;
        }
        growingRatio = new double[branch];
        for (int i = 0; i < branch; i++) {
            growingRatio[i] = Ratio;
        }
    }

    public Stem[] Branch(Stem stem) {
        Stem[] branchStems;
        branchStems = new Stem[branch];
        for (int i = 0; i < branch; i++) {
            Vector2 branchVector = stem.getAB().Rotate(branchAngle[i], growingRatio[i]);
            branchStems[i] = new Stem(stem.getB(), branchVector);
        }
        return branchStems;
    }

    public int getLayer() {
        return layer;
    }

}
