/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raymarching;

/**
 *
 * @author 94450
 */
public class Camera 
{
    private Vector3 position;
    private Vector3 direction;
    private Vector3 up;
    
    private final double RATIO = 1.6;
    public Camera()
    {
        position = new Vector3(0,0,-20);
        up = new Vector3(0,1,0);
        direction = new Vector3(0,0,1);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getDirection() {
        return direction;
    }

    public Vector3 getUp() {
        return up;
    }

    public double getRATIO() {
        return RATIO;
    }
    public Vector3 getRight()
    {
        return direction.cross(up);
        
    }
    public void translate(Vector3 displacement)
    {
        position = position.add(displacement);
    }
    
}
