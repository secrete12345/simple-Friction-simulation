import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Friction{
    static final double g = 9.8;
    static final double u_of_floor = 0.2;
    public static class Box{
        double x , y;
        double vec , mass;
        double friction;
        int width, height;
        Box(int a , int b , double c , double d , int w , int h){
            x = a;
            y = b;
            vec = c;
            mass = d;
            friction = mass * g * u_of_floor;
            width = w;
            height = h;
        }
    }
    public static void main(String[] args){
        //init jframe
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //start physic
        int delay = 16;
        double dt = delay / 1000.0;
        double init_force = 10000;
        Box box = new Box(0,400,50,100,25,25);
        JPanel drawBox = new JPanel();
        drawBox.setBackground(Color.BLACK);
        drawBox.setBounds((int)(box.x), (int)box.y, box.width, box.height);
        double total_force = Math.max(init_force - box.friction , 0);
        double acceleration = total_force / box.mass;
        box.vec += acceleration * dt;
        box.vec = Math.max(box.vec , 0);
        double friction_acceleration = -(box.friction / box.mass);
        frame.add(drawBox);
        Timer timer = new Timer(delay , new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                box.vec += friction_acceleration * dt;
                box.vec = Math.max(box.vec , 0);
                box.x += box.vec*dt;
                drawBox.setBounds((int)(box.x), (int)box.y, box.width, box.height);
            }
        });
        timer.start();
    }
}