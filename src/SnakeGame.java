import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class SnakeGame implements ActionListener, KeyListener
{

    public static SnakeGame snakeGame; //an object that can be used everywhere in this package
    public JFrame frame;
    public Panel panel;
    public Timer clock = new Timer(20, this);//clock that will repaint the snakeGame every tick
    public ArrayList<Point> snakeBody = new ArrayList<Point>(); //body of the snakeGame
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 20, COLUMN = 30, ROW = 20;
    public int ticks = 0, direction = DOWN, score, tailLength = 3, time;
    public Point head, apple;
    public Random random;
    public boolean over = false, paused;
    public Dimension dim;

    public SnakeGame()
    {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("SnakeGame");
        frame.setVisible(true);
        frame.setSize(SCALE*COLUMN, SCALE*ROW);
        frame.setResizable(false);
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
        frame.add(panel = new Panel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        start();
    }

    public void start()
    {
        over = false;
        paused = false;
        time = 0;
        score = 0;
        ticks = 0;
        direction = DOWN;
        head = new Point(5, -1);
        random = new Random();
        snakeBody.clear();
        apple = new Point(random.nextInt(COLUMN-1), random.nextInt(ROW-1));//x and y coordinates of food will be random
        clock.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        panel.repaint();
        ticks++;

        if (ticks % 5 == 0 && head != null && !over && !paused)
        {
            time++;

            snakeBody.add(new Point(head.x, head.y));

            if (direction == UP)
            {
                if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1))
                {
                    head = new Point(head.x, head.y - 1);
                }
                else
                {
                    over = true;

                }
            }

            if (direction == DOWN)
            {
                if (head.y + 1 < ROW-1 && noTailAt(head.x, head.y + 1))
                {
                    head = new Point(head.x, head.y + 1);
                }
                else
                {
                    over = true;
                }
            }

            if (direction == LEFT)
            {
                if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y))
                {
                    head = new Point(head.x - 1, head.y);
                }
                else
                {
                    over = true;
                }
            }

            if (direction == RIGHT)
            {
                if (head.x + 1 < COLUMN-1 && noTailAt(head.x + 1, head.y))
                {
                    head = new Point(head.x + 1, head.y);
                }
                else
                {
                    over = true;
                }
            }

            if (snakeBody.size() > tailLength)
            {
                snakeBody.remove(0);

            }

            if (apple != null)
            {
                if (head.equals(apple))
                {
                    score += 10;
                    tailLength++;
                    apple.setLocation(random.nextInt(COLUMN-1), random.nextInt(ROW-1));
                }
            }
        }
    }

    public boolean noTailAt(int x, int y) //checks if the snake didn't eat its tail
    {
        for (Point point : snakeBody)
        {
            if (point.equals(new Point(x, y)))
            {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        snakeGame = new SnakeGame();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int i = e.getKeyCode();

        if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != RIGHT)
        {
            direction = LEFT;
        }

        if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != LEFT)
        {
            direction = RIGHT;
        }

        if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != DOWN)
        {
            direction = UP;
        }

        if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != UP)
        {
            direction = DOWN;
        }

        if (i == KeyEvent.VK_SPACE)
        {
            if (over)
            {
                start();
            }
            else
            {
                paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

}
