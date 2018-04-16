package edu.neu.csye6200.bg;

import java.awt.Point;

/**
 *
 * @author BumbleBee
 */
public class BGGeneration {
    private BGStem bgs[];
    
    public BGGeneration(BGRule bgr){
        bgs = new BGStem[bgr.getLayer()+1];
        Point O = new Point (0,0);
        Vector2 v0 = new Vector2 (0,50);
        Stem s0 = new Stem(O, v0);
        bgs[0] = new BGStem();
        bgs[0].getLayerStems().add(s0);
        for (int i = 0; i < bgs.length-1; i++) {
            bgs[i+1] = bgs[i].Generation(bgr);
        }
    }

    public BGStem[] getBgs() {
        return bgs;
    }
    
    public int stemNum(){
        int num = 0;
        for (BGStem bg : bgs) {
            num += bg.getLayerStems().size();
        }
        return num;
    }
    
}
