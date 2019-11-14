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
        }

        catch (Exception e) {
            System.out.println(e);
        }
    }
}