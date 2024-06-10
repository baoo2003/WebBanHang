package shop.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.dto.response.SaleOverDto;
import shop.dto.response.YearBreakDto;
import shop.service.SaleOverService;

@Controller("/admin")
public class DashboardController {
	@Autowired
	SaleOverService saleOverService;
	
	@RequestMapping
	public String index(ModelMap model) {
		List<SaleOverDto> salesOverviewDTOs= saleOverService.getSalesOverview();
		Double maxTotal = 0.0;
		for(SaleOverDto dto : salesOverviewDTOs) {
			if(dto.getTotal() > maxTotal)
				maxTotal = dto.getTotal();
				
		}
		maxTotal+=20;
		double previousMonthTotal = salesOverviewDTOs.get(4).getTotal();
        double currentMonthTotal = salesOverviewDTOs.get(5).getTotal();

        if (previousMonthTotal != 0) {
        	if(currentMonthTotal < previousMonthTotal) {
	        	double percentageChange = 100 - (currentMonthTotal / previousMonthTotal * 100);
	            DecimalFormat df = new DecimalFormat("0.00");
	            String pChange =  df.format(percentageChange) + " %";
	            model.addAttribute("pChange",pChange);
        	}
        	else {
        		double percentageChange =  (currentMonthTotal / previousMonthTotal * 100) -100;
	            DecimalFormat df = new DecimalFormat("0.00");
	            String pChange =  df.format(percentageChange) + " %";
	            model.addAttribute("pChange",pChange);
        	}
        }
        YearBreakDto yearBreakDto = saleOverService.getYearBreak();
        Integer totalCustomer = saleOverService.totalCustomer();
        Integer totalProduct = saleOverService.totalProduct();
        Integer totalOrder = saleOverService.totalOrder();
        
        model.addAttribute("yearBreakDto",yearBreakDto);		
		model.addAttribute("salesOverviewDTOs",salesOverviewDTOs);
		model.addAttribute("maxTotal",maxTotal);
		model.addAttribute("totalCustomer",totalCustomer);
		model.addAttribute("totalProduct",totalProduct);
		model.addAttribute("totalOrder",totalOrder);
		return "admin/dashboard";
	}
}
