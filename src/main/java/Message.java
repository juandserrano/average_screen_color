import java.math.BigInteger;

public class Message {
    private String hexColor;
    private String brightness;

    public Message(String hexColor, String brightness) {
        this.hexColor = hexColor;
        this.brightness = Double.toString(Math.round(Double.parseDouble(brightness)));

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

    public void setAverageChannel(String averageChannel) {
        this.brightness = averageChannel;
    }
}
