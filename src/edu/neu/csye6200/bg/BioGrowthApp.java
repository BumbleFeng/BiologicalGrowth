package edu.neu.csye6200.bg;

import edu.neu.csye6200.ui.BGApp;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author BumbleBee
 */
public class BioGrowthApp extends BGApp {

    private static Logger log = Logger.getLogger(BioGrowthApp.class.getName());
    private BGRule[] bgr;

    private JPanel mainPanel;
    private JPanel northPanel;
    private JButton startBtn;
    private JButton stopBtn;
    private BGPanel bgPanel;

    private JTextField layerTF;
    private JTextField branchTF;
    private JTextField angleTF;
    private JTextField ratioTF;
    private JTextField gapTF;

    private JComboBox comboBox;
    private int index = 0;

    private Thread t;

    public BioGrowthApp() {
        frame.setSize(800, 400);
        frame.setTitle("Biological Growth App");
        menuMgr.createDefaultActions();

        showUI();

    }

    @Override
    public JPanel getMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        growthBGRules(6, 3, 45, 0.5);

//        double angle[] = {-90,0,90};
//        double ration[] = {0.5,0.5,0.5};        
//        BGRule r0 = new BGRule(7, 3, angle, ration);
//        bgr = new BGRule[1];
//        bgr[0] = r0;
        bgPanel = new BGPanel();
        bgPanel.setBgr(bgr);
        bgPanel.newStart();
        mainPanel.add(BorderLayout.CENTER, bgPanel);
        mainPanel.add(BorderLayout.NORTH, getNorthPanel());

        return mainPanel;
    }

    public JPanel getNorthPanel() {
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());

        String label[] = {"Max Layer", "Max Branch", "Max Angle"};
        comboBox = new JComboBox(label);

        layerTF = new JTextField(2);
        layerTF.setText("6");
        branchTF = new JTextField(2);
        branchTF.setText("3");
        angleTF = new JTextField(2);
        angleTF.setText("45");
        ratioTF = new JTextField(2);
        ratioTF.setText("0.5");
        gapTF = new JTextField(2);
        gapTF.setText("5");

        northPanel.add(comboBox);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = comboBox.getSelectedIndex();
            }
        });

        northPanel.add(new JLabel("Layer"));
        northPanel.add(layerTF);
        northPanel.add(new JLabel("Branch"));
        northPanel.add(branchTF);
        northPanel.add(new JLabel("Angle"));
        northPanel.add(angleTF);
        northPanel.add(new JLabel("Ratio"));
        northPanel.add(ratioTF);
        northPanel.add(new JLabel("Angle Gap"));
        northPanel.add(gapTF);

        startBtn = new JButton("Start");
        startBtn.addActionListener(this);
        northPanel.add(startBtn);

        stopBtn = new JButton("Stop");
        stopBtn.addActionListener(this);
        northPanel.add(stopBtn);

        return northPanel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        log.info("We received an ActionEvent ");

        //Press “Stop” to pause. Than press “Start” to resume
        //While paused, press “Stop” to stop and press “Start” to start new
        if (ae.getActionCommand().equalsIgnoreCase("Start")) {

            int layer = Integer.parseInt(layerTF.getText());
            int branch = Integer.parseInt(branchTF.getText());
            double angle = Double.parseDouble(angleTF.getText());
            double ratio = Double.parseDouble(ratioTF.getText());
            double gap = Double.parseDouble(gapTF.getText());

            switch (index) {
                case 0:
                    growthBGRules(layer, branch, angle, ratio);
                    break;
                case 1:
                    branchBGRule(layer, branch, angle, ratio);
                    break;
                case 2:
                    angelBGRules(layer, branch, angle, ratio, gap);
                    break;
                default:
                    break;
            }

            if (!bgPanel.isSuspended() | bgPanel.isStoped()) {
                bgPanel.stop();
                bgPanel.newStart();
                bgPanel.setBgr(bgr);
                t = new Thread(bgPanel);
                t.start();
            } else {
                bgPanel.resume();
            }
        } else if (ae.getActionCommand().equalsIgnoreCase("Stop")) {
            if (!bgPanel.isSuspended()) {
                bgPanel.suspend();
            } else {
                bgPanel.stop();
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        log.info("Window opened");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        log.info("Window closing");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        log.info("Window closed");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        log.info("Window iconified");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        log.info("Window deiconified");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        log.info("Window activated");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        log.info("Window deactivated");
    }

    public void growthBGRules(int Layer, int Branch, double Angle, double Ratio) {
        bgr = new BGRule[Layer];
        for (int i = 0; i < Layer; i++) {
            bgr[i] = new BGRule(i, Branch, Angle, Ratio);
        }
    }

    public void branchBGRule(int Layer, int Branch, double Angle, double Ratio) {
        bgr = new BGRule[Branch + 1];
        for (int i = 0; i < Branch + 1; i++) {
            bgr[i] = new BGRule(Layer, i, Angle, Ratio);
        }
    }

    public void angelBGRules(int Layer, int Branch, double Angle, double Ratio, double Gap) {
        int a = (int) (Angle / Gap);
        bgr = new BGRule[a];
        for (int i = 0; i < a; i++) {
            bgr[i] = new BGRule(Layer, Branch, Angle - i * Gap, Ratio);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BioGrowthApp bga = new BioGrowthApp();
        log.info("BioGrowthApp started");
    }
}
