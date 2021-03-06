package com.brkg.bank.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brkg.bank.Vo.BankVo;
import com.brkg.bank.repo.BankRepository;
import com.brkg.bank.service.BankService;
import com.brkg.pincode.Vo.PincodeVo;
import com.brkg.pincode.repo.PinCodeRepository;

@Service
public class BankServiceImpl implements BankService {
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private PinCodeRepository pinCodeRepository;

	@Override
	public List<BankVo> getAllBank() {
		List<BankVo> allBankInfo = bankRepository.findAll();
		if (allBankInfo.size() > 0) {
			Collections.sort(allBankInfo, (bankVo1, bankVo2) -> bankVo1.getIfscCode().compareTo(bankVo2.getIfscCode()));
			return allBankInfo;
		}
		return null;
	}

	@Override
	public List<BankVo> getAllBankByPinCode(int pinCode) {
		List<BankVo> allBankInfo = bankRepository.findAll().stream()
				.filter(bankVo -> bankVo.getPincodeVo().getPinCode() == pinCode).collect(Collectors.toList());
		if (allBankInfo.size() > 0) {
			Collections.sort(allBankInfo, (bankVo1, bankVo2) -> bankVo1.getIfscCode().compareTo(bankVo2.getIfscCode()));
			return allBankInfo;
		}

		return null;
	}

	@Override
	public BankVo getBankInfo(String IfscCode) {
		BankVo bankVo = bankRepository.findByIfscCode(IfscCode);
		if (bankVo != null) {
			return bankVo;
		}
		return null;
	}

	@Override
	public boolean addBankInfo(BankVo bankVo, int pinCode) {
		boolean check = false;
		PincodeVo pinCodeVo = pinCodeRepository.findByPinCode(pinCode);
		if (pinCodeVo != null) {
			PincodeVo pincodeVo = bankVo.getPincodeVo();
			pincodeVo.setPinCode(pinCode);
			bankVo.setPincodeVo(pincodeVo);
			BankVo afterSaveDbBankVo = bankRepository.save(bankVo);
			if (afterSaveDbBankVo != null) {
				check = true;
			}

		}
		return check;
	}

	@Override
	public boolean updateBankInfo(String ifscCode, BankVo bankVo) {
		boolean check = false;
		BankVo dbBankVo = bankRepository.findByIfscCode(ifscCode);
		if (dbBankVo != null) {
			if (bankVo.getName() != null) {
				dbBankVo.setName(bankVo.getName());
				BankVo saveBankVo = bankRepository.save(dbBankVo);
				if (saveBankVo != null) {
					check = true;
				}
			}
			if (bankVo.getAddress() != null) {
				dbBankVo.setAddress(bankVo.getAddress());
				BankVo saveBankVo = bankRepository.save(dbBankVo);
				if (saveBankVo != null) {
					check = true;
				}
			}
			if (bankVo.getOpeingTime() != null) {
				dbBankVo.setOpeingTime(bankVo.getOpeingTime());
				BankVo saveBankVo = bankRepository.save(dbBankVo);
				if (saveBankVo != null) {
					check = true;
				}
			}
			if (bankVo.getClosingTime() != null) {
				dbBankVo.setClosingTime(bankVo.getClosingTime());
				BankVo saveBankVo = bankRepository.save(dbBankVo);
				if (saveBankVo != null) {
					check = true;
				}
			}
			if (bankVo.getIfscCode() != null) {
				dbBankVo.setIfscCode(bankVo.getIfscCode());
				BankVo saveBankVo = bankRepository.save(dbBankVo);
				if (saveBankVo != null) {
					check = true;
				}
			}
		}

		return check;
	}

	@Override
	public boolean deleteBankInfo(String ifscCode) {
		boolean check = false;
		BankVo dbBankVo = bankRepository.findByIfscCode(ifscCode);
		if (dbBankVo != null) {
			bankRepository.deleteByIfscCode(ifscCode);
			check = true;
		}
		return check;
	}

}
