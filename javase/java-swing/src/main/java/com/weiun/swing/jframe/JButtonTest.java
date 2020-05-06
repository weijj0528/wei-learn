package com.weiun.swing.jframe;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;

public class JButtonTest {

    private static int tag = 0;

    public static void main(String[] args) throws MalformedURLException {
        // 面板示例
        JFrame jFrame = new JFrame("按钮示例");
        Panel contentPane = new Panel(new BorderLayout());
        contentPane.setSize(400, 800);
        contentPane.setBackground(Color.BLUE);
        // 上北下南 左西右东
        final JButton comp = new JButton("点我-中");
        comp.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tag == 0) {
                    comp.setText("我好");
                    tag = 1;
                } else {
                    comp.setText("你好");
                    tag = 0;
                }
            }
        });

        contentPane.add(comp, BorderLayout.CENTER);
        contentPane.add(new JButton("点我-上（北）"), BorderLayout.NORTH);
        contentPane.add(new JButton("点我-左（西）"), BorderLayout.WEST);
        contentPane.add(new JButton("点我-左（西）"), BorderLayout.EAST);
        contentPane.add(new JButton("点我-下（南）"), BorderLayout.SOUTH);
        JPanel panel = new JPanel(new FlowLayout());
        contentPane.add(panel, BorderLayout.SOUTH);


        ChangeListener listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Object source = e.getSource();
                JToggleButton button = (JToggleButton) source;
                System.out.println("stateChanged:" + button.getText() + ":" + button.isSelected());
            }
        };
        ItemListener itemlitener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object source = e.getItem();
                JToggleButton button = (JToggleButton) source;
                System.out.println("itemStateChanged:" + button.getText() + ":" + button.isSelected());
            }
        };
        for (int i = 0; i < 5; i++) {
            JToggleButton button = new JToggleButton("切换按钮" + i);
            button.addChangeListener(listener);
            button.addItemListener(itemlitener);
            panel.add(button);
        }


        jFrame.setSize(400, 300);
        jFrame.setContentPane(contentPane);
        jFrame.setVisible(true);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

}
