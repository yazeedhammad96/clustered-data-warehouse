/**
 * 
 */
package com.progresssoft.cdw.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.progresssoft.cdw.enums.ISOCurrencyCode;

/**
 * @author Yazeed
 *
 */
@Entity
@Table(name = "valid_deal")
public class ValidDeal extends BaseEntity {

	private static final long serialVersionUID = 1428982116594321272L;

	@Column(nullable = false)
	private String dealId;

	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private ISOCurrencyCode fromCurrencyISOCode;

	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private ISOCurrencyCode toCurrencyISOCode;

	@Column(nullable = false)
	private Timestamp dealTimestamp;

	@Column(nullable = false)
	private Double dealAmountInOrderingCurrency;

	@Column(nullable = false)
	private String sourceFile;

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public ISOCurrencyCode getFromCurrencyISOCode() {
		return fromCurrencyISOCode;
	}

	public void setFromCurrencyISOCode(ISOCurrencyCode fromCurrencyISOCode) {
		this.fromCurrencyISOCode = fromCurrencyISOCode;
	}

	public ISOCurrencyCode getToCurrencyISOCode() {
		return toCurrencyISOCode;
	}

	public void setToCurrencyISOCode(ISOCurrencyCode toCurrencyISOCode) {
		this.toCurrencyISOCode = toCurrencyISOCode;
	}

	public Timestamp getDealTimestamp() {
		return dealTimestamp;
	}

	public void setDealTimestamp(Timestamp dealTimestamp) {
		this.dealTimestamp = dealTimestamp;
	}

	public Double getDealAmountInOrderingCurrency() {
		return dealAmountInOrderingCurrency;
	}

	public void setDealAmountInOrderingCurrency(Double dealAmountInOrderingCurrency) {
		this.dealAmountInOrderingCurrency = dealAmountInOrderingCurrency;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

}
