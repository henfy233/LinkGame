package com.icss.linkgame.data;

/**
 * 游戏规则
 * @author Henghui Lu
 *
 */
public class GameRule {
	/**
	 * 能否相连
	 * @param data
	 * @param row1
	 * @param col1
	 * @param row2
	 * @param col2
	 * @return
	 */
	public static boolean isConnect(int[][] data,int row1,int col1,int row2,int col2){
		int index1 = (row1-1)*GameData.cols+col1; // 计算第一个按钮的索引
		int index2 = (row2-1)*GameData.cols+col2; // 计算第二个按钮的索引
		System.out.println("显示index:"+index1+" "+index2);
		
		// 如果点击同一个按钮，不能相连
		if(index1==index2){
			System.out.println("点击同一个按钮，不能相连");
			return false;
		}
		System.out.println(data[row1][col1]+" "+data[row2][col2]);
		
		// 如果点击不是相同的图片，不能相连
		if(data[row1][col1] != data[row2][col2]){
			System.out.println("点击不是相同的图片，不能相连");
			return false;
		}
		
//		int row1 = index1/GameData.cols; // 获取第一个按钮的行数
//		int col1 = index1%GameData.cols; // 获取第一个按钮的列数
//		int row2 = index2/GameData.cols; // 获取第二个按钮的行数
//		int col2 = index2%GameData.cols; // 获取第二个按钮的列数
		
		// 直线相连
		if(connect0(data,row1 ,col1,row2,col2)){
			System.out.println("直线相连");
			return true;
		}
		
		// 一拐角相连
		if(connect1(data,row1 ,col1,row2,col2)){
			System.out.println("一拐角相连");
			return true;
		}
		
		// 二拐角相连
		if(connect2(data,row1 ,col1,row2,col2)){
			System.out.println("二拐角相连");
			return true;
		}
		return false;
	}
	
	// 一拐角相连
	private static boolean connect1(int[][] data, int row1, int col1, int row2, int col2) {
		//上角 在边角的两条直线都能直线相连
//		int index1 = row1*GameData.cols + col2;
		if(canArrived(data,row1,col1,row1,col2) && canArrived(data,row1,col2,row2,col2) && data[row1][col2]==0){
			return true;
		}
		//下角 在边角的两条直线都能直线相连
//		int index2 = row2*GameData.cols + col1;
		if(canArrived(data,row1,col1,row2,col1) && canArrived(data,row2,col1,row2,col2)&& data[row2][col1]==0){
			return true;
		}
		return false;
	}
	// 二拐角相连
	private static boolean connect2(int[][] data, int row1, int col1, int row2, int col2) {
		// for 循环找点 若能直线相连 以及 一拐角相连
		for(int col = 0;col<GameData.rows+2;col++){
//			int index = row1*GameData.cols + col;
			if(canArrived(data,row1,col1,row1,col)&& data[row1][col]==0 &&connect1(data,row1,col,row2,col2)){
				return true;
			}
		}
		for(int row = 0;row<GameData.cols+2;row++){
//			int index = row*GameData.cols + col1;
			if(canArrived(data,row1,col1,row,col1)&& data[row][col1]==0 &&connect1(data,row,col1,row2,col2)){
				return true;
			}
		}
		return false;
	}
	// 直线相连
	private static boolean connect0(int[][] data, int row1, int col1, int row2, int col2) {
		if(canArrived(data,row1,col1,row2,col2)){
			return true;
		}else{
			return false;
		}
	}
	// 能否在直线相连
	public static boolean canArrived(int[][] data, int row1, int col1, int row2, int col2) {
		// 同一行
		if(row1 == row2){
			int row = row1;
			int sum = 0;
			if(col1 > col2){  // col1 必须小于 col2
				int t = col1;
				col1 = col2;
				col2 = t;
			}
			for(int col = col1 +1 ; col < col2 ; ++col){
//				int index = row*GameData.cols + col;
//						System.out.println("index:"+index);
				sum += data[row][col]; // 计算两个坐标之间的data值
			}
//					System.out.println(sum);
			if(sum == 0){ // 两个坐标之间都消除了
				return true; // 能够相连
			}
		}
		// 同一列
		else if(col1 == col2){
			int col = col1;
			int sum = 0;
			if(row1 > row2){  // row1 必须小于row2
				int t = row1;
				row1 = row2;
				row2 = t;
			}
			for(int row = row1 +1; row < row2 ; ++row){
//				int index = row*GameData.cols + col;
//						System.out.println("index:"+index);
				sum += data[row][col]; // 计算两个坐标之间的data值
			}
//					System.out.println(sum);
			if(sum == 0){ // 两个坐标之间都消除了
				return true; // 能够相连
			}
		}
		return false;
	}
	// 是否全部消除完
//	private static boolean isClear(int[] data){
//		int sum = 0;
//		for(int i =0;i < data.length;i++){
//			sum+=data[i];
//		}
//		if(sum==0){
//			return true;
//		}else{
//			return false;
//		}
//	}
}
