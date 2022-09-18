import java.util.Random;

class PSO_Particle {

    public Config.PsoType ptype;
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
    private Random r = new Random();
    /**
     * Construct a PSO_Particle
     * @param controlParams list of model control parameters of form {w, c1, c2}
     * @param flag flag which denotes the objective function this particle attempts to minimnize
     * @param dim The dimension of this particles search space
     */
    public PSO_Particle(int[] NNSize, Config.PsoType ptype, int dim) {

        this.ptype = ptype;

        //set useful global fields
        this.NNSize = NNSize;
        this.DIM = dim;

        // control params of the form [w, c1, c2]
        this.w = Config.w;
        this.c1 = Config.c1;
        this.c2 = Config.c2;
        this.position = new double[dim];

        for (int i = 0; i < this.position.length; i++)
            position[i] = Math.random() * ((Math.random() > 0.5) ? -1:1);

        this.pBestVec = duplicate(position);
        this.pBest = Double.MAX_VALUE;
        this.velocity = new double[dim];
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
        if (ptype == Config.PsoType.INTERTIA) {
            for (int i = 0; i < this.velocity.length; i++) {
                this.position[i] += this.velocity[i];
            }
        } else if (ptype == Config.PsoType.QUANTUM) {
                for (int i = 0; i < this.velocity.length; i++) {
                    this.position[i] = gBestVec[i] +
                            (r.nextGaussian() * Config.radius);
                }
            }
        }

    public double evaluateParticle(DataPoint[] points) {
        NeuralNetwork net = new NeuralNetwork(this.NNSize, this.position);
        double evalPos = net.Cost(points);

        if (evalPos < this.pBest) {
            this.pBest = evalPos;
            this.pBestVec = duplicate(this.position);
        }

        return evalPos;
    }

}