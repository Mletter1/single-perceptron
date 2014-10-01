import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by matthewletter on 9/30/14.
 */
public class Perceptron {


    public void learn(Sample[] samples) {
        int i;
        int iterations = 0;
        boolean error = true;

        int maxIterations = 0;

        Random rnd = new Random();

        double w0 = rnd.nextDouble();
        double w1 = rnd.nextDouble();
        double w2 = rnd.nextDouble();
        double learningRate = .25;
        double x0 = -1;

        double alpha =  (learningRate / 1000);

        while (error && iterations < maxIterations) {
            error = false;

            for (i = 0; i <= samples.length - 1; i++) {
                double x1 = samples[i].X1;
                double x2 = samples[i].X2;
                int y;

                if (((w1 * x1) + (w2 * x2) - w0) < 0) {
                    y = -1;
                } else {
                    y = 1;
                }

                if (y != samples[i].expectedClass) {
                    error = true;

                    w0 = w0 + alpha * (samples[i].expectedClass - y) * x0 / 2;
                    w1 = w1 + alpha * (samples[i].expectedClass - y) * x1 / 2;
                    w2 = w2 + alpha * (samples[i].expectedClass - y) * x2 / 2;
                }
            }
            iterations++;
        }
    }
    public static void main(String[] args){
        Perceptron p = new Perceptron();
        File f = new File("/Users/matthewletter/Documents/single-perceptron/PercepClass1Training.txt");
        Scanner scanner;
        String[] sA;
        String s;
        ArrayList<Sample> samples = new ArrayList<Sample>();
        try {
            scanner = new Scanner(f);
            s = scanner.nextLine();

            while(scanner.hasNext()){
                sA = s.split(" ");
                samples.add(new Sample(Integer.parseInt(sA[0]), Double.parseDouble(sA[1]), Double.parseDouble(sA[2]),
                        1));
                scanner.next();
            }
            System.out.print(samples.get(0).X1);
            scanner.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

}
