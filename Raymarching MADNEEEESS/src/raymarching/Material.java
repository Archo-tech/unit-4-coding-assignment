/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raymarching;

import java.awt.Color;

/**
 *
 * @author 94450
 */
public class Material 
{
    private Color color; 
    private double diffusion;
    public Material(Color color, double dif)
    {
        this.color=color;
        this.diffusion = dif;
    }
    public Color getColor()
    {
        return color;
    }
    public double getDiffusion()
    {
        return diffusion;
    }
    public double getHue()
    {
        float[] hsb = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(),hsb);
        return hsb[0];
    }
    public double getSaturation()
    {
        float[] hsb = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(),hsb);
        return hsb[1];
    }
}
