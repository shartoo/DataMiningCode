

import java.util.Scanner;
//用于获取属性字段数组类
public class GetArryList {
       public static String[] str;
	public Object[] getAttrList(){            //得到属性划分字段
		   Scanner scan=new Scanner(System.in);
		   System.out.println("***请输入属性标示字段！,以逗号分隔，回车结束输入！********");
		   String attr=scan.next();
		   String[] str=attr.split("，|,");
		   return str;
	}
	public int getAttrSum(){
		return str.length;
	}
}
