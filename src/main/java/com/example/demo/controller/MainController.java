package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.BillinfoService;

@Controller
public class MainController {
	
	@Autowired
	private BillinfoService billinfoService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String viewHomePage(Model model)
	{
		long numberOfNewBill = billinfoService.countBillinfo(1);
		long numberOfProcessingBill = billinfoService.countBillinfo(2);
		float totalPriceOfSuccessfulBill = billinfoService.sumTotalPrice(3);
		model.addAttribute("numberOfNewBill", numberOfNewBill);
		model.addAttribute("numberOfProcessingBill", numberOfProcessingBill);
		model.addAttribute("totalPriceOfSuccessfulBill", totalPriceOfSuccessfulBill);
		return "index";
	}
}
