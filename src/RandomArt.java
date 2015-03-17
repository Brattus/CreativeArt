/**
 * Created by Ramin on 16.03.2015.
 */

import processing.core.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RandomArt extends PApplet implements ActionListener
{
    PGraphics pg1;
    PGraphics pg2;
    PImage img = null;

    long time = 0;

    int st;

    public void setup()
    {
        size( 1000, 680 );
        background( 255 );
        noStroke();
        img = loadImage( "splash.jpg" );
        imageMode( CENTER );
        image( img, width / 2, height / 2, 500, 500 );
    }



    @Override
    public void draw()
    {
        time = millis()/1000;

        if(time >= 3 && time <= 4)
        {
            clearCanvas();
        }
    }


    /**
     * Creates random triangles
     */
    public void createTriangles()
    {
        println( "Triangle clicked at: " + millis() / 1000 + " s" );
        for(st = 0; st < 50; st++)
        {
            fill( random( 255 ), random( 255 ), random( 255 ), 200 );
            triangle( random( 0, width ), random( 0, height ), random( 0, (float) ( width / 1.7 ) ), random( 0, height ), (float) ( width / 2 ), height / 2 );
        }
    }

    /**
     * Creates random squares
     */
    public void createSquares()
    {
        println( "Square clicked at: " + millis() );
        for(st = 0; st < 50; st++)
        {
            noStroke();
            fill( random( 255 ), random( 255 ), random( 255 ), 170 );
            rect( random( 0, width ), random( 0, height ), 60, 60 );
        }
    }

    /**
     * Creates random circles
     */
    public void createCircles()
    {
        println( "Circle clicked at: " + millis() );
        for(st = 0; st < 50; st++)
        {
            noStroke();
            fill( random( 255 ), random( 255 ), random( 255 ), 170 );
            ellipse( random( 0, width ), random( 0, height ), 60, 60 );
        }
    }

    /**
     * Blur filter on canvas
     */
    public void blurFilter()
    {
        filter(BLUR, 5);
    }

    /**
     * Threshold filter on canvas
     */
    public void thresholdFilter()
    {
        filter( THRESHOLD );
    }

    /**
     *  Gray scale filter on canvas
     */
    public void grayScaleFilter()
    {
        filter( GRAY );
    }



    public void clearCanvas()
    {
        clear();
        background( 255 );
    }

    private void changeColor()
    {
        Color c = JColorChooser.showDialog( null, "Choose a Color", Color.BLACK );
        background( c.getRGB() );
    }

    /**
     * Save the image to a file
     */
    private void saveToFile()
    {
        PImage partialSave = null;

        FileNameExtensionFilter filter = new FileNameExtensionFilter( "Only JPG, GIF & png Images", "jpg", "gif", "png", "tif" );

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter( filter );
        chooser.setDialogTitle( "Save file" );

        String[] extensionTypes = filter.getExtensions();

        int returnVal = chooser.showSaveDialog( this );
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            for(String s : extensionTypes)
            {
                switch(s)
                {
                    case "tif":
                        JOptionPane.showMessageDialog( this, "Not an valid extension\n" + "Use .jpg, .png or .GIF" );
                        return;
                    default:
                        partialSave = get( 0, 0, width, height );
                        partialSave.save( chooser.getSelectedFile().getPath() );
                        break;
                }
            }

        }
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "triangles":
                createTriangles();
                break;
            case "squares":
                createSquares();
                break;
            case "circles":
                createCircles();
                break;
            case "clear":
                clearCanvas();
                break;
            case "color":
                changeColor();
                break;
            case "save":
                saveToFile();
                break;
        }
    }

}
