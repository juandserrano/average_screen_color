
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class AverageColor {

    public static Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    public static BufferedImage screenCapture;
    //public static BufferedImage capture;
    public static MQTT mqtt = new MQTT();
    public static String brightness;
    public static Color average_color;

    public static void main(String[] args) throws Exception {
        boolean quit = false;
        Scanner input = new Scanner(System.in);
        String hexColor;
        while(!quit) {

            hexColor = run();
            Message msg = new Message(hexColor, brightness);
            mqtt.message(msg);
            //CopyHelper.copyPaste(hexColor);

            Thread.sleep(3000);
        }

    }

    public static BufferedImage givenMainScreen_whenTakeScreenshot_thenSaveToFile() throws Exception {

        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        return capture;
    }

    public static void averageColor(BufferedImage bi,int x0, int y0, int w, int h) throws Exception{
        int x1 = x0 + w;
        int y1 = y0 + h;
        long sumr = 0, sumg = 0, sumb = 0;
        for (int x = x0; x < x1; x++) {
            for (int y = y0; y < y1; y++) {
                Color pixel = new Color(bi.getRGB(x, y));
                sumr += pixel.getRed();
                sumg += pixel.getGreen();
                sumb += pixel.getBlue();
            }
        }
        int num = w * h;

        int ri = Integer.parseInt(Long.toString(sumr/num));
        int gi = Integer.parseInt(Long.toString(sumg/num));
        int bi2 = Integer.parseInt(Long.toString(sumb/num));

        average_color = new Color(ri, gi, bi2);

    }

    public static String run() throws Exception{

        screenCapture = givenMainScreen_whenTakeScreenshot_thenSaveToFile();
        int h = screenCapture.getHeight();
        int w = screenCapture.getWidth();
        averageColor(screenCapture, 0, 0, w, h);
        averageBrightness(screenCapture, 0, 0, w, h);
        screenCapture.flush();
        int r = average_color.getRed();
        int g = average_color.getGreen();
        int b = average_color.getBlue();
        String hex = String.format("#%02X%02X%02X", r, g, b);
        return hex;
    }

    public static void averageBrightness(BufferedImage bi,int x0, int y0, int w, int h) throws Exception{
        int x1 = x0 + w;
        int y1 = y0 + h;
        long sumr = 0, sumg = 0, sumb = 0;
        for (int x = x0; x < x1; x++) {
            for (int y = y0; y < y1; y++) {
                Color pixel = new Color(bi.getRGB(x, y));
                sumr += pixel.getRed();
                sumg += pixel.getGreen();
                sumb += pixel.getBlue();
            }
        }
        int num = w * h;

        int ri = Integer.parseInt(Long.toString(sumr/num));
        int gi = Integer.parseInt(Long.toString(sumg/num));
        int bi2 = Integer.parseInt(Long.toString(sumb/num));

        double averageBrightness = (((ri + gi + bi2) / 3.0) * 100) / 255;
        brightness = String.valueOf(averageBrightness);

    }
}
