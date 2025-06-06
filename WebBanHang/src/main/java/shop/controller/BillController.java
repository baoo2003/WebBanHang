package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.entity.Bill;
import shop.service.BillService;

@Controller
@RequestMapping()
public class BillController {
	@Autowired
	BillService billService;
	
	@RequestMapping("/manage-bill")
	public String index(ModelMap model, @RequestParam("id") Integer orderId) {
		Bill bill = billService.findBillByOrderId(orderId);
		System.out.println(bill.getId());
		model.addAttribute("bill", bill);
		return "admin/bill";
	}
}
