package shop.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dto.response.SaleOverDto;
import shop.dto.response.YearBreakDto;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class SaleOverService {
    @Autowired
    private SessionFactory sessionFactory;

    public List<SaleOverDto> getSalesOverview() {
        Session session = sessionFactory.openSession();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -6);
            Date sixMonthsAgo = calendar.getTime();

            String hql = "SELECT MONTH(b.createTime), YEAR(b.createTime), SUM(b.totalPrice) " +
                         "FROM Bill b " +
                         "WHERE b.createTime >= :sixMonthsAgo " +
                         "GROUP BY YEAR(b.createTime), MONTH(b.createTime) " +
                         "ORDER BY YEAR(b.createTime) ASC, MONTH(b.createTime) ASC";

            Query query = session.createQuery(hql);
            query.setParameter("sixMonthsAgo", sixMonthsAgo);
            List<Object[]> results = query.list();

            // Initialize a map to store results for each month
            Map<String, SaleOverDto> salesMap = new LinkedHashMap<>();
            Calendar cal = Calendar.getInstance();

            // Initialize the map with the last 6 months and total as 0
            for (int i = 5; i >= 0; i--) {
                cal.setTime(new Date());
                cal.add(Calendar.MONTH, -i);
                int month = cal.get(Calendar.MONTH) + 1;
                int year = cal.get(Calendar.YEAR);

                SaleOverDto dto = new SaleOverDto();
                dto.setMonth(month);
                dto.setYear(year);
                dto.setTotal(0.0);
                salesMap.put(year + "-" + month, dto);
            }

            // Update the map with actual results from the database
            if (!results.isEmpty()) {
                for (Object[] detail : results) {
                    int month = (Integer) detail[0];
                    int year = (Integer) detail[1];
                    double total = (Double) detail[2];

                    SaleOverDto dto = salesMap.get(year + "-" + month);
                    if (dto != null) {
                        dto.setTotal(total);
                    }
                }
            }

            // Convert the map values to a list
            List<SaleOverDto> salesOverviewDTOs = new ArrayList<>(salesMap.values());

            return salesOverviewDTOs;
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }
    
    public YearBreakDto getYearBreak() {
        YearBreakDto yearBreakDto = new YearBreakDto();
        Session session = sessionFactory.openSession();
        try {
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            int previousYear = currentYear - 1;

            String hql = "SELECT YEAR(b.createTime), SUM(b.totalPrice) " +
                         "FROM Bill b " +
                         "WHERE YEAR(b.createTime) = :currentYear OR YEAR(b.createTime) = :previousYear " +
                         "GROUP BY YEAR(b.createTime)";

            Query query = session.createQuery(hql);
            query.setParameter("currentYear", currentYear);
            query.setParameter("previousYear", previousYear);

            List<Object[]> results = query.list();
            for (Object[] result : results) {
                int year = (Integer) result[0];
                double total = (Double) result[1];
                if (year == currentYear) {
                	yearBreakDto.setCurrentYear(currentYear);
                    yearBreakDto.setCurrentYearTotal(total);
                } else if (year == previousYear) {
                    yearBreakDto.setPreviousYearToTal(total);
                }
            }
            if(yearBreakDto.getCurrentYearTotal() == null) {
            	 yearBreakDto.setCurrentYearTotal(0.0);
            }
            if(yearBreakDto.getPreviousYearToTal() == null) {
           	 yearBreakDto.setPreviousYearToTal(0.0);
           }
            if(yearBreakDto.getCurrentYearTotal() >= yearBreakDto.getPreviousYearToTal()){
        	   double percentageChange =  (yearBreakDto.getCurrentYearTotal() / yearBreakDto.getPreviousYearToTal() * 100) -100 ;
               DecimalFormat df = new DecimalFormat("0.00");
               String pChange =  df.format(percentageChange) + " %";
               yearBreakDto.setPercentageChange(pChange);
           }
            else {
            	double percentageChange = 100 - (yearBreakDto.getCurrentYearTotal() / yearBreakDto.getPreviousYearToTal() * 100)  ;
                DecimalFormat df = new DecimalFormat("0.00");
                String pChange =  df.format(percentageChange) + " %";
                yearBreakDto.setPercentageChange(pChange);
            }
            return yearBreakDto;
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }
    
    public Integer totalCustomer() {
    	 Session session = sessionFactory.openSession();
    	 try {
    		 String hql = "SELECT COUNT(c) FROM Customer c";
    		 Query query = session.createQuery(hql);
    		 Long count = (Long) query.uniqueResult();
    		 return count.intValue();
    	 }catch (Exception e) {
             throw e;
         } finally {
             session.close();
         }
    }
    
    public Integer totalProduct() {
   	 Session session = sessionFactory.openSession();
   	 try {
   		 String hql = "SELECT COUNT(p) FROM Product p";
   		 Query query = session.createQuery(hql);
   		 Long count = (Long) query.uniqueResult();
   		 return count.intValue();
   	 }catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
   }
    
    public Integer totalOrder() {
      	 Session session = sessionFactory.openSession();
      	 try {
      		 String hql = "SELECT COUNT(o) FROM Order o";
      		 Query query = session.createQuery(hql);
      		 Long count = (Long) query.uniqueResult();
      		 return count.intValue();
      	 }catch (Exception e) {
               throw e;
           } finally {
               session.close();
           }
      }
}
