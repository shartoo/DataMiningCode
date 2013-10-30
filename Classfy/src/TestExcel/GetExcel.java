package TestExcel;

import java.util.Scanner;

public class GetExcel {
    
     public static void main(String[] args){
    	 CreateXLS xls=new CreateXLS();
         CreateNewXls nxl=new CreateNewXls();
         Create6Xls c6l=new Create6Xls();
         String[] attr={"workclass","education-num","education","fnlwgt","marital-status","occupation","relationship"
        		 ,"race","sex","capital-gain","capital-loss","hours-per-week","native-country","50k","age"};
         
         String[] attr2={"A1","A2","A3","A4","A5","A6","A7","A8","A9","A10","A11","A12","A13","A14","A15","A16"};
         String[] attr3={"University-name","State","location","Control","number-of-students","male:female ratio",
        		 "student:faculty ratio"," sat-verbal ","sat-math","expenses","percent-financial-aid","number-of-applicants",
        		 "percent-admittance","percent-enrolled ","academics"," social"," quality-of-life"," academic-emphasis"};
         
         String[] attr6={"parents","has_nurs","form","children","housing","finance","social","health","suggetion"};
         String name="D:/数据挖掘/数据挖掘数据/1/adult.txt";
         String name2="D:/数据挖掘/数据挖掘数据/3/crx.txt";
         String name3="D:/数据挖掘/数据挖掘数据/5/university.txt";
         String name6="D:/数据挖掘/数据挖掘数据/6/nursery.txt";
    	 xls.create(name,attr);
         //nxl.create(name3, attr3);
         // xls.create(name6, attr6);
        // c6l.create(name6 , attr6);
    	 System.out.println("transform ready!");
     }
}
