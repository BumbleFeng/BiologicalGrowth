package edu.neu.csye6200.bg;

import java.util.ArrayList;

/**
 *
 * @author BumbleBee
 */
public class BGStem {

    private ArrayList<Stem> layerStems;

    public BGStem() {
        layerStems = new ArrayList<Stem>();
    }

    public BGStem Generation(BGRule bgr) {
        BGStem bgs = new BGStem();
        for (Stem ls : this.layerStems) {
            Stem[] bStems = bgr.Branch(ls);
            for (Stem bs : bStems) {
                bgs.layerStems.add(bs);
            }
        }
        return bgs;
    }

    public ArrayList<Stem> getLayerStems() {
        return layerStems;
    }

}
