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
        try {
            fileBuffer = new File(rawImagePath);
            imageBuffer = ImageIO.read(fileBuffer);
        } catch (IOException ex) {
            System.out.println("Problem with reading image.");
            return;
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

                int avgRed = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                int avgGreen = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                int avgBlue = (int)(0.272 * red + 0.534 * green + 0.131 * blue);

                if (avgRed > 255) {
                    red = 255;
                } else {
                    red = avgRed;
                }

                if (avgGreen > 255) {
                    green = 255;
                } else {
                    green = avgGreen;
                }

                if (avgBlue > 255) {
                    blue = 255;
                } else {
                    blue = avgBlue;
                }

                //set new RGB value
                pixels = (autos << 24) | (red << 16) | (green << 8) | blue;
                imageBuffer.setRGB(horizontal, vertical, pixels);
            }
        }

        try {
            writeImage(outputImagePath, format);
        } catch (IOException ex) {
            System.out.println("We faced some problem during processing image.");
        }
    }

    public void addRedDevil(String outputImagePath) {
        try {
            fileBuffer = new File(rawImagePath);
            imageBuffer = ImageIO.read(fileBuffer);
        } catch (IOException ex) {
            System.out.println("Problem with reading image.");
            return;
        }

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

        try {
            writeImage(outputImagePath, format);
        } catch (IOException ex) {
            System.out.println("We faced some problem during processing image.");
        }
    }

    public void addNegativeEffect(String outputImagePath) {
        try {
            fileBuffer = new File(rawImagePath);
            imageBuffer = ImageIO.read(fileBuffer);
        } catch (IOException ex) {
            System.out.println("Problem with reading image.");
            return;
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

                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;

                pixels = (autos << 24) | (red << 16) | (green << 8) | blue;
                imageBuffer.setRGB(horizontal, vertical, pixels);
            }
        }

        try {
            writeImage(outputImagePath, format);
        } catch (IOException ex) {
            System.out.println("We faced some problem during processing image.");
        }
    }

    public void addGreenMatrix(String outputImagePath) {
        try {
            fileBuffer = new File(rawImagePath);
            imageBuffer = ImageIO.read(fileBuffer);
        } catch (IOException ex) {
            System.out.println("Problem with reading image.");
            return;
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
            writeImage(outputImagePath, format);
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
            return;
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
            writeImage(outputImagePath, format);
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
            return;
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
            writeImage(outputImagePath, format);
        } catch (IOException ex) {
            System.out.println("We faced some problem during processing image.");
        }
    }

    private void writeImage(String filePath, String format) throws IOException {
        fileBuffer = new File(filePath);
        ImageIO.write(imageBuffer, format, fileBuffer);
        System.out.println("Image successfully saved to " + filePath);
    }
}