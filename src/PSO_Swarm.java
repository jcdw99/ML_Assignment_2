public class PSO_Swarm {

    public int[] NNSize;
    public PSO_Particle[] particles;  
    public double[] gBestVec;
    public double gBestEval;
    public int stagnant = 0;
    public boolean ringTopology = true;
    public double[] params;

    public PSO_Swarm(int size, int[] NNSize) {
        // determine dimension of WeightBias Vector corresponding to NeuralNet of shape NNSize
        // accomplished by accumulating product of all layer sizes, adding number of noninput neurons
        int dim = 0;
        int dimProd = 1;
        for (int i = NNSize.length - 1; i >= 0; i--) {
            dimProd *= NNSize[i];
            if (i > 0)
                dim += NNSize[i];
        }
        dim += dimProd;
        this.NNSize = NNSize;

        // initialize swarm structure
        particles = new PSO_Particle[size];
        for (int i = 0; i < size; i++) {
            if (Config.activePsoType == Config.PsoType.INTERTIA)
                particles[i] = new PSO_Particle(NNSize, Config.activePsoType, dim);
            else
                particles[i] = new PSO_Particle(
                                    NNSize, 
                                    (Math.random() < Config.chargedPercent) ? Config.PsoType.QUANTUM : Config.PsoType.INTERTIA, 
                                    dim
                                );

        }
        // updateGbest
        if (!ringTopology)
            setGBest(findGBest());
        else {
            updateRingTopology();
        }
    }

    public void updateRingTopology() {

        // global gbest observed
        double bestObserved = Double.MAX_VALUE;
        int gbestDex = 0;

        for (int i = 0; i < particles.length; i++) {
            int partnerUp = (i + 1) % this.particles.length;
            int partnerDown = (i == 0) ? this.particles.length - 1 : i - 1;
            double bestEval = Double.MAX_VALUE;

            int bestDex = i;
            // find bestEval of neighborhoood
            for (int j = partnerDown; j <= partnerUp; j++) {
                if (particles[j].pBest < bestEval) {
                    bestEval = particles[j].pBest;
                    bestDex = j;
                    // if this neighborhood best is better than our observed Gbest
                    if (bestEval < bestObserved) {
                        bestObserved = bestEval;
                        gbestDex = j;
                    }
                }
            }

            // set bestEval as gbest in neighborhood
            particles[i].gBest = bestEval;
            particles[i].gBestVec = duplicate(particles[bestDex].pBestVec);
        }
        // update gbestVec
        this.gBestVec = duplicate(particles[gbestDex].pBestVec);
        this.gBestEval = particles[gbestDex].pBest;

    }


    public double diversity() {
        double[] averagePos = new double[particles[0].DIM];
        for (int i = 0; i < this.particles.length; i++) {
            for (int j = 0; j < this.particles[i].position.length; j++) {
                averagePos[j] += this.particles[i].position[j];
            }
        }
        for (int i = 0; i < averagePos.length; i++) {
            averagePos[i] /= this.particles.length;
        }
        // now that we have the average particle position, get distance of each particle to this
        double avgdist = 0.0;
        for (int i = 0; i < this.particles.length; i++) {
            avgdist += distance(averagePos, particles[i].position);
        }
        return avgdist / particles.length;
    }

    public double distance(double[] center, double[] candidate) {
        double distance = 0;
        for (int i = 0; i < center.length; i++) {
            distance += ((center[i] - candidate[i]) * (center[i] - candidate[i]));
        }
        return Math.sqrt(distance);
    }

    /**
     * Run the update Procedure for each particle in the swarm
     * After the procedure has updated both velocity and position, compute and update the new gBest
     */
    public void doUpdate(DataPoint[] points) {
        // for each particle, do velocity and position update
        for (int i = 0; i < particles.length; i++) {
            particles[i].evaluateParticle(points);
            particles[i].updateVel();
            particles[i].updatePos();
        }
        // find the new gBest, and make each particle reflect this value
        if (ringTopology) {
            updateRingTopology();
        } else {      
            setGBest(findGBest());
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
     * This function iterates the pbest's of the particles in the swarm, and returns the "best PBest" of the swarm
     * 
     * @return
     */
    public double[][] findGBest() {
        int bestdex = 0;
        double bestEval = particles[bestdex].pBest;
        
        for (int i = 1; i < particles.length; i++) {
            if (particles[i].pBest < bestEval)
                bestdex = i;
                bestEval = particles[i].pBest;
        }
        double[][] retarr = {particles[bestdex].pBestVec, {bestEval}};
        return retarr;
    }

    /**
     * This function assigns the supplied vector as the Gbest for each particle in the swarm. It also assigns
     * gBest evaluation to each particle, this is the evaluation of the supplied vector.
     * @param best
     */
    public void setGBest(double[][] best) {
        double bestEval = best[1][0];
        for (int i = 0 ; i < particles.length; i++) {
            particles[i].gBestVec = duplicate(best[0]);
            particles[i].gBest = bestEval;
        }
        this.gBestVec = duplicate(best[0]);
        this.gBestEval = bestEval;
    }

    public double[] duplicate(double[] target) {
        double[] dup = new double[target.length];
        for (int i = 0; i < target.length; i++)
            dup[i] = target[i];
        return dup;

    }

}
