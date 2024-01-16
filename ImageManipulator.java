/*
    File containing the ImageManipulator class with methods to manipulate images.
    Author: Ibne Nahian (evilprince2009)
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

public class ImageManipulator {
    private final String rawImagePath;
    private BufferedImage imageBuffer;
    private File fileBuffer;
    private final String format;

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
                int pixels = getImagePixels(horizontal, vertical);

                int autos = (pixels >> 24) & 0xff;
                int red = (pixels >> 16) & 0xff;
                int green = (pixels >> 8) & 0xff;
                int blue = pixels & 0xff;

                int avgRed = (int) (0.393 * red + 0.769 * green + 0.189 * blue);
                int avgGreen = (int) (0.349 * red + 0.686 * green + 0.168 * blue);
                int avgBlue = (int) (0.272 * red + 0.534 * green + 0.131 * blue);

                red = Math.min(avgRed, 255);
                green = Math.min(avgGreen, 255);
                blue = Math.min(avgBlue, 255);

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
                int pixels = getImagePixels(horizontal, vertical);

                int autos = (pixels >> 24) & 0xff;
                int red = (pixels >> 16) & 0xff;

                pixels = (autos << 24) | (red << 16) | (0);
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
                int pixels = getImagePixels(horizontal, vertical);

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
                int pixels = getImagePixels(horizontal, vertical);
                int autos = (pixels >> 24) & 0xff;
                int green = (pixels >> 8) & 0xff;
                pixels = (autos << 24) | (0) | (green << 8);
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
                int pixels = getImagePixels(horizontal, vertical);
                int autos = (pixels >> 24) & 0xff;
                int red = (pixels >> 16) & 0xff;
                int green = (pixels >> 8) & 0xff;
                int blue = pixels & 0xff;
                int average = (int) Stream.of(red, blue, green).mapToInt(Integer::intValue)
                        .summaryStatistics()
                        .getAverage();
                pixels = (autos << 24) | (average << 16)
                        | (average << 8) | average;
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
                int pixels = getImagePixels(horizontal, vertical);
                int autos = (pixels >> 24) & 0xff;
                int blue = pixels & 0xff;
                pixels = (autos << 24) | (0) | blue;
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
            logger(ex.getMessage());
        }
    }

    private void writeImage(String filePath, String format) {
        try {
            fileBuffer = new File(filePath);
            ImageIO.write(imageBuffer, format, fileBuffer);
            logger("Image successfully saved to " + filePath);
        } catch (IOException ex) {
            logger(ex.getMessage());
        }
    }

    private void logger(String message) {
        final String filePath = "details.log";
        try {
            File file = new File(filePath);
            if (!file.exists())
                file.createNewFile();

            try (FileWriter writer = new FileWriter(filePath, true)) {
                writer.append("\n").append(message);
            }
        } catch (Exception e) {
            System.err.println("A full log can be found at " + filePath);
        }
    }

    private int getImagePixels(int horizontal, int vertical) {
        return imageBuffer.getRGB(horizontal, vertical);
    }
}