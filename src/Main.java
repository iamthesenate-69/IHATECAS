import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collection;

public class Main {



    //time it takes to refresh image
    final public static int period = 1;
    static Object[] obj;
    //time per frame
    final static int t = 100;

    //timeScale : 1 ms
    final static int timeScale = 1;

    //scale : 1 m
    final static int scale = 1000000;

    //size by size pixel grid
    final static int size = 1200;

    final static boolean showOrbitals = true;

    final static double G = 6.67*Math.pow(10, -11);
    static int frames = 1;

    public static void main(String args[]) throws InterruptedException {

//        //earth
        Object obj1 = new Object(5.972*Math.pow(10, 24), 6371000 , 0, 0, 0, 0, true, true);
        //moon
        Object obj2 = new Object(7.348 * Math.pow(10, 22),1737500, 0, 405400000, 966,0, true, true);

        //satellite on moon
        Object obj3 = new Object(1,1, 0, 405400000 + 1737500 + 2000000, 966 + 1147.62631281, 0, true, true);

        //satellite on earth
        Object obj4 = new Object(1,1, 0, 6371000 + 4000000,  0 + 6197.44240422, 0, true, true);

//        Object obj3 = new Object(0,0,0,0,0,0,false,true);
//
//        Object obj4 = new Object(5.972*Math.pow(10, 24), 6371000 , Math.pow(10, 8), 100000000, 0, 5*Math.pow(10, 3), true, true);
//        Object obj5 = new Object(5.972*Math.pow(10, 25), 6371000 , 70000000, 70000000, Math.pow(10, 4), Math.pow(10, 3), true, true);
//        Object obj6 = new Object(5.972*Math.pow(10, 24), 6371000 , -Math.pow(10, 8), 100000000, 0, -5*Math.pow(10, 3), true, true);

        obj = new Object[]{obj1, obj2, obj3, obj4};
        new Renderer().setVisible(true);

    }

    static void update() {

        for (Object focus : obj) {
            for (Object others : obj) {
                if (focus.equals(others)) continue;
                if (!focus.log) continue;

//						image.setRGB(x, y, scale);
//                    double d = Math.sqrt(Math.pow((focus.Sx - others.Sx),2) + Math.pow((focus.Sy - others.Sy),2));

                //not calculus
                double dx = focus.Sx - others.Sx;
                double dy = focus.Sy - others.Sy;
                double d = Math.sqrt(dx*dx+dy*dy);
                //a = Gm/r^2

                double a = G*others.mass/(d*d);
//                System.out.println(a);
                double ax = -dx / d * a;
                double ay = -dy / d * a;

//                    double ay = 9.81;

                focus.setVelocity( focus.Vx + ax*t, focus.Vy +ay*t);
                //v = u + at
                //s = s0 + vt
//                focus.addVelocity(ax*t, ay*t);
            }
            //s = ut + 1/2at^2
            focus.setDisplacement(focus.Sx + focus.Vx*t, focus.Sy + focus.Vy*t);
        }

        frames++;


    }

    static void printObject(Object[] obj) {
        for (Object o : obj) {
            o.print();
        }
    }


}
