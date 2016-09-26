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
    private static final int HIDDEN_NUMBER = 20;
    private static final int INPUT_NUMBER = 7;
    private static final int OUTPUT_NUMBER = 10;
    private static final int TRAIN_NUMBER = 20000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please input:");
            String inputStr = sc.nextLine();
            String[] strs = inputStr.split(" ");
            double[] input = new double[strs.length];
            for (int i = 0; i < input.length; i++) {
                input[i] = Double.parseDouble(strs[i]);
            }
            BackPropagation bp = new BackPropagation();
            double[] out;
            try {
                out = bp.getResult(input, OUTPUT_PATH, INPUT_NUMBER, HIDDEN_NUMBER, OUTPUT_NUMBER);
                for (int i = 0; i < 10; i++) {
                    System.out.println(i + " " + String.valueOf(out[i]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void train() throws IOException {
        BackPropagation bp = new BackPropagation();
        bp.train(OUTPUT_PATH, INPUT_NUMBER, HIDDEN_NUMBER, OUTPUT_NUMBER, INPUT, OUTPUT, TRAIN_NUMBER);
    }


}
