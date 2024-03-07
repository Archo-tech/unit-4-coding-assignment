/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raymarching;

/**
 *
 * @author 94450
 */
public class BoxSDF extends SDF{
    Vector3 size;
    private Material material;
    
    public BoxSDF(Vector3 size, Material material)
    {
        this.size = size;
        this.material = material;
    }
    @Override
    public double getDistance(Vector3 point)
    {
        Vector3 diff = point.getAbs().subtract(size);
        Vector3 v = new Vector3(Math.max(diff.getX(), 0),Math.max(diff.getY(),0), Math.max(diff.getZ(),0));
        return v.getMagnitude() + Math.min(Math.max(diff.getX(), Math.max(diff.getY(), diff.getZ())), 0);
    }
    
    @Override
    public Material getMaterial(Vector3 point)
    {
        return material;
    }
    
}
