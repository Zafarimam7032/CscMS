package com.brkg.pincode.Vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brkg.bank.Vo.BankVo;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "Bank_Code")
public class PincodeVo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "Bank_Name",length = 6)
	@JsonProperty(value = "pincode")
	private int pinCode;
	@OneToMany(cascade = CascadeType.ALL,targetEntity = BankVo.class,fetch = FetchType.EAGER)
	private List<BankVo> bankVos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public List<BankVo> getBankVos() {
		return bankVos;
	}

	public void setBankVos(List<BankVo> bankVos) {
		this.bankVos = bankVos;
	}

	public PincodeVo(long id, int pinCode) {
		super();
		this.id = id;
		this.pinCode = pinCode;
	}

	public PincodeVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PincodeVo [id=" + id + ", pinCode=" + pinCode + ", bankVos=" + bankVos + "]";
	}

}
