package com.zlht.util.fadada.model;

import java.io.Serializable;

/**
 * 
 * ClassName: BatchSignData 
 * @Description: 批量签署请求数据
 * @author WUH
 * @date 2016-7-8
 */
public class BatchSignData implements Serializable{
	private static final long serialVersionUID = -2301014091312374285L;
	
	/** 客户编号*/
	private String customerId;
	
	/** 交易号*/
	private String transactionId;
	
	/** 合同编号*/
	private String contractId;
	
	/** 签章关键字*/
	private String signKeyword;
	
	private String clientType;
	
	private String clientRole;
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getSignKeyword() {
		return signKeyword;
	}
	public void setSignKeyword(String signKeyword) {
		this.signKeyword = signKeyword;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getClientRole() {
		return clientRole;
	}
	public void setClientRole(String clientRole) {
		this.clientRole = clientRole;
	}
	
}
