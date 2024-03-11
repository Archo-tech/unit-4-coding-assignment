/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raymarching;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author 94450
 */
public class RaymarchingPanel extends JPanel
{
    private BufferedImage image;
    private SDF scene;
    private Camera camera;
    private Vector3 light;
    private double ambientLight;
    private Material backgroundMaterial;
    
    private static final double MIN_DISTANCE = 0.01;
    private static final double MAX_DISTANCE = 500.0;
    
    private final void generateScene()
    {
        camera = new Camera();
        SDF sphere = new SphereSDF(5, new Material(Color.RED,0.6));
        SDF plane = new PlaneSDF(new Vector3(0,1, 0), -2, new Material(Color.PINK, .6));
        SDF box = new BoxSDF(new Vector3(8,1,3), new Material(Color.YELLOW, 0.4));
        
        scene = new UnionSDF(sphere,plane);
        scene = new UnionSDF(scene,box);
        backgroundMaterial = new Material(Color.BLUE,0);
        light = (new Vector3(8, -5, 1)).getNormalized();
        ambientLight = 0.1;
        
    }
    public RaymarchingPanel()
    {
        image = new BufferedImage(1920,1080,BufferedImage.TYPE_INT_RGB);
        generateScene();
    }
    public void renderScene()
    {
        for(int x = 0; x < image.getWidth();x++)
        {
            for(int y = 0; y < image.getHeight();y++)
            {
                Vector3 ray = camera.getDirection();
                double xRatio = (x - image.getWidth()/2.0)/image.getWidth();
                ray = ray.add(camera.getRight().getScaled(xRatio * camera.getRatio()));
                double yRatio = -(y - image.getHeight()/2.0)/image.getHeight();
                ray = ray.add(camera.getUp().getScaled(yRatio));
                Vector3 point = raymarch(camera.getPosition(),ray);
                renderPixel(x,y,point);
            }
        }
        repaint();
    }
    private void renderPixel(int x, int y, Vector3 point)
    {
        Material material;
        if(point==null)
        {
            material = backgroundMaterial;
            image.setRGB(x,y,material.getColor().getRGB());
            return;
        }
        else
        {
            material = scene.getMaterial(point);
        }
        double lightValue = light.dot(estimateNormal(point).getScaled(-1));
        
        if(lightValue < 0)
        {
            lightValue = 0;
        }
        else
        {
            Vector3 direction = light.getScaled(-1);
            Vector3 sdfObj = raymarch(point.add(direction.getScaled(MIN_DISTANCE * 20)),direction);
            if(sdfObj != null)
            {
                lightValue=0;
            }
        }
        
        lightValue = Math.max(0, lightValue);
        double brightness = lightValue * material.getDiffusion()+ambientLight;
        brightness = Math.min(brightness,1.0);
        
        
        
        double hue = material.getHue();
        double saturation = material.getSaturation();
        
        Color color =  Color.getHSBColor((float)hue, (float)saturation, (float)brightness);
        
        image.setRGB(x,y,color.getRGB());
        
        
    }
    private Vector3 estimateNormal(Vector3 p)
    {
        double e = 0.01;
        Vector3 x1 = new Vector3(p.getX()+e,p.getY(),p.getZ());
        Vector3 x2 = new Vector3(p.getX()-e,p.getY(),p.getZ());
        double x = scene.getDistance(x1)-scene.getDistance(x2);
        Vector3 y1 = new Vector3(p.getX(),p.getY()+e,p.getZ());
        Vector3 y2 = new Vector3(p.getX(),p.getY()-e,p.getZ());
        double y = scene.getDistance(y1)-scene.getDistance(y2);
        Vector3 z1 = new Vector3(p.getX(),p.getY(),p.getZ()+e);
        Vector3 z2 = new Vector3(p.getX(),p.getY(),p.getZ()-e);
        double z = scene.getDistance(z1)-scene.getDistance(z2);
        return new Vector3(x,y,z).getNormalized();
    }
    
    //VERIFIED
    private Vector3 raymarch(Vector3 start, Vector3 direction)
    {
        boolean marching = true;
        double totalDistance = 0;
        direction = direction.getNormalized();
        while(marching)
        {
            Vector3 point = start.add(direction.getScaled(totalDistance));
            double distance = scene.getDistance(point);
            if(distance < MIN_DISTANCE)
            {
                return point;
            }
            totalDistance += distance;
            if(totalDistance > MAX_DISTANCE)
            {
                return null;
            }
        }
        return null;
    }
   
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(), null);
    }
}
