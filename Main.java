import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        String inputImagePath = "D:\\GitHub Repos\\Java-Image-Manipulation\\raw images\\imran-original.jpg";

        String blueImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-blue.jpg";

        String grayscaleImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-grayscale.jpg";
        
        String greenImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-green.jpg";

        ImageManipulator image = new ImageManipulator(inputImagePath);
        image.addBlueEffect(blueImageOutPath);
        image.addGrayScale(grayscaleImageOutPath);
        image.addGreenMatrix(greenImageOutPath);
    }
}