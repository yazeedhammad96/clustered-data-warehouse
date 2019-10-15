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
@Table(name = "accumulative_deals_of_currency")
public class AccumulativeDealsOfCurrency extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6669569165412583932L;

	@Column(nullable = false, unique = true)
	private String currencyISOCode;
	@Column(nullable = false)
	private Double amountOfDeals;

	public String getCurrencyISOCode() {
		return currencyISOCode;
	}

	public void setCurrencyISOCode(String currencyISOCode) {
		this.currencyISOCode = currencyISOCode;
	}

	public Double getAmountOfDeals() {
		return amountOfDeals;
	}

	public void setAmountOfDeals(Double amountOfDeals) {
		this.amountOfDeals = amountOfDeals;
	}
	
}
