package com.brkg.bank.controller.imp;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brkg.bank.Vo.BankVo;
import com.brkg.bank.controller.BankController;
import com.brkg.bank.service.impl.BankServiceImpl;

@RestController
@RequestMapping(path = "/home/bank/")
public class BankControllerImp implements BankController {

	@Autowired
	private BankServiceImpl bankService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(path = "get/all")
	@Override
	public ResponseEntity<List<BankVo>> getAllBank() {
		try {
			List<BankVo> allBankInfo = bankService.getAllBank();
			return ResponseEntity.of(Optional.of(allBankInfo));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(path = "get/pincode/{pinCode}")
	@Override
	public ResponseEntity<List<BankVo>> getAllBankByPinCode(@PathVariable("pinCode") int pinCode) {
		try {
			List<BankVo> allBankInfo = bankService.getAllBankByPinCode(pinCode);
			return ResponseEntity.of(Optional.of(allBankInfo));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(path = "get/ifsc/{IfscCode}")
	@Override
	public ResponseEntity<BankVo> getBankInfo(@PathVariable("IfscCode") String IfscCode) {
		try {
			BankVo bankVo = bankService.getBankInfo(IfscCode);
			return ResponseEntity.of(Optional.of(bankVo));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping(path = "add/bank/info/pincode/{pinCode}")
	@Override
	public ResponseEntity<Boolean> addBankInfo(@RequestBody(required = true) BankVo bankVo,
			@PathVariable("pinCode") int pinCode) {
		try {
			boolean addBankInfo = bankService.addBankInfo(bankVo, pinCode);
			if (addBankInfo) {
				URI uri = WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getBankInfo(bankVo.getIfscCode())).toUri();
				return ResponseEntity.created(uri).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping(path = "update/bank/info/ifsc/{ifscCode}")
	@Override
	public ResponseEntity<Boolean> updateBankInfo(@PathVariable("ifscCode") String ifscCode,
			@RequestBody BankVo bankVo) {
		try {
			boolean addBankInfo = bankService.updateBankInfo(ifscCode, bankVo);
			if (addBankInfo) {
				URI uri = WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getBankInfo(bankVo.getIfscCode())).toUri();
				return ResponseEntity.created(uri).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(path = "delete/infsc/{ifscCode}")
	@Override
	public ResponseEntity<Boolean> deleteBankInfo(@PathVariable("ifscCode  ") String ifscCode) {
		try {
			boolean deleteBankInfo = bankService.deleteBankInfo(ifscCode);
			if (deleteBankInfo) {
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
