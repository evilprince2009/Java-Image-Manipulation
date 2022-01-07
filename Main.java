
public class Main {
    public static void main(String[] args) {

        final String format = "jpg";

        String inputImagePath = "D:\\GitHub Repos\\Java-Image-Manipulation\\raw images\\imran-original.jpg";

        String blueImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-blue.jpg";

        String grayscaleImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-grayscale.jpg";

        String greenImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-green.jpg";

        String negativeImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-negative.jpg";

        String redImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-red.jpg";

        String sepiaImageOutPath = "D:\\GitHub Repos\\Java-Image-Manipulation\\manipulated images\\imran-sepia.jpg";

        ImageManipulator imageInstance = new ImageManipulator(inputImagePath, format);

        imageInstance.addBlueEffect(blueImageOutPath);
        imageInstance.addGrayScale(grayscaleImageOutPath);
        imageInstance.addGreenMatrix(greenImageOutPath);
        imageInstance.addNegativeEffect(negativeImageOutPath);
        imageInstance.addRedDevil(redImageOutPath);
        imageInstance.addSepia(sepiaImageOutPath);
    }
}