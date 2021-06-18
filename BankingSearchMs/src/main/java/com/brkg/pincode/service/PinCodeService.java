package com.brkg.pincode.service;

import java.util.List;

import com.brkg.pincode.Vo.PincodeVo;

public interface PinCodeService {
	
	public List<PincodeVo> getAllBank();
	public PincodeVo getAllBankByPinCode(int pincode);
	public boolean addPinCode(PincodeVo pincodeVo);
	public boolean updatePinCode(int oldPinCode,int newPinCode);
	public boolean deletePinCode(int pinCode);

}
