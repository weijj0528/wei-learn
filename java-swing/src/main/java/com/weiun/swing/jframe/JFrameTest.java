package com.weiun.swing.jframe;

import javax.swing.*;
import java.awt.*;

public class JFrameTest {

    public static void main(String[] args) {
        // 简单示例
        JFrame jFrame = new JFrame();
        jFrame.setTitle("JFrameTest");
        jFrame.getContentPane().add(new JButton("Press me"), BorderLayout.CENTER);
        System.out.println(jFrame.getLayout());
        JPanel jPanel = new JPanel();
        System.out.println(jPanel.getLayout());
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
