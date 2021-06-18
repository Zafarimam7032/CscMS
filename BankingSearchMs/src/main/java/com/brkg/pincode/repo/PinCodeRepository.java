package com.brkg.pincode.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brkg.pincode.Vo.PincodeVo;
@Repository
public interface PinCodeRepository extends JpaRepository<PincodeVo, Long> {
public PincodeVo findByPinCode(int pinCode); 
}
