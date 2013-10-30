

import java.util.Arrays;
public class quickSort {

 /**
     * 快速排序
     * 
     * 基本思想
     * 　设当前待排序的无序区为R[low..high]，利用分治法可将快速排序的基本思想描述为：
     * ①分解： 
     * 在R[low..high]中任选一个记录作为基准(Pivot)，
     * 以此基准将当前无序区划分为左、右两个较小的子区间R[low..pivotpos-1)和R[pivotpos+1..high]，
     * 并使左边子区间中所有记录的关键字均小于等于基准记录(不妨记为pivot)的关键字pivot.key，
     * 右边的子区间中所有记录的关键字均大于等于pivot.key，
     * 而基准记录pivot则位于正确的位置(pivotpos)上，它无须参加后续的排序。
     * 注意：
     * 划分的关键是要求出基准记录所在的位置pivotpos。
     * 划分的结果可以简单地表示为(注意pivot=R[pivotpos])：
     * R[low..pivotpos-1].keys≤R[pivotpos].key≤R[pivotpos+1..high].keys
     * 其中low≤pivotpos≤high。
     * ②求解： 
     * 　通过递归调用快速排序对左、右子区间R[low..pivotpos-1]和R[pivotpos+1..high]快速排序。
     */
    
 /**
  * @author fangtuo 2008-09-20
  * @param pData 需要排序的数组
  * @param left 左边的位置,初始值为0
  * @param right 右边的位置,初始值为数组长度
  */
    public static void quickSort(int[] pData,int left,int right)
    {
     int i ,j ;
     int middle,temp ;
     i = left ;
     j = right ;
     middle = pData[left] ;
     while(true)
     {
      while((++i)<right-1 && pData[i]<middle) ;
      while((--j)>left && pData[j]>middle) ;
      if(i>=j)
       break ;
         temp = pData[i] ;
         pData[i] = pData[j] ;
         pData[j] = temp ;
      
     }
     pData[left] = pData[j] ;
     pData[j] = middle ;
        
     if(left<j)
      quickSort(pData,left,j) ;
     
     if(right>i)
      quickSort(pData,i,right) ;
    }

 public static void main(String[] args) {
  // TODO Auto-generated method stub
  int[] pData = new int[]{49,38,65,97,76,13,27} ;
  quickSort(pData,0,pData.length) ;
  System.out.println(Arrays.toString(pData)) ;

 }

}