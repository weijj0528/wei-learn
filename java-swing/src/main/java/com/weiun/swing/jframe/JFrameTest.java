package com.weiun.swing.jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class JFrameTest {

    public static void main(String[] args) {
        // 简单示例
        JFrame jFrame = new JFrame();
        jFrame.setTitle("JFrameTest");
        System.out.println(jFrame.getLayout());
        JPanel jPanel = new JPanel();
        System.out.println(jPanel.getLayout());
        jFrame.setContentPane(jPanel);
        jPanel.add(new JButton("Press me"), BorderLayout.CENTER);
        // 组合框
        String[] strings = {"One", "Two"};
        JComboBox jComboBox = new JComboBox(strings);
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb = (JComboBox) e.getSource();
                System.out.println("actionPerformed:" + jcb.getSelectedItem());
            }
        });
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox jcb = (JComboBox) e.getSource();
                System.out.println("itemStateChanged:" + jcb.getSelectedItem());
            }
        });
        jComboBox.addItem("Three");
        jComboBox.addItem("Four");
        jComboBox.addItem("Five");
        jPanel.add(jComboBox);
//        jFrame.pack();
        jFrame.setSize(400, 300);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
