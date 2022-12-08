package com.example.demo.service;
import com.example.demo.model.Billinfo;


import java.util.List;

import org.springframework.data.repository.query.Param;

public interface BillinfoService {
	List<Billinfo> getAllBillinfo();
	List<Billinfo> getAllBillinfoDependStatus(@Param("status") int status); 
	Billinfo getBillinfoById(Integer id);
	void updateStatusBill( @Param("status") int status, @Param("id") Integer id);
	public List<Billinfo> searchBillinfo(String keyword); 
	long countBillinfo(@Param("status") Integer status);
	float sumTotalPrice(@Param("status") Integer status);

}
