/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raymarching;

/**
 *
 * @author 94450
 */
public class PlaneSDF extends SDF{
    private Vector3 normal;
    private double height;
    
    private Material material;
    public PlaneSDF(Vector3 normal, double height, Material material)
    {
        this.normal = normal.getNormalized();
        this.height = height;
        this.material = material;
    }
    @Override
    public double getDistance(Vector3 point)
    {
        return point.dot(normal)-height;
    }
    @Override
    public Material getMaterial(Vector3 point)
    {
        return material;
    }
}
