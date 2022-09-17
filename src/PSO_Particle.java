class PSO_Particle {

    public int[] NNSize;
    public double[] position;
    public double[] pBestVec;
    public double pBest;
    public double[] gBestVec;
    public double gBest;
    public double[] velocity;
    public double w;
    public double c1;
    public double c2;
    public int DIM;
    boolean nnmode = true;
    /**
     * Construct a PSO_Particle
     * @param controlParams list of model control parameters of form {w, c1, c2}
     * @param flag flag which denotes the objective function this particle attempts to minimnize
     * @param dim The dimension of this particles search space
     */
    public PSO_Particle(double[] controlParams, int[] NNSize, int dim) {
        //set useful global fields
        this.NNSize = NNSize;
        this.DIM = dim;

        // control params of the form [w, c1, c2]
        this.w = controlParams[0];
        this.c1 = controlParams[1];
        this.c2 = controlParams[2];

        this.position = new double[dim];
        for (int i = 0; i < this.position.length; i++)
            position[i] = Math.random() * ((Math.random() > 0.5) ? -1:1);

        this.pBestVec = duplicate(position);
        if (nnmode) {
            this.pBest = evaluatePos();
        } else {
            this.pBest = evaluateFake();
        }
        this.velocity = new double[dim];
    }
    /**
     * Evaluate the quality of this particles position with respect to the objective function being minimized
     * @return
     */
    public double evaluatePos() {
        NeuralNetwork n = new NeuralNetwork(this.NNSize, this.position);
        double eval = n.Cost(Driver.getRandomBatch(PSO_Swarm.dataSet, Config.batchSize));

        return eval;
    }

    public double evaluateFake() {
        double sum = 0;
        for (int i = 0; i < this.position.length; i++) {
            sum += this.position[i] * this.position[i];
        }   
        return sum;
    }

    public double[] duplicate(double[] target) {
        double[] dup = new double[target.length];
        for (int i = 0; i < target.length; i++)
            dup[i] = target[i];
        return dup;

    }
    /**
     * Runs the standard PSO velocity update equation
     * 
     * Assigns the updated velocity to this particle's velocity
     */
    public void updateVel() {
        for (int i = 0; i < this.velocity.length; i++) {
            this.velocity[i] = w * this.velocity[i] + 
                          c1 * Math.random() * (this.pBestVec[i] - this.position[i]) +
                          c2 * Math.random() * (this.gBestVec[i] - this.position[i]);
        }
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

    /**
     * Updates the position, assumes that the velocity was previously updated
     * 
     * This will update pbestVal and pbestVec if needed
     */
    public void updatePos() {
        for (int i = 0; i < this.velocity.length; i++) {
            this.position[i] += this.velocity[i];
        }
        double evalPos;
        if (nnmode) {
            evalPos = evaluatePos();
        } else {
            evalPos = evaluateFake();
        }
        if (evalPos < this.pBest) {
            this.pBest = evalPos;
            this.pBestVec = duplicate(this.position);
        }
    }

    /**
     * update the GBest value of this particle to reflect the provided vector
     */
    public void updateGBest(double[] newBest) {
        this.gBestVec = duplicate(newBest);
        if (nnmode) {
            this.gBest = evaluateVec(newBest);
        } else {
            this.gBest = evaluateVecFake(newBest);
        }
    }

    /**
     * Evaluate the quality of the provided vector with respect to the objective function being minimized
     * @param vec
     * @return
     * @throws Exception
     */
    public double evaluateVec(double[] vec) {
        NeuralNetwork n = new NeuralNetwork(this.NNSize, vec);
        return n.Cost(Driver.getRandomBatch(PSO_Swarm.dataSet, Config.batchSize));
    }


    public double evaluateVecFake(double[] pos) {
        double sum = 0;
        for (int i = 0; i < pos.length; i++) {
            sum += pos[i] * pos[i];
        }   
        return sum;
    }

}