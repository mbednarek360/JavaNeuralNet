// yaml
import java.io.FileWriter;
import java.io.FileReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.esotericsoftware.yamlbeans.YamlReader;

// handles encoding and abstraction of network training data
public class Dataset {

    // input and output node values
    private double[] inputs;
    private double[] outputs;

    // init arrays
    public Dataset(int inputCount, int outputCount) {
        inputs = new double[inputCount];
        outputs = new double[outputCount];
    }

    // deserialize from yaml
    public Dataset(String fileName) {



    }


    // serialize to yaml
    public void exportDataset(String fileName) {

    }







}





