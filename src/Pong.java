import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Pong implements ActionListener, KeyListener {

    public static Pong pong;
    public JFrame jframe;
    public RenderPanel renderPanel;
    public Timer timer = new Timer(20, this);

    public static final int PADDLESPEED = 10, SCALE = 10;
    public static int paddleLength = 10, x, y1, y2, ballx, bally, yVel = 3, xVel = -3, p1Score = 0, p2Score = 0;
    public boolean over = false, paused, isWPressed = false, isSPressed = false, isUpPressed = false, isDownPressed = false;

    public Pong() {
        jframe = new JFrame("Pong");
        jframe.setVisible(true);
        jframe.setSize(800, 700); // 800,700
        jframe.setResizable(false); // cant resize
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        startGame();
    }

    public void startGame() {
        over = false;
        paused = false;
        //paddleLength = 10;
        x = jframe.getWidth() / 2;
        y1 = jframe.getHeight() / 2;
        y2 = jframe.getHeight() / 2;
        ballx = jframe.getWidth() / 2;
        bally = jframe.getHeight() / 2;
        timer.start();
    }

    public void buttonHandler() {
        if (isWPressed && y1 != 0) {
            y1 -= PADDLESPEED;
        }
        if (isSPressed && y1 != jframe.getHeight() - paddleLength * SCALE - 30) {
            y1 += PADDLESPEED;
        }
        if (isUpPressed && y2 != 0) {
            y2 -= PADDLESPEED;
        }
        if (isDownPressed && y2 != jframe.getHeight() - paddleLength * SCALE - 30) {
            y2 += PADDLESPEED;
        }
    }

    public void ballMovement() {
        // collision with outside walls
        if (ballx <= -10) {
            p2Score++;
            over = true;
        }
        if (ballx >= jframe.getWidth()) {
            p1Score++;
            over = true;
        }
        // collision with paddles
        if ((ballx < 35 && (bally <= y1 + paddleLength * SCALE && bally >= y1)) ||
                (ballx > 755 && (bally <= y2 + paddleLength * SCALE && bally >= y2))) {
            xVel = -xVel;
        }
        // collision with top and bottom
        if (bally <= 5 || bally >= jframe.getHeight() - 30) {
            yVel = -yVel;
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (!over) {
            ballx += xVel;
            bally += yVel;
            buttonHandler();
            ballMovement();
            renderPanel.repaint();
            //ticks++;
        } else {
            startGame();
        }
    }

    public static void main(String[] args) {
        pong  = new Pong();
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) { // moves paddle and pauses
        int i = e.getKeyCode();

        // player 1
        if (i == KeyEvent.VK_W) {
            isWPressed = true;
        }
        if (i == KeyEvent.VK_S) {
            isSPressed = true;
        }

        // player 2
        int j = e.getKeyCode();
        if (j == KeyEvent.VK_UP) {
            isUpPressed = true;
        }
        if (j == KeyEvent.VK_DOWN) {
            isDownPressed = true;
        }

        // pause
        if (i == KeyEvent.VK_SPACE) {
            if (over) {
                startGame();
            } else {
                paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // stops paddle when key is released
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_W) {
            isWPressed = false;
        }
        if (i == KeyEvent.VK_S) {
            isSPressed = false;
        }
        if (i == KeyEvent.VK_UP) {
            isUpPressed = false;
        }
        if (i == KeyEvent.VK_DOWN) {
            isDownPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}