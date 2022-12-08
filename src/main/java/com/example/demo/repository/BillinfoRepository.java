package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Billinfo;


@Repository
public interface BillinfoRepository extends JpaRepository<Billinfo, Integer >{

	@Query("SELECT b FROM Billinfo b WHERE b.status =:status")
	public List<Billinfo> getAllBillinfoDependStatus(@Param("status") int status);
	
	@Modifying
	@Query("UPDATE Billinfo b SET b.status = :status WHERE b.id LIKE :id")
	void updateStatusBill( @Param("status") int status, @Param("id") Integer id);
	
	@Query("SELECT b FROM Billinfo b WHERE CONCAT(b.shippingAddress, b.recipientName, b.recipientPhone, b.datePayment) LIKE %?1%")
	public List<Billinfo> searchBillinfo(String keyword); 
	
	@Query("SELECT COUNT(b) FROM Billinfo b WHERE b.status =:status")
	public long countBillinfo(@Param("status") Integer status);
	
	@Query("SELECT SUM(b.totalPrice) FROM Billinfo b WHERE b.status =:status")
	public float sumTotalPrice(@Param("status") Integer status);
}
