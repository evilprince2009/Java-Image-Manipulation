import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageManipulator {
    private final String rawImagePath;

    public ImageManipulator(String rawImagePath) {
        this.rawImagePath = rawImagePath;
    }

    public void addGrayScale(String outputImagePath) {
        BufferedImage imageBuffer = null;
        File fileBuffer = null;

        try {
            fileBuffer = new File(rawImagePath);
            imageBuffer = ImageIO.read(fileBuffer);
        } catch (IOException ex) {
            System.out.println("Problem with reading image.");
        }

        int width = imageBuffer.getWidth();
        int height = imageBuffer.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixels = imageBuffer.getRGB(x, y);
                int a = (pixels >> 24) & 0xff;
                int red = (pixels >> 16) & 0xff;
                int green = (pixels >> 8) & 0xff;
                int blue = pixels & 0xff;
                int avarage = (red + green + blue) / 3;

                pixels = (a << 24) | (avarage << 16) | (avarage << 8) | avarage;
                imageBuffer.setRGB(x, y, pixels);
            }
        }

        try {
            fileBuffer = new File(outputImagePath);
            ImageIO.write(imageBuffer, "jpg", fileBuffer);
            System.out.println("Image successfully saved to " + outputImagePath);
        } catch (IOException ex) {
            System.out.println("We faced some problem during processing image.");
        }
    }

    public void addBlueEffect(String outputImagePath) {
        BufferedImage imageBuffer = null;
        File fileBuffer = null;

        try {
            fileBuffer = new File(rawImagePath);
            imageBuffer = ImageIO.read(fileBuffer);
        } catch (IOException ex) {
            System.out.println("Problem with reading image.");
        }

        int width = imageBuffer.getWidth();
        int height = imageBuffer.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixels = imageBuffer.getRGB(x, y);
                int a = (pixels >> 24) & 0xff;
                int b = pixels & 0xff;
                pixels = (a << 24) | (0 << 16) | (0 << 8) | b;
                imageBuffer.setRGB(x, y, pixels);
            }
        }

        try {
            fileBuffer = new File(outputImagePath);
            ImageIO.write(imageBuffer, "jpg", fileBuffer);
            System.out.println("Image successfully saved to " + outputImagePath);
        } catch (IOException ex) {
            System.out.println("We faced some problem during processing image.");
        }
    }
}