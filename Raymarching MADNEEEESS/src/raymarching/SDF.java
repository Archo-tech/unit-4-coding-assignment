/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raymarching;

/**
 *
 * @author 94450
 */
public abstract class SDF 
{
    public abstract double getDistance(Vector3 point);
    public abstract Material getMaterial(Vector3 point);
    
}
