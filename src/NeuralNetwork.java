import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NeuralNetwork implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    // collection of layers (not including the input layer)
    Layer[] layers;

    int weightCount;
    int inputSize;
    int neuronCount;
    double WD = 0.01;

    public NeuralNetwork(int[] sizes) {
        this.neuronCount = sizes[0];
        this.weightCount = sizes[0];
        this.inputSize = sizes[0];
        this.layers = new Layer[sizes.length - 1];
        // loop through sizes-1, initialize layers using forward lookahead 
        for (int i = 0; i < this.layers.length; i++) {
            this.layers[i] = new Layer(sizes[i], sizes[i + 1]);
            this.weightCount *= sizes[i + 1];
            this.neuronCount += sizes[i + 1];
        }
    }

    /**
     * This constructor builds a neuralnet from a single input vector (useful for PSO training)
     * @param sizes Used to determine the number of neurons in each layer for weight/bias chunking
     * @param weightBiasData Weight bias data, organized layerNw - layer1w - bias1 - biasN; top to bottom
     */
    public NeuralNetwork(int[] sizes, double[] weightBiasData) {
        this.neuronCount = sizes[0];
        this.weightCount = sizes[0];
        this.inputSize = sizes[0];
        this.layers = new Layer[sizes.length - 1];

        // index into the weightBias Vector
        int weightIndex = 0;
        // iterate layer size array backwards
        for (int i = sizes.length - 1; i > 0; i--) {
            // build up weightcount and neuroncount
            this.weightCount *= sizes[i];
            this.neuronCount += sizes[i];
            // neurons in this layer
            int thisLayerSize = sizes[i];
            // neurons in the previous layer
            int prevLayerSize = sizes[i - 1];
            // weight matrix of this layer only
            double[] layerWeights = new double[thisLayerSize * prevLayerSize]; 
            int layerDex = 0;
            // for-loop to populate weight matrix of this layer
            for (int j = 0; j < thisLayerSize; j++) {
                // iterate amount of weights that input into this neuron
                for (int k = 0; k < prevLayerSize; k++) {
                    layerWeights[layerDex] = weightBiasData[weightIndex];
                    weightIndex++;
                    layerDex++;
                }
            }
            this.layers[i-1] = new Layer(thisLayerSize, layerWeights);
        }

        // layers have been initialized, we now need to set the bias values
        int biasDex = 0;
        for (int i = 0; i < this.layers.length; i++) {
            for (int j = 0; j < this.layers[i].neurons.length; j++) {
                this.layers[i].neurons[j].updateBias(weightBiasData[weightIndex + biasDex]);
                biasDex++;
            }
        }
    }

    public double weightTerm() {
        double weightSum = 0;
        double[] weights = this.toConfigVector();
        for (int i = 0; i < weights.length - (this.neuronCount - this.inputSize); i++) {
            weightSum += weights[i];
        }
        return Math.pow(weightSum, 2) * WD;
    }

    public double[] toConfigVector() {
        double[] toConfigVector = new double[this.weightCount + (this.neuronCount - this.inputSize)];
        int weightDex = 0;
        int biasDex = 0;
        int seenNeurons = 0;
        // fill the config vector with weights from the network, iterate in right-left, top-down order
        for (int i = this.layers.length - 1; i >= 0; i--) {
            seenNeurons += this.layers[i].neurons.length;
            for (int j = 0; j < this.layers[i].neurons.length; j++) {
                for (int k = 0; k < this.layers[i].neurons[j].weights.length; k++) {
                    toConfigVector[weightDex++] = this.layers[i].neurons[j].weights[k];
                }
                toConfigVector[(toConfigVector.length - 1) - (seenNeurons - ++biasDex)] = this.layers[i].neurons[j].bias;
            }
            biasDex = 0;
        }
        return toConfigVector;
    }

    public static NeuralNetwork fromSave(String savePath) {
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(savePath);
            objectInputStream = new ObjectInputStream(fileInputStream);
            NeuralNetwork net = (NeuralNetwork) objectInputStream.readObject();
            objectInputStream.close();
            return net;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Forward propagate input vector throughout network, using the forward() function of each layer.
     * @param inputVector
     * @return
     */
    public double[] Forward(double[] inputVector) {
        for (Layer layer : this.layers) {
            inputVector = layer.forward(inputVector);
        }
        return inputVector; 
    }

    public void BackProp(DataPoint d) {
        // run the input through the network to set nodevalues and activations 
        Forward(d.inputVector);
        // call backprop on final (output) layer
        this.layers[layers.length - 1].backward(this.layers[layers.length-2].getActivations(), d.expectedOutputVector);
        // call backprop on hidden layers
        for (int i = this.layers.length-2; i > 0; i--) {
            double[][] weightsMat = new double[this.layers[i].neurons.length][this.layers[i+1].neurons.length];
            for (int j = 0; j < weightsMat.length; j++) {
                for (int k = 0; k < weightsMat[j].length; k++) {
                    weightsMat[j][k] = this.layers[i+1].neurons[k].weights[j];
                }
            }
            this.layers[i].backwardHidden(weightsMat, this.layers[i-1].getActivations(), this.layers[i+1].getNodeVals());
        }
    }


    public void BackProp(DataPoint[] trainingBatch, double learnRate) {
        for (DataPoint d: trainingBatch)
            BackProp(d);

        //apply all gradients
        for (Layer l: this.layers)
            l.applyGradients(learnRate / trainingBatch.length);

        //reset all gradients
        for (Layer l: this.layers)
            l.clearGradients();
    }

    /**
     * Computes the cost associated with the classification of the provided datapoint
     * @param d
     * @return
     */
    public double Cost(DataPoint d) {
        double[] outputs = Forward(d.inputVector);
        double[] target = d.expectedOutputVector;
        double cost = 0;
        for (int i = 0; i < outputs.length; i++) {
            cost += (outputs[i] - target[i]) * (outputs[i] - target[i]);
        }
        return cost * weightTerm();
    }

    /**
     * Computes the total cost associated with the classification of the dataset
     * @param points
     * @return
     */
    public double Cost(DataPoint[] points) {
        double totalCost = 0;
        for (int i = 0; i < points.length; i++) {
            totalCost += Cost(points[i]);
        }
        return (totalCost / points.length);
    }

    /**
     * Identify the most active neuron in output layer, return it's index
     * @param inputVector
     * @return
     */
    public int Classify(double[] inputVector) {
        double[] outputVector = Forward(inputVector);
        int maxIndex = 0;
        double maxActivation = outputVector[maxIndex];
        for (int i = 1; i < outputVector.length; i ++) {
            if (outputVector[i] > maxActivation) {
                maxActivation = outputVector[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }


    public void saveNet(String netName) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(netName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        StringBuilder net = new StringBuilder("");
        for (int i = 0; i < this.layers.length; i++) {
            // for each neuron in this layer
            for (int j = 0; j < this.layers[i].neurons.length; j++) {
                net.append(printVector(this.layers[i].neurons[j].weights));
                net.append(this.layers[i].neurons[j].bias + "\n");
            }
        }
        return net.toString();
    }

    public String printVector(double[] x) {
        StringBuilder vec = new StringBuilder("[ ");
        for (int i = 0; i < x.length; i++) {
            vec.append(x[i]);
            if (i < x.length - 1)
                vec.append(", ");
        }
        vec.append(" ]\n");
        return vec.toString();
    }


}
