/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raymarching;

/**
 *
 * @author 94450
 */
public class SmoothUnionSDF extends SDF
{
    private SDF a;
    private SDF b;
    private double k;
    public SmoothUnionSDF(SDF a, SDF b, double k)
    {
        this.a = a;
        this.b = b;
        this.k = k;
    }
    @Override
    public double getDistance(Vector3 point)
    {
        double dA = a.getDistance(point);
        double dB = b.getDistance(point);
        double h = .5 + .5 * (dA - dB)/k;
        h = Math.min(Math.max(h, 0), 1);
        double m = dA + (dB - dA) * h;
        return m - k * h * (1.0 - h);
    }
    @Override
    public Material getMaterial(Vector3 point)
    {
        double dA = a.getDistance(point);
        double dB = b.getDistance(point);
        double h = .5 + .5 * (dA - dB)/k;
        h = Math.min(Math.max(h, 0), 1);
        return a.getMaterial(point).blend(b.getMaterial(point), h);
    }
}
