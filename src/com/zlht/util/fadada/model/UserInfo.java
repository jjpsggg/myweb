package com.zlht.util.fadada.model;

// default package

/**
 * <h3>概要:</h3> p2p用户信息 <br>
 * <h3>功能:</h3>
 * <ol>
 * <li>TODO(这里用一句话描述功能点)</li>
 * </ol>
 * <h3>履历:</h3>
 * <ol>
 * <li>2015-5-5[cbz] 新建</li>
 * </ol>
 */
public class UserInfo implements java.io.Serializable {

	/** TODO 一句话描述字段含义 */
	private static final long serialVersionUID = 1L;
	/** 邮箱 */
	private String email;
	/** 客户姓名 */
	private String customerName;
	/** 客户编号 */
	private String customerId;
	/** 密码 */
	private String userPwd;
	/** 身份证号 **/
	private String idCard;
	/** 手机号 **/
	private String mobile;

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 概要：Account类的构造函数
	 */
	public UserInfo() {
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}