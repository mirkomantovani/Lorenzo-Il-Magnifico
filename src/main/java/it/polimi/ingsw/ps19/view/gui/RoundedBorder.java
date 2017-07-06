package it.polimi.ingsw.ps19.view.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

/**
 * The Class RoundedBorder.
 */
public class RoundedBorder implements Border {

    /** The radius. */
    private int radius;


    /**
     * Instantiates a new rounded border.
     *
     * @param radius the radius
     */
    RoundedBorder(int radius) {
        this.radius = radius;
    }


    /* (non-Javadoc)
     * @see javax.swing.border.Border#getBorderInsets(java.awt.Component)
     */
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    /* (non-Javadoc)
     * @see javax.swing.border.Border#isBorderOpaque()
     */
    public boolean isBorderOpaque() {
        return true;
    }


    /* (non-Javadoc)
     * @see javax.swing.border.Border#paintBorder(java.awt.Component, java.awt.Graphics, int, int, int, int)
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}