import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageManipulator {
    private final String rawImagePath;
    private final String outputImagePath;
    
   
    public ImageManipulator(String rawImagePath, String outputImagePath) {
        this.rawImagePath = rawImagePath;
        this.outputImagePath = outputImagePath;
    }


    public void addBlueEffect() {
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
        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int pixels = imageBuffer.getRGB(x, y);
                int a = (pixels >> 24) & 0xff;
                int b = pixels&0xff;
                pixels = (a << 24) | (0 << 16) | (0 << 8) | b;
                imageBuffer.setRGB(x, y, pixels);
            }
        }

        try {
            fileBuffer = new File(outputImagePath);
            ImageIO.write(imageBuffer, "jpg", fileBuffer);
            System.out.println("Image successfullt saved to " + outputImagePath);
        } catch(IOException ex) {
            System.out.println("We faced some problem during processing image.");
        }
    }
}