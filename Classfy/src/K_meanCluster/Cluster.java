package K_meanCluster;

import java.io.File;
import java.util.Iterator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import Genera_decis_tree.PublicData;

public class Cluster {
	public static final int MAX_TIME=30;    //最大迭代次数     
	 public int count=0;            //计数，看迭代次数
    SetSelAttr ssa=new SetSelAttr();
    String[] str=PublicData.getClusterAttr();
    static int cluCoreNum=PublicData.getClusterNum();
    BigDecimal[][] cluCore=new BigDecimal[str.length][cluCoreNum+1];    //聚类中心,用户选择的m个属性，并且每个属性有k个聚类中心,+1是将0为存储属性位置
    
public HashMap<String,BigDecimal[]> getCluster(){
	//此HashMap中 key对应着属性名称，value的数据[0]存储在Excel表中位置,[1]存储该属性的最小值,[2]存储最大值
	HashMap<String,BigDecimal[]> attr_loc_M=new HashMap<String,BigDecimal[]>();  
	String[][]  AttrAndMaMi=new String[str.length][4];
	//用于寻找用户选择的聚类属性在所有属性中的位置，方便下一步我们在Excel表中寻找，注意我们此时只是遍历了Excel表第一行 
	try {   
        Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
        Sheet sheet = book.getSheet(0);  
        for(int i=0;i<sheet.getColumns();i++)
        {
        	Cell cell = sheet.getCell(i, 0);   //获取当前属性所在列
            String result = cell.getContents().replaceAll("\"", "").trim();
            for(int j=0;j<str.length;j++)
            {
            	if(result.equals(str[j]))
            	    	{
            		     AttrAndMaMi[j][0]=str[j];    //数组的第一个位置存储的是属性名称以便对应
            		     AttrAndMaMi[j][1]=i+"";    //将属性所在列位置也变成String类型
            		     String mi=sheet.getCell(i,2).getContents();      //不可以初始指定为0,否则最小值有可能是不存在的0
            		     AttrAndMaMi[j][2]=mi;
            		     String ma=sheet.getCell(i,1).getContents();
            		     AttrAndMaMi[j][3]=ma;     //分别存储最大值和最小值    ,2是最小值，3是最大值
            	    	}         
            }
        }
      book.close();   
    } catch (Exception e) {   
    e.printStackTrace();   
    }  
    //*******************************以上部分是获取用户选择的聚类属性在Excel文件中的位置，接下来开始遍历Excel表统计相关属性最大最小值
    
    try {   
        Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
        Sheet sheet = book.getSheet(0);  
        for(int i=1;i<sheet.getRows();i++)
        {  	
        	for(int j=0;j<AttrAndMaMi.length;j++)
        	{
        	   BigDecimal min=new BigDecimal(AttrAndMaMi[j][2]);
        	   BigDecimal max=new BigDecimal(AttrAndMaMi[j][3]);
       	       Cell c=sheet.getCell(Integer.parseInt(AttrAndMaMi[j][1]),i);   //注意此处的属性所在列位置，必须为int类型,不要转换成了BigDecimal
        	   String res=c.getContents();
        	   if(res.equals("?"))                   //针对Excel表中出现的？内容的问题
        	   {
        		   continue;
        	   }   
        	   else
        	   {
        	     BigDecimal ExcelValue=new BigDecimal(res);    //获取Excel表中的精确值 
        	     if(ExcelValue.compareTo(max)==1)
        		    AttrAndMaMi[j][3]=ExcelValue.toString();     //更新数组的中当前属性的最大值和最小值
        	     else if(ExcelValue.compareTo(min)==-1)
        		    AttrAndMaMi[j][2]=ExcelValue.toString();
        	   }
           }
    }
    book.close();   
  } catch (Exception e) {   
  e.printStackTrace();   
  }  
  //将数组变成HashMap
  for(int i=0;i<AttrAndMaMi.length;i++)
  {
	BigDecimal[] p=new BigDecimal[3];
	p[0]=new BigDecimal(AttrAndMaMi[i][1]);
	p[1]=new BigDecimal(AttrAndMaMi[i][2]);
	p[2]=new BigDecimal(AttrAndMaMi[i][3]);
	attr_loc_M.put(AttrAndMaMi[i][0],p);
  }
    return attr_loc_M;   
}	
public void initCluCore(){
	HashMap<String,BigDecimal[]> map=this.getCluster();
	 Set<Map.Entry<String, BigDecimal[]>> set =map.entrySet();
    java.util.Iterator<Entry<String, BigDecimal[]>> it=set.iterator();
    int j=0;
    while(it.hasNext()) 
    {
   	 Map.Entry<String,BigDecimal[]> entry = (Map.Entry<String,BigDecimal[]>) it.next();
   	 BigDecimal[] value=entry.getValue();
   	 String name=entry.getKey();
   	 Random rd1 = new Random();
   	 System.out.println("-----来自属性"+name+"的最大值和最小值");
     System.out.print("列数是"+value[0]+"最小值是"+value[1]+"\t"+"最大值是"+value[2]+"\t");     
     System.out.println("按照用户选择生成区间内的随机数");
     // 进行除法运算,保留200位小数,末位使用四舍五入方式,返回结果
     //BigDecimal result = bd.divide(bd2, 200, BigDecimal.ROUND_HALF_DOWN);
     Double a=value[1].doubleValue();
     Double b=value[2].doubleValue();
     cluCore[j][0]=value[0];               //第0位存储的是属性名称
     /************************此处我们制定初始聚类中心，以便测试我们的聚类是否正确
      * 只需要用一个系数替换nextDouble即可,我们选择0.5，类似平均值
      */
  	 
        for(int i=1;i<cluCoreNum+1;i++)
         {     
             cluCore[j][i]=new BigDecimal(a+(0.5+i*0.1)*(b-a));    //+i*0.1是避免多个聚类中心初始值一样
    	     System.out.print(a+rd1.nextDouble()*(b-a)+"\t"); 
         }
      /********************第二段是以随机方法来制定初始化聚类中心的
         for(int i=1;i<cluCoreNum+1;i++)
          {     
             cluCore[j][i]=new BigDecimal(a+rd1.nextDouble()*(b-a));
    	     System.out.print(a+rd1.nextDouble()*(b-a)+"\t"); 
          }
    */
          System.out.println();
         j++;
    }
    System.out.println("来自初始化的聚类中心值--------------");
    PublicData.clearInitCore();
    for(int i=0;i<cluCore.length;i++)
    {
    	String s="";
    	for(int m=0;m<cluCore[i].length;m++)
    	{
    		System.out.print(cluCore[i][m].setScale(4,BigDecimal.ROUND_HALF_DOWN)+"\t");
    		s+=cluCore[i][m].setScale(4,BigDecimal.ROUND_HALF_DOWN)+"\t";
    	}
    	PublicData.setClusterInitCore(s);
    	System.out.println();
    }
}
//获取真正的 聚类中心，过程会是反复迭代
public BigDecimal[][] getRealCore()
{   
	 try {   
	        Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
	        Sheet sheet = book.getSheet(0);
	        Boolean f=false;      //用来控制更新操作是否继续进行的标志
	       PublicData.clearCluster();
	       String front="===============================以下是聚类结果=======================";
	       PublicData.setClusterResult(front);
	       String setss="\r\n迭代次数		";
	       for(int i=0;i<str.length;i++){
	    	   setss=setss+str[i]+"\t";
	    	   String s="";
	    	   for(int j=0;j<PublicData.getClusterNum();j++){               //只是为了多空几个tab而已
	    		   s=s+"\t\t";										//注意此处只能两个tab，多了就出问题
	    	   }
	    	   setss=setss+s;            
	       }
	     PublicData.setClusterResult(setss);
	     PublicData.setClusterResult("\r\n");
	     while(!f&&count<MAX_TIME)
	     {	 count++;
	        HashMap<String,ArrayList<BigDecimal>> map=new HashMap<String,ArrayList<BigDecimal>>();
	        for(int i=1;i<sheet.getRows();i++)
	        {  	
	         for(int j=0;j<cluCore.length;j++)
	         {
	           Cell s=sheet.getCell(cluCore[j][0].intValue(),i);
	           String resl=s.getContents();
	           BigDecimal num=new BigDecimal(0);
	           if(!resl.equals("?"))
	        	   num=new BigDecimal(resl);
	           BigDecimal val=num;
	           //我们首先必须确定此Excel表中的得到的值是离那个聚类中心比较近
	           BigDecimal minLag=new BigDecimal(Math.abs(val.subtract(cluCore[j][1]).doubleValue()));
	           BigDecimal min=cluCore[j][1];
	           for(int m=1;m<cluCore[j].length;m++)
	           {
	        	   BigDecimal lag=new BigDecimal(Math.abs(val.subtract(cluCore[j][m]).doubleValue()));
	        	   if(lag.compareTo(minLag)==-1){
	        		   min=cluCore[j][m];                //找到最近的中心点
	        	   }
	           }
	           //再次遍历，为接下来求均值做准备
	           for(int n=0;n<cluCore[j].length;n++)
	           {  												  //cluCore的第一个元素存储的是其位置
	        	   String a=cluCore[j][0]+"";
	        	   String b=min+"";
	        	   String c=a+","+b;
	        
	        	   if(!map.containsKey(c))                //以属性位置+逗号+上一步的聚类中心值作为key
	        	   {  
	        		   ArrayList<BigDecimal> list=new ArrayList<BigDecimal>();
	        		   map.put(c,list);
	        		   System.out.println("我们加入key     "+c);
	        	   }
	        	   else if(map.containsKey(c))
	        	   {
	        		   map.get(c).add(val);    //将新获得的Excel中的值加入到hashMap的list中去
	        	   }
	           }          
	         }	        	
	        }
	     //接下来遍历整个map以求得根据每个聚类中心得到的每个Excel表中元素的值的平均值
	        BigDecimal[][] newArray=new BigDecimal[str.length][cluCoreNum+1];
	        //System.arraycopy(cluCore, 0, newArray, 0, cluCore.length);
	        	//(BigDecimal[][])cluCore.clone();        //在我们更新之前先保存上一步的，为后面对比做准备
	        this.CopyArray(cluCore, newArray);
	    	Set<Map.Entry<String, ArrayList<BigDecimal>>> set =map.entrySet();
	    	Iterator<Map.Entry<String,ArrayList<BigDecimal>>> it=set.iterator();
	    	while(it.hasNext())
	    	    {
	    	        Map.Entry<String,ArrayList<BigDecimal>> souentry =(Map.Entry<String,ArrayList<BigDecimal>>)it.next();
	    	        ArrayList<BigDecimal> fg=souentry.getValue();
	    	        BigDecimal sum=new BigDecimal(0);
	    	        String[] keyName=souentry.getKey().split(",");   
	    	        for(int m=0;m<keyName.length;m++){
	    	        	System.out.println("keyName="+keyName[m]);
	    	        }
	    	        for(int i=0;i<fg.size();i++)
	    	        {
	    	        	sum=sum.add(fg.get(i));      //求和
	    	        }
	    	     System.out.println("fg的长度是"+fg.size());  
	    	     //注意在除法运算时会有除不尽的情况，所以我们必须制定其保留多少位并制定保留规则
	    	     BigDecimal avg=sum.divide(new BigDecimal(fg.size()),10,BigDecimal.ROUND_HALF_DOWN); //当前聚类中心的所有值的平均值
	    	     System.out.println("新得到的聚类中心值是：（按照平均值来算）"+avg);
	             String row=keyName[0];                          //keyName[0]保存的是此属性在Excel表中的位置
	             String clu=keyName[1];                           //keyName[1]保存的是此属性的其中之一上一步的中心点
	             //ccluCore=new BigDecimal[str.length][cluCoreNum+1];,其中的0号位置存储的是属性在所有属性中的位置
                 //*****************以下这些代码每次只能同时更新用户所选N个属性的所有的第一个聚类中心而已
	             for(int i=0;i<cluCore.length;i++)
                 {
                	 if(cluCore[i][0].compareTo(new BigDecimal(row))==0)
                	 {
                		 System.out.println("cluCore[i][0]="+cluCore[i][0]+"\trow="+row);
                	    for(int j=1;j<cluCore[i].length;j++)
                	    {
                	    	System.out.println("我输出多少次代表cluCore[i]有多少个元素（即聚类中心）cluCore[i][j]="+cluCore[i][j]); 
                	    	
                	    	if(cluCore[i][j].compareTo(new BigDecimal(clu))==0)
                	    	 {
                	    		cluCore[i][j]=avg;                        //用平均值更新位置为row-->初始聚类中心为cluCore（也即clu）的值
                	    		System.out.println("clu=="+clu);
                	    	 }	
                	    }
                	 }
                 }	                 
	    	    }
	    	
	    String resul="\t\n";                      //将我们的聚类结果保存在resul中，然后写入到文件中去   
        for(int i=0;i<cluCore.length;i++)
        {
       	    for(int j=1;j<cluCore[i].length;j++)
       	    {
       	    	resul=resul+cluCore[i][j].setScale(4, BigDecimal.ROUND_HALF_DOWN)+"\t";
       	    }
       	    
       	   for(int j=0;j<PublicData.getClusterNum();j++)
       	     {               //只是为了多空几个tab而已
  		        resul=resul+"\t";										//注意此处只能两个tab，多了就出问题
  	         } 
        } 	  
        resul=resul+"\t\n\t";                            //必须弄成这样了，多了或少了都有问题
        String tt=count+"    ";
        PublicData.setClusterResult(tt+resul);		
        PublicData.setClusterResult("\r\n");
	    f=ifCore(newArray,cluCore);     //判断是否有必要继续更新        
	     }    
           book.close();   
       } 
	    catch (Exception e) {   
          e.printStackTrace();
       }
	    System.out.println("迭代了多少次"+count);
	    return cluCore;
}
//判断两个数组是不是已经完全一致了
public Boolean ifCore(BigDecimal a[][],BigDecimal b[][]){
	Boolean flag=true;
	for(int i=0;i<a.length;i++)
	{
		for(int j=0;j<a[i].length;j++){
			System.out.println("a[i][j==]"+a[i][j]+"\tb[i][j]=="+b[i][j]);
			if(!(a[i][j].compareTo(b[i][j])==0))
				flag=false;	
		}
	}
	return flag;
	
}
public void CopyArray(BigDecimal[][] a,BigDecimal[][] b){
	for(int i=0;i<a.length;i++)
		for(int j=0;j<a[i].length;j++){
			b[i][j]=a[i][j];
		}
}
public static void main(String[] args){
		Cluster clu=new Cluster();
        clu.doCluster();
	}
public void doCluster(){
	Cluster clu=new Cluster();
    clu.initCluCore();
	BigDecimal[][] a=clu.getRealCore();
	ShowClusterResult show=new ShowClusterResult();
	show.show();
}
}
