package raytracer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller
{

   private final Viewer viewer;

   private final Scene scene;

   private final Camera camera;

   private final Tracer tracer;

   private final int[] pixels;

   private double angle;

   public Controller(Viewer v, Scene s)
   {
      viewer = v;
      scene = s;
      camera = new Camera(300.0, viewer.getHeight());
      pixels = new int[viewer.getWidth() * viewer.getHeight()];
      tracer = new Tracer(pixels, viewer.getWidth(), viewer.getHeight());
   }

   public Viewer getView()
   {
      return viewer;
   }

   public void step()
   {
      camera.rotate(0.6, angle );
      tracer.render(camera, scene);
      viewer.setRGB(pixels);
      
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
            
            int x = (e.getX() - 600);
            int y = (e.getY() - 400) *(-1);
            
            int yStart = viewer.getHeight() >> 1;
            
            // WX: -211
            // YSTART E FINAL: 119
            
            Ray hitRay = new Ray();

            camera.transform(ray.origin.set(0.0, 0.0, 1.0)).mul(-camera.distance);
            camera.transform(ray.direction.set(x, yStart - y, camera.fov)).normalize();

            ray.direction.set(x, yStart - y, camera.fov);
            
            Geometry geometry = scene.intersect(ray, hitRay);
            
            System.out.println("x:" + x + " Y:" + y);
            
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
     
   }

   public Camera getCamera()
   {
      return camera;
   }
}
