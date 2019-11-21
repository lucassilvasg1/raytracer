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
      camera.rotate(0.6, 0);
      tracer.render(camera, scene);
      viewer.setRGB(pixels);
     
   }

   public Camera getCamera()
   {
      return camera;
   }
}
