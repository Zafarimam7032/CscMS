package com.brkg.pincode.controller.impl;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brkg.pincode.Vo.PincodeVo;
import com.brkg.pincode.controller.PinCodeController;
import com.brkg.pincode.service.impl.PinCodeServiceImpl;

@RestController
@RequestMapping(path = "/home/pincode/")
public class PinCodeControllerImpl implements PinCodeController {

	@Autowired
	private PinCodeServiceImpl pinCodeService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(path = "get/all")
	@Override
	public ResponseEntity<List<PincodeVo>> getAllBank() {
		try {
			List<PincodeVo> allBankinfo = pinCodeService.getAllBank();
			return ResponseEntity.of(Optional.of(allBankinfo));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@GetMapping(path = "get/pincode/{pincode}")
	@Override
	public ResponseEntity<PincodeVo> getAllBankByPinCode(@PathVariable("pincode") int pincode) {
		try {
			PincodeVo bankinfo = pinCodeService.getAllBankByPinCode(pincode);
			return ResponseEntity.of(Optional.of(bankinfo));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PostMapping(path = "add")
	@Override
	public ResponseEntity<Boolean> addPinCode(@RequestBody(required = true) PincodeVo pincodeVo) {
		try {
			boolean addPinCode = pinCodeService.addPinCode(pincodeVo);
			if (addPinCode) {
				URI uri = WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllBankByPinCode(pincodeVo.getPinCode()))
						.toUri();
				return ResponseEntity.created(uri).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}
	@PutMapping(path = "update/pin/oldpin/{oldPinCode}/newpin/{newPinCode}")
	@Override
	public ResponseEntity<Boolean> updatePinCode(@PathVariable("oldPinCode") int oldPinCode,
			@PathVariable("newPinCode") int newPinCode) {
		try {
			boolean updatePinCode = pinCodeService.updatePinCode(oldPinCode, newPinCode);
			if (updatePinCode) {
				URI uri = WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllBankByPinCode(newPinCode)).toUri();
				return ResponseEntity.created(uri).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@Override
	public ResponseEntity<Boolean> deletePinCode(int pinCode) {
		try {
			boolean deletePinCode = pinCodeService.deletePinCode(pinCode);
			if (deletePinCode) {
				return ResponseEntity.status(HttpStatus.OK).build();
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}
}
