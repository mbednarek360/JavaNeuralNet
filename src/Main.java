import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // handle command line arguements
        try {

            // create new random network
            if (args[0].equals("create")) {
                int[] layerCounts = new int[args.length - 2];
                for (int i = 2; i < args.length; i++) {
                    layerCounts[i - 2] = Integer.parseInt(args[i]);
                }
                new Network(layerCounts).exportNetwork(args[1]);
            }

            // run network with given input
            if (args[0].equals("run")) {


            }

            // train network on dataset
            if (args[0].equals("train")) { 
            
            }

            // for random tests
            if (args[0].equals("test")) {
                Network n = new Network(new int[]{2, 3, 1});
                Random r = new Random();


                for (int i = 0; i < 10000; i++) {

                    int first = r.nextBoolean() ? 0 : 1;
                    int second = r.nextBoolean() ? 0 : 1;
                    int expected = first ^ second;

                    n.learn(new Dataset(new double[]{first, second}, new double[]{expected}));




                }
            
            
            
            
            }
        }

        catch (Exception e) {
            System.out.println(e);
        }
    }
}