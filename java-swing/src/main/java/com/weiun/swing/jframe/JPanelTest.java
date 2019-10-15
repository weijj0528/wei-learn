package com.weiun.swing.jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;

public class JPanelTest {

    private static int tag = 0;

    public static void main(String[] args) throws MalformedURLException {
        // 面板示例
        JFrame jFrame = new JFrame("面板示例");
        Panel contentPane = new Panel(new BorderLayout());
        contentPane.setSize(400, 800);
        contentPane.setBackground(Color.BLUE);
        // 上北下南 左西右东
        final JButton comp = new JButton("点我-中");
        contentPane.add(comp, BorderLayout.CENTER);
        contentPane.add(new JButton("点我-上（北）"), BorderLayout.NORTH);
        contentPane.add(new JButton("点我-下（南）"), BorderLayout.SOUTH);
        contentPane.add(new JButton("点我-左（西）"), BorderLayout.WEST);
        contentPane.add(new JButton("点我-右（东）"), BorderLayout.EAST);

        URL location = new URL("https://gss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=190960168,1405953923&fm=173&app=49&f=JPEG?w=218&h=146&s=6A7210C5C671182D0A04BC0B03006091");
        ImageIcon icon = new ImageIcon(location);
        contentPane.add(new JLabel("标签1", icon, JLabel.CENTER), BorderLayout.NORTH);
        contentPane.add(new JLabel("标签2", icon, JLabel.CENTER), BorderLayout.CENTER);
        contentPane.add(new JLabel("标签3", icon, JLabel.CENTER), BorderLayout.SOUTH);


        JScrollPane jScrollPane = new JScrollPane(contentPane);
        jScrollPane.setSize(400, 300);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        jScrollPane.setVerticalScrollBar(new JScrollBar(Scrollbar.VERTICAL, 10, 5, 0, 100));

        jFrame.setSize(400, 300);
        jFrame.setContentPane(jScrollPane);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
