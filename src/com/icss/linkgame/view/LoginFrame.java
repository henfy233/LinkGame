package com.icss.linkgame.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.icss.linkgame.data.UserEmpty;
/**
 * 显示登录界面
 * @author Henghui Lu
 *
 */
public class LoginFrame extends JFrame {
	private static final int height = 200; // 设置窗口高度
	private static final int width = 350; // 设置窗口宽度
	
	private JTextField username = new JTextField("小哥哥"); 
//	private JPasswordField password = new JPasswordField();
	
	public LoginFrame() {
		// 获取屏幕大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(1, 1, width + 17, height + 38); 	// 设置大小
		this.setLocation((screenSize.width - width) / 2,
				(screenSize.height - height) / 2); // 居中显示
		this.setTitle("登录界面"); // 设置程序名字
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭按钮
		initPane(); // 初始化登录面板
		
	}
	/**
	 * 初始化登录面板
	 */
	private void initPane() {
		final JPanel fieldPanel = new JPanel(); // 生成panel对象
		fieldPanel.setLayout(null); // 设置空布局
		
		JLabel a1 = new JLabel("你的名字:");
		a1.setBounds(70,70,100,30); // 设置位置以及大小
//		JLabel a2 = new JLabel("密  码:");
//		a2.setBounds(80,90,50,20);
		fieldPanel.add(a1); // 添加按钮
//		fieldPanel.add(a2);
		
		username.setBounds(140, 70, 120, 30); // 设置位置以及大小
//		password.setBounds(130, 90, 120, 20);
		fieldPanel.add(username); // 添加文本框
//		fieldPanel.add(password);
		
		JButton jbu1= new JButton("开始游戏"); // 生成确定按钮
		JButton jbu2= new JButton("退出游戏"); // 生成取消按钮
		jbu1.setBounds(80, 120, 100, 30); // 设置位置以及大小
		jbu2.setBounds(200, 120, 100, 30); // 设置位置以及大小
		fieldPanel.add(jbu1); // 添加按钮
		fieldPanel.add(jbu2); // 添加按钮
		/**
		 * 点击“确定”按钮
		 */
		jbu1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = username.getText(); //获取文本框的文本
//				String pwd = String.valueOf(password.getPassword());
				if(!"".equals(name)){   //&&!"".equals(pwd) 文本不能为空
//					System.out.println(name);
//					System.out.println(pwd);
					setVisible(false);	
					UserEmpty.username=name; // 设置名字
					MainFrame mainFrame = new MainFrame(); // 打开主界面
					mainFrame.setVisible(true); //设置可见
					dispose(); // 当前界面消失
				}else{
					setVisible(false);	
					UserEmpty.username="小哥哥"; // 设置名字
					MainFrame mainFrame = new MainFrame(); // 打开主界面
					mainFrame.setVisible(true); //设置可见
					dispose(); // 当前界面消失
				}
				
			}
		});
		/**
		 * 点击"取消"按钮
		 */
		jbu2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0); // 退出游戏
			}
		});
		this.getContentPane().add(fieldPanel, BorderLayout.CENTER); // 居中显示窗口
	}
}
