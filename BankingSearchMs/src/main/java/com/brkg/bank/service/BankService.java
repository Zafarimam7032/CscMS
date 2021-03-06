package com.brkg.bank.service;

import java.util.List;

import com.brkg.bank.Vo.BankVo;

public interface BankService {
	public List<BankVo> getAllBank();
	public List<BankVo> getAllBankByPinCode(int pinCode);
	public BankVo getBankInfo(String IfscCode);
	public boolean addBankInfo(BankVo bankVo,int pinCode);
	public boolean updateBankInfo(String ifscCode,BankVo bankVo);
	public boolean deleteBankInfo(String ifscCode);

}
