package NeuralNetwork;
import java.util.Random;

/**
 * BPNN.
 * 
 * @author RenaQiu
 * 使用单层线性网络时，SDBP等价于LMS算法。但应用于多层网络时，SDBP(Steepest Descent Backpropagation最速下降反向传播算法)
 * 完全不同，单层线性网络的均方误差只有一个极小点，
 * ，并且具有常数曲率，但是多层网络的性能可能有多个局部极小点切在参数空间的不同区域曲率不同
 * 
 * 首先对于训练的样本（是随机生成的数字），将它转化为一个32位的向量，向量的每个分量就是其二进制形式对应的位上的0或1。
 * 将目标输出视作一个4维的向量，[1,0,0,0]代表正奇数，[0,1,0,0]代表正偶数，[0,0,1,0]代表负奇数，[0,0,0,1]代表负偶数。
训练样本为1000个，学习200次。
 */
public class BP_Classfy {
    /**
     * input vector.
     */
    private final double[] input;
    /**
     * hidden layer.
     */
    private final double[] hidden;
    /**
     * output layer.
     */
    private final double[] output;
    /**
     * target.
     */
    private final double[] target;

    /**
     * delta vector of the hidden layer .隐藏层敏感性
     */
    private final double[] hidDelta;
    /**
     * output layer of the output layer.输出层敏感性
     */
    private final double[] optDelta;

    /**
     * learning rate.
     */
    private final double eta;
    /**
     * momentum.
     */
    private final double momentum;

    /**
     * weight matrix from input layer to hidden layer.
     */
    private final double[][] iptHidWeights;
    /**
     * weight matrix from hidden layer to output layer.
     */
    private final double[][] hidOptWeights;
    /**
     * previous weight update.
     */
    private final double[][] iptHidPrevUptWeights;
    /**
     * previous weight update.
     */
    private final double[][] hidOptPrevUptWeights;

    public double optErrSum = 0d;

    public double hidErrSum = 0d;

    private final Random random;

    /**
     * Constructor.
     * <p>
     * <strong>Note:</strong> The capacity of each layer will be the parameter
     * plus 1. The additional unit is used for smoothness.
     * </p>
     * 
     * @param inputSize
     * @param hiddenSize
     * @param outputSize
     * @param eta
     * @param momentum    动量
     * @param epoch
     */
    public BP_Classfy(int inputSize, int hiddenSize, int outputSize, double eta,
            double momentum) {
        input = new double[inputSize + 1];
        hidden = new double[hiddenSize + 1];
        output = new double[outputSize + 1];
        target = new double[outputSize + 1];

        hidDelta = new double[hiddenSize + 1];
        optDelta = new double[outputSize + 1];
         //是多对多的模型，看书上198页图11-1               
        iptHidWeights = new double[inputSize + 1][hiddenSize + 1];
        hidOptWeights = new double[hiddenSize + 1][outputSize + 1];

        random = new Random(19881211);
        randomizeWeights(iptHidWeights);
        randomizeWeights(hidOptWeights);

        iptHidPrevUptWeights = new double[inputSize + 1][hiddenSize + 1];
        hidOptPrevUptWeights = new double[hiddenSize + 1][outputSize + 1];

        this.eta = eta;
        this.momentum = momentum;
    }
    private void randomizeWeights(double[][] matrix) {
        for (int i = 0, len = matrix.length; i != len; i++)
            for (int j = 0, len2 = matrix[i].length; j != len2; j++) {
                double real = random.nextDouble();
                matrix[i][j] = random.nextDouble() > 0.5 ? real : -real;
            }
    }

    /**
     * Constructor with default eta = 0.25 and momentum = 0.3.
     * 
     * @param inputSize
     * @param hiddenSize
     * @param outputSize
     * @param epoch
     */
    public BP_Classfy(int inputSize, int hiddenSize, int outputSize) {
        this(inputSize, hiddenSize, outputSize, 0.25, 0.9);
    }
    /**
     * Entry method. The train data should be a one-dim vector.
     * 
     * @param trainData
     * @param target
     */
    public void train(double[] trainData, double[] target) {
        loadInput(trainData);
        loadTarget(target);
        forward();
        calculateDelta();
        adjustWeight();
    }
    /**
     * Test the BPNN.
     * 
     * @param inData
     * @return
     */
    public double[] test(double[] inData) {
        if (inData.length != input.length - 1) {
            throw new IllegalArgumentException("Size Do Not Match.");
        }
        System.arraycopy(inData, 0, input, 1, inData.length);
        forward();
        return getNetworkOutput();
    }

    /**
     * Return the output layer.
     * 
     * @return
     */
    private double[] getNetworkOutput() {
        int len = output.length;
        double[] temp = new double[len - 1];
        for (int i = 1; i != len; i++)
            temp[i - 1] = output[i];
        return temp;
    }

    /**
     * Load the target data.
     * 
     * @param arg
     */
    private void loadTarget(double[] arg) {
        if (arg.length != target.length - 1) {
            throw new IllegalArgumentException("Size Do Not Match.");
        }
        System.arraycopy(arg, 0, target, 1, arg.length);
    }

    /**
     * Load the training data.
     * 
     * @param inData
     */
    private void loadInput(double[] inData) {
        if (inData.length != input.length - 1) {
            throw new IllegalArgumentException("Size Do Not Match.");
        }
        System.arraycopy(inData, 0, input, 1, inData.length);
    }

    /**
     * Forward.
     * 
     * @param layer0    //第0层的净输出   （此处将会被当做第1层的净输入）
     * @param layer1    //第1层的净输出----------见课本图11-1
     * @param weight
     */
    private void forward(double[] layer0, double[] layer1, double[][] weight) {
        // threshold unit.(临界值)
        layer0[0] = 1.0;
        for (int j = 1, len = layer1.length; j != len; ++j) {
            double sum = 0;
            for (int i = 0, len2 = layer0.length; i != len2; ++i)
                sum += weight[i][j] * layer0[i];
            layer1[j] = sigmoid(sum);
        }
    }

    /**
     * Forward.
     */
    private void forward() {
        forward(input, hidden, iptHidWeights);
        forward(hidden, output, hidOptWeights);
    }

    /**
     * Calculate output error.
     */
    private void outputErr() {
        double errSum = 0;
        for (int idx = 1, len = optDelta.length; idx != len; ++idx) 
        {
            double o = output[idx];
            //第一层传输函数采用的是log-sigmoid函数f(n)~1=1/(1+e~-n) 第二层   f(n)~2=n(线性函数)
            //对第一层函数求导数f(n)~1=(1-a~1)(a~1),    target[idx]-o=e为误差,a~1为第一层的最后输出值
            optDelta[idx] = o * (1d - o) *              (target[idx] - o);
            errSum += Math.abs(optDelta[idx]);
        }
        optErrSum = errSum;
    }

    /**
     * Calculate hidden errors.
     */
    private void hiddenErr() {
        double errSum = 0;
        for (int j = 1, len = hidDelta.length; j != len; ++j) {
            double o = hidden[j];
            double sum = 0;
            for (int k = 1, len2 = optDelta.length; k != len2; ++k)
                sum += hidOptWeights[j][k] * optDelta[k];
            hidDelta[j] = o * (1d - o) * sum;
            errSum += Math.abs(hidDelta[j]);
        }
        hidErrSum = errSum;
    }

    /**
     * Calculate errors of all layers.
     */
    private void calculateDelta() {
        outputErr();
        hiddenErr();
    }

    /**
     * Adjust the weight matrix.
     * 
     * @param delta     //敏感度
     * @param layer     //第i层的净输出
     * @param weight
     * @param prevWeight
     */
    private void adjustWeight(double[] delta, double[] layer,double[][] weight, double[][] prevWeight) 
    {
        layer[0] = 1;
        for (int i = 1, len = delta.length; i != len; ++i) {
            for (int j = 0, len2 = layer.length; j != len2; ++j) {
            	//权值更新
                double newVal = momentum * prevWeight[j][i] +eta * delta[i]* layer[j];
                weight[j][i] += newVal;
                prevWeight[j][i] = newVal;
            }        
        }
    }

    /**
     * Adjust all weight matrices.
     */
    private void adjustWeight() {
        adjustWeight(optDelta, hidden, hidOptWeights, hidOptPrevUptWeights);
        adjustWeight(hidDelta, input, iptHidWeights, iptHidPrevUptWeights);
    }

    /**
     * Sigmoid.
     * 
     * @param val
     * @return
     */
    private double sigmoid(double val) {
        return 1d / (1d + Math.exp(-val));
    }
}

