import java.io.IOException;
import java.util.Scanner;

/**
 * Created by admin on 2016/9/26.
 */
public class SineFitting {
    private static final double PI = Math.PI;

    private static final int DATE_NUMBER = 8000;
    private static final String PATH = "sineResult.txt";
    private static final int HIDDEN_NUMBER = 1;
    private static final int TRAIN_NUMBER = 20000;

    public static double sigmoReverse(double x) {
        return Math.log(x / (1 - x));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please input:");
            String inputStr = sc.nextLine();
            double[] input = {Double.parseDouble(inputStr)};
            BackPropagation bp = new BackPropagation();
            try {
                double[] out = bp.getResult(input, PATH, 1, 1, 1);
                System.out.println(out[0]);
                System.out.println(sigmoReverse(out[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void train() throws IOException {
        double[][] input = new double[DATE_NUMBER + 1][1];
        double[][] output = new double[DATE_NUMBER + 1][1];
        double gap = PI / DATE_NUMBER;
        for (int i = 0; i <= DATE_NUMBER; i++) {
            input[i][0] = -PI / 2 + gap * i;
            output[i][0] = sigmo(Math.sin(input[i][0]));
        }
        BackPropagation bp = new BackPropagation();
        bp.train(PATH, 1, HIDDEN_NUMBER, 1, input, output, TRAIN_NUMBER);
    }

    public double sigmo(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}
