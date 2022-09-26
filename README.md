# A Mini-Java Neural Network Library

This project provides the implementation of a mini java-based neural network library. Included in this implementation include:
  - Variable configurable network architectures
  - Stochastic gradient decent as a network optimiser
  - Particle swarm optimisation as a network optimiser
  - Quantum particle swarm optimisation as its dynamic equivalent
  - Various created datasets, with different classification difficulty
  - A java-based visualisation framework using StdDraw
  - Bulk data collection for the purposes of performance analysis

## Installation

This project is self contained, and should not require any additional installation to become functional. The project does however assume you have installed the JAVA language, and configured JDK and JRE.


## Directory structure
Please ensure that all CSV datafiles are in the 
```
data/
```
directory, in-line with the src directory. If you wish to create your own data and use the loader.java file, you need to follow the following convention:
 1. A line in this file represents a datapoint
 2. The first N entires (comma separated) represent the decision fields.
 3. The N+1th entry is the target class (integer).

## Files
1. The neuronal network implementation is composed of the following files:
    - NeuralNetwork.java
    - Layer.java
    - Neuron.java
   
These files contain the neural-network related objects, and can be viewed and 
  updated accordingly.

2. PSO is self contained in the PSO_Swarm.java and PSO_Particle.java files.
3. Model parameters and configurations are saved in the Config.java file.
4. Visualisations are created using the Visualize2d.java file. This is not a static  class, although the StdDraw library it uses is static. 
5. Data is stored in objects of the DataPoint.java class
6. Data is loaded via a the Loader.java class.
7. To run (and visualize) the performance of any algorithm please refer to the Driver.java class. In this class, there are a variety of callable methods, each will automatically initialise a neural network, and configure it accordingly. It will also automatically load in the data, and run the model. Thereafter a visualisation will be presented of the model training on the provided data. To run the S data-set for example, include ONLY the following line in the main method.
```java
Driver.doS();
```
8. If you wish to collect bulk data, I have included an Evaluator.java file which is statically accessed. This file functions similarly to the Driver.java file, though instead of loading a visualize2d instance, it simply writes the files to the
```
output/
```
directory which should be inline with 
```
src/
data/
output/
```
To run an instance of the evaluator, add ONLY the following line of code to the main method, in Main.java.
```java
Evaluator.do3Trivial();
```
which will run the SGD training algorithm for the 3classtrivial dataset, referenced in my paper. 

## Running
I have included a script used to compile and run the program. Please navigate into the src/ directory, inline with all the code, from the terminal. Once in this directory please execute the following command. If you get a permissions error, see the subsequent command.

```bash
./run.sh
```
and in the case of a permissions error run the below command, and then try again.

```bash
chmod +x run.sh
```

## Serialization
Neural network objects can be serialized, so that they can be trained once, and used for classification at a later time, see the relevant serialisation methods in NeuralNetwork.java


## Contributing
All code was written myself, and is originally created for the purposes of this assignment.


