import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BackPropagation {
    private double[] output;
    private double[] hidden;
    private double[][] weight1;
    private double[][] weight2;
    private double biasWeight1;
    private double biasWeight2;
    private double bias1 = 1;
    private double bias2 = 1;

    public void train(String outputPath, int inputNumber, int hiddenNumber,
                      int outputNumber, double[][] allInput, double[][] allOutput, int trainNumber) throws IOException {
        weight1 = new double[inputNumber][hiddenNumber];
        weight2 = new double[hiddenNumber][outputNumber];
        hidden = new double[hiddenNumber];
        output = new double[outputNumber];
        init();
        double r = 0.2;
        for (int count = 0; count < trainNumber; count++) {
            for (int num = 0; num < allInput.length; num++) {
                double[] input = allInput[num];
                double[] correct = allOutput[num];

                this.weighting(input, bias1, weight1, biasWeight1, hidden);
                this.weighting(hidden, bias2, weight2, biasWeight2, output);

                for (int h = 0; h < hiddenNumber; h++) {
                    double sum = 0;

                    //调整第二层
                    for (int o = 0; o < outputNumber; o++) {
                        //..if (Math.abs(correct[o] - output[o]) > 0.5) {
                            double delta = r * (correct[o] - output[o]) * output[o] * (1 - output[o]) * hidden[h];//hidden权重
                            sum += (weight2[h][o] * output[o] * (1 - output[o]) * (correct[o] - output[o]));
                            weight2[h][o] += delta;
                        //}
                    }

                    //调整第一层
                    for (int i = 0; i < inputNumber; i++) {
                        double delta = r * hidden[h] * (1 - hidden[h]) * input[i] * sum;
                        weight1[i][h] += delta;
                    }

                    //调整bias1
                    double delta = r * hidden[h] * (1 - hidden[h]) * sum;
                    biasWeight1 += delta;
                }

                //调整bias2
                for (int o = 0; o < outputNumber; o++) {
                   // if (Math.abs(correct[o] - output[o]) > 0.5) {
                        double delta = r * (correct[o] - output[o]) * output[o] * (1 - output[o]);//hidden权重
                        biasWeight2 += delta;
                    //}
                }
            }
        }
        List<String> outText = new LinkedList<>();
        for (int i = 0; i < inputNumber; i++) {
            String s = "";
            for (int j = 0; j < hiddenNumber; j++) {
                s += weight1[i][j] + " ";
            }
            outText.add(s);
            System.out.println(s);
        }

        outText.add(String.valueOf(biasWeight1));

        for (int i = 0; i < hiddenNumber; i++) {
            String s = "";
            for (int j = 0; j < outputNumber; j++) {
                s += weight2[i][j] + " ";
            }
            outText.add(s);
        }
        outText.add(String.valueOf(biasWeight2));

        FileIO.writeFile(outputPath, outText);
    }

    private void weighting(double[] input, double bias, double[][] weight, double biasWeight, double[] output) {
        for (int i = 0; i < output.length; i++) {
            double sum = 0;
            for (int j = 0; j < input.length; j++) {
                sum += (input[j] * weight[j][i]);
            }
            sum += biasWeight * bias;
            output[i] = this.sigmo(sum);
           // System.out.println(output[i]);
        }
    }

    private void init() {
        for (int i = 0; i < weight1.length; i++) {
            // double sum = 0;
            for (int j = 0; j < weight1[0].length; j++) {
                weight1[i][j] = Math.random() * 2 - 1;
                //  sum += weight1[][i];
            }
//            for (int j = 0; j < weight1.length; j++) {
//                weight1[j][i] /= sum;
//            }

        }
        for (int i = 0; i < weight2.length; i++) {
            //double sum = 0;
            for (int j = 0; j < weight2[0].length; j++) {
                weight2[i][j] = Math.random() * 2 - 1;
            }
//            for (int j = 0; j < weight2.length; j++) {
//                weight2[j][i] /= sum;
//            }
        }
        biasWeight1 = Math.random() * 2 - 1;

        biasWeight2 = Math.random() * 2 - 1;

    }

    public void load(String path, int inputNumber, int hiddenNumber,
                              int outputNumber) throws IOException {
        weight1 = new double[inputNumber][hiddenNumber];
        weight2 = new double[hiddenNumber][outputNumber];
        hidden = new double[hiddenNumber];
        output = new double[outputNumber];
        List<String> strs = FileIO.readFile(path);
        int line = 0;
        for (; line < inputNumber; line++) {
            String[] ss = strs.get(line).split(" ");
            for (int j = 0; j < hiddenNumber; j++) {
                weight1[line][j] = Double.parseDouble(ss[j]);
            }
        }

        biasWeight1=Double.parseDouble(strs.get(line));
        line++;
        for (; line < hiddenNumber; line++) {
            String[] ss = strs.get(line).split(" ");
            for (int j = 0; j < outputNumber; j++) {
                weight2[line][j] = Double.parseDouble(ss[j]);
            }
        }

    }

    public double[] getResult(double[] input,String path, int inputNumber, int hiddenNumber,
                              int outputNumber) throws IOException {
        this.load(path,  inputNumber,  hiddenNumber,  outputNumber);
        this.weighting(input, bias1, weight1, biasWeight1, hidden);
        this.weighting(hidden, bias2, weight2, biasWeight2, output);
        return output;
    }

    public double sigmo(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}
