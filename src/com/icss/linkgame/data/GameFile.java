package com.icss.linkgame.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 游戏文件
 * @author Henghui Lu
 *
 */
public class GameFile {
	// 读取数据
//	String path = this.getClass().getResource("/").getPath();
	File file = new File("src/txts/score.txt"); // 设置score.txt文件路径
	/**
	 * 读取分数
	 * @return
	 */
	public int readScore() {
		int score = 0;
		try {
			if (!file.exists()) { // score.txt文件不存在
				try {
					System.out.println(file); // 打印score.txt文件路径
					file.createNewFile(); // 生成文件score.txt
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			FileInputStream fis = new FileInputStream(file);
			System.out.println(file.length()); // 打印score.txt文件内容长度
			if (file.length() > 0) {
				byte[] buf = new byte[(int) (file.length())];
				fis.read(buf); // 读取文件中的数据存放到字节数组中
				String str = new String(buf); // 利用字节数组创建字符串
				score = Integer.parseInt(str);
			}
			fis.close(); // 关闭文件写入流
		} catch (Exception e) {
			System.out.println("文件打开失败。");
			e.printStackTrace();
		}
		return score;
	}

	/**
	 * 保存分数
	 * @param score
	 */
	public void saveScore(String score) {
		try {
			if (!file.exists()) { // score.txt文件不存在
				try {
					file.createNewFile(); // 生成文件score.txt
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			FileOutputStream fos = new FileOutputStream(file, false);

			// 逐个将字符写入到文件中
			for (int i = 0; i < score.length(); i++) {
				fos.write(score.charAt(i));
			}
			fos.close(); // 关闭文件输出流
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
