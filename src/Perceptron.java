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
    public double w0;
    public double w1;
    public double w2;
    public double learningRate;
    private double x0;
    private int maxIterations;


    /**
     * this constructor build our random weights and sets learning rate and learning iterations
     */
    Perceptron(){
        this.rnd = new Random();
//        this.w0 = rnd.nextDouble();
//        this.w1 = rnd.nextDouble();
//        this.w2 = rnd.nextDouble();
        this.w0 = 0;
        this.w1 = 0;
        this.w2 = 0;
        this.learningRate = 15.85;
        this.x0 = -1;
        this.maxIterations = 30;

    }

    /**
     * this class is used to teach the perceptron its weights, when there are no longer errors
     * or there are no longer iterations its stops learning
     * @param samples ArrayList of samples
     */
    public void learn(ArrayList<Sample> samples) {
        int iterations = 0;
        boolean error = true;

        double alpha =  (learningRate / 1000);

        //go through the epoche's
        while (error && iterations < maxIterations) {
            error = false;
            Collections.shuffle(samples);
            //iterate through the epoche
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
            System.out.println("epoche: " + iterations + " |\n w0: "+w0+" w1: "+w1+" w2: "+w2 +"\n");
        }

    }

    /**
     * used to plot all of our data points
     * @param cls1 class 1
     * @param cls2 class 2
     */
    public static void plotClasses(ArrayList<Sample> cls1, ArrayList<Sample> cls2, Perceptron p){
        // define your data
        double[] x;
        double[] y;

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
        plot.addScatterPlot("my plot1", Color.RED, x, y);


        double y0=0;
        double y1=1;
        double[] linex = {-4,-3,-2,-1,0,1,2,3,4,5};
        double[] liney = {-4,-3,-2,-1,0,1,2,3,4,5};
        for (int i = 0; i < linex.length; i++) {
            liney[i]= ((-p.w1/p.w2)*linex[i])+(p.w0/p.w2);
        }
        //add line plot weights
        plot.addLinePlot("my plot2",Color.GREEN,linex,liney);



        x = new double[cls2.size()];
        y = new double[cls2.size()];

        for (int i = 0; i < cls2.size(); i++) {
            x[i]=cls2.get(i).X1;
            y[i]=cls2.get(i).X2;
        }
        plot.addScatterPlot("my plot3", Color.BLUE, x, y);

        // put the PlotPanel in a JFrame like a JPanel
        JFrame frame = new JFrame("a plot panel");
        frame.setSize(1000, 1000);
        frame.setContentPane(plot);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    /**
     * used to parse the provided text files
     * @param f file
     * @param classNumber +-1
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

        //plotClasses(cls1, cls2, p);

        ArrayList<Sample> allClasses = new ArrayList<Sample>();
        allClasses.addAll(cls1);
        allClasses.addAll(cls2);

        p.learn(allClasses);

        plotClasses(cls1, cls2, p);
    }
}
