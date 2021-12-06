package Extra;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.System.in;
//Source:https://stackoverflow.com/questions/5882005/how-to-download-image-from-any-web-page-in-java
public class SaveImageFromUrl {

    public static void saveImage(String str, String name) throws IOException {
        URL url = new URL(str);
        URLConnection urlc = url.openConnection();
        //To allow access to url and prevent server error 403
        urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; "
                + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
        InputStream inputFile = urlc.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=inputFile.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        inputFile.close();
        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream(name+".jpg");
        fos.write(response);
        fos.close();
    }
}