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
    private final String raw_image_path;
    private BufferedImage image_buffer;
    private File file_buffer;
    private final String format;

    public ImageManipulator(String raw_image_path, String format) {
        this.raw_image_path = raw_image_path;
        this.image_buffer = null;
        this.file_buffer = null;
        this.format = format;
    }

    public void addSepia(String out_image_path) {
        setBuffer();
        int width = image_buffer.getWidth();
        int height = image_buffer.getHeight();

        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                int pixels = image_buffer.getRGB(horizontal, vertical);

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
                image_buffer.setRGB(horizontal, vertical, pixels);
            }
        }
        writeImage(out_image_path, format);
    }

    public void addRedDevil(String out_image_path) {
        setBuffer();
        int width = image_buffer.getWidth();
        int height = image_buffer.getHeight();

        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                int pixels = image_buffer.getRGB(horizontal, vertical);

                int autos = (pixels >> 24) & 0xff;
                int red = (pixels >> 16) & 0xff;

                pixels = (autos << 24) | (red << 16) | (0);
                image_buffer.setRGB(horizontal, vertical, pixels);
            }
        }
        writeImage(out_image_path, format);
    }

    public void addNegativeEffect(String out_image_path) {
        setBuffer();
        int width = image_buffer.getWidth();
        int height = image_buffer.getHeight();

        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                int pixels = image_buffer.getRGB(horizontal, vertical);

                int autos = (pixels >> 24) & 0xff;
                int red = (pixels >> 16) & 0xff;
                int green = (pixels >> 8) & 0xff;
                int blue = pixels & 0xff;

                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;

                pixels = (autos << 24) | (red << 16) | (green << 8) | blue;
                image_buffer.setRGB(horizontal, vertical, pixels);
            }
        }
        writeImage(out_image_path, format);
    }

    public void addGreenMatrix(String out_image_path) {
        setBuffer();
        int width = image_buffer.getWidth();
        int height = image_buffer.getHeight();

        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                int pixels = image_buffer.getRGB(horizontal, vertical);
                int autos = (pixels >> 24) & 0xff;
                int green = (pixels >> 8) & 0xff;
                pixels = (autos << 24) | (0) | (green << 8);
                image_buffer.setRGB(horizontal, vertical, pixels);
            }
        }
        writeImage(out_image_path, format);
    }

    public void addGrayScale(String out_image_path) {
        setBuffer();
        int width = image_buffer.getWidth();
        int height = image_buffer.getHeight();
        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                int pixels = image_buffer.getRGB(horizontal, vertical);
                int autos = (pixels >> 24) & 0xff;
                int red = (pixels >> 16) & 0xff;
                int green = (pixels >> 8) & 0xff;
                int blue = pixels & 0xff;
                int average = (int) Stream.of(red, blue, green).mapToInt(Integer::intValue)
                        .summaryStatistics()
                        .getAverage();
                pixels = (autos << 24) | (average << 16)
                        | (average << 8) | average;
                image_buffer.setRGB(horizontal, vertical, pixels);
            }
        }
        writeImage(out_image_path, format);
    }

    public void addBlueEffect(String outputImagePath) {
        setBuffer();
        int width = image_buffer.getWidth();
        int height = image_buffer.getHeight();

        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                int pixels = image_buffer.getRGB(horizontal, vertical);
                int autos = (pixels >> 24) & 0xff;
                int blue = pixels & 0xff;
                pixels = (autos << 24) | (0) | blue;
                image_buffer.setRGB(horizontal, vertical, pixels);
            }
        }
        writeImage(outputImagePath, format);
    }

    private void setBuffer() {
        try {
            file_buffer = new File(raw_image_path);
            image_buffer = ImageIO.read(file_buffer);
        } catch (IOException ex) {
            logger(ex.getMessage());
        }
    }

    private void writeImage(String file_path, String format) {
        try {
            file_buffer = new File(file_path);
            ImageIO.write(image_buffer, format, file_buffer);
            logger("Image successfully saved to " + file_path);
        } catch (IOException ex) {
            logger(ex.getMessage());
        }
    }

    private void logger(String message) {
        final String file_path = "details.log";
        try {
            File file = new File(file_path);
            if (!file.exists())
                file.createNewFile();

            try (FileWriter writer = new FileWriter(file_path, true)) {
                writer.append("\n").append(message);
            }
        } catch (Exception e) {
            System.err.println("A full log can be found at " + file_path);
        }
    }
}