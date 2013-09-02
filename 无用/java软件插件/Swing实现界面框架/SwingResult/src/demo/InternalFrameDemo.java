/*
 * 系统名称：
 * 模块名称：
 * 描述：
 * 作者：徐骏
 * version 1.0
 * time  2010-8-3 上午10:18:10
 * copyright Anymusic Ltd.
 */
package demo;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InternalFrameDemo extends JFrame {

    public static void main(String[] args) {
    	InternalFrameDemo test = new InternalFrameDemo();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setSize(500, 400);
        test.setLocationRelativeTo(null);
        test.setVisible(true);
    }

    public InternalFrameDemo() {
        JPanel panel = new JPanel();
        for (int i = 0; i < 10; i++) {
            JButton button = new JButton(i + "");
            panel.add(button);
        }
        this.setContentPane(panel);

        JPanel controlPanel = createControlPanel(this.getContentPane());
        JInternalFrame frame = new JInternalFrame();
        frame.getContentPane().add(controlPanel);
        frame.putClientProperty("JInternalFrame.isPalette", Boolean.valueOf(true));
        frame.setTitle("control");
        frame.pack();
        frame.setLocation(30, 30);
        frame.setVisible(true);
        this.getLayeredPane().add(frame, 0);
    }

    public JPanel createControlPanel(final Container parent) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 2, 2));
        final Color color = parent.getBackground();
        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.add(new JLabel("r"));
        final JSlider sliderRed = new JSlider(0, 255);
        sliderRed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                parent.setBackground(new Color(sliderRed.getValue(), color.getGreen(), color.getBlue()));
            }
        });
        sliderRed.setValue(color.getRed());
        panel1.add(sliderRed);

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(new JLabel("g"));
        final JSlider sliderGreen = new JSlider(0, 255);
        sliderGreen.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                parent.setBackground(new Color(color.getRed(), sliderGreen.getValue(), color.getBlue()));
            }
        });
        sliderGreen.setValue(color.getGreen());
        panel2.add(sliderGreen);

        JPanel panel3 = new JPanel(new FlowLayout());
        panel3.add(new JLabel("b"));
        final JSlider sliderBlue = new JSlider(0, 255);
        sliderBlue.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                parent.setBackground(new Color(color.getRed(), color.getGreen(), sliderBlue.getValue()));
            }
        });
        sliderBlue.setValue(color.getBlue());
        panel3.add(sliderBlue);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        return panel;
    }

}

