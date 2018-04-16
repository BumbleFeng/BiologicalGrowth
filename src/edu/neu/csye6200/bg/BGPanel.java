package edu.neu.csye6200.bg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author BumbleBee
 */
public class BGPanel extends JPanel implements Runnable{
    
    private Logger log = Logger.getLogger(BGPanel.class.getName());
    private BGRule[] bgr;
    private BGGenerationSet bggs;
    private int ctr;
    private int max;
    private boolean suspended = false;
    private boolean stoped = false;
    
    public BGPanel() {
    }
    
    public void paint(Graphics g) {
	super.paint(g);
        drawBG(g);
    }
    
    public void drawBG(Graphics g) {
        //log.info("Drawing BG");
	Graphics2D g2d = (Graphics2D) g;
	//Dimension size = getSize(); 
        //g2d.setColor(Color.WHITE);
	//g2d.fillRect(0, 0, size.width, size.height);
        
        bggs = new BGGenerationSet(bgr); 
        max = bggs.maxNum();
        int precent =(int) (ctr / (max / 100.0));
        g2d.drawString(precent + " %", 10, 15);

                
        g2d.translate(0,200);
        
        for(BGGeneration c : bggs.getBgg()){
            int i = 0;
            g2d.translate(100, 0);
            for(BGStem b : c.getBgs()){
                for(Stem a : b.getLayerStems()) {
                    g2d.drawLine(-a.getA().x, -a.getA().y, -a.getB().x, -a.getB().y);
                    i++;
                    if(i == ctr) break;
                }
                if (i == ctr) break;
            }
        }
    }

    public void setBgr(BGRule[] bgr) {
        this.bgr = bgr;
    }
    
    synchronized void stop(){
        stoped = true;
        notify();
    }
    
    synchronized void suspend(){
        suspended = true;
    }
    
    synchronized void resume(){
        suspended = false;
        notify();
    }

    public boolean isSuspended() {
        return suspended;
    }

    public boolean isStoped() {
        return stoped;
    }
    
    public void newStart(){
        suspended = false;
        stoped = false;
    }
    
    
    @Override
    public void run() {
        try{
            for (ctr = 1; ctr <= max; ctr++) {
                repaint();
                Thread.sleep(50);
                synchronized(this){
                    while(suspended){
                        wait();
                    }
                    if(stoped) break;
                }    
            }
            this.stop();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}
