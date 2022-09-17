import java.io.Serializable;

public class Layer implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    // number of nodes feeding into this layer.
    int numNodesIn;
    // number of nodes in this layer
    int numNodesOut;
    // each node as an array of weights feeding into it, and we store our nodes in an array
    Neuron[] neurons;

    public Layer(int nodesIn, int nodesOut) {
        this.numNodesIn = nodesIn;
        this.numNodesOut = nodesOut;
        // initialize array of neurons
        this.neurons = new Neuron[nodesOut];
        if (numNodesIn > 0)
            for (int i = 0; i < neurons.length; i++) {
                neurons[i] = new Neuron(nodesIn);
            }
    }

    /**
     * Use this constructor when creating a NeuralNetwork from a weightbias Vector
     * @param numNodesOut nodes feeding into this layer
     * @param weights
     */
    public Layer(int numNodesOut, double[] weights) {
        this.numNodesIn = weights.length / numNodesOut;
        this.numNodesOut = numNodesOut;
        this.neurons = new Neuron[numNodesOut];

        for (int i = 0; i < numNodesOut; i++) {
            // for each neuron of this layer
            double[] neuronWeights = new double[numNodesIn];
            for (int j = 0; j < numNodesIn; j++) {
                // get weight vector for this neuron
                neuronWeights[j] = weights[(i * numNodesIn) + j];
            }
            this.neurons[i] = new Neuron(neuronWeights);
        }

    }

    /**
     * Forward propagation function for this layer. Muliply input values by weights, add bias
     * @param inputs
     * @return
     */
    public double[] forward(double[] inputs) {
        double[] outputValues = new double[this.numNodesOut];
        // for output node (IE a node in this layer)
        for (int nodeIndex = 0; nodeIndex < this.numNodesOut; nodeIndex++) {
            neurons[nodeIndex].forward(inputs);
            outputValues[nodeIndex] = neurons[nodeIndex].activation;
        }
        return outputValues;
    }

    /**
     * output layer backprop
     * @param activations activations of the previous (second last) layer
     * @param expectedOutputVector
     */
    public void backward(double[] activations, double[] expectedOutputVector) {
        for (int i = 0; i < this.neurons.length; i++) {
            this.neurons[i].backward(activations, expectedOutputVector[i]);
        }
    }

    /**
     * Get neuron activations of this layer
     * @return
     */
    public double[] getActivations() {
        double[] activations = new double[this.neurons.length];
        for (int i = 0; i < this.neurons.length; i++) {
            activations[i] = neurons[i].activation;
        }
        return activations;
    }

    /**
     * Get neuronVals of this layer
     * @return
     */
    public double[] getNodeVals() {
        double[] vals = new double[this.neurons.length];
        for (int i = 0; i < this.neurons.length; i++) {
            vals[i] = neurons[i].neuronValue;
        }
        return vals;
    }

    public void applyGradients(double learnRate, double weightDecay) {
        for (int i = 0; i < this.neurons.length; i++) {
            this.neurons[i].updateGradient(learnRate, weightDecay);
        }
    }


    public void clearGradients() {
        for (int i = 0; i < this.neurons.length; i++) {
            this.neurons[i].clearGradients();
        }
    }


    /**
     * Backprop on a hidden layer
     * @param weights this is a collection of weights, on a per-neuron basis. The weights are the ones which connect from the next layer, into the neuron of this layer
     * @param activations
     * @param nodeVals these are the nodeValues calculated from the NEXT (already backpropped) layer
     */
    public void backwardHidden(double[][] weights, double[] activations, double[] nodeVals) {
        for (int i = 0; i < this.neurons.length; i++) {
            this.neurons[i].backwardHidden(weights[i], nodeVals, activations);
        }
    }
}