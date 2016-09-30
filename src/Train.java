import java.io.File;
import java.io.IOException;

public class Train {

    public static void main(String[] args) {
        //NumeralRecognition nr=new NumeralRecognition();
        SineFitting sf=new SineFitting();
        try {
            //nr.train();
            sf.train();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
