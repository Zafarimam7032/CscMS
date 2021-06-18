package com.brkg.pincode.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.brkg.pincode.Vo.PincodeVo;

public interface PinCodeController {

	public ResponseEntity<List<PincodeVo>> getAllBank();

	public ResponseEntity<PincodeVo> getAllBankByPinCode(int pincode);

	public ResponseEntity<Boolean> addPinCode(PincodeVo pincodeVo);

	public ResponseEntity<Boolean> updatePinCode(int oldPinCode, int newPinCode);

	public ResponseEntity<Boolean> deletePinCode(int pinCode);
}
