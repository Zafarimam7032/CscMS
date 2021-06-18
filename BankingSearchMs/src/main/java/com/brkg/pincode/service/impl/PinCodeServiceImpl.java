package com.brkg.pincode.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brkg.pincode.Vo.PincodeVo;
import com.brkg.pincode.repo.PinCodeRepository;
import com.brkg.pincode.service.PinCodeService;

@Service
public class PinCodeServiceImpl implements PinCodeService {

	@Autowired
	private PinCodeRepository pinCodeRepository;

	@Override
	public List<PincodeVo> getAllBank() {
		List<PincodeVo> allBankWithPinCode = pinCodeRepository.findAll();
		if (allBankWithPinCode.size() > 0) {
			Collections.sort(allBankWithPinCode, (pinCodeVo, PinCodeVo1) -> Integer.valueOf(pinCodeVo.getPinCode())
					.compareTo(Integer.valueOf(PinCodeVo1.getPinCode())));
			return allBankWithPinCode;
		}

		return null;
	}

	@Override
	public PincodeVo getAllBankByPinCode(int pincode) {
		PincodeVo pinCodeVo = pinCodeRepository.findByPinCode(pincode);
		if (pinCodeVo != null) {
			return pinCodeVo;
		}

		return null;
	}

	@Override
	public boolean addPinCode(PincodeVo pincodeVo) {
		boolean check = false;
		PincodeVo dbPincodeVo = pinCodeRepository.save(pincodeVo);
		if (dbPincodeVo != null) {
			check = true;
		}
		return check;
	}

	@Override
	public boolean updatePinCode(int oldPinCode, int newPinCode) {
		boolean check = false;
		PincodeVo dbPinCodeVo = pinCodeRepository.findByPinCode(oldPinCode);
		if (dbPinCodeVo != null) {
			dbPinCodeVo.setPinCode(newPinCode);
			PincodeVo updatePinCodeVo = pinCodeRepository.save(dbPinCodeVo);
			if (updatePinCodeVo != null) {
				check = true;
			}
		}

		return check;
	}

	@Override
	public boolean deletePinCode(int pinCode) {
		boolean check = false;
		PincodeVo dbPinCodeVo = pinCodeRepository.findByPinCode(pinCode);
		if (dbPinCodeVo != null) {
			pinCodeRepository.deleteById(dbPinCodeVo.getId());
			check = true;
		}
		return check;
	}

}
