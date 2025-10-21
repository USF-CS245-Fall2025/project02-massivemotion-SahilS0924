import java.awt.*;

public class CelestialBody {
    public double x, y;
    public double vx, vy;
    public int size;
    public Color color;

    
    public CelestialBody(double x, double y, double vx, double vy, int size, Color color) {//Constructor for the Celestial Body
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.size = size;
        this.color = color;
    }

    public void move() {//Changes position based on velocity
        x += vx;
        y += vy;
    }

    public void draw(Graphics g) {//Draws the celestial body
        g.setColor(color);
        g.fillOval((int) x, (int) y, size, size);
    }

    public boolean isOffScreen(int width, int height) {
        return (x + size < 0 || y + size < 0 || x > width || y > height);
    }
}
