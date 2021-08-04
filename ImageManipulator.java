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
    private String format;

    public ImageManipulator(String rawImagePath, String format) {
        this.rawImagePath = rawImagePath;
        this.imageBuffer = null;
        this.fileBuffer = null;
        this.format = format;
    }

    public void addSepia(String outputImagePath) {
        setBuffer();
        int width = imageBuffer.getWidth();
        int height = imageBuffer.getHeight();

        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                int pixels = imageBuffer.getRGB(horizontal, vertical);

                int autos = (pixels >> 24) & 0xff;
                int red = (pixels >> 16) & 0xff;
                int green = (pixels >> 8) & 0xff;
                int blue = pixels & 0xff;

                int avgRed = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                int avgGreen = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                
                int avgBlue = (int)(0.272 * red + 0.534 * green + 0.131 * blue);
                red = (avgRed > 255) ? 255 : avgRed;
                green = (avgGreen > 255) ? 255 : avgGreen;
                blue = (avgBlue > 255) ? 255 : avgBlue;

                pixels = (autos << 24) | (red << 16) | (green << 8) | blue;
                imageBuffer.setRGB(horizontal, vertical, pixels);
            }
        }
        writeImage(outputImagePath, format);
    }

    public void addRedDevil(String outputImagePath) {
        setBuffer();
        int width = imageBuffer.getWidth();
        int height = imageBuffer.getHeight();

        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                int pixels = imageBuffer.getRGB(horizontal, vertical);

                int autos = (pixels >> 24) & 0xff;
                int red = (pixels >> 16) & 0xff;

                pixels = (autos << 24) | (red << 16) | (0 << 8) | 0;
                imageBuffer.setRGB(horizontal, vertical, pixels);
            }
        }
        writeImage(outputImagePath, format);
    }

    public void addNegativeEffect(String outputImagePath) {
        setBuffer();
        int width = imageBuffer.getWidth();
        int height = imageBuffer.getHeight();

        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                int pixels = imageBuffer.getRGB(horizontal, vertical);

                int autos = (pixels >> 24) & 0xff;
                int red = (pixels >> 16) & 0xff;
                int green = (pixels >> 8) & 0xff;
                int blue = pixels & 0xff;

                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;

                pixels = (autos << 24) | (red << 16) | (green << 8) | blue;
                imageBuffer.setRGB(horizontal, vertical, pixels);
            }
        }
        writeImage(outputImagePath, format);
    }

    public void addGreenMatrix(String outputImagePath) {
        setBuffer();
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
        writeImage(outputImagePath, format);
    }

    public void addGrayScale(String outputImagePath) {
        setBuffer();
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
        writeImage(outputImagePath, format);
    }

    public void addBlueEffect(String outputImagePath) {
        setBuffer();
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
        writeImage(outputImagePath, format);
    }

    private void setBuffer() {
        try {
            fileBuffer = new File(rawImagePath);
            imageBuffer = ImageIO.read(fileBuffer);
        } catch (IOException ex) {
            System.out.println("Problem with reading image.");
            return;
        }
    }

    private void writeImage(String filePath, String format) {
        try {
            fileBuffer = new File(filePath);
            ImageIO.write(imageBuffer, format, fileBuffer);
            System.out.println("Image successfully saved to " + filePath);
        } catch (IOException ex) {
            System.out.println("We faced some problem during processing image.");
        }
    }
}