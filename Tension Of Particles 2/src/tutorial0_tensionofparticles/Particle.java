package tutorial0_tensionofparticles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

public class Particle {

    public int x;
    public int y;
    public double energy = 0;
    public int radius = 40;
    public int diameter = 2 * radius;
    private Point offset;

    public Particle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        canvas.drawOval(x - radius, y - radius, diameter, diameter);
        canvas.setColor(ColorUtils.interpolateColor(energy));
        canvas.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        canvas.drawString(String.format("%.1f", energy), x, y);
    }

    public boolean isMouseInside(Point mouse) {
        int dx = x - mouse.x;
        int dy = y - mouse.y;
        double length = Math.hypot(dx, dy);
        return radius > length;
    }

    public void setOffset(Point mouse) {
        int dx = x - mouse.x;
        int dy = y - mouse.y;
        offset = new Point(dx, dy);
    }

    public void drag(Point current) {
        x = current.x + offset.x;
        y = current.y + offset.y;
    }

}
