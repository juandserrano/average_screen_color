import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.event.KeyEvent;

public class CopyHelper {

    public static void copyPaste(String hexColor){

        StringSelection stringSelection = new StringSelection(hexColor);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        try
        {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_META);
            r.keyPress(KeyEvent.VK_V);
            r.keyRelease(KeyEvent.VK_META);
            r.keyRelease(KeyEvent.VK_V);
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
        }
        catch(Exception e)
        {

        }
    }

}
