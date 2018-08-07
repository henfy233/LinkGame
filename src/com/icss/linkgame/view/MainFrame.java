package com.icss.linkgame.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.icss.linkgame.data.*;

/**
 * 显示游戏界面
 * 
 * @author Henhui Lu
 *
 */
public class MainFrame extends JFrame {
	final int width = 600; // 设置窗口宽度
	final int height = 600; // 设置窗口高度
	int leftBlock = GameData.rows * GameData.cols; // 获取总图片数
	JButton button1 = null; // 创建按钮对象
	JButton button2 = null; // 创建按钮对象
    Timer gameTimer;
	GameFile gameFile = new GameFile();
	final GameData gameData = new GameData();
	// 生成多个按钮
	JButton[][] buttons = new JButton[GameData.rows][GameData.cols];

	// 分数
	JLabel lblScroe = new JLabel();
	int score ;//gameFile.readScore()
	// 时间
	final int timeLimit = 20;
	int timeLeft = timeLimit;

	public MainFrame() {
		// 获取屏幕大小
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // 屏幕分辨率
		this.setTitle("动物连连看"); // 设置标题
//		this.resize(width + 7, height + 54);
		this.setBounds(1, 1, width + 17, height + 38); // 设置大小
		this.setLocation((screenSize.width - width) / 2,
				(screenSize.height - height) / 2); // 居中显示
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭模式
		this.setResizable(false); // 设置不允许拉伸窗口

		// 添加菜单栏
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar); // 设置菜单栏
		JMenu menu1 = new JMenu("文件");
		JMenuItem mi1 = new JMenuItem("重新开始");
		JMenuItem mi3 = new JMenuItem("退出");
		menubar.add(menu1); // 添加菜单栏
		menu1.add(mi1);
		menu1.add(mi3);
		
		/**
		 * 设置菜单栏监听事件
		 */
		mi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("重新开始");
				gameFile.saveScore(String.valueOf(0));
				setVisible(false);
				new MainFrame().setVisible(true);
				dispose();
			}
		});
		mi3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("退出");
				gameFile.saveScore(String.valueOf(0));
				System.exit(0);
			}
		});
		// 设置分数
		if(gameFile.readScore()>0){
			setScore(gameFile.readScore()); // （继续游戏时分数可增加）
		}else{
			setScore(0);
		}
		
		initPane(); // 设置分数
		initButton(); // 初始化按钮
		showImage(); // 显示图片
		initTimePane(); // 时间处理
	}
	/**
	 * 设置分数
	 * @param score
	 */
	private void setScore(int score) {
		this.score = score;
	}
	/**
	 * 面板，显示在窗口上方
	 */
	private void initPane() {
		JPanel scorePane = new JPanel(new FlowLayout()); // 设置成流体布局
		scorePane.add(new JLabel(UserEmpty.username));
		scorePane.add(new JLabel("    "));
		scorePane.add(new JLabel("得分："));
		lblScroe.setText("" + score);
		
		JButton btnResort = new JButton("重排");
		JButton btnReplay = new JButton("新游戏");
		btnResort.setContentAreaFilled(false);
		btnResort.setBorderPainted(false); 

		/**
		 * 对"重排"按钮添加监听事件
		 */
		btnResort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameData.shuffleData(); // 打乱顺序
				showImage(); // 显示图片
				timeLeft=timeLeft-10; // 时间减少10s
//				initTimePane();
			}
		});
		/**
		 * 对"新游戏"按钮添加监听事件
		 */
		btnReplay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("新游戏");
				gameFile.saveScore(String.valueOf(0));
				setVisible(false);
				new MainFrame().setVisible(true);
				dispose();
			}
		});
		scorePane.add(lblScroe);
		scorePane.add(btnResort);
		scorePane.add(btnReplay);
		this.getContentPane().add(scorePane, BorderLayout.NORTH); // 添加此在面板上面
	}
	/**
	 * 初始化面板上面的按钮。
	 */
	public void initButton() {
		JPanel gamePanel = new JPanel(new GridLayout(GameData.rows,
				GameData.cols)); // 设置成网格布局
		for (int i = 0; i < GameData.rows; ++i)
			for (int j = 0; j < GameData.cols ; ++j) {
				buttons[i][j] = new JButton(); // 生成按钮
				/**
				 * 对每个按钮设置监听事件
				 */
				buttons[i][j].addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// 判断哪个按钮被点击
						JButton button = (JButton) arg0.getSource();
						// 如果button1 没有记录，即点击第一个按钮
						if (button1 == null) {
							button1 = button; // 记录第一个按钮
							button1.setBackground(Color.BLUE); // 记录第一个按钮的底色为蓝色
						} else {
							// 否则，如果点击的是第二个按钮，则两个按钮都隐藏
							button2 = button; // 记录第二个按钮
							button2.setBackground(Color.BLUE); // 记录第二个按钮的底色为蓝色
							// 记录第一个按钮的行数
							int row1 = (button1.getY() / button1.getHeight()) + 1; 
							// 记录第一个按钮的列数
							int col1 = (button1.getX() / button1.getWidth()) + 1;
							// 记录第二个按钮的行数
							int row2 = (button2.getY() / button2.getHeight()) + 1;
							// 记录第二个按钮的列数
							int col2 = (button2.getX() / button2.getWidth()) + 1;
							// 输出两个按钮的位置 以及 相应的data值
							System.out.println(row1 + " " + col1);
							System.out.println(row2 + " " + col2);
							for (int k = 0; k < GameData.rows + 2; ++k) {
								for (int l = 0; l < GameData.cols + 2; ++l) {
									System.out.print(gameData.data[k][l] + " ");
								}
								System.out.println();
							}
							// 判断能否相连
							if (GameRule.isConnect(gameData.data, row1, col1, row2, col2)) {
								// 设置两个按钮不可见
								button1.setVisible(false);
								button2.setVisible(false);
								// 设置两个按钮的 data值为0
								gameData.data[row1][col1] = 0;
								gameData.data[row2][col2] = 0;
								// 每消除一对方块数减少2
								leftBlock -= 2;
								//---加分处理，每消除一对就加10分
								score += 10;
								lblScroe.setText("" + score);
								// 每消除一对方时间增加5s
								timeLeft += 5;
								if (timeLeft > timeLimit) {
									timeLeft = timeLimit;
								}
								// 当消除完毕，则弹出提示框
								if (leftBlock == 0) {
									System.out.println("winner");
									gameTimer.stop();
									// 保存当前的分数到score.txt
									gameFile.saveScore(String.valueOf(score));
									// 设置提示框
									Component jPanel = null;
									Object[] options = { " 继续游戏 ", " 结束游戏" };
									int response = JOptionPane.showOptionDialog(null,
											"你还想获得更高分吗？", "继续游戏", JOptionPane.YES_OPTION,
											JOptionPane.QUESTION_MESSAGE, null, options,
											options[0]);
									if (response == 0) { // 点击 继续游戏
										setVisible(false);
										new MainFrame().setVisible(true);
										score = gameFile.readScore(); // 获取当前的分数
										dispose();
									}else if (response == 1) { // 点击 结束游戏
										gameFile.saveScore(String.valueOf(0)); // 把分数清零
										System.exit(0); // 退出游戏
									}
								}
							}
							// 还原两个按钮为空
							button1.setBackground(Color.WHITE);
							button2.setBackground(Color.WHITE);
							button1 = null;
							button2 = null;
						}
					}
				});
				gamePanel.add(buttons[i][j]);
				// 居中显示
				this.getContentPane().add(gamePanel, BorderLayout.CENTER);
			}
	}
	/**
	 * 显示图片
	 */
	private void showImage() {
		for (int i = 0; i < GameData.rows ; ++i) {
			for (int j = 0; j < GameData.cols ; ++j) {
				buttons[i][j].setVisible(true);
//				String path = this.getClass().getResource("/image").getPath();
				// 获取图片路径
				ImageIcon icon = new ImageIcon("src/images/" + gameData.data[i+1][j+1] + ".png");
//				System.out.println(icon);
				// 设置图片大小
				icon.setImage(icon.getImage().getScaledInstance(
						60, 60,
						Image.SCALE_DEFAULT));
				buttons[i][j].setIcon(icon);
				// 如果data为零，按钮不显示
				if (gameData.data[i+1][j+1] == 0) {
					buttons[i][j].setVisible(false);
				}
			}
		}
	}
	/**
	 * 时间处理
	 */
	private void initTimePane() {
		// 创建进度条面板对象
		JPanel timePane = new JPanel(new BorderLayout());
		// 创建进度条对象
		final JProgressBar timeLine = new JProgressBar();
		timeLine.setStringPainted(true);
		timeLine.setMaximum(timeLimit); // 设置进度条最大值
		timeLine.setValue(timeLimit); // 设置进度条当前值
		timeLine.setForeground(new Color(112, 34, 141));
		timePane.add(timeLine); // 添加进度条到timePane面板
		this.getContentPane().add(timePane, BorderLayout.SOUTH); // 添加timePane面板到容器
		/**
		 * 每过1s，运行以下函数
		 */
		gameTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 时间过了，生成提示框 "时间到了，重新开始游戏吗？"
				if (timeLeft <= 0) {
					((Timer) arg0.getSource()).stop(); // 时间停止
					Object[] options = { " 确定 ", " 取消 " };
					int response = JOptionPane.showOptionDialog(null,
							"时间到了，重新开始游戏吗？", "游戏结束 ", JOptionPane.YES_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);

					if (response == 0) { // 确定
						gameFile.saveScore(String.valueOf(0)); // 修改记录分数文件的值为0
						setVisible(false);
						new MainFrame().setVisible(true); // 重新打开主界面
						dispose();
					}else if (response == 1) { // 取消
						gameFile.saveScore(String.valueOf(0)); // 修改记录分数文件的值为0
						System.exit(0); // 退出游戏
					}
				}
				--timeLeft; // 时间自减1
				timeLine.setValue(timeLeft); // 设置当前时间
			}
		});
		gameTimer.start();
	}
}