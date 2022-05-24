import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Object {
    double mass;
    double Sx;
    double Sy;
    double Vx;
    double Vy;
    double R;
    boolean log;
    int[] orbitalLinesX;
    int[] orbitalLinesY;

    public Object(double _mass, double _R, double _Sx, double _Sy, double _Vx, double _Vy, boolean centre, boolean _log) {
        mass = _mass;
        Sx = _Sx;
        Sy = _Sy;
        R = _R;
        if (centre) {
            Sx += Main.scale*Main.size/2;
            Sy += Main.scale*Main.size/2;
        }
        Vx = _Vx;
        Vy = _Vy;
        log = _log;

        orbitalLinesX = new int[7270];
        orbitalLinesY = new int[7270];

        Arrays.fill(orbitalLinesX, 0);
        Arrays.fill(orbitalLinesY, 0);
    }

    public void setVelocity(double _Vx, double _Vy) {
        Vx = _Vx;
        Vy = _Vy;
    }

    public void setDisplacement(double _Sx, double _Sy) {
        Sx = _Sx;
        Sy = _Sy;
    }

    public void print() {
        if (log)
            System.out.println(Sx + " " + Sy + " " + Vx + " " + Vy);
    }
}
