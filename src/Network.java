// yaml
import java.io.FileWriter;
import com.esotericsoftware.yamlbeans.YamlWriter;

// handles training and network usage
public class Network {
    
    // bias init
    private final double BIAS_INIT = 0;

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
}
