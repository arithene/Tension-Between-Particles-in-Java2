package tutorial0_tensionofparticles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

public class Tension {
    private Particle a;
    private Particle b;
    private double initialLength;

    public Tension(Particle a, Particle b) {
        this.a = a;
        this.b = b;
        this.initialLength = length();
    }

    public Point setForce(double multiplier) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        Point force = new Point((int) (dx * tension() * multiplier), (int) (dy * tension() * multiplier));
        a.x += force.x;
        a.y += force.y;
        b.x -= force.x;
        b.y -= force.y;
        return force;
    }

    public Point setNForce(double multiplier) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        double distance = Math.hypot(dx, dy);
        Point force = new Point((int) ((dx / distance) * tension() * multiplier),
                (int) ((dy / distance) * tension() * multiplier));
        a.x += force.x;
        a.y += force.y;
        b.x -= force.x;
        b.y -= force.y;
        return force;
    }

    public double length() {
        int dx = a.x - b.x;
        int dy = a.y - b.y;
        return Math.hypot(dx, dy);
    }

    public double tension() {
        double currentLength = length();
        double difference = (initialLength - currentLength);
        double tension = difference / currentLength;
        a.energy = Math.max(-1, Math.min(a.energy, 1)) + tension;
        b.energy = Math.max(-1, Math.min(b.energy, 1)) + tension;
        return tension;
    }

    public void draw(Graphics2D canvas) {
        double tension = tension();
        canvas.setColor(ColorUtils.interpolateColor(tension));
        canvas.drawLine(a.x, a.y, b.x, b.y);
        // i don't recommend compute in draw methods.
        int x = a.x + b.x;
        int y = a.y + b.y;
        canvas.setColor(Color.DARK_GRAY);
        canvas.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        canvas.drawString(String.format("%.3f", tension), x / 2, y / 2);
    }

}
