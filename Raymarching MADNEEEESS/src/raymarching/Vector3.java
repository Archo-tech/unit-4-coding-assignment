/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raymarching;

/**
 *
 * @author 94450
 */
public class Vector3 
{
    private double x;
    private double y;
    private double z;
    
    public Vector3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }
    
    
    public Vector3 add(Vector3 other)
    {
        return new Vector3(other.x+this.x,other.y+this.y,other.z+this.z);
    }
    public Vector3 subtract(Vector3 other)
    {
        return new Vector3(-other.x+this.x,-other.y+this.y,-other.z+this.z);
    }
    public double getMagnitude()
    {
        return Math.sqrt(x*x+y*y+z*z);
    }
    public Vector3 getNormalized()
    {
        double mag = getMagnitude();
        if(mag==0)
        {
            return new Vector3(0,0,0);
        }
        return new Vector3(x/mag,y/mag,z/mag);
    }
    
    
    public Vector3 getScaled(double s)
    {
        return new Vector3(x * s, y * s, z * s);
    }
    public void scale(double s)
    {
        x*=s;
        y*=s;
        z*=s;
    }
    public double dot(Vector3 other)
    {
        return this.x*other.x + this.y * other.y + this.z * other.z;
    }
    public Vector3 cross(Vector3 other)
    {
        double x = this.y * other.z - other.y * this.z;
        double y = this.z * other.x - other.z * this.x;
        double z = this.x * other.y - other.x * this.y;
        return new Vector3(x,y,z);
    }
    
    
    
    
    
   @Override
   public String toString()
   {
       return "< "+x+", "+y+", "+z+" >";
   }
    
}
