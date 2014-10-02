import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import org.math.plot.*;

import javax.swing.*;

/**
 * Created by matthewletter on 9/30/14.
 * this class is designed to represent the single layer perceptron
 */
public class Perceptron {
    private Random rnd;
    private double w0;
    private double w1;
    private double w2;
    private double learningRate;
    private double x0;
    private int maxIterations;


    /**
     * this constructor build our random weights and sets learning rate and learning iterations
     */
    Perceptron(){
        this.rnd = new Random();
        this.w0 = rnd.nextDouble();
        this.w1 = rnd.nextDouble();
        this.w2 = rnd.nextDouble();
        this.learningRate = .25;
        this.x0 = -1;
        this.maxIterations = 10;

    }

    /**
     * this class is used to teach the perceptron its weights, when there are no longer errors
     * or there are no longer iterations its stops learning
     * @param samples ArrayList of samples
     */
    public void learn(ArrayList<Sample> samples) {
        int i;
        int iterations = 0;
        boolean error = true;

        double alpha =  (learningRate / 1000);

        while (error && iterations < maxIterations) {
            error = false;
            Collections.shuffle(samples);
            //for (i = 0; i <= samples.size() - 1; i++) {
            for(Sample sample : samples) {
                double x1 = sample.X1;
                double x2 = sample.X2;
                int y;

                if (((w1 * x1) + (w2 * x2) - w0) < 0) {
                    y = -1;
                } else {
                    y = 1;
                }

                if (y != sample.expectedClass) {
                    error = true;

                    w0 = w0 + alpha * (sample.expectedClass - y) * x0 / 2;
                    w1 = w1 + alpha * (sample.expectedClass - y) * x1 / 2;
                    w2 = w2 + alpha * (sample.expectedClass - y) * x2 / 2;
                }
                //System.out.println("w0: "+w0+" w1: "+w1+" w2: "+w2 +"\n");
            }
            iterations++;
            System.out.println("round: " + iterations + " | w0: "+w0+" w1: "+w1+" w2: "+w2 +"\n");
        }

    }

    /**
     * used to plot all of our data points
     * @param cls1
     * @param cls2
     */
    public static void plotClasses(ArrayList<Sample> cls1, ArrayList<Sample> cls2){
        // define your data
        double[] x = {1, 2, 3, 4, 5, 6};
        double[] y = {45, 89, 6, 32, 63, 12};

        x = new double[cls1.size()];
        y = new double[cls1.size()];

        for (int i = 0; i < cls1.size(); i++) {
            x[i]=cls1.get(i).X1;
            y[i]=cls1.get(i).X2;
        }


        // create your PlotPanel (you can use it as a JPanel)
        Plot2DPanel plot = new Plot2DPanel();

        // define the legend position
        plot.addLegend("SOUTH");

        // add a line plot to the PlotPanel
        plot.addScatterPlot("my plot", Color.RED, x, y);

        x = new double[cls2.size()];
        y = new double[cls2.size()];

        for (int i = 0; i < cls2.size(); i++) {
            x[i]=cls2.get(i).X1;
            y[i]=cls2.get(i).X2;
        }
        plot.addScatterPlot("my plot", Color.BLUE, x, y);

        // put the PlotPanel in a JFrame like a JPanel
        JFrame frame = new JFrame("a plot panel");
        frame.setSize(800, 800);
        frame.setContentPane(plot);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    /**
     * used to parse the provided text files
     * @param f
     * @param classNumber
     * @return ArrayList of Sample
     */
    public static ArrayList<Sample> parseFile(File f,int classNumber){
        Scanner scanner;
        String[] sA;
        String s;
        ArrayList<Sample> samples = new ArrayList<Sample>();
        try {
            scanner = new Scanner(f);
            s = scanner.nextLine();

            while(scanner.hasNext()){
                sA = s.split(" ");
                if(sA.length==3)
                samples.add(new Sample(Integer.parseInt(sA[0]), Double.parseDouble(sA[1]), Double.parseDouble(sA[2]),
                        classNumber));
                s = scanner.nextLine();
            }
            scanner.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return samples;
    }

    /**
     * sets up the ptron and plots data points
     * @param args not used
     */
    public static void main(String[] args){
        Perceptron p = new Perceptron();

        //class 1
        File f1 = new File("/Users/matthewletter/Documents/single-perceptron/PercepClass1Training.txt");
        ArrayList<Sample> cls1 = parseFile(f1,1);

        //class 2
        File f2 = new File("/Users/matthewletter/Documents/single-perceptron/PercepClass2Training.txt");
        ArrayList<Sample> cls2 = parseFile(f2,-1);

        plotClasses(cls1, cls2);

        p.learn(cls1);
        p.learn(cls2);
        p.learn(cls1);
        p.learn(cls2);
        p.learn(cls1);
        p.learn(cls2);
    }
}
