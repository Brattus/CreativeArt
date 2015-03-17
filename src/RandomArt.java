/**
 * Created by RaminErIkkeDigg on 16.03.2015.
 */



import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import processing.core.*;

public class RandomArt extends PApplet implements ActionListener
{
    // Need G4P library

    PGraphics pg1;
    PGraphics pg2;
    PImage img;
    float r,g,b = 0;


    //fonts
    PFont myFont;

    int st;

    public void setup()
    {

        size( 1000, 680 );

        background( 255 );

        noStroke();
    }


    public void draw() {
        if (mousePressed == true) {
            if (mouseButton == RIGHT) {
                Color c = JColorChooser.showDialog( null, "Choose a Color", Color.BLACK );
                r = c.getRed();
                g = c.getGreen();
                b = c.getBlue();
            }

            if (mouseButton == LEFT) {
                stroke(r,g,b);
                strokeWeight(5);
                line(mouseX,mouseY,pmouseX,pmouseY);
            }

        }
    }


    public void gui()
    {
        pg1.beginDraw();
        pg1.background( 200 );
        image( pg1, displayWidth - displayWidth / 4, displayHeight - displayHeight );
        pg1.endDraw();

        //Setting the font, texts size and text of the right window.
        myFont = createFont( "Arial", 25 );
        textFont( myFont );
        textAlign( CENTER, CENTER );
        fill( 255 );
        String s = "Welcome to Random Art Generator!";
        fill( 255 );
        text( s, displayWidth - displayWidth / 4, displayHeight / 40, displayWidth / 4, displayHeight / 12 );  // Text wraps within text box

        //Font text
        textAlign( LEFT, CENTER );
        textSize( 22 );
        text( "Choose filter:", (float) ( displayWidth - displayWidth / 4.1 ), (float) ( displayHeight / 2.55 ), 400, 100 );


        //Objects text
        textAlign( LEFT, CENTER );
        textSize( 22 );
        text( "Choose Objects:", (float) ( displayWidth - displayWidth / 4.1 ), (float) ( displayHeight / 3.8 ), 400, 100 );

    }

    // Use this method to add additional statements
    // to customise the GUI controls

    void imageChosen(File file)
    {
        if(file.exists())
        {
            img = loadImage( file.getAbsolutePath() );
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

    public void clearCanvas()
    {
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the canvas? Everything you created so far will disappear", "Clear canvas",  JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            background(color(255));
        }
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
        }
    }
}
