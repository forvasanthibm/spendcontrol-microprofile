package com.ibm.spendcontrol.microprofile.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "email", "spendDate", "spendType", "category", "vendor", "invoiceAmount" })
public class Spend {

	@JsonProperty("id")
	private String id;
	@JsonProperty("email")
	private String email;
	@JsonProperty("spendDate")
	private Date spendDate;
	@JsonProperty("spendType")
	private String spendType;
	@JsonProperty("category")
	private String category;
	@JsonProperty("vendor")
	private String vendor;
	@JsonProperty("invoiceAmount")
	private Integer invoiceAmount;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("spendDate")
	public Date getSpendDate() {
		return spendDate;
	}

	@JsonProperty("spendDate")
	public void setSpendDate(Date spendDate) {
		this.spendDate = spendDate;
	}

	@JsonProperty("spendType")
	public String getSpendType() {
		return spendType;
	}

	@JsonProperty("spendType")
	public void setSpendType(String spendType) {
		this.spendType = spendType;
	}

	@JsonProperty("category")
	public String getCategory() {
		return category;
	}

	@JsonProperty("category")
	public void setCategory(String category) {
		this.category = category;
	}

	@JsonProperty("vendor")
	public String getVendor() {
		return vendor;
	}

	@JsonProperty("vendor")
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	@JsonProperty("invoiceAmount")
	public Integer getInvoiceAmount() {
		return invoiceAmount;
	}

	@JsonProperty("invoiceAmount")
	public void setInvoiceAmount(Integer invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}