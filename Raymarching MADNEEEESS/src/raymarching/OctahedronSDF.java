/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raymarching;

/**
 *
 * @author 94450
 */
public class OctahedronSDF extends SDF
{
    private float s;
    private Material material;
    
    public OctahedronSDF(Vector3 p, float s, Material material)
    {
       this.material = material;
       this.s = s;
    }
    
    
    @Override
    public double getDistance(Vector3 p)
    {
       p = p.getAbs();
       float m = (float)(p.getX()+p.getY()+p.getZ()-s);
  
       Vector3 q;
       if( 3.0*p.getX() < m ) q = new Vector3(p.getX(),p.getY(), p.getZ());
       else if( 3.0*p.getY() < m ) q = new Vector3(p.getY(), p.getZ(),p.getX());
       else if( 3.0*p.getZ() < m ) q =new Vector3(p.getZ(),p.getX(),p.getY());
       else return m*0.57735027;
    
       
       double k = Math.max(0.0, Math.min(s, 0.5*(q.getZ()-q.getY()+s)));
       return new Vector3(q.getX(),q.getY()-s+k,q.getZ()-k).getMagnitude();
    }
    
    @Override
    public Material getMaterial(Vector3 point)
    {
        return material;
    }
    
    
}
