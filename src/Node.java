// handles single node pre and post execution
public class Node {

    // contstants
    private double bias;
    private double[] weights;
    private double value;

    // create node and init weights
    public Node(int count, double b) {
        this.bias = b;
        weights = new double[count];
        for (int i = 0; i < count; i++) {
            weights[i] = Common.randomWeight();
        }
    }

    // print weights and bias
    public void printStats() {
        System.out.println("Bias: " + this.bias);
        for (double weight : this.weights) {
            System.out.println(weight);
        }
    }

    // get current node value
    public double getValue() {
        return this.value;
    }

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
}