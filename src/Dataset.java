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
        this.inputs = new double[inputCount];
        this.outputs = new double[outputCount];
    }

    // override for mutation
    public Dataset(double[] i, double[] o) {
        this.inputs = i;
        this.outputs = o;
    }

    // deserialize from yaml
    public Dataset(String fileName) {
        YSet ySet = new YSet();

        // attempt read
        try {
            YamlReader reader = new YamlReader(new FileReader(fileName));
            ySet = reader.read(YSet.class);
            reader.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        // set values
        this.inputs = ySet.inputs;
        this.outputs = ySet.outputs;
    }

    // serialize to yaml
    public void exportDataset(String fileName) {
        try {
            YamlWriter writer = new YamlWriter(new FileWriter(fileName));
            writer.write(this.toYSet());
            writer.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // for serialization
    public YSet toYSet() {
        YSet ySet = new YSet();
        ySet.inputs = this.inputs;
        ySet.outputs = this.outputs;
        return ySet;
    }

    // getters
    public double[] getInputs() {
        return this.inputs;
    }
    public double[] getOutputs() {
        return this.outputs;
    }
}