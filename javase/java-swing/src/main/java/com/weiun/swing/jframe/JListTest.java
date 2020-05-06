package com.weiun.swing.jframe;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListTest {

    public static void main(String[] args) {
        // 简单示例
        JFrame jFrame = new JFrame();
        jFrame.setTitle("JListTest");
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < 100; i++) {
            listModel.addElement("Item:" + i);
        }
        JList jList = new JList(listModel);
        jList.setVisibleRowCount(5);
        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList jl = (JList) e.getSource();
                System.out.println(jl.getSelectedValue());
            }
        });
        JScrollPane jsp = new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jFrame.setContentPane(jsp);
        jFrame.setSize(400, 300);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
