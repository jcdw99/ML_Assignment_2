public class NeuralNetwork {
    // collection of layers (not including the input layer)
    Layer[] layers;

    public NeuralNetwork(int[] sizes) {
        this.layers = new Layer[sizes.length - 1];
        // loop through sizes-1, initialize layers using forward lookahead 
        for (int i = 0; i < this.layers.length; i++) {
            this.layers[i] = new Layer(sizes[i], sizes[i + 1]);
        }
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
            double[][] weightsMat = new double[this.layers[i].neurons.length][this.layers[i+1].neurons[0].weights.length];
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
        return cost;
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
        return totalCost / points.length;
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

}
