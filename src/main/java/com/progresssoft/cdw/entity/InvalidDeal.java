/**
 * 
 */
package com.progresssoft.cdw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Yazeed
 *
 */
@Entity
@Table(name = "not_valid_deal")
public class InvalidDeal extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7558708235167268045L;

	@Column(nullable = true)
	private String dealId;

	@Column(nullable = true)
	private String fromCurrencyISOCode;

	@Column(nullable = true)
	private String toCurrencyISOCode;

	@Column(nullable = true)
	private String dealTimestamp;

	@Column(nullable = true)
	private String dealAmountInOrderingCurrency;

	@Column(nullable = false)
	private String sourceFile;

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getFromCurrencyISOCode() {
		return fromCurrencyISOCode;
	}

	public void setFromCurrencyISOCode(String fromCurrencyISOCode) {
		this.fromCurrencyISOCode = fromCurrencyISOCode;
	}

	public String getToCurrencyISOCode() {
		return toCurrencyISOCode;
	}

	public void setToCurrencyISOCode(String toCurrencyISOCode) {
		this.toCurrencyISOCode = toCurrencyISOCode;
	}

	public String getDealTimestamp() {
		return dealTimestamp;
	}

	public void setDealTimestamp(String dealTimestamp) {
		this.dealTimestamp = dealTimestamp;
	}

	public String getDealAmountInOrderingCurrency() {
		return dealAmountInOrderingCurrency;
	}

	public void setDealAmountInOrderingCurrency(String dealAmountInOrderingCurrency) {
		this.dealAmountInOrderingCurrency = dealAmountInOrderingCurrency;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	
	
}
