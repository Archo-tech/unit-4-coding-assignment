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
    public Material blend(Material other, double ratio)
    {
        double hue;
        if(this.getHue() > other.getHue())
        {
            if(this.getHue() - other.getHue() < other.getHue() + 1 - this.getHue())
            {
                hue = this.getHue() - (this.getHue() - other.getHue()) * ratio;
                
                
            }
            else
            {
                hue = this.getHue() + (other.getHue() + 1 - this.getHue()) * ratio;
                if(hue > 1) hue-=1;
            }
        }
        else
        {
            if(other.getHue() - this.getHue() < this.getHue() + 1 - other.getHue())
            {
                hue = this.getHue() + (other.getHue() - this.getHue())*ratio;
            }
            else
            {
                hue = this.getHue() + 1 - (other.getHue() - this.getHue())*ratio;
                if(hue > 1) hue-=1;
            }
        }
        double saturation = this.getSaturation() + (other.getSaturation()-this.getSaturation())*ratio;
        double diffusion = this.diffusion + (other.getDiffusion()-this.getDiffusion())*ratio;
        Color color = Color.getHSBColor((float)hue, (float)saturation, .5f);
        return new Material(color, diffusion);
    }
}
