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
    private Material backgroundMaterial = new Material(Color.BLUE);
    
    private static final double MIN_DISTANCE = 0.01;
    private static final double MAX_DISTANCE = 500;
    
    public RaymarchingPanel()
    {
        image = new BufferedImage(1920,1080,BufferedImage.TYPE_INT_RGB);
        camera = new Camera();
        scene = new SphereSDF(5,new Material(Color.RED));
    }
    void generateScene()
    {
        SphereSDF sphere = new SphereSDF(-10,new Material(Color.RED));
    }
    void renderScene()
    {
        for(int x = 0; x < image.getWidth();x++)
        {
            for(int y = 0; y < image.getHeight();y++)
            {
                Vector3 ray = camera.getPosition().add(camera.getDirection());
                double xRatio = (x - image.getWidth()/2.0)/image.getWidth();
                double yRatio = -(y - image.getHeight()/2.0)/image.getHeight();
                
                ray = ray.add(camera.getRight().getScaled(xRatio));
                ray = ray.add(camera.getRight().getScaled(yRatio));
                Material material = raymarch(camera.getPosition(),ray);
                image.setRGB(x,y,material.getColor().getRGB());
            }
        }
        repaint();
    }
   
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(), null);
    }
    private Material raymarch(Vector3 start, Vector3 direction)
    {
        boolean marching = true;
        double totalDistance = 0.0;
        direction = direction.getNormalized();
        while(marching)
        {
            Vector3 point = start.add(direction.getScaled(totalDistance));
            double distance = scene.getDistance(point);
            if(scene.getDistance(point)<MIN_DISTANCE)
            {
                return scene.getMaterial(point);
            }
            totalDistance += distance;
            if(totalDistance>MAX_DISTANCE)
            {
                return backgroundMaterial;
            }
        }
        return null;
    }
}
