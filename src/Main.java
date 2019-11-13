public class Main {
    public static void main(String[] args) {
        
        Network n = new Network(new int[]{2, 3, 2});
        n.printNetwork();
        //double[] out = n.runNetwork(new double[]{0.3, 0.7});
        //System.out.println("##########################");
        //for (double val : out) {
        //    System.out.println(val);
        //}
        n.exportNetwork("test.yml");
    }
}