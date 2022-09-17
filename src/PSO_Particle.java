import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class PSO_Particle {

    enum ParticleType {
        inertia,
        quantum,
        charged
    }

    public int[] NNSize;
    public double[] position;
    public double[] pBestVec;
    public double pBest;
    public double[] gBestVec;
    public double gBest;
    public double[] velocity;
    private double[] params;
    double charge;
    public int DIM;
    boolean nnmode = true;
    ParticleType type = ParticleType.inertia;
    Random rand;


    /**
     * Construct a PSO_Particle
     * @param controlParams list of model control parameters of form {w, c1, c2}
//     * @param flag flag which denotes the objective function this particle attempts to minimnize
     * @param dim The dimension of this particles search space
     */
    public PSO_Particle(double[] controlParams, int[] NNSize, int dim,
                        ParticleType type, Random rand) {

        //set useful global fields
        this.NNSize = NNSize;
        this.DIM = dim;

        this.type = type;

        // control params of the form [w, c1, c2]
        this.params = controlParams;

        this.rand = rand;

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
    public void updateVel(double[] a) {
        for (int i = 0; i < this.velocity.length; i++) {
            if (type == ParticleType.inertia) {
                this.velocity[i] =
                        params[Driver.w] * this.velocity[i] +
                        params[Driver.c1] * Math.random() * (this.pBestVec[i] - this.position[i]) +
                        params[Driver.c2] * Math.random() * (this.gBestVec[i] - this.position[i]) +
                        a[i];
            }
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
        if (type == ParticleType.inertia) {
            for (int i = 0; i < this.velocity.length; i++) {
                this.position[i] += this.velocity[i];
            }
        } else if (type == ParticleType.quantum) {
            for (int i = 0; i < this.velocity.length; i++) {
                this.position[i] = gBestVec[i] +
                        (rand.nextGaussian() * params[Driver.radius]);
            }
        }
    }

    /**
     * Evaluate the quality of the provided vector with respect to the objective function being minimized
     * @return
     */  
    public double evaluateParticle(DataPoint[] points) {
        NeuralNetwork net = new NeuralNetwork(NNSize, position);
        double evalPos = net.Cost(Driver.getRandomBatch(points, (int)params[Driver.batchSize]),
                params[Driver.weightDecay]);

        if (evalPos < this.pBest) {
            this.pBest = evalPos;
            this.pBestVec = duplicate(this.position);
        }

        return evalPos;
    }
}