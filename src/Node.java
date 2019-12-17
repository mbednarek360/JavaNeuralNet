// handles single node pre and post execution
public class Node {

    // contstants
    private double bias;
    private double[] weights;
    private double value;
    private double error;

    // create node and init weights
    public Node(int count, double b) {
        this.bias = b;
        weights = new double[count];
        for (int i = 0; i < count; i++) {
            weights[i] = Common.randomWeight();
        }
    }

    // for deserialization
    public Node(double[] w, double b) {
        this.weights = w;
        this.bias = b;
    }

    // print weights and bias
    public void printStats() {
        System.out.println("Bias: " + this.bias);
        for (double weight : this.weights) {
            System.out.println(weight);
        }
    }

    // get weights
    public double[] getWeights() {
        return this.weights;
    } 

    // get bias
    public double getBias() {
        return this.bias;
    }
    
    // get current node value
    public double getValue() {
        return this.value;
    }

    // get error of node
    public double geError() {
        return this.error;
    }

    // for serialization
    public YNode toYNode() {
        YNode out = new YNode();
        out.bias = this.bias;
        out.weights = this.weights;
        return out;
    }

    // for input nodes
    public void setValue(double v) {
        this.value = v;
    }

    // gets final value from past layer
    public void setValue(Node[] layer) {

        // sum of weights
        double sum = this.bias;
        int i = 0;
        for (Node node : layer) {
            sum += this.weights[i] * node.getValue();
            i++; 
        }

        // activate
        this.value = Common.sigmoid(sum);
    }   

    // get error from next layer
    public void setError(Node[] layer, int index) {

        // get sum of weights 
        double totalWeight = 0; 
        for (Node n : layer) {
            totalWeight += n.getWeights()[index];
        }
     
        // error dependant on significance of weight
        this.error = 0;
        for (Node n : layer) {
            this.error += n.getError() * (n.getWeights()[index] / totalWeight)
        }
    }

    // square of difference for backpropogation init
    public void setError(double expected) {
        this.error = Math.pow(expected - this.value, 2); 
    }
}