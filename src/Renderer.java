
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import javax.swing.*;


public class Renderer extends JFrame {

    private BufferedImage image;

    public Renderer() throws InterruptedException {

        super("ACCURATE SIMULATION OF EARTH AND MOON");
        setBounds(100, 100, Main.size, Main.size);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {

                image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics g = image.getGraphics();
                g.setColor(Color.WHITE);

//                Main.update();

                //log
                System.out.println("frame #: "+Main.frames + " time elapsed: "+Main.frames*Main.t);
                Main.printObject(Main.obj);


                for (Object obj : Main.obj) {
                    int x = (int) obj.Sx / Main.scale;
                    int y = (int) obj.Sy / Main.scale;

                    boolean inGrid = obj.Sx/Main.scale < image.getWidth() && obj.Sy/Main.scale < image.getHeight() && 0 <= obj.Sx/Main.scale && 0 <= obj.Sy/Main.scale;
                    int radius = (int)obj.R/Main.scale;

                    //body
                    if (inGrid) {
                        g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
                    }
                    if (Main.showOrbitals) {
                        //orbital lines
                        for (int i = 1; i < obj.orbitalLinesX.length; i++) {
                            if (inGrid) {
                                image.setRGB(obj.orbitalLinesX[i], obj.orbitalLinesY[i], Color.white.getRGB());
                            }
                        }

                        obj.orbitalLinesX = pushBack(obj.orbitalLinesX);
                        obj.orbitalLinesY = pushBack(obj.orbitalLinesY);

                        obj.orbitalLinesX[0] = x;
                        obj.orbitalLinesY[0] = y;
                    }

                }

                repaint();



            }
        }, 0, Main.period);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Main.update();
            }
        }, 0, Main.timeScale);


    }

    private int[] pushBack(int[] orbitalLines) {
        int[] n = new int[orbitalLines.length];
        for (int i = 1; i < orbitalLines.length; i++) {
            n[i] = orbitalLines[i-1];
        }
        return n;
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

}