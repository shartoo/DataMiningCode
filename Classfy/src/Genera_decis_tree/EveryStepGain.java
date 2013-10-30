package Genera_decis_tree;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
public class EveryStepGain {
	public static final float MIN_GAIN=(float)0.001;
	InformationGain ing=new InformationGain();
	ShowTreePane tree=new ShowTreePane();
	MathLog mlog=new MathLog();      //引用自定义的计算2的对数函数，此函数有getLog()方法
	public HashMap<String,String[]> hs=ing.getMap();//谨慎调用，每次调用之后都要多次遍历Excel表，只要一次调用只有就可以只接在其同名文件中得到list
    public  void showDescionTree(){
    	EveryStepGain  erv=new EveryStepGain();  
        System.out.println("*************************************执行整个过程之前****************************");
        LinkedList<TreeNode> newlist=erv.SetTree();
        System.out.println("*************************************执行整个过程之后****************************");
        System.out.println("已经加入多少个结点到树中了？？？？？？？？？？"+newlist.size());
        for(int i=0;i<newlist.size();i++){
        System.out.println("当前结点是："+newlist.get(i).name+"\t信息增益是"+newlist.get(i).gain+"\t支持度是"+newlist.get(i).YES
        		+"\t否决度是"+newlist.get(i).NO+"\t深度是"+newlist.get(i).Row+"\t广度是"+newlist.get(i).Comlumn);
   
        }
        JFrame f=new JFrame("决策树显示");
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\predata\\120.jpg");
  	    f.setIconImage(icon);
        JScrollPane scroll=new JScrollPane();
        scroll.setViewportView(new ShowTreePane());
        f.add(scroll);
        f.setLocation(200,100);
        f.setSize(1000,900);
        f.setVisible(true);
    }
    //参数中name代表了属性的其中一个分支名称，返回的将是该名称在Excel表中所有位置
    public TreeSet<Integer> setLocation(String name,TreeSet<Integer> field){
    	int yes_attr=0;
    	System.out.println("产生分支过程选择的属性分支是："+name);
    	TreeSet<Integer> set=new TreeSet<Integer>();
    	String[] t={};
    	int mp=0;    //存储所给名称的属性在Excel表中的列数
    	int c=0;
    	String local="";
    	String[] sel=PublicData.getSelvar();
    	for(int l=0;l<sel.length;l++){
    		t=hs.get(sel[l]);
    		System.out.println("属性是"+sel[l]+"有"+t.length+"个分支");
    		System.out.print("分别是：");
    		for(int n=0;n<t.length;n++){
    			System.out.print(t[n]+"\t");
    			if(t[n].equals(name)){
    				local=sel[l];
    			}
    		}
    		System.out.println();
    	}
    	String[] all=PublicData.getAttr();  	
    	for(int p=0;p<all.length;p++){
    		if(all[p].equals(local))
    			mp=p;
    		else if(all[p].equals(PublicData.getTarvar())){
    			c=p;
    		}
    	}
    	try {   
            Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
            Sheet sheet = book.getSheet(0);     
            Iterator<Integer> it=field.iterator();
            //for(int i=1;i<sheet.getRows();i++)   //默认第一列为属性辨别列，不参与计算
            while(it.hasNext())
            {        int i=it.next(); 	  
           		     Cell cell = sheet.getCell(mp, i);   //获取当前属性所在列
                     String result = cell.getContents().replaceAll("\"", "").trim();  
                     //System.out.print(result+"\t");
                     Cell c2=sheet.getCell(c,i);
                     String tar=c2.getContents().replaceAll("\"", "").trim();    //必须去除字符中的双引号
    	             if(result.equals(name)){    	
    	            	 set.add(i);
    	            	 if(tar.equals("yes")||tar.equals("y")){ 	            		 
    	            		 yes_attr++;
    	            	 }
    	             }
            }
            book.close();   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   	
        System.out.println("最后的set大小是："+set.size());
        int last=0;   //鉴于TreeSet的顺序性，最后一个元素必然最大，因此将支持度与最后一个节点相加后再放入set中就可
                                       //即存储了当前字符的行位置，也能知道其支持度
        if(set.size()>0){
        	System.out.println("set最后位置是"+set.last());
        	last=set.last()+yes_attr;
        }
        else
        {   System.out.println("该分支下已经没有元素，返回的set集合为空！");
        	return set;
        }
       
        if(set.add(last)){
        System.out.println("加入成功！set的最后一个元素");
        }
        else 
        	System.out.println("加入失败！set最后一个元素------");    //表明最后一个元素与之前的元素重复，也即yes_attr=0
        Iterator<Integer> it=set.iterator();
        while(it.hasNext())
        	System.out.print(it.next()+"\t");
        return set;
    }
    
    //对于给定的Excel行数set来计算其信息增益，需要指定行数set的集合和当前需要计算的另外一个属性
    public String computeGain(TreeSet<Integer> set,String local){
         ArrayList<String> list=new ArrayList<String>();   
    	 String gain="";
    	int com=0;    //获取当前属性所在列
    	int cla=0;   //代表分类数目
    	int part=0;   //最终分类所在列数
    	Iterator<Integer> allline=set.iterator();
    	String[] attr=PublicData.getAttr();
    	for(int i=0;i<attr.length;i++){
    		if(attr[i].equals(local)){
    			com=i;
    		}
    		else if(attr[i].equals(PublicData.getTarvar())){
    			part=i;
    		}
    	}
    	int sum=set.size();    //总行数
    	int[][] value=new int[30][2];
    	int yes_to_attr=0;   //属于当前类的数目
    	int no_to_attr=0;
    	BigDecimal info=new BigDecimal(0);     //计算元组分类所需的期望信息
    	BigDecimal infoD=new BigDecimal(0);    //存储当前列属性的信息增益
       	java.text.DecimalFormat DataFormat=(java.text.DecimalFormat)java.text.DecimalFormat.getInstance();
	      //为了得到整数相处保留三位小数的结果。注意，经过这样的处理后，计算得到的结果是String类型
  	  DataFormat.applyPattern("#.####"); //为了得到整数相处保留三位小数的结果
  	  DecimalFormat df1 = new DecimalFormat("#.######");
  	   try {   
           Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
           Sheet sheet = book.getSheet(0);      
           while(allline.hasNext())   //默认第一列为属性辨别列，不参与计算
           {         int i=allline.next(); 	  
          		     Cell cell = sheet.getCell(com, i);   //获取当前属性所在列
          		     Cell cell2=sheet.getCell(part,i);     //获取结果列，即训练元组最终分类类别那一列
          		     String c2=cell2.getContents().replaceAll("\"", " ").trim();
                    String result = cell.getContents().replaceAll("\"", " ").trim();  
                    if(list.contains(result)==false&&result!=null)      //将所选属性加入到list中
                    {   list.add(result);   cla=list.indexOf(result);   
                    }
                    else if(list.contains(result)==true)
                   	  cla=list.indexOf(result);    //以当前属性的类别（String类型）所在list中的索引为value的第一维
                    if(c2.equals("y")||c2.equals("yes"))   //以二维数组value的第二维的第一列来存储属于最终类的总数
                    {	
                   	    value[cla][0]++;
                        yes_to_attr++;          //最终列中符合条件占比
                    }  
                    else  //if(c2.equals("n")||c2.equals("no"))
                    {
                   	 value[cla][1]++;             //第二列来存储不属于最终列的总数
                   	 no_to_attr++;            //用来计算信息增益，元组分类所需的期望信息 
                    }                                   
           }
           //writer.close(); 
           book.close();   
       } catch (Exception e) {   
           e.printStackTrace();   
       }   	
       sum=yes_to_attr+no_to_attr;
       if(yes_to_attr==0){       //如果全部都是否定结点，就返回叶子
    	   gain="NLeaf";
    	   return gain;
       }
       else if(no_to_attr==0){   //如果全部都是肯定结点，就返回叶子
    	   gain="YLeaf";
    	   return gain;
       }
       
       BigDecimal ysum=mlog.getDivide(yes_to_attr,sum);
       BigDecimal nsum=mlog.getDivide(no_to_attr,sum);
      info=(nsum.multiply(mlog.getLog(nsum))).abs().add((ysum.multiply(mlog.getLog(ysum))).abs());
      
   	  String[] at=new String[list.size()];
      for(int p=0;p<list.size();p++)        
    	  
   	   {  /**
    	  if(!(list.get(p).equals("")||list.get(p).equals(null)))
   	                     { at[p]=list.get(p);    }
   	        */             
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
     gain=info.subtract(infoD).toString();
	 System.out.println("当前属性信息增益："+infoD+"   属性是"+attr[com]+"     来自computeGain");
    return gain;
    }
    
    
//首先获取我们在InformationGain类中产生的所有属性的信息增益，从中比较得出最好的属性，然后按照该属性依次向后遍历其他属性
public LinkedList<TreeNode> SetTree(){
    	LinkedList<TreeNode> list=new LinkedList<TreeNode>();  
    	PublicData.clearRule();                      //在我们接下来生成决策树规则必须清楚规则
    	String[] allgain=PublicData.getVarinformationgain().split(",");
        int tp=1;
        //****************第一步先从默认数据中心PublicData中获取所选属性的最高信息增益属性作为头结点***************
        int allline=0;//第一个属性的行数，代表了Excel表的所有行数
        float MAX=0;   //最大信息增益
        String MAX_attr="";
    	while(tp<allgain.length){
    		String s=allgain[tp];
    		Float f=Float.valueOf(s);
    		if(f>MAX){
    			MAX=f;
    			MAX_attr=allgain[tp-1];
    		}
    		tp+=2;
    	}
    	System.out.println("信息增益最高的是："+MAX+"\t"+"属性是："+MAX_attr);
    	//先算得头结点信息
        String[] head=ing.getInformationGain(PublicData.getTarvar(),MAX_attr); 	
        float f1=Float.parseFloat(head[1]);
        float f2=Float.parseFloat(head[2]);
        //头结点的位置是0行0列
        tree.addNode(MAX_attr, (int)f1, (int)f2, Float.parseFloat(head[0]), 0, 0);
    	list.add(new TreeNode(MAX_attr,(int)f1,(int)f2, Float.parseFloat(head[0]), 0, 0));
    	//*****************已经加入最终存储结点链表**************************************
    	System.out.println("加入已经成功，长度是"+list.size());
    	System.out.println("以下是各个值"+"\t属性名"+list.get(0).name+"\t支持度"+list.get(0).YES+"\t否决度"+list.get(0).NO+"\t信息增益"+list.get(0).gain);
        //再次打开Excel表，得到所有的行数
    	try {   
            Workbook book = Workbook.getWorkbook(new File(PublicData.getFile()));    
            Sheet sheet = book.getSheet(0);
            allline=sheet.getRows();
            book.close();   
    } catch (Exception e) {   
        e.printStackTrace();   
    } 
    TreeSet<Integer> HeadAttr=new TreeSet<Integer>();   //存储头结点的所有行数
    for(int i=0;i<allline;i++){
    	HeadAttr.add(i);
    }
   
    //正式开始遍历
    HashMap<String,String[]> h=new HashMap<String,String[]>();    //先将保存了所有属性及其分支的Map复制到新的Map中，避免对对操作造成后面的使用问题
    h.putAll(hs);         //只有这样才是真正的复制到新表中
    System.out.println("原有表的长度是："+hs.size()+"是否包含"+MAX_attr+hs.containsKey(MAX_attr));
    System.out.println("新Map长度是："+h.size()+"是否包含"+"credit"+h.containsKey("credit"));
   
    //遍历之前存储属性及其分支的HashMap中所有value及String[] 
    //先设置好行数和列数变量，HashMap的Key每向后遍历一次，行数必须加一，HashMap的value即list每向后遍历一次，列数加一
    
    //先建立处理Map，其中的key存储的是当前分支的连接String（如：age+yes+income...），其中value存储的是当前key下剩余属性列表
    //处理过程会不断加入新的key(表现在长度的增加),而value的长度不断减少（但是会不断增多），判断条件是value是否全部都是空，树叶结点会清空value
    HashMap<String,HashMap<String, String[]>> source=new HashMap<String,HashMap<String, String[]>>();
	HashMap<String,HashMap<String, String[]>> newMap=new HashMap<String,HashMap<String, String[]>>();   //两个Map交换
	HashMap<String,TreeSet<Integer>> sset=new HashMap<String,TreeSet<Integer>>();                        //与HashMap同步的存储各个分支的位置的子Set
    source.put(MAX_attr,h);
    sset.put(MAX_attr,HeadAttr);
    int Att_length=1;
    boolean flag=true;    //整个程序的中心控制
    while(flag){
    	Set<Map.Entry<String, HashMap<String, String[]>>> souset = source.entrySet();
    	Iterator<Map.Entry<String, HashMap<String, String[]>>> souit=souset.iterator();
    	int Column=0; 
    	while(souit.hasNext())
    	    {
    	        Map.Entry<String, HashMap<String, String[]>> souentry =(Map.Entry<String, HashMap<String, String[]>>) souit.next();
    	        HashMap<String,String[]> fg=souentry.getValue();
    	        String keyName=souentry.getKey();
    	        
    	        System.out.println("fg的长度是多少---"+fg.size());
    	        if(fg.size()>0)
    	        //if(keyName!="Leaf")
    	        {
    	           String[] allstr=keyName.split(",");
    	           TreeSet<Integer> parentset=sset.get(keyName);
    			   String key=allstr[allstr.length-1];
    			   String[] part=hs.get(key);           //最后一个字符串即分裂点,获取其分支属性,注意这里必须是从未修改的hs，不可以是fg
    			   HashMap<String,String[]> newFg=souentry.getValue();
    			   newFg.putAll(fg);         //即将遍历fg中所有属性及其分支，我们只能在其替代表上执行删除
    			   newFg.remove(key);
    			   System.out.println("起始的Map长度是：（用于查看Map为何早早为空）"+newFg.size()); 			   
    			   HashMap<String,String[]> tempMap=new HashMap<String,String[]>();  			   
    			   for(int i=0;i<part.length;i++)
    			     {
    				   tempMap.putAll(newFg);          //临时的Map用来控制，防止接下来随着分支而依次删除而不是从原Map中删除一个
    				   System.out.println(part[i]+"分支\t-----------------------"+"长度是"+part.length);
    				   TreeSet<Integer> s=this.setLocation(part[i],parentset);   //返回哈希表中分支属性在Excel表中的位置,注意该set最后一个元素存储了支持度信息			
    				   Iterator<Integer> its=s.iterator();
    				   System.out.println("执行求交集之后的set元素包含");
    				   while(its.hasNext())
    					   System.out.print(its.next()+"\t");
    				   if(s.size()>0)
    				   { 
                  	      int a=s.last();                 //由于设计的时候是将组后的一个元素原本的最后一个元素加上支持度得到来的，所以使用前必须先移除
                          s.remove(a);
                          System.out.println("移除之后的set还剩下多少元素===="+s.size());
                          if(s.size()==0)
                        	  break;
                          int num=s.last();
                          int yes=a-num;    //得到当前属性分支的支持度
                          int no=s.size()-yes;   //得到当前属性分支的否决度
                          if(no<0){                  //如果no为负数代表最后加入set的值为0，也即yes为0
                        	  s.add(a);              //再将最后一个元素放回去
                        	  yes=0;
                        	  no=s.size();
                          }           
                          System.out.println("查看为何yes和no中存在负数,其中的set包含元素  "+s.size()+"  yes有多少   "+yes+"  no有多少    "+no);
                          //当前一步找出当前属性的分支下下一个最大信息增益属性----第三重循环
                          float MAX_gain=0;
                          String MAX_at="初始属性值";    //标记每次求得的最大信息增益的属性名称
                          Column++; 
                          Set<Map.Entry<String, String[]>> hset=newFg.entrySet();                                                           
                   	      Iterator<Map.Entry<String, String[]>> hit=hset.iterator();
                          while(hit.hasNext())
                             {   
                              Map.Entry<String, String[]> htmp=hit.next();   //从剩下的属性中寻找最大的信息增益值
                              String gain=this.computeGain(s,htmp.getKey() );    //返回其信息增益
                              if(gain.equals("YLeaf")){
                            	  gain=1+"";
                            	  MAX_gain=1;
                            	  MAX_at=htmp.getKey();
                              }                      
                              else if(gain.equals("NLeaf")){
                            	  gain=0+"";
                              }
                              else if(Float.parseFloat(gain)>MAX_gain)
                                {
                      	        MAX_gain=Float.parseFloat(gain);
                      	        MAX_at=htmp.getKey();      
                                }
                            }
                           tree.addNode(part[i]+","+MAX_at,yes,no,MAX_gain,Att_length,Column);
                           list.add(new TreeNode(MAX_at,yes,no,MAX_gain,Att_length,Column));
                           tempMap.remove(MAX_at);
                           System.out.println("起始的Map长度是：（用于查看Map为何早早为空）删除之后情况"+tempMap.size());
                           String newKeyName=keyName+","+part[i]+","+MAX_at;
                           if(MAX_gain==0||MAX_gain==1){
                        	   tempMap.clear();
                           }
                           System.out.println("tempMap中还有多少元素"+tempMap.size());
                           newMap.put(newKeyName,tempMap);
                           sset.put(newKeyName,s);
    				     }
    			     }
    			    }	
             }
                 Att_length+=2;                                   //由于每次加入得都是分支和下一个属性会增加2
             	source.clear();
            	source.putAll(newMap);                            //以新的Map替代原来的   
                int null_num=0;
                int Max_Attr_len=0;
                //遍历新Map，如果全部都是null的话表示，已经全部遍历完成
            	Set<Map.Entry<String, HashMap<String, String[]>>> checkset = source.entrySet();
            	Iterator<Map.Entry<String, HashMap<String, String[]>>> checkit=checkset.iterator();
            	while(checkit.hasNext())
            	    {
            		 Map.Entry<String, HashMap<String, String[]>> checkentry =(Map.Entry<String, HashMap<String, String[]>>) checkit.next();
         	         HashMap<String,String[]> lastfg=checkentry.getValue();
         	         String LastName=checkentry.getKey();
         	         if(LastName.length()>Max_Attr_len)
         	        	      Max_Attr_len=LastName.length();
         	         System.out.println("最后的结点到底是什么--"+LastName+"最后的Map长度是"+lastfg.size());
         	         //将得到的规则写入到文本中
         	         if(lastfg.size()==0){
         	        	 PublicData.setDescionTreeRule(LastName);
         	        	 PublicData.setDescionTreeRule("\r\n");
         	         }
            	     if(lastfg.size()>1)           //至于为何是大于1而不是0，我也无从解释。大于0会有问题，然而大于1却能得到正确答案
            	        {
            	    	 null_num++;
            	        }
            	    }   
            	//如果全部都是null,则跳出循环
                if(null_num==0){
                	flag=false;
                }           		
             }          	      
    return list;
       }       

  }