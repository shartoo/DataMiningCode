package Genera_decis_tree;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class InformationGain {
	ArrayList<Set<String>> attr_and_part=new ArrayList<Set<String>>();   //用来存储所有属性及其分支，每个属性名在set的0位置其余的是其分支
	int[][] value;   //代表此分类的属性的总数
	public ArrayList<String> list=new ArrayList<String>();          
	MathLog mlog=new MathLog();      //引用自定义的计算2的对数函数，此函数有getLog()方法
	//ShowTreePane treepane=new ShowTreePane();      //将所有属性及其分支更新到Tree中，以便查阅
	public String attr_part="";
	public static final String HEAD="Data/DescionTree/";
	public static final String LAST=".txt";
	/**
	 * 获取数据表中某个属性的信息增益
	 * @param pt  需要计算信息增益的属性
	 * @param t   判断属性，也即属性pt分类时的依据属性。
	 * @return    属性pt的信息增益
	 */
    public String[] getInformationGain(String pt,String t){
    	String[] gain_and_support=new String[3];
	    String gain=null;
    	int cla=0;    //代表分类数目
    	BufferedWriter writer = null;
    	int part=0;
    	int target=0;
    	String[] attr=PublicData.getAttr();
    	for(int p=0;p<attr.length;p++)
    	{
    		if(attr[p].equals(pt))
    			{
    			part=p;
    		    System.out.println("分类属性是："+attr[p]);
    			}
    		if(attr[p].equals(t))
    		{
    			target=p;
    			System.out.println("所选属性是："+attr[p]);
    		}	
    	}
    	/**所有参与计算的变量必须都放到方法内部来，避免下次计算时重复*/
    	float yes_to_attr=0;   //属于当前类的数目
    	float no_to_attr=0;
    	BigDecimal info=new BigDecimal(0);     //计算元组分类所需的期望信息
    	BigDecimal infoD=new BigDecimal(0);    //存储当前列属性的信息增益
       	float sum=0;     //存储总行数
    	java.text.DecimalFormat DataFormat=(java.text.DecimalFormat)java.text.DecimalFormat.getInstance();
	      //为了得到整数相处保留三位小数的结果。注意，经过这样的处理后，计算得到的结果是String类型
    	DataFormat.applyPattern("##.###"); //为了得到整数相处保留三位小数的结果
    	value=new int[50][2];
    	//以下是用来读取Excel文件中的单元值
    	try {   
            Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
            Sheet sheet = book.getSheet(0);      
            for(int i=1;i<sheet.getRows();i++)   //默认第一行为属性辨别行，不参与计算
            {        	  
           		 Cell cell = sheet.getCell(target, i);   //获取当前属性所在列
           		 Cell cell2=sheet.getCell(part,i);       //获取结果列，即训练元组最终分类类别那一列
           		 String c2=cell2.getContents().replaceAll("\"", "").trim();
                 String result = cell.getContents().replaceAll("\"", " ").trim();  
                 if(list.contains(result)==false&&result!=null)      //将所选属性加入到list中
                 {  
                	 list.add(result); 
                	 cla=list.indexOf(result);        
                  }
                 else if(list.contains(result)==true)
                     cla=list.indexOf(result);    //以当前属性的类别（String类型）所在list中的索引为value的第一维
                 if(c2.equals("y")||c2.equals("yes"))   //以二维数组value的第二维的第一列来存储属于最终类的总数
                  {	
                     value[cla][0]++;
                     yes_to_attr++;          //全部数据中符合条件占比
                  }  
                  else  //if(c2.equals("n")||c2.equals("no"))
                   {
                      value[cla][1]++;          //第二列来存储不属于最终列的总数
                      no_to_attr++;            //全部数据中不符合数量 
                   }                 
                   
            }
            //writer.close(); 
            book.close();   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   	
    	//以下是用来计算信息增益的程序段
        System.out.println("属性长度"+list.size());
        sum=yes_to_attr+no_to_attr;
        BigDecimal ysum=mlog.getDivide(yes_to_attr,sum);
        BigDecimal nsum=mlog.getDivide(no_to_attr,sum);
        info=(nsum.multiply(mlog.getLog(nsum))).abs().add((ysum.multiply(mlog.getLog(ysum))).abs());  
    	String[] at=new String[list.size()];
    	System.out.println("属性长度-------------"+list.size());
        for(int p=0;p<list.size();p++)                
     	 {  
        	if(!(list.get(p).equals("")||list.get(p).equals(null)))
     	       { 
        		at[p]=list.get(p); 
        		}
     		int a=value[p][0];
     	    int b=value[p][1];
     	    System.out.println("|"+list.get(p)+"| "+a+" | "+b+" |");
     	    int c=a+b;
     	    BigDecimal asum=mlog.getDivide(a,c );  
     	    BigDecimal bsum=mlog.getDivide(b,c);   //asum是肯定总量，bsum是否定总量
     	    BigDecimal csum=mlog.getDivide(c,sum);
     		if(a==0&&b!=0)
     		   infoD=infoD.add((csum.multiply(bsum.multiply(mlog.getLog(bsum)))).abs());	   
     		else if(b==0&&a!=0)
     		    infoD=infoD.add((csum.multiply(asum.multiply(mlog.getLog(asum)))).abs());
     		else if(a!=0&&b!=0)   
     		    infoD=infoD.add(((csum.multiply(asum.multiply(mlog.getLog(asum)))).abs()).add((csum.multiply(bsum.multiply(mlog.getLog(bsum)))).abs()));
     		else if(a==0&&b==0)
     		    infoD=infoD.add(new BigDecimal(0));
        }
    	for(int i=0;i<at.length;i++)
    	{
    		if(i==0&&at[i]!=null)
    		{
    			attr_part=at[i];
    		}
    		else
    		{
    		  if(at[i]!=null)
    			 { 
    			  attr_part=attr_part+","+at[i];
    			  }
    		}
    	}
    	System.out.println("attr_part长度是："+attr_part.length());
    	try{
    		String f=HEAD+t+LAST;
    		System.out.println("正在尝试写入文件-------"+f);
    		BufferedWriter w = new BufferedWriter(new FileWriter(new File(f)));
    	   w.append(attr_part.trim());   //去掉双引号  
    	   w.flush();
           w.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}     
    	attr_part="";
    	gain=info.subtract(infoD).toString();
    	System.out.println("当前属性信息增益："+infoD);
    	list.clear();     
    	//清空list表，以便下次存储时不会由于前面的插入元素受干扰
    	gain_and_support[0]=gain;
    	gain_and_support[1]=yes_to_attr+"";
    	gain_and_support[2]=no_to_attr-1+"";
    	return gain_and_support;
    }

    public void writeInformation(){//由于调用 getMap方法中包含此方法，所以现在基本没用
    	String informationGain="";    	
    	String[] attall=PublicData.getSelvar();    //只考虑用户选择的属性的信息增益值
    	for(int a=0;a<attall.length;a++){
    		if(a==0){
    			informationGain=attall[a]+","+this.getInformationGain(PublicData.getTarvar(),attall[a])[0];
    		}
    		else
    		 informationGain=informationGain+","+attall[a]+","+this.getInformationGain(PublicData.getTarvar(),attall[a])[0];
    	}
    	PublicData.setInformationGain(informationGain);
    }

    public static void main(String[] args){
    	InformationGain ing=new InformationGain();
    	String[] sel=PublicData.getSelvar();
    	String tar=PublicData.getTarvar();
    	 HashMap<String,String[]> h=ing.getMap();
    	for(int i=0;i<sel.length;i++){
    		System.out.println("当前测试属性的信息增益："+ing.getInformationGain(tar,sel[i])[0]);
    		System.out.println("当前测试属性支持度："+ing.getInformationGain(tar,sel[i])[1]);
    		System.out.println("当前测试属性否决度："+ing.getInformationGain(tar,sel[i])[2]);
    		System.out.println("属性"+sel[i]+"包括");
    		for(int j=0;j<h.get(sel[i]).length;j++){
    			System.out.print(h.get(sel[i])[j]+"\t");
    		}
    	}
    	//ing.writeInformation();
    	System.out.println("MAP=="+ing.getMap().size());
    }
  /**
   * 返回一个属性与其分枝的Map对应表，并且将每个属性的信息增益写入 Data/DescionTree/varinfg.txt文件中,作为执行第一步  
   * @return 属性与其分枝的Map对应表
   */
   public HashMap<String,String[]> getMap(){    
	   String informationGain="";  
	   HashMap<String,String[]> hs=new HashMap<String,String[]>();
	   String[] sel=PublicData.getSelvar();
    	for(int i=0;i<sel.length;i++)
    	{ 		
    		BufferedReader r;
    		String s=null;
    		String fl=HEAD+sel[i]+LAST;
    		try{
    			r=new BufferedReader(new FileReader(fl));
    			s=r.readLine();     
    		}catch(IOException e){
    	         e.printStackTrace();		
    		 }
    	//文件被缓存在字符串 s 中，之后借用这个try-catch立马删除其文件，对用户而言是透明的
         File file=new File(fl);
    	 if(file.isFile()&&file.exists())
    	 {
    		file.delete();
    		System.out.println("删除文件"+fl+"成功！");
    	  }	
    	//写入信息增益相关信息。informationGain=age,0.003,student,0.098,.....
    	 if(i==0)
    	 {   
    		 System.out.println("PublucData.getTarVar=="+PublicData.getTarvar()+"\t"+sel[i]);
    		 informationGain=sel[i]+","+this.getInformationGain(PublicData.getTarvar(),sel[i])[0];
    	 }
    	else
    	  {
    	   informationGain=informationGain+","+sel[i]+","+this.getInformationGain(PublicData.getTarvar(),sel[i])[0];
    	  }
    	 String[] str=s.split(",");	
    	 hs.put(sel[i],str);
    	}
       PublicData.setInformationGain(informationGain);	
       System.out.println("hs有多少元素："+hs.size());
	   return hs;   
   }
}
