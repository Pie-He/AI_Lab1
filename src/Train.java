import java.io.File;
import java.io.IOException;

public class Train {

    public static void main(String[] args) {
        NumeralRecognition nr=new NumeralRecognition();
        try {
            nr.train();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
