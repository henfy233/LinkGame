package com.icss.linkgame.data;

/**
 * 设置用户信息
 * @author Henghui Lu
 *
 */
public class UserEmpty {
	private int id;
	public static String username; // 设置用户名
//	private String password;
//	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	// 获取用户名
	public String getUsername() {
		return username; 
	}
	// 设置用户名
	public void setUsername(String username) {
		this.username = username;
	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}

}
