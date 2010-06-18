/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ocr.nnet.factories;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import ocr.OcrApp;
import ocr.nnet.NeuralNetworkLayer;
import ocr.nnet.OcrNetwork;
import ocr.nnet.SoftMaxActivation;
import ocr.nnet.TanhActivation;

/**
 * Factory in charge of creating, loading and saving ashallow multi-layer
 * perceptron (MLP) with one hidden layer of 1000 units.
 *
 * @author dauphiya
 */
public class MLPFactory {

    /**
     * Create the Ocr Network with the predefined configuration.
     */
    public static OcrNetwork create() {
        OcrNetwork network = new OcrNetwork(new NeuralNetworkLayer[]{
                    new NeuralNetworkLayer(32 * 32, 1000, new TanhActivation()),
                    new NeuralNetworkLayer(1000, 62, new SoftMaxActivation())
                });

        return network;
    }

    /**
     * Load the parameters of the network from predefined text files.
     */
    public static void loadParameters(OcrNetwork network) {
        network.loadParameters(new InputStream[]{
                    OcrApp.class.getResourceAsStream("resources/params/layer0.save"),
                    OcrApp.class.getResourceAsStream("resources/params/layer1.save"),
                });
    }

    /**
     * Serialize the neural network to a predefined file.
     */
    public static void save(OcrNetwork network) {
        try {
            network.saveObject(new FileOutputStream("mlp_network.save"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SDAFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Deserialize neural network from a predefined file.
     * @return deserialized network
     */
    public static OcrNetwork load() {
        return OcrNetwork.loadObject(OcrApp.class.getResourceAsStream("resources/params/mlp_network.save.bz2"));
    }
}
