import java.math.BigInteger;

public class Message {
    private String hexColor;
    private String brightness;
    private int[] values = {0, 5 , 10 , 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100};

    public Message(String hexColor, String brightness) {
        this.hexColor = hexColor;
        setBrightness(brightness);

    }

    public String getHexColor() {
        return hexColor;
    }

    public String getBrightness() {
        return this.brightness;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public void setBrightness(String brightness) {
        double doubleBrightness = Double.parseDouble(brightness);
        double lowestDifference = 101;
        int lowestDiffValueIndex = 0;
        for (int i = 0; i < values.length; i++){
            if (Math.abs(values[i] - doubleBrightness) < lowestDifference){
                lowestDiffValueIndex = i;
                lowestDifference = Math.abs(values[i] - doubleBrightness);
            }
        }
        this.brightness = Integer.toString(values[lowestDiffValueIndex]);
    }
}
