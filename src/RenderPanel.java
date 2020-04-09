import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 700);

        g.setColor(Color.white);
        g.fillRect(30, Pong.y1, 1 * Pong.SCALE, Pong.paddleLength * Pong.SCALE);
        g.fillRect(770, Pong.y2, 1 * Pong.SCALE, Pong.paddleLength * Pong.SCALE);
        g.fillOval(Pong.ballx + (1 * Pong.SCALE/2), Pong.bally - (1 * Pong.SCALE/2), 1 * Pong.SCALE, 1 * Pong.SCALE);

        String string = "Player 1: " + Pong.p1Score + ", Player 2: " + Pong.p2Score;
        g.setColor(Color.white);
        g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 15);
    }
}