import java.util.ArrayList;
import java.util.Stack;


public class SubListTest {
    public void test(){
        ArrayList<String> al=new ArrayList<String>();
        ArrayList<String> bl=new ArrayList<String>();
        al.add("a");
        al.add("b");
        al.add("c");
        al.add("d");
        for(int i=al.size()-1;i>-1;i--){
        	bl.add(al.get(i));
            System.out.print("来自a表------"+al.get(i)+"\t");
            al=this.subSet(al, bl);
        }
        for(int j=0;j<bl.size();j++){
            System.out.println(bl.get(j));
        }
        
       ArrayList<String> cl=new ArrayList<String>();
       ArrayList<String> v=new ArrayList<String>();
        cl.add("c");
        cl.add("d");
        v=this.subSet(bl,cl);
        System.out.println("--------------"+v.size());
        for(int i=0;i<v.size();i++){
        	System.out.println("-----*********-------");
        	System.out.print(v.get(i));
        }
        
        
    }
    //求两个List表的差集合
    public  ArrayList<String> subSet(ArrayList<String> a,ArrayList<String> b){
    	for(int i=b.size()-1;i>-1;i--){
            if(a.contains(b.get(i))){
                a.remove(a.indexOf(b.get(i)));
            }
        }
    	Stack<String> tmp=new Stack<String>();
    	for(int j=0;j<a.size();j++){
    		tmp.push(a.get(j));
    	}
    	a.clear();
    	while(!tmp.empty()){
    	   a.add(tmp.pop());	
    	}
        return a;
    }
}
