import java.io.IOException;

public class Main {
    public static void main(String args[])throws IOException{
        String inputImagePath = "C:\\path_to_raw_image\\original-image.jpg";
        String outputImagePath = "C:\\path_to_manipulated_image\\manipulated-image.jpg";

        ImageManipulator image = new ImageManipulator(inputImagePath, outputImagePath);
        image.addBlueEffect();
    }
}