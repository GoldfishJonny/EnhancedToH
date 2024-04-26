package com.javiergs;
import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MapPanel extends JPanel {

  public MapPanel() {
    setBackground(Color.LIGHT_GRAY);
    try {
      ImageIcon imageIcon = getMap();
      imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
      add(new JLabel(imageIcon));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private ImageIcon getMap() throws Exception {
    String location = "35.270378,-120.680656";
    String apiKey = "[ADD HERE YOUR KEY FOR A LOCAL TEST]";
    
    // create a second version in which your KEY is not embedded in your code
    
    String mapUrl = "https://dev.virtualearth.net/REST/v1/Imagery/Map/Road/"
      + location + "?zoomLevel=10&mapSize=200,200&key="
      + apiKey;

    URL mapImageUrl = new URL(mapUrl);
    URLConnection connection = mapImageUrl.openConnection();
    InputStream inputStream = connection.getInputStream();

    OutputStream outputStream = new FileOutputStream("image.jpg");
    byte[] b = new byte[2048];
    int length;
    while ((length = inputStream.read(b)) != -1) {
      outputStream.write(b, 0, length);
    }
    inputStream.close();
    outputStream.close();
    return new ImageIcon("image.jpg");
  }
}
