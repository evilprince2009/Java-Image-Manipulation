import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        String inputImagePath = "D:\\GitHub Repos\\Java-Image-Manipulation\\raw images\\imran-original.jpg";

        String blueImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-blue.jpg";

        String grayscaleImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-grayscale.jpg";

        String greenImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-green.jpg";

        String negativeImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-negative.jpg";
        String redImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-red.jpg";

        String sepiaImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-sepia.jpg";

        ImageManipulator imageInstace = new ImageManipulator(inputImagePath, "jpg");
        
        imageInstace.addBlueEffect(blueImageOutPath);
        imageInstace.addGrayScale(grayscaleImageOutPath);
        imageInstace.addGreenMatrix(greenImageOutPath);
        imageInstace.addNegativeEffect(negativeImageOutPath);
        imageInstace.addRedDevil(redImageOutPath);
        imageInstace.addSepia(sepiaImageOutPath);
    }
}