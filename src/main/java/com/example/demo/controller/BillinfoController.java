package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Billinfo;
import com.example.demo.service.BillinfoService;

@Controller
public class BillinfoController {

	@Autowired
	private BillinfoService billinfoService;
	

	// display list of department
	@GetMapping("/new_billinfo")
	public String viewNewBillInfoPage(Model model) {
		// New bills have status 1
		List<Billinfo> listNewBillInfos = billinfoService.getAllBillinfoDependStatus(1);
		model.addAttribute("listNewBillInfos", listNewBillInfos);
		return "new_bills";
	}
	
	@GetMapping("/processing_billinfo")
	public String viewProcessingBillInfoPage(Model model) {
		// Processing bills have status 2
		List<Billinfo> listProcessingBillInfos = billinfoService.getAllBillinfoDependStatus(2);
		model.addAttribute("listProcessingBillInfos", listProcessingBillInfos);
		return "processing_bills";
	}
	
	@GetMapping("/successful_billinfo")
	public String viewSuccessfulBillInfoPage(Model model) {
		// Successful bills have status 3
		List<Billinfo> listSuccessfulBillInfos = billinfoService.getAllBillinfoDependStatus(3);
		model.addAttribute("listSuccessfulBillInfos", listSuccessfulBillInfos);
		return "successful_bills";
	}
	
	@GetMapping("/cancelled_billinfo")
	public String viewCancelledfulBillInfoPage(Model model) {
		// Cancelled bills have status 0
		List<Billinfo> listCancelledBillInfos = billinfoService.getAllBillinfoDependStatus(0);
		model.addAttribute("listCancelledBillInfos", listCancelledBillInfos);
		return "cancelled_bills";
	}
	
	@GetMapping("/cancelNewBillinfo/{id}")
	public String cancelNewBillinfo(@PathVariable (value="id") Integer id)
	{
		// cancel New Bill by updating status 
		this.billinfoService.updateStatusBill(0, id);
		return "redirect:/new_billinfo";
		
	}
	
	@GetMapping("/updateNewBillToProcessinglBill/{id}")
	public String updateNewBillToProcessingBill(@PathVariable (value="id") Integer id)
	{
		// update New Bill to Processing Bill
		this.billinfoService.updateStatusBill(2, id);
		return "redirect:/new_billinfo";
		
	}
	
	@GetMapping("/updateProcessingBillToSuccessfulBill/{id}")
	public String updateProcessingBillToSuccessfulBill(@PathVariable (value="id") Integer id)
	{
		// update Processing Bill to Successful Bill
		this.billinfoService.updateStatusBill(3, id);
		return "redirect:/processing_billinfo";
		
	}
	
	@GetMapping("/searchNewBillinfo")
	public String searchNewBillinfo(Model model, @Param("keyword") String keyword)
	{
		List<Billinfo> listBillinfo = billinfoService.searchBillinfo(keyword);
		List<Billinfo> listNewBillInfos = new ArrayList<Billinfo>();
		int i = 0;
		for(i = 0; i < listBillinfo.size(); i++ )
		{
			if(listBillinfo.get(i).getStatus() == 1)
			{
				listNewBillInfos.add(listBillinfo.get(i));		
			}
		}
		model.addAttribute("listNewBillInfos", listNewBillInfos);
		return "new_bills";
	}
	
	@GetMapping("/searchProcessingBillinfo")
	public String searchProcessingBillinfo(Model model, @Param("keyword") String keyword)
	{
		List<Billinfo> listBillinfo = billinfoService.searchBillinfo(keyword);
		List<Billinfo> listProcessingBillInfos = new ArrayList<Billinfo>();
		int i = 0;
		for(i = 0; i < listBillinfo.size(); i++ )
		{
			if(listBillinfo.get(i).getStatus() == 2)
			{
				listProcessingBillInfos.add(listBillinfo.get(i));				
			}
		}
		model.addAttribute("listProcessingBillInfos", listProcessingBillInfos);
		return "processing_bills";
	}
	
	@GetMapping("/searchSucessefulBillinfo")
	public String searchSucessefulBillinfo(Model model, @Param("keyword") String keyword)
	{
		List<Billinfo> listBillinfo = billinfoService.searchBillinfo(keyword);
		List<Billinfo> listSuccessfulBillInfos = new ArrayList<Billinfo>();
		int i = 0;
		for(i = 0; i < listBillinfo.size(); i++ )
		{
			if(listBillinfo.get(i).getStatus() == 3)
			{
				listSuccessfulBillInfos.add(listBillinfo.get(i));				
			}
		}
		model.addAttribute("listSuccessfulBillInfos", listSuccessfulBillInfos);
		return "successful_bills";
	}
	
	@GetMapping("/searchCancelledBillinfo")
	public String searchCancelledBillinfo(Model model, @Param("keyword") String keyword)
	{
		List<Billinfo> listBillinfo = billinfoService.searchBillinfo(keyword);
		List<Billinfo> listCancelledBillInfos = new ArrayList<Billinfo>();
		int i = 0;
		for(i = 0; i < listBillinfo.size(); i++ )
		{
			if(listBillinfo.get(i).getStatus() == 0)
			{
				listCancelledBillInfos.add(listBillinfo.get(i));				
			}
		}
		model.addAttribute("listCancelledBillInfos", listCancelledBillInfos);
		return "cancelled_bills";
	}

}
