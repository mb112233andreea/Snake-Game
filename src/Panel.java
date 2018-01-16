import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel
{
    private static BufferedImage apple=null;
    private static BufferedImage head=null;
    private static BufferedImage body=null;
    private static BufferedImage tail1=null;
    private static BufferedImage tail2=null;
    private static BufferedImage tail0=null;


    public static int curColor=0;

    public Panel(){
        super();
        try{
            apple= ImageIO.read(new File("C:\\Users\\Maria\\Desktop\\Snake_images\\apple.png"));
        }catch(Exception e){

        }
        try{
            head=ImageIO.read(new File("C:\\Users\\Maria\\Desktop\\Snake_images\\head.png"));
        }catch(Exception e){

        }
        try{
            body=ImageIO.read(new File("C:\\Users\\Maria\\Desktop\\Snake_images\\body.png"));
        }catch(Exception e){

        }
        try{
            tail2=ImageIO.read(new File("C:\\Users\\Maria\\Desktop\\Snake_images\\tail2.png"));
        }catch(Exception e){

        }
        try{
            tail1=ImageIO.read(new File("C:\\Users\\Maria\\Desktop\\Snake_images\\tail1.png"));
        }catch(Exception e){

        }
        try{
            tail0=ImageIO.read(new File("C:\\Users\\Maria\\Desktop\\Snake_images\\tail0.png"));
        }catch(Exception e){

        }


    }


    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        SnakeGame s = SnakeGame.snakeGame;

        g.setColor(new Color(curColor));
        curColor++;

        g.fillRect(0, 0, s.SCALE*s.COLUMN,s.SCALE* s.ROW);// fills a rectangle if the frame dimension with color



        int i=0;
        for (Point point : s.snakeBody)//paint the snake'S body
        {

            if (i==0){
                if (tail0 != null) {
                    g.drawImage(tail0, point.x * s.SCALE, point.y *s.SCALE, null);
                } else {
                    g.setColor(Color.RED);
                    g.fillRect(point.x * s.SCALE, point.y * s.SCALE, s.SCALE, s.SCALE);
                }

            }else if (i==1){
                if (tail1 != null) {
                    g.drawImage(tail1, point.x * s.SCALE, point.y *s.SCALE, null);
                } else {
                    g.setColor(Color.RED);
                    g.fillRect(point.x * s.SCALE, point.y * s.SCALE, s.SCALE, s.SCALE);
                }

            }else if(i==2){
                if (tail2 != null) {
                    g.drawImage(tail2, point.x * s.SCALE, point.y *s.SCALE, null);
                } else {
                    g.setColor(Color.RED);
                    g.fillRect(point.x * s.SCALE, point.y * s.SCALE, s.SCALE, s.SCALE);
                }

            } else {
                if (body != null) {
                    g.drawImage(body, point.x * s.SCALE, point.y *s.SCALE, null);
                } else {
                    g.setColor(Color.RED);
                    g.fillRect(point.x * s.SCALE, point.y * s.SCALE, s.SCALE, s.SCALE);
                }

            }


        }


        if (head != null) {
            g.drawImage(head, s.head.x * s.SCALE, s.head.y *s.SCALE, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(s.head.x * s.SCALE, s.head.y * s.SCALE, s.SCALE, s.SCALE);
        }

        if (apple!= null) {
            g.drawImage(apple, s.apple.x * s.SCALE, s.apple.y *s.SCALE, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(s.apple.x * s.SCALE, s.apple.y * s.SCALE, s.SCALE, s.SCALE);
        }


        String string =  "    LENGHT: " + s.tailLength + "    TIME: " + s.time / 20;
        String string1;
        g.setColor(Color.PINK);

        g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);

        string = "GAME OVER !";
        string1="SCORE:"+s.score;


        if (s.over)
        {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Score"));
                writer.write(" Highest Score " + s.score);
                writer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }



            g.drawString(string1, (int) (getWidth() / 2 - string1.length() * 2.5f), (int) s.dim.getHeight() / 3);
            g.drawString(string, (int) (getWidth() / 2 - string.length()*2.5f ), (int) s.dim.getHeight() / 4);
        }

        string = "PAUSED!";

        if (s.paused && !s.over)
        {
            g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) s.dim.getHeight() / 4);
        }
    }
}
