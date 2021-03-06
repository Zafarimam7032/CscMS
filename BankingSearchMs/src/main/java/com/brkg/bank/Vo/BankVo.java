package com.brkg.bank.Vo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.brkg.pincode.Vo.PincodeVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Bank_Info")
public class BankVo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Bank_id")
	@JsonProperty(value = "Bank_id")
	private long id;
	@Column(name = "Bank_Name")
	@Min(value = 10, message = "bank name should be greater the 10 charector")
	@Max(value = 25, message = "bank name should be lesser the 25 charector")
	@JsonProperty(value = "Bank_Name")
	private String name;
	@Column(name = "Bank_Address")
	@Min(value = 10, message = "bank address should be greater the 10 charector")
	@Max(value = 60, message = "bank adress should be lesser the 25 charector")
	@JsonProperty(value = "Bank_Address")
	private String address;
	@NotEmpty
	@Column(name = "ifscCode", length = 11)
	private String ifscCode;
	@NotEmpty
	@JsonFormat(pattern = "HH:mm:ss")
	private String opeingTime;
	@NotEmpty
	@JsonFormat(pattern = "HH:mm:ss")
	private String closingTime;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = PincodeVo.class)
	private PincodeVo pincodeVo;

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public PincodeVo getPincodeVo() {
		return pincodeVo;
	}

	public void setPincodeVo(PincodeVo pincodeVo) {
		this.pincodeVo = pincodeVo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getOpeingTime() {
		return opeingTime;
	}

	public void setOpeingTime(String opeingTime) {
		this.opeingTime = opeingTime;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public BankVo(long id, String name, String address, String opeingTime, String closingTime) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.opeingTime = opeingTime;
		this.closingTime = closingTime;

	}

	public BankVo(long id,
			@Min(value = 10, message = "bank name should be greater the 10 charector") @Max(value = 25, message = "bank name should be lesser the 25 charector") String name,
			@Min(value = 10, message = "bank address should be greater the 10 charector") @Max(value = 60, message = "bank adress should be lesser the 25 charector") String address,
			@NotEmpty String ifscCode, @NotEmpty String opeingTime, @NotEmpty String closingTime, PincodeVo pincodeVo) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.ifscCode = ifscCode;
		this.opeingTime = opeingTime;
		this.closingTime = closingTime;
		this.pincodeVo = pincodeVo;
	}

	public BankVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BankVo [id=" + id + ", name=" + name + ", address=" + address + ", opeingTime=" + opeingTime
				+ ", closingTime=" + closingTime + "]";
	}

}
