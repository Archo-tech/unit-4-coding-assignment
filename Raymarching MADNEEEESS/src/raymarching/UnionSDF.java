/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raymarching;

/**
 *
 * @author 94450
 */
public class UnionSDF extends SDF{
    private SDF a;
    private SDF b;
    public UnionSDF(SDF a, SDF b)
    {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public double getDistance(Vector3 point)
    {
        return Math.min(a.getDistance(point), b.getDistance(point));
    }
    @Override
    public Material getMaterial(Vector3 point)
    {
        if(a.getDistance(point) < b.getDistance(point))
        {
            return a.getMaterial(point);
        }
        return b.getMaterial(point);
    }
}
