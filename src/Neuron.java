import java.io.Serializable;

public class Neuron implements Serializable{

    private static final long serialVersionUID = 6529685098267757690L;

    double bias;
    double[] weights;
    double activation;
    double weightedInput;
    double expectedActivation;
    double neuronValue;
    double[] costGradientWeight;
    double costGradientBias;


    public Neuron(int numInNodes) {
        this.weights = new double[numInNodes];
        for (int i = 0; i < numInNodes; i++)
            this.weights[i] = Math.random() * ((Math.random() > 0.5) ? -1:1);
        this.bias = Math.random() * ((Math.random() > 0.5) ? -1:1);
        this.costGradientWeight = new double[this.weights.length];
    }

    /**
     * Use this constructor when creating a Neuralnetwork from a weight bias vector
     */
    public Neuron(double[] weights) {
        this.weights = weights;
        this.costGradientWeight = new double[weights.length];
    }

    public double forward(double[] inputVec) {
        double weighted = this.bias;
        for (int i = 0; i < this.weights.length; i++) {
            weighted += this.weights[i] * inputVec[i];
        }
        this.weightedInput = weighted;
        this.activation = ActivationFuncs.Sigmoid(this.weightedInput);
        return this.activation; 
    }

    private double CostDeriv(double expectedActivation) {
        return 2 * (this.activation - expectedActivation);
    }
    
    /**
     * 
     * @param weights These are the weights which are coming OUT of the neuron in question, used to get the NEW node values of this neuron
     * @param nodeVals This is the OLD node values, IE, the node values of the layer AFTER this neuron's layer.
     * @param activations The activations of the PREVIOUS layer
     */
    public void backwardHidden(double[] weights, double[] nodeVals, double[] activations) {
        // start by updating this neurons neuronValue
        double val = 0.0;
        for (int i = 0; i < nodeVals.length; i ++)
            val += weights[i] * nodeVals[i];
        val *= ActivationFuncs.SigmoidDeriv(this.weightedInput);
        this.neuronValue = val;
        for (int i = 0; i < activations.length; i++) {
            this.costGradientWeight[i] += activations[i] * this.neuronValue;
        }
        this.costGradientBias += this.neuronValue;
    }

    /**
     * This is backprop for nodes which are not hidde, IE, members of the final layer.
     * @param activations Activations of the previous (2nd last) layer
     * @param expectedActivation
     */
    public void backward(double[] activations, double expectedActivation) {
        // this neuron tracks its opinion on what should happen to the n ingoing weights. This is a running sum to track gradient across
        // all training data
        updateNodeValue(expectedActivation);
        for (int i = 0; i < activations.length; i++) {
            this.costGradientWeight[i] += activations[i] * this.neuronValue;
        }
        this.costGradientBias += this.neuronValue;
    }

    public void updateGradient(double learnRate) {
        this.bias -= (costGradientBias) * learnRate;
        for (int i = 0; i < this.weights.length; i++) {
            weights[i] -= ((costGradientWeight[i]  + Config.WD * weights[i]) * learnRate);
        }
    }

    public void clearGradients() {
        this.costGradientBias = 0;
        this.costGradientWeight = new double[this.costGradientWeight.length];
    }

    private void updateNodeValue(double expectedActivation) {
         this.neuronValue = CostDeriv(expectedActivation) * ActivationFuncs.SigmoidDeriv(this.weightedInput); 
    }

    public void updateBias(double bias) {
        this.bias = bias;
    }
}
