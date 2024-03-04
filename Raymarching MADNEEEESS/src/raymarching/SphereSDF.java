/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raymarching;

/**
 *
 * @author 94450
 */
public class SphereSDF extends SDF
{
    private double radius;
    private Material material;
    public SphereSDF(double radius, Material material)
    {
        this.radius = radius;
        this.material = material;
    }
    @Override
    public double getDistance(Vector3 point)
    {
        return point.getMagnitude()-radius;
    }
    @Override
    public Material getMaterial(Vector3 point)
    {
        return material;
    }
}
