package com.brkg.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.brkg.bank.Vo.BankVo;

public interface BankController {
	public ResponseEntity<List<BankVo>> getAllBank();
	public ResponseEntity<List<BankVo>> getAllBankByPinCode(int pinCode);
	public ResponseEntity<BankVo> getBankInfo(String IfscCode);
	public ResponseEntity<Boolean> addBankInfo(BankVo bankVo,int pinCode);
	public ResponseEntity<Boolean> updateBankInfo(String ifscCode,BankVo bankVo);
	public ResponseEntity<Boolean> deleteBankInfo(String ifscCode);
}
