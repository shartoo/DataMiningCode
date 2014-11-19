package Genera_decis_tree;

import java.math.BigDecimal;

//������2Ϊ�׵�log����
public class MathLog {
     public BigDecimal getLog(BigDecimal number)
     {  
    	 return  this.getDivide((float)Math.log(number.floatValue()),(float)Math.log(2));
     }
     
     public BigDecimal getDivide(int b1,int b2){
    	 BigDecimal v1 = new BigDecimal(Double.toString(b1));  
    	 BigDecimal v2 = new BigDecimal(Double.toString(b2));   	
    	 BigDecimal rslt = v1.divide((BigDecimal)v2,(int)5,BigDecimal.ROUND_HALF_UP);
    	 return rslt;
     }
     public BigDecimal getDivide(float b1,float b2){
    	 if(b2==(float)0||b1==Float.NEGATIVE_INFINITY||b2==Float.POSITIVE_INFINITY)
    		 return new BigDecimal(0);
    	 System.out.println(b1);
    	 BigDecimal v1 = new BigDecimal(Float.toString(b1));  
    	 BigDecimal v2 = new BigDecimal(Float.toString(b2));   	   
    	 BigDecimal rslt = v1.divide((BigDecimal)v2,(int)5,BigDecimal.ROUND_HALF_UP);
    	 return rslt;
     }
}
