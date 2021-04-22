
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class AverageColor {

    public static Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    public static BufferedImage screenCapture;
    //public static BufferedImage capture;
    public static MQTT mqtt = new MQTT();


    public static void main(String[] args) throws Exception {
        boolean quit = false;
        Scanner input = new Scanner(System.in);
        String hexColor;
        while(!quit) {
            hexColor = run();
            mqtt.message(hexColor);
            //CopyHelper.copyPaste(hexColor);

            Thread.sleep(3000);
        }

    }

    public static BufferedImage givenMainScreen_whenTakeScreenshot_thenSaveToFile() throws Exception {

        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        return capture;
    }

    public static Color averageColor(BufferedImage bi,int x0, int y0, int w, int h) throws Exception{
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

        Color averageColor = new Color(ri, gi, bi2);
        return averageColor;
    }

    public static String run() throws Exception{

        screenCapture = givenMainScreen_whenTakeScreenshot_thenSaveToFile();
        int h = screenCapture.getHeight();
        int w = screenCapture.getWidth();
        Color average_color = averageColor(screenCapture, 0, 0, w, h);
        screenCapture.flush();
        int r = average_color.getRed();
        int g = average_color.getGreen();
        int b = average_color.getBlue();
        String hex = String.format("#%02X%02X%02X", r, g, b);
        return hex;
    }
}
