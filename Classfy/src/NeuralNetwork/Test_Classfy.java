package NeuralNetwork;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test_Classfy {

    /**
     * @param args
     * @throws IOException
     * 测试程序第16行,创建一个BPNN,设定各层的神经元数目.
           测试程序第43行,通过train方法训练即可,输入向量和输出向量由自己指定,只需要满足创建的神经元的显层(即输入层)和输出层的规模即可
     */
	public Double trainData[][];
	public Double testTarget[][];

    public  void test_bp(){
        BP_Classfy bp = new BP_Classfy(32, 15, 4);
        Random random = new Random();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i != 1000; i++) {
            int value = random.nextInt();
            list.add(value);
        }
        
        for (int i = 0; i != 200; i++) {
            for (int value : list) {
                double[] real = new double[4];
                if (value >= 0)
                    if ((value & 1) == 1)
                        real[0] = 1;      //正奇数
                    else
                        real[1] = 1;    //正偶数
                else if ((value & 1) == 1)
                    real[2] = 1;        //负奇数
                else  
                    real[3] = 1;        //负偶数
                double[] binary = new double[32];
                int index = 31;
                do {
                    binary[index--] = (value & 1);
                    value >>>= 1;
                } while (value != 0);

                bp.train(binary, real);
            }
        }
        System.out.println("训练完毕，下面请输入一个任意数字，神经网络将自动判断它是正数还是复数，奇数还是偶数。");
        while (true) {
            byte[] input = new byte[10];
            try {
				System.in.read(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Integer value = Integer.parseInt(new String(input).trim());
            int rawVal = value;
            double[] binary = new double[32];
            int index = 31;
            do {
                binary[index--] = (value & 1);
                value >>>= 1;
            } while (value != 0);

            double[] result = bp.test(binary);

            double max = -Integer.MIN_VALUE;
            int idx = -1;

            for (int i = 0; i != result.length; i++) {
                if (result[i] > max) {
                    max = result[i];
                    idx = i;
                }
            }

            switch (idx) {
            case 0:
                System.out.format("%d是一个正奇数\n", rawVal);
                break;
            case 1:
                System.out.format("%d是一个正偶数\n", rawVal);
                break;
            case 2:
                System.out.format("%d是一个负奇数\n", rawVal);
                break;
            case 3:
                System.out.format("%d是一个负偶数\n", rawVal);
                break;
            }
        }
    }
    //载入训练数据
  public void setTrainData(){
	  
  }
  //载入测试数据
  public void setTargetData(){
	  
  }
  /**
  public static void main(String[] args){ 
  	Test_Classfy bp=new Test_Classfy();
  	bp.test_bp();
  }
  */
}

