import java.io.IOException;
import java.util.Scanner;

public class NumeralRecognition {
    final static double[][] INPUT = {
            {1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 1, 1, 0, 0},
            {1, 0, 1, 1, 0, 1, 1},
            {0, 0, 1, 1, 1, 1, 1},
            {0, 1, 0, 1, 1, 0, 1},
            {0, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1},
            {0, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1},
    };
    final static double[][] OUTPUT = {
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    };
    private final static String OUTPUT_PATH = "numberResult.txt";

    public void train() throws IOException {
        BackPropagation bp = new BackPropagation();
        bp.train(OUTPUT_PATH,7,20,10,INPUT,OUTPUT,200000);
    }

    public  static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String inputStr=sc.nextLine();
        String[] strs=inputStr.split(" ");
        double[] input=new double[strs.length];
        for(int i=0;i<input.length;i++){
            input[i]=Double.parseDouble(strs[i]);
        }
        BackPropagation bp=new BackPropagation();
        double[] out;
        try {
            out=bp.getResult(input,OUTPUT_PATH,7,20,10);
            for(int i=0;i<10;i++){
                System.out.println(i+" "+String.valueOf(out[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void getResult(){

    }
    //public void

//    private void weighting(double[] input, double[][] weight, double[] output) {
//        for (int i = 0; i < output.length; i++) {
//            double sum = 0;
//            for (int j = 0; j < input.length; j++) {
//                sum += input[j] * weight[j][i];
//            }
//            output[i] = this.sigmo(sum);
//        }
//    }
//
//    private void init() {
//        for (int i = 0; i < weight1.length; i++) {
//            for (int j = 0; j < weight1[0].length; j++) {
//                weight1[i][j] = Math.random() * 2 - 1;
//            }
//        }
//        for (int i = 0; i < weight2.length; i++) {
//            for (int j = 0; j < weight2[0].length; j++) {
//                weight2[i][j] = Math.random() * 2 - 1;
//            }
//        }
//    }

    public double sigmo(double x) {
        return 1 / (1 + Math.cbrt(-x));
    }
}
