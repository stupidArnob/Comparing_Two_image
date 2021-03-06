
// Java Program to compare two images
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;


public class matchMain {


    public static void main(String[] args) throws Exception {

        BufferedImage imgA = null;
        BufferedImage imgB = null;

        try {
            // File fileA = new File("./pic1.jpeg");
            // File fileB = new File("./pic1.jpeg");
            image_read imread = new image_read();
            imread.read();

            // Set Image 1
            imgA = ImageIO.read(new File("H:\\Project\\Saleh Project (image processing)\\Testers\\ImageMatchin\\src\\1.jpg"));
            
            // Set Image 2
            // imgB = ImageIO.read(new File("H:\\Project\\Saleh Project (image processing)\\Testers\\ImageMatchin\\src\\2.jpg"));   // image from file
            imgB = ImageIO.read(imread.binImage);                                                                                // image from database


        } catch (IOException e) {
            System.out.println(e);
        }

        // Set H and W of the Two image
        int width1 = imgA.getWidth();
        int width2 = imgB.getWidth();
        int height1 = imgA.getHeight();
        int height2 = imgB.getHeight();

        if ((width1 != width2) || (height1 != height2))
            System.out.println("Error: Images dimensions" + " mismatch");
        else {

            // In
            long difference = 0;
            for (int y = 0; y < height1; y++) {
                for (int x = 0; x < width1; x++) {
                    int rgbA = imgA.getRGB(x, y);
                    int rgbB = imgB.getRGB(x, y);
                    int redA = (rgbA >> 16) & 0xff;
                    int greenA = (rgbA >> 8) & 0xff;
                    int blueA = (rgbA) & 0xff;
                    int redB = (rgbB >> 16) & 0xff;
                    int greenB = (rgbB >> 8) & 0xff;
                    int blueB = (rgbB) & 0xff;
                    difference += Math.abs(redA - redB);
                    difference += Math.abs(greenA - greenB);
                    difference += Math.abs(blueA - blueB);
                }
            }

            // Total number of red pixels = width * height
            // Total number of blue pixels = width * height
            // Total number of green pixels = width * height
            // So total number of pixels = width * height * 3
            double total_pixels = width1 * height1 * 3;

            // Normalizing the value of different pixels
            // for accuracy(average pixels per color
            // component)
            double avg_different_pixels = difference /
                    total_pixels;

            // There are 255 values of pixels in total
            double percentage = (avg_different_pixels /
                    255) * 100;

            // Test image store componenet
            // get_Date d = new get_Date();
            // d.readDataBase();
            // image_store is = new image_store();
            // is.Store();

            System.out.println("Difference Percentage-->" +
                    percentage);
        }
    }

}
