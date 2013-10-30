package Genera_decis_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//公共数据类，主要是操作文件的文件名和属性名
public class PublicData {
  // public static String file;   //文件名
   public  static String[] attr;   //属性名
   //public static final String attr_and_part="d:/predata/attr_and_part.txt";
   public static final String partrule="d:/predata/partrule.txt";    //存储决策树分裂的所有规则，所有分支,每行一条,由头结点往下以逗号分隔
                                                                   //设置时，必须传入二维数组，获取时也是二维数组
   public static final String FileDataStatic="d:/predata/FileDataSatic.txt"; 
   public static final String filename="d:/predata/filename.txt";
   public static final String attrname="d:/predata/attr.txt";
   public static final String tarvar="d:/predata/tarvar.txt";    //存储决策树中目标属性
   public static final String selvar="d:/predata/selvar.txt";   //存储决策树中选择度量属性
   public static final String varInformationGain="d:/predata/varinfg.txt";  //存储属性的信息增益，奇数(1,3,..)index存储增益，偶数存储属性
   public static final String  descionTreeRule="d:/predata/DescionTree/rule.txt";
   public static final String clusterAttr="d:/predata/Cluster/SelAttr.txt";
   public static final String clusterNum="d:/predata/Cluster/clusterNum.txt";
   public static final String clusterResult="d:/predata/Cluster/clusterResult.txt";
   public static final String clusterInitCore="d:/predata/Cluster/clusterInitCore.txt";
   
   
public static String getFile(){
	BufferedReader read;
	String f=null;
	try {
		read = new BufferedReader(new FileReader(filename));
		f=read.readLine();                 //文件中只会存储文件名一行
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("文件不存在！");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return f;
}
public static void setInformationGain(String gain){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(varInformationGain)));
	   writer.append(gain.replaceAll("\"", ""));   //去掉双引号
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}	
}
public static String getVarinformationgain() {           //将返回一个长的字符串，包含属性和信息增益，二者紧跟，全部以逗号分隔
	BufferedReader r;
	String s=null;
	try{
		r=new BufferedReader(new FileReader(varInformationGain));
		s=r.readLine();
	}catch(IOException e){
         e.printStackTrace();		
         System.out.println("获取信息增益失败，没有找到文件");
	}
	return s;
}
public static void setFile(String f){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
	   writer.append(f.replaceAll("\"", ""));   //去掉双引号
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public static String[] getAttr() {
	BufferedReader r;
	String s=null;
	try{
		r=new BufferedReader(new FileReader(attrname));
		s=r.readLine();
		attr=s.split(",");                //假若属性有多行就会有问题
	}catch(IOException e){
         e.printStackTrace();		
         System.out.println("没有找到文件");
	}
	return attr;
}
public static void setAttr(String[] attr) {    //其实就是将属性全部存储在attrname文件中
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(attrname)));
		for(int i=0;i<attr.length;i++){
			if(i==0){
				writer.append(attr[i].replaceAll("\"", "").trim());
			}
			else{
				writer.append(","+attr[i].replaceAll("\"", "").trim());	//去掉双引号	
			}
			writer.flush();
		}   
	} catch (IOException e) {
		e.printStackTrace();
	}	
}
public static String getTarvar() {
	String tar=null;
	BufferedReader r;
	try{
		r=new BufferedReader(new FileReader(tarvar));
		tar=r.readLine();
	}catch(IOException e){
         e.printStackTrace();		
	}
	return tar;
}
public static String[] getSelvar() {
	String var[]={};
	BufferedReader r;
	String s=null;
	try{
		r=new BufferedReader(new FileReader(selvar));
		s=r.readLine();
		 var=s.split(",");                //假若属性有多行就会有问题
	}catch(IOException e){
         e.printStackTrace();		
	}
	return var;
}
  public void setSelvar(String[] sel){      //设置决策树中已经选择的属性
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(selvar)));
			writer.append(sel[0]);
			for(int i=1;i<sel.length;i++){
				writer.append(","+sel[i].replaceAll("\"", "").trim());	
				writer.flush();
			}   
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
  }
  public void setTarvar(String tar){              //设置决策树中目标属性  
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(tarvar)));
		   writer.append(tar.replaceAll("\"", ""));
		   writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
  }
  public static void setDescionTreeRule(String rule){              //设置决策树中目标属性  
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(descionTreeRule),true));
		   writer.append(rule.replaceAll("\"", ""));
		   writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
}
  //用来清空规则，在我们重新写入规则之前
public static void clearRule(){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(descionTreeRule),true));
	   writer.append("");
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
//============================================聚类用的数据==========================
public static void setClusterAttr(String var) {
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterAttr)));
	   writer.append(var.replaceAll("\"", ""));
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}

}
public static String[] getClusterAttr() {
	String var[]={};
	BufferedReader r;
	String s=null;
	try{
		r=new BufferedReader(new FileReader(clusterAttr));
		s=r.readLine();
		 var=s.split(",");                //假若属性有多行就会有问题
	}catch(IOException e){
         e.printStackTrace();		
	}
	return var;
}
public static void setClusterNum(String n){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterNum)));
	   writer.append(n.replaceAll("\"", ""));
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public static int getClusterNum(){
	int num=0;
	BufferedReader r;
	String s=null;
	try{
		r=new BufferedReader(new FileReader(clusterNum));
		s=r.readLine();
	    num=Integer.parseInt(s);
	}catch(IOException e){
         e.printStackTrace();		
	}
	return num;
}
//将我们随机初始化的聚类中心存储起来以便比较
public static void clearInitCore(){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterInitCore)));
	   writer.append("");
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public static void setClusterInitCore(String str){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterInitCore),true));
	   writer.append(str.replaceAll("\"", ""));
	   writer.append("\r\n");
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}


//存储聚类结果
public static void setClusterResult(String n){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterResult),true));
	   writer.append(n.replaceAll("\"", ""));
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
//每次开始聚类的时候都必须先清除之前文本的聚类结果，我们设置了清空方法
public static void clearCluster(){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterResult)));
	   writer.append("");
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
  //***********************************************暂时未使用
  public static void setStaticData(String f){
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(FileDataStatic),true));
		   writer.append(f.replaceAll("\"", ""));   //去掉双引号
		   writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  
}
