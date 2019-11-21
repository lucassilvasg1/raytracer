package raytracer;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Program {

    private boolean alive;

    public static void main(String[] args) {
        new Program().run();
    }

    private void run() {
        java.awt.EventQueue.invokeLater(() -> {
            Viewer viewer = new Viewer(600, 400);
            Scene scene = new Scene();
            Controller controller = new Controller(viewer, scene);
            Thread tick = new Thread(() -> {
                alive = true;
                while (alive) {
                    controller.step();
                    alive = false;
                }
            });
            
            viewer.addMouseListener(new MouseListener()
            {

               @Override
               public void mouseReleased(MouseEvent e)
               {
               }

               @Override
               public void mousePressed(MouseEvent e)
               {
               }

               @Override
               public void mouseExited(MouseEvent e)
               {
               }

               @Override
               public void mouseEntered(MouseEvent e)
               {
               }

               @Override
               public void mouseClicked(MouseEvent e)
               {
                  Ray ray = new Ray();
                  ray.direction.set(e.getX(), e.getY(), controller.getCamera().fov);
                  Geometry geometry = scene.intersect(ray, new Ray());
                  
                  System.out.println("x:" + e.getX() + " Y:" + e.getY());
                  
                  if(geometry == null)
                  {
                     System.out.println("BACKGROUND");
                  }
                  else
                  {
                     System.out.println(geometry.getClass().getCanonicalName());
                  }
                  
               }
            });

            
            JFrame frame = new JFrame();
            frame.setResizable(false);
            frame.setTitle("Ray Tracer");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().add(controller.getView());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    alive = false;
                    super.windowClosing(e);
                }
            });
            tick.start();
        });
    }
}
