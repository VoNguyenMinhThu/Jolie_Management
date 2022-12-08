package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Billinfo;
import com.example.demo.repository.BillinfoRepository;

@Service
public class BillinfoServiceImpl implements BillinfoService {

	@Autowired
	private BillinfoRepository billinfoRepository;
	
	@Override
	public List<Billinfo> getAllBillinfo() {
		return billinfoRepository.findAll();
	}

	@Override
	public List<Billinfo> getAllBillinfoDependStatus(int status) {
		return billinfoRepository.getAllBillinfoDependStatus(status);
	}

	@Override
	public Billinfo getBillinfoById(Integer id) {
		Optional<Billinfo> optional = billinfoRepository.findById(id);
		Billinfo billinfo = null;
		if (optional.isPresent()) {
			billinfo = optional.get();
		} else {
			throw new RuntimeException("Billinfo not found for id:" + id);
		}
		return billinfo;
	}

	@Override
	@Transactional
	public void updateStatusBill(int status, Integer id) {
	   this.billinfoRepository.updateStatusBill(status, id);		
	}

	@Override
	public List<Billinfo> searchBillinfo(String keyword) {
		if (keyword != null) {
			return billinfoRepository.searchBillinfo(keyword);
		}
		return null;
	}

	@Override
	public long countBillinfo(Integer status) {
		return billinfoRepository.countBillinfo(status);
	}

	@Override
	public float sumTotalPrice(Integer status) {
		return billinfoRepository.sumTotalPrice(status);
	}


}
