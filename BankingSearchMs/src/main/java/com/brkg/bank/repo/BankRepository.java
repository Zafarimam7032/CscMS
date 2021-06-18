package com.brkg.bank.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brkg.bank.Vo.BankVo;

@Repository
public interface BankRepository extends JpaRepository<BankVo, Long> {
	public BankVo findByIfscCode(String ifscCode);
	public void deleteByIfscCode(String ifscCode);
}
