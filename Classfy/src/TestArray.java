import java.util.ArrayList;


public class TestArray {
   public static void main(String[] args){
	   ArrayList<String> al=new ArrayList<String>();
	   for(int i=0;i<6;i++){
		   al.add("8888---9999");
	   }
	   
	   
	  String s="什么情况"; 
	  String[] s1=s.split(",");
	   
	   System.out.println("最后一个元素是"+s1[s1.length-1]);
	   
	   for(int j=0;j<al.size();j++){
		   System.out.print(al.get(j)+"\t");
	   }
   }
}
