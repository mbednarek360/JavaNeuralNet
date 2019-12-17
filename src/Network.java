// yaml
import java.io.FileWriter;
import java.io.FileReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.esotericsoftware.yamlbeans.YamlReader;

////////////////////////////////////////////////////////////////
////                 general network definition and organization
////////////////////////////////////////////////////////////////
 
// handles training and network usage
public class Network {
    
    // bias init
    private final double BIAS_INIT = 0;

    // learning rate
    private final double LEARNING_RATE = 5;

    // layer - node
    private Node[][] nodes;

    // gen nodes per layer with appropriate connection count
    public Network(int[] layerCounts) {

        // init
        this.nodes = new Node[layerCounts.length][];

        // first layer
        this.nodes[0] = new Node[layerCounts[0]]; 
        for (int i = 0; i < layerCounts[0]; i++) {
            this.nodes[0][i] = new Node(0, BIAS_INIT);
        }
       
        // each layer
        for (int i = 1; i < layerCounts.length; i++) {

            // each node
            this.nodes[i] = new Node[layerCounts[i]];
            for (int n = 0; n < layerCounts[i]; n++) {
                this.nodes[i][n] = new Node(layerCounts[i - 1], BIAS_INIT);
            } 
        }
    }

    // load network from yaml file
    public Network(String fileName) {
        YNet yNet = new YNet();

        try {
            YamlReader reader = new YamlReader(new FileReader(fileName));
            yNet = reader.read(YNet.class);
            reader.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        // build layer count
        int[] layerCounts = new int[yNet.nodes.length];
        int layerIndex = 0;

        for (YNode[] node : yNet.nodes) {
            layerCounts[layerIndex] = node.length;
            layerIndex++;
        }

        // mutable time
        this.nodes = new Node[layerCounts.length][];

        // first layer
        this.nodes[0] = new Node[layerCounts[0]]; 
        for (int i = 0; i < layerCounts[0]; i++) {
            this.nodes[0][i] = new Node(0, yNet.nodes[0][i].bias);
        }
       
        // each layer
        for (int i = 1; i < layerCounts.length; i++) {

            // each node
            this.nodes[i] = new Node[layerCounts[i]];
            for (int n = 0; n < layerCounts[i]; n++) {
                this.nodes[i][n] = new Node(yNet.nodes[i][n].weights, yNet.nodes[i][n].bias);
            } 
        }
    }

    // export network to yaml file
    public void exportNetwork(String fileName) {
        try {
            YamlWriter writer = new YamlWriter(new FileWriter(fileName));
            writer.write(this.toYNet());
            writer.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // for serialization
    public YNet toYNet() {

        // each layer
        YNode[][] yNodes = new YNode[this.nodes.length][];
        for (int i = 0; i < this.nodes.length; i++) {

            // each node
            yNodes[i] = new YNode[this.nodes[i].length];
            for (int n = 0; n < this.nodes[i].length; n++) {
                yNodes[i][n] = this.nodes[i][n].toYNode();
            }
        }

        // make network
        YNet yNet = new YNet();
        yNet.nodes = yNodes;
        return yNet;
    }

    // literally just print everything
    public void printNetwork() {

        // each layer
        for (int i = 0; i < this.nodes.length; i++) {
            System.out.println("############### Layer: " + i);

            // each node
            for (int n = 0; n < this.nodes[i].length; n++) {
                System.out.println("##### Node: " + n);

                // each weight
                this.nodes[i][n].printStats();
            }
        }
    }


////////////////////////////////////////////////////////////////
////                                  actual network usage stuff
////////////////////////////////////////////////////////////////
    
    // run network with given values
    public double[] runNetwork(double[] inputs) {

        // init inputs
        int inputIndex = 0;
        for (Node inputNode : this.nodes[0]) {
            inputNode.setValue(inputs[inputIndex]);
            inputIndex++;
        }

        // each layer
        for (int layerIndex = 1; layerIndex < this.nodes.length; layerIndex++) {

            // each node
            for (int nodeIndex = 0; nodeIndex < this.nodes[layerIndex].length; nodeIndex++) {

                // read from last layer
                System.out.println("Ight we out here setting node: " + layerIndex + ", " + nodeIndex);
                this.nodes[layerIndex][nodeIndex].setValue(this.nodes[layerIndex - 1]);
            }
        }

        // read output
        double[] outVals = new double[this.nodes[this.nodes.length - 1].length];
        int outIndex = 0;
        for (Node outNode : this.nodes[this.nodes.length - 1]) {
            outVals[outIndex] = outNode.getValue();
            outIndex++;
        }
        return outVals;
    }


////////////////////////////////////////////////////////////////
////                                              training stuff
////////////////////////////////////////////////////////////////

    // gradient format
    // layer
    // j index
    // k index

    //  l
    // w
    //  jk
    // arr[l, k, j]

    // arr[l, j] = node
    // node.getWeights()[k] = w

    // layer 0 = inputs


    // backpropogate and calculate errors per node
    public void setError() {






    }



























    // calculate negative gradient for given training example
    public double[][][] getGradient(double[] input, double[] expected) {
    
        // propogate backwards through nodes

        // sum derivative per node to get value in gradient  


        // init gradient and loop 
        double[][][] gradient = new double[this.nodes.legnth][][];
        for (int l = 0; l < this.nodes.length; l++) {
            gradient[l] = new double[this.nodes[l].length][];
            for (int k = 0; k < this.nodes[l].length; k++) {
                gradient[l][k] = new double[this.nodes[l][k].getWeights().length];


                // do the cool stuff here
                this.nodes[l][k][j]


`


            }
        }










    }



   

    // apply a previously calculated gradient
    public void applyGradient(double[][][] gradient) {

        // loop through nodes
        for (int l = 0; l < gradient.length; l++) {
            for (int k = 0; k < gradient[l].length; k++) {

                // get values for current node
                double[] weights = this.nodes[l][k].getWeights();
                double bias = this.nodes[l][k].getBias();

                // go through weights and mutate
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += LEARNING_RATE * gradient[l][k][j];
                }

                // replace node
                this.nodes[l][k] = new Node(weights, bias);
            }
        }
    }
}
