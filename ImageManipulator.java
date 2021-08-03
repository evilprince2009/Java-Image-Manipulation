/*
    File containing the ImageManipulator class with methods to manipulate images.
    Author: Ibne Nahian (evilprince2009)
*/

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageManipulator {
    private final String rawImagePath;
    private BufferedImage imageBuffer;
    private File fileBuffer;

    public ImageManipulator(String rawImagePath) {
        this.rawImagePath = rawImagePath;
        this.imageBuffer = null;
        this.fileBuffer = null;
    }

    public void addGreenMatrix(String outputImagePath) {
        try {
            fileBuffer = new File(rawImagePath);
            imageBuffer = ImageIO.read(fileBuffer);
        } catch (IOException e) {
            System.out.println("Problem with reading image.");
        }

        int width = imageBuffer.getWidth();
        int height = imageBuffer.getHeight();

        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                int pixels = imageBuffer.getRGB(horizontal, vertical);
                int autos = (pixels >> 24) & 0xff;
                int green = (pixels >> 8) & 0xff;
                pixels = (autos << 24) | (0 << 16) | (green << 8) | 0;
                imageBuffer.setRGB(horizontal, vertical, pixels);
            }
        }

        try {
            writeImage(outputImagePath);
        } catch (IOException ex) {
            System.out.println("We faced some problem during processing image.");
        }
    }

    public void addGrayScale(String outputImagePath) {
        try {
            fileBuffer = new File(rawImagePath);
            imageBuffer = ImageIO.read(fileBuffer);
        } catch (IOException ex) {
            System.out.println("Problem with reading image.");
        }
        int width = imageBuffer.getWidth();
        int height = imageBuffer.getHeight();
        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                int pixels = imageBuffer.getRGB(horizontal, vertical);
                int autos = (pixels >> 24) & 0xff;
                int red = (pixels >> 16) & 0xff;
                int green = (pixels >> 8) & 0xff;
                int blue = pixels & 0xff;
                int avarage = (red + green + blue) / 3;
                pixels = (autos << 24) | (avarage << 16) | (avarage << 8) | avarage;
                imageBuffer.setRGB(horizontal, vertical, pixels);
            }
        }

        try {
            writeImage(outputImagePath);
        } catch (IOException ex) {
            System.out.println("We faced some problem during processing image.");
        }
    }

    public void addBlueEffect(String outputImagePath) {
        try {
            fileBuffer = new File(rawImagePath);
            imageBuffer = ImageIO.read(fileBuffer);
        } catch (IOException ex) {
            System.out.println("Problem with reading image.");
        }
        int width = imageBuffer.getWidth();
        int height = imageBuffer.getHeight();

        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                int pixels = imageBuffer.getRGB(horizontal, vertical);
                int autos = (pixels >> 24) & 0xff;
                int blue = pixels & 0xff;
                pixels = (autos << 24) | (0 << 16) | (0 << 8) | blue;
                imageBuffer.setRGB(horizontal, vertical, pixels);
            }
        }

        try {
            writeImage(outputImagePath);
        } catch (IOException ex) {
            System.out.println("We faced some problem during processing image.");
        }
    }

    private void writeImage(String filePath) throws IOException {
        fileBuffer = new File(filePath);
        ImageIO.write(imageBuffer, "jpg", fileBuffer);
        System.out.println("Image successfully saved to " + filePath);
    }
}