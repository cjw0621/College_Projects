import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

// Chase Whitney

public class StarsAndStripes {
    public static void drawFlag(int stars, int stripes, Graphics g, int x, int y, int width, int height) {
        // Fill this in according to the assignment!
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);

        int redHeight = (height / stripes);
        int stripeY = y;

        int stripesToDraw = (int) Math.ceil(stripes / 2.0 + 1);

        for (int i = 0; i < stripesToDraw; i++) {
            g.setColor(Color.red);

            if (i == stripesToDraw - 1 && stripes % 2 != 0) {
                if (stripeY + redHeight < height + y) {
                    int redHeight1 = redHeight + (height + y) - (stripeY + redHeight);
                    g.fillRect(x, stripeY, width, redHeight1);
                }
            } else {
                g.fillRect(x, stripeY, width, redHeight);
            }
            stripeY = stripeY + 2 * redHeight;
        }

        int blueHeight = (int) Math.floor(redHeight * stripesToDraw);
        int blueWidth = (int) Math.floor(blueHeight * (width / height));

        g.setColor(Color.blue);
        g.fillRect(x, y, blueWidth, blueHeight);

        int columns = 0;
        int rows = 0;

        for(int i =1; i < stars; i++){
            for(int j = i + 1; j < stars; j++){
                if(stars % (i * j) == 0){
                    rows = i;
                    columns = j;
                }
            }
        }

        int starFieldSize = Math.min(blueHeight / rows, blueWidth / columns);

        x = x + (blueWidth - starFieldSize * columns) / 2;
        y = y + (blueHeight - starFieldSize * rows) / 2;

        for(int i= 0;i < rows; i++){
            for(int j = 0; j < columns; j++){
                drawStar(g, x+j*starFieldSize,y+i*starFieldSize, starFieldSize);
            }
        }

    }

    public static void drawStar(Graphics g, int x, int y, int size) {
        // Fill this in according to the assignment!

        g.setColor(Color.white);
        g.drawLine(x + (size / 2), y, x + (size / 5), y + size);
        g.drawLine(x + (size / 2), y, x+(size * 4 / 5), y + size);
        g.drawLine(x, y + (size * 2 / 5), x + size, y + (size * 2 / 5));
        g.drawLine(x, y + (size * 2 / 5), x + (size * 4 / 5), y + size);
        g.drawLine(x + (size / 5), y + size, x + size, y + (size * 2 / 5));
    }

    // Only alter the "drawFlag" part of the paintComponent
    // code to call it in different ways. You can also test
    // drawing multiple flags at once!
    public static void main(String[] args) {
        JFrame window = new JFrame("Graphics window");
        window.setLocationByPlatform(true);
        final JLabel coords = new JLabel(" ");
        @SuppressWarnings("serial")
        final JPanel panel = new JPanel() {

            protected void paintComponent(Graphics gx) {
                coords.setText(" ");
                Graphics2D g = (Graphics2D) gx;
                int width = getWidth();
                int height = getHeight();
                g.setBackground(Color.CYAN); // To make sure you cover the base rectangle!
                g.clearRect(0, 0, width, height);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(Color.BLACK);

                // You could alter this code to try different flags!
                drawFlag(48, 16, g, width/2, height/2, width/2, height/2);
                drawFlag(24, 15, g, 0, height/2, width/2, height/2);
                drawFlag(20, 14, g, width/2, 0, width/2, height/2);
                drawFlag(15, 13, g, 0, 0, width/2, height/2);
            }
        };
        panel.addMouseMotionListener(new MouseMotionListener() {


            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                coords.setText(e.getX()+", "+e.getY());
            }

        });
        window.setLayout(new BorderLayout());
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        window.setSize(d.width / 2, d.height / 2);

        JPanel coordPanel = new JPanel();
        coordPanel.setLayout(new BorderLayout());
        coordPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        window.add(coordPanel, BorderLayout.SOUTH);
        coordPanel.add(coords, BorderLayout.WEST);

        window.setBackground(Color.WHITE); // To make sure you cover the base rectangle!
        panel.setBackground(Color.BLACK);
        window.add(panel, BorderLayout.CENTER);
        //window.setContentPane(panel);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
