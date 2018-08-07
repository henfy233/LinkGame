package com.icss.linkgame.data;

import java.util.Random;
/**
 * 数据类，只负责数据。
 * @author Henghui Lu
 *
 */
public class GameData {
	public static int rows = 6; // 连连看的行数
	public static int cols = 6; // 连连看的列数
	
	public int[][] data = new int[rows+2][cols+2]; // 设置data数组大小
	public int length = rows*cols; // 计算data长度
	public GameData(){
		// 成对产生数据
		initData();
		// 打乱顺序
		shuffleData();
	}
	/**
	 * 打乱data数组中的数据
	 */
	public void shuffleData() {
		// 生成random对象
		Random random = new Random();
		for(int i=0;i<length;++i){
			// 随机得到两个坐标
			int row1 = random.nextInt(rows)+1;
			int col1 = random.nextInt(cols)+1;
			int row2 = random.nextInt(rows)+1;
			int col2 = random.nextInt(cols)+1;
			// 交换这两个坐标处的数
			int temp = data[row1][col1];
			data[row1][col1] = data[row2][col2];
			data[row2][col2] = temp;
		}
	}
	/**
	 * 成对生成数据，存入data[][]
	 */
	public void initData() {
		for(int i=1;i<rows+1;++i){
			for(int j=1;j<cols+1;++j){
				// 计算每个data数组的值
				data[i][j] = (4*(i-1)+(j-1))/(length/8) + 1;
//				System.out.println(data[i][j]+" ");
			}
//			System.out.println();
		}
	}
}
