package Project5_Drawing;

/*
Raymond Gee, Kelly Suen, Yulia Bugrova
 */

import java.awt.*;

public class ColoredPoint extends Point {

    private Color color; // Holds paint color.
    private int diameter;

    public ColoredPoint(Point point, Color newColor, int diameter) {
        super(point);
        this.color = newColor;
        this.diameter = diameter;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        if (diameter > 0) {
            this.diameter = diameter;
        }

    }


}
