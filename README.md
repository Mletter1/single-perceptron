single-perceptron
=================

implementation of a single preceptron with 2 inputs

1) Perceptron
=================

Using the attached data files, create the code for and test a single neuron classifier using the Perceptron Learning Rule.
This is a two class problem with a two dimensional feature vector x. The data sets provided below contain a list of samples for each of the two classes. The fields in the data are: feature vector index, x[1], x[2]. You must include a bias input x[0] and weight.

  a) 
=================
The first sets of data are for training, labeled "Percep", while the second testing sets, labeled "PDR" are for independently estimating the error. The desired value for class 1 is +1, while the desired value for class 2 is -1. Both sets are drawn from the same parent distributions for each class, except that the training data for this part of the homework is linearly separable, while the testing data is not. Plot the data for the two classes on a single scatter plot to study any class overlap.

  b) 
=================
Train and test the single neuron with the included data. Experiment with different conditions, including learning rate parameters, random initial weight selection, and ordering of presentation. Tabulate the results of your experiments in tables. For several of these conditions, produce generalization graphs. A generalization graph is where the training and testing errors are plotted together on the same graph as a function of epoch number. To compute this: at the end of each training epoch, record the current total training error, turn off learning, present the testing data set, and record the current total testing error. Turn back on learning and proceed with the next training epoch.

  c)
=================
Write several paragraphs reporting the results of your studies. Explain your stopping criterion. Label all graphs and tables.
* You may implement these classifiers in any computer language you like, but you must do it your self. For homework assignments, do not work in groups and do not share code. Include code listings in your report.
* 

2) Delta-Rule (LMS)
=================

Create, test, and document a single neuron classifier using the Delta-Rule Learning Rule exactly as in Part 1. For this part, use the files labeled "DeltaRule" for training but use the same testing files labeled "PDR" as in Part 1. Note that this training data is NOT linearly separable in this part of the homework.
- Note that for Part 2 you will need to establish a different stopping criterion from that of the Perceptron, which has the possibility of reaching zero error for some epoch number. Just make sure you tell me exactly what criterion you use in your report.
