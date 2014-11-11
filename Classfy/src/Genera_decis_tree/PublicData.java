package Genera_decis_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//涓昏鏄彁渚涘涓�簺鍏叡鏁版嵁鐨勬搷浣滄帴鍙ｆ柟娉�
public class PublicData {
  // public static String file;   //锟侥硷拷锟斤拷
   public  static String[] attr;   //褰撳墠鏁版嵁鎵�湁灞炴�鍒楄〃
   //public static final String attr_and_part="d:/predata/attr_and_part.txt";
   /**
    * 鍐崇瓥鏍戞渶缁堢殑鍐崇瓥鍒嗘敮锛屼緥濡傛垜浠湁灞炴� wether(rain,sunny),windy(strong,weak),fish?[鍐冲畾灞炴�](yes,no)
    * 鍒欏彲浠ユ湁濡備笅瑙勫垯锛�rain,weak,no;    sunny,weak,yes鈥︹�
    */
   public static final String partrule="/Data/partrule.txt";
   
   public static final String FileDataStatic="Data/DescionTree/FileDataSatic.txt";
   public static final String filename="Data/DescionTree/filename.txt";//褰撳墠鐢ㄦ埛鏁版嵁鏂囦欢鎵�湪鐩綍
   public static final String attrname="Data/DescionTree/attr.txt";    //褰撳墠鏂囦欢鎵�湁灞炴�鍒楄〃
   public static final String tarvar="Data/DescionTree/tarvar.txt";    //鐩爣灞炴�涔熷嵆浣滀负鏈�粓鍒嗙被鐨勫睘鎬�
   public static final String selvar="Data/DescionTree/selvar.txt";   //鐢ㄦ埛閫夋嫨浜嗗摢浜涘睘鎬�
   /**
    * //姣忎釜灞炴�鐨勪俊鎭鐩婏紝鎸夌収澶у皬鎺掑簭,骞朵笖灞炴�鍚嶄笌鍏蜂綋澶у皬闂撮殧寮�潵銆傚睘鎬у悕绉板垎鍒崰 0,2,4鈥︹�鍏蜂綋澶у皬鍗�1,3,5鈥︹�
    */
   public static final String varInformationGain="Data/DescionTree/varinfg.txt";  
   public static final String  descionTreeRule="Data/DescionTree/rule.txt";
   
   /************************浠ヤ笅鏄仛绫婚儴鍒嗙殑鏁版嵁鎿嶄綔********************************/
   public static final String clusterAttr="Data/Cluster/SelAttr.txt";    //鐢ㄦ埛閫夋嫨灞炴�
   public static final String clusterNum="Data/Cluster/clusterNum.txt";  //鐢ㄦ埛閫夋嫨浜嗗灏戜釜鑱氱被涓績
   public static final String clusterResult="Data/Cluster/clusterResult.txt";    //鑱氱被缁撴灉
   public static final String clusterInitCore="Data/Cluster/clusterInitCore.txt";//鍒濆鑱氱被涓績
   
   
public static String getFile(){
	BufferedReader read;
	String f=null;
	try {
		read = new BufferedReader(new FileReader(filename));
		f=read.readLine();                 //鏂囦欢璺緞涓�埇涓�涔嬪唴
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("鎵句笉鍒版枃浠讹紒璇诲彇鏂囦欢閿欒");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return f;
}
public static void setInformationGain(String gain){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(varInformationGain)));
	   writer.append(gain.replaceAll("\"", ""));   //去锟斤拷双锟斤拷锟�
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}	
}
public static String getVarinformationgain() {           //锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟街凤拷锟斤拷锟斤拷锟皆猴拷锟斤拷息锟斤拷锟芥，锟斤拷锟竭斤拷锟斤拷全锟斤拷锟皆讹拷锟脚分革拷
	BufferedReader r;
	String s=null;
	try{
		r=new BufferedReader(new FileReader(varInformationGain));
		s=r.readLine();
	}catch(IOException e){
         e.printStackTrace();		
         System.out.println("计算信息增益");
	}
	return s;
}
public static void setFile(String f){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
	   writer.append(f.replaceAll("\"", ""));   //去锟斤拷双锟斤拷锟�
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
		attr=s.split(",");                //锟斤拷锟斤拷锟斤拷锟斤拷锟叫讹拷锟叫就伙拷锟斤拷锟斤拷锟斤拷
	}catch(IOException e){
         e.printStackTrace();		
         System.out.println("没锟斤拷锟揭碉拷锟侥硷拷");
	}
	return attr;
}
public static void setAttr(String[] attr) {    //锟斤拷实锟斤拷锟角斤拷锟斤拷锟斤拷全锟斤拷锟芥储锟斤拷attrname锟侥硷拷锟斤拷
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(attrname)));
		for(int i=0;i<attr.length;i++){
			if(i==0){
				writer.append(attr[i].replaceAll("\"", "").trim());
			}
			else{
				writer.append(","+attr[i].replaceAll("\"", "").trim());	//去锟斤拷双锟斤拷锟�
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
		 var=s.split(",");                //锟斤拷锟斤拷锟斤拷锟斤拷锟叫讹拷锟叫就伙拷锟斤拷锟斤拷锟斤拷
	}catch(IOException e){
         e.printStackTrace();		
	}
	return var;
}
  public void setSelvar(String[] sel){      //锟斤拷锟矫撅拷锟斤拷锟斤拷锟斤拷锟窖撅拷选锟斤拷锟斤拷锟斤拷锟�
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
  public void setTarvar(String tar){              //锟斤拷锟矫撅拷锟斤拷锟斤拷锟斤拷目锟斤拷锟斤拷锟斤拷  
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(tarvar)));
		   writer.append(tar.replaceAll("\"", ""));
		   writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
  }
  public static void setDescionTreeRule(String rule){              //锟斤拷锟矫撅拷锟斤拷锟斤拷锟斤拷目锟斤拷锟斤拷锟斤拷  
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(descionTreeRule),true));
		   writer.append(rule.replaceAll("\"", ""));
		   writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
}
  //锟斤拷锟斤拷锟斤拷展锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟叫达拷锟斤拷锟斤拷之前
public static void clearRule(){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(descionTreeRule),true));
	   writer.append("");
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
//============================================锟斤拷锟斤拷锟矫碉拷锟斤拷锟�=========================
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
		 var=s.split(",");                //锟斤拷锟斤拷锟斤拷锟斤拷锟叫讹拷锟叫就伙拷锟斤拷锟斤拷锟斤拷
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
//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷始锟斤拷锟侥撅拷锟斤拷锟斤拷锟侥存储锟斤拷锟斤拷锟皆憋拷冉锟�
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


//锟芥储锟斤拷锟斤拷锟斤拷
public static void setClusterResult(String n){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterResult),true));
	   writer.append(n.replaceAll("\"", ""));
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
//每锟轿匡拷始锟斤拷锟斤拷锟绞憋拷蚨急锟斤拷锟斤拷锟斤拷锟斤拷之前锟侥憋拷锟侥撅拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷辗锟斤拷锟�
public static void clearCluster(){
	try{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(clusterResult)));
	   writer.append("");
	   writer.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
  //***********************************************锟斤拷时未使锟斤拷
  public static void setStaticData(String f){
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(FileDataStatic),true));
		   writer.append(f.replaceAll("\"", ""));   //去锟斤拷双锟斤拷锟�
		   writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  
}
