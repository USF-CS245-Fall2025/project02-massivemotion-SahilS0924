import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import javax.swing.*;
import java.util.ArrayList;

public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;
    private Properties props;

    // TODO: Consider removing the next two lines (coordinates for two balls)
    // protected int x1, y1;
    // protected int x2, y2;
    private int timerDelay;
    private int windowSizeX, windowSizeY;
    private double genX, genY;
    private int bodySize, bodyVelocity;
    private int starX, starY, starSize, starVx, starVy;
    private final CelestialBody star;  
    private final Random rand = new Random();
    private final ArrayList<CelestialBody> comets = new ArrayList<>();
    // public MassiveMotion(String propfile) {

        // TODO: insert your code to read from configuration file here.
public MassiveMotion(String configFile) {//Constructor
        loadProperties(configFile);//Fixed: Load properties from config file based on command line input
        tm = new Timer(timerDelay, this);
        // Create the big star
        star = new CelestialBody(starX, starY, starVx, starVy, starSize, Color.YELLOW);
    }

    private void loadProperties(String filename) {//Loads the properties from config file
        props = new Properties();
        try (InputStream input = new FileInputStream(filename)) {
            props.load(input);
        } catch (IOException e) {
            System.out.println("Config fail: "+filename);
        }
//Read properties and convert to appropriate types, fixed:gave defauklt values in case the og file is missing them
        timerDelay = Integer.parseInt(props.getProperty("timer_delay", "75"));
        windowSizeX = Integer.parseInt(props.getProperty("window_size_x","1024"));
        windowSizeY = Integer.parseInt(props.getProperty("window_size_y","768"));

        genX = Double.parseDouble(props.getProperty("gen_x","0.06"));
        genY = Double.parseDouble(props.getProperty("gen_y","0.06"));
        bodySize = Integer.parseInt(props.getProperty("body_size","10"));
        bodyVelocity = Integer.parseInt(props.getProperty("body_velocity","3"));

        starX = Integer.parseInt(props.getProperty("star_position_x","512"));
        starY = Integer.parseInt(props.getProperty("star_position_y","384"));
        starSize = Integer.parseInt(props.getProperty("star_size","30"));
        starVx = Integer.parseInt(props.getProperty("star_velocity_x","0"));
        starVy = Integer.parseInt(props.getProperty("star_velocity_y","0"));

    }

       // tm = new Timer(75, this);  TODO: Replace the first argument with delay with value from config file.


        // TODO: Consider removing the next two lines (coordinates) for random starting locations.
        // x1 = 100; y1 = 50;
        // x2 = 200; y2 = 400;
    
    @Override
    public void paintComponent(Graphics g) {//Paints the components
        super.paintComponent(g); // Probably best you leave this as is.

        // TODO: Paint each ball. Here's how to paint two balls, one after the other:
        // g.setColor(Color.BLUE);
        // g.fillOval(x1, y1, 20, 20);

        // g.setColor(Color.RED);
        // g.fillOval(x2, y2, 20, 20);
        star.draw(g);
        for (CelestialBody c : comets) {
            c.draw(g);
        }
        // Recommend you leave the next line as is
        tm.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {//Main Logic, Loop that runs every timer tick in a correct manner
        // TODO: Change the location of each ball. Here's an example of them moving across the screen:
        //       ... but to be clear, you should change this.
        // x1 += 10;
        // x2 -= 15;
        // These two "if" statements keep the balls on the screen in case they go off one side.
        // if (x1 > 640)
        //     x1 = 0;
        // if (x2 < 0)
        //     x2 = 640;
   star.move();

        // Move all comets
        for (CelestialBody comet : comets) {
            comet.move();
        }

        // Remove comets that are off-screen
        for (int i = comets.size() - 1; i >= 0; i--) {
            CelestialBody c = comets.get(i);
            if (c.x < 0 || c.x > windowSizeX || c.y < 0 || c.y > windowSizeY) {
                comets.remove(i);
            }
        }

        //Random Comet Generation on X axis
        if (Math.random() < genX) {//Randomly generate comets on X axis
            double y = (Math.random() < 0.5) ? 0 : windowSizeY;//50/50 chance to spawn on top or bottom
            double x = Math.random() * windowSizeX;//Random position along X axis
            double vx = 0, vy = 0;//Sets Initial velocities
            while (vx == 0) vx = rand.nextDouble() * 2 * bodyVelocity - bodyVelocity;//Random non0 X velocity
            while (vy == 0) vy = rand.nextDouble() * 2 * bodyVelocity - bodyVelocity;//Random non0 Y velocity
            comets.add(new CelestialBody(x, y, vx, vy, bodySize, Color.RED));//Adds this new comet to list
        }

        //Random Comet Generation on Y axis
        if (Math.random() < genY) {//Randomly generate comets on Y axis
            double x = (Math.random() < 0.5) ? 0 : windowSizeX; //50/50 chance to spawn left or right
            double y = Math.random() * windowSizeY;//Random position along Y axis
            double vx = 0, vy = 0;//Sets Initial velocities
            while (vx == 0) vx = rand.nextDouble() * 2 * bodyVelocity - bodyVelocity;//Random non0 X velocity
            while (vy == 0) vy = rand.nextDouble() * 2 * bodyVelocity - bodyVelocity;//Random non0 Y velocity
            comets.add(new CelestialBody(x, y, vx, vy, bodySize, Color.RED));//Adds this new comet to list
        }



        // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");
         MassiveMotion mm = new MassiveMotion(args[0]);//Fixed: takes in command line arg
        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.windowSizeX, mm.windowSizeY); // TODO: Replace with the size from configuration!
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
