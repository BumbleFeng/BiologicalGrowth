package edu.neu.csye6200.bg;

/**
 *
 * @author BumbleBee
 */
public class BGGenerationSet {
    private BGGeneration[] bgg;
    
    public BGGenerationSet(BGRule[] bgrs){
        bgg = new BGGeneration[bgrs.length];
        for (int i = 0; i < bgrs.length; i++) {
            bgg[i] = new BGGeneration(bgrs[i]);
        }
    }

    public BGGeneration[] getBgg() {
        return bgg;
    }
    
    public int maxNum(){
        int num[] = new int [bgg.length];
        int max = 0;
        for (int i = 0; i < bgg.length; i++) {
            num[i] = bgg[i].stemNum();
            if (max < num[i]) max = num[i];
        }
        return max;
    }
    
}
