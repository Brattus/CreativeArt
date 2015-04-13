/**
 * Created by Ramin, Per-Olav, Ole-Martin and Knut Olav on 16.03.2015.
 */

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


public class RandomArt extends PApplet implements ActionListener, MouseListener
{
    public int response;
    PGraphics pg1;
    PGraphics pg2;
    PImage img = null;
    Color color = null;
    float red = 0;
    float green = 0;
    float blue = 0;
    boolean bordersEnabled = false;
    long time = 0;
    int st;
    boolean brush1, brush2, brush3 = false;
    int opacity = 170;

    // BG colors
    int redBG, greenBG, blueBG = 0;
    boolean eraserEnabled = false;

    public void setup() {
        size( 1009, 710 );
        background(255);
        noStroke();

        frameRate(60);

    }


    @Override
    public void draw()
    {
        time = millis() / 1000;

        camera();

        drawPencil();
       // eraser();
    }


    /**
     * Creates random triangles
     */
    public void createTriangles()
    {
        String firstNumber = JOptionPane.showInputDialog( "Enter how many triangles you want" );
        int number = Integer.parseInt( firstNumber );
        if(number > 2000)
        {
            number = 2000;
        }
        println( "Triangle clicked at: " + millis() / 1000 + " s" );
        for(st = 0; st < number; st++)
        {
            stroke( 1 );
            fill( random( 255 ), random( 255 ), random( 255 ), opacity );
            triangle(random(0, width), random(0, height), random(0, (float) (width / 1.7)), random(0, height), (float) (width / 2), height / 2);
        }
    }

    /**
     * Creates random squares
     */
    public void createSquares()
    {
        String firstNumber =
                JOptionPane.showInputDialog ( "Enter how many squares you want" );
        int number = Integer.parseInt (firstNumber);
        if (number > 2000)
        {
            number = 2000;
        }
        println( "Square clicked at: " + millis() );
        for(st = 0; st < number; st++)
        {
            noStroke();
            fill( random( 255 ), random( 255 ), random( 255 ), opacity );
            rect( random( 0, width ), random( 0, height ), 60, 60 );
        }
    }

    /**
     * Creates random circles
     */
    public void createCircles()
    {
        String firstNumber = JOptionPane.showInputDialog( "Enter how many circles you want" );
        int number = Integer.parseInt( firstNumber );
        if(number > 2000)
        {
            number = 2000;
        }
        println( "Circle clicked at: " + millis() / 1000 + " s" );
        for(st = 0; st < number; st++)
        {
            stroke( 1 );
            fill( random( 255 ), random( 255 ), random( 255 ), opacity );
            ellipse( random( 0, width ), random( 0, height ), 60, 60 );
        }
    }

    /**
     * Blur filter on canvas
     */
    public void blurFilter()
    {
        println( "Blur effect clicked at: " + millis() / 1000 + " s" );
        filter( BLUR, 5 );
        redraw();
    }

    /**
     * Threshold filter on canvas
     */
    public void thresholdFilter()
    {
        println( "Threshold filter clicked at: " + millis() / 1000 + " s" );
        filter( THRESHOLD );
        redraw();
    }

    /**
     * Gray scale filter on canvas
     */
    public void grayScaleFilter()
    {
        println( "Gray scale filter clicked at: " + millis() / 1000 + " s" );
        filter( GRAY );
        redraw();
    }

    /**
     * Invert filter on canvas
     */
    public void invertFilter()
    {
        println( "Invert filter clicked at: " + millis() / 1000 + " s" );
        filter( INVERT );
        redraw();
    }

    /**
     * Posterize filter on canvas
     */
    public void posterizeFilter()
    {
        println( "Posterize filter clicked at: " + millis() / 1000 + " s" );
        filter( POSTERIZE, 3 );
        redraw();
    }

    /**
     * Erode filter on canvas
     */
    public void erodeFilter()
    {
        println( "Erode filter clicked at: " + millis() / 1000 + " s" );
        filter(ERODE);
        redraw();
    }

    /**
     * Dilate filter on canvas
     */
    public void dilateFilter()
    {
        println( "Dilate filter clicked at: " + millis() / 1000 + " s" );
        filter( DILATE );
        redraw();
    }

    /**
     * Clear the canvas
     */
    public void clearCanvas()
    {
        int reply = JOptionPane.showConfirmDialog( null, "Are you sure you want to discard your masterpiece?", "Clear canvas", JOptionPane.YES_NO_OPTION );
        if (reply == JOptionPane.YES_OPTION) {
            clear();
            background(255);
        }
    }

    /**
     * Change the background color, by using a color from the palette.
     */
    private void changeColor()
    {
        Color c = JColorChooser.showDialog( null, "Choose a Color", Color.BLACK );
        redBG = c.getRed();
        greenBG = c.getGreen();
        blueBG = c.getBlue();

        background( redBG, greenBG, blueBG );
    }

    public void saveHighRes()
    {
        PImage partialSave = null;

        JTextField xField = new JTextField( 5 );
        JTextField yField = new JTextField( 5 );

        JPanel myPanel = new JPanel();
        myPanel.add( new JLabel( "Width:" ) );
        myPanel.add( xField );
        myPanel.add( Box.createHorizontalStrut( 15 ) ); // a spacer
        myPanel.add( new JLabel( "Height:" ) );
        myPanel.add( yField );

        FileNameExtensionFilter filter = new FileNameExtensionFilter( "Only JPG, GIF & png Images", "jpg", "gif", "png", "tif" );

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter( filter );
        chooser.setDialogTitle( "Save high resolution file" );

        String[] extensionTypes = filter.getExtensions();

        int returnVal = chooser.showSaveDialog( this );

        int result = JOptionPane.showConfirmDialog( null, myPanel, "Please select a resolution to save", JOptionPane.OK_CANCEL_OPTION );
        if(result == JOptionPane.OK_OPTION)
        {
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                for(String s : extensionTypes)
                {
                    switch(s)
                    {
                        case "tif":
                            break;
                        default:
                            partialSave = get( 0, 0, width, height );
                            partialSave.resize( Integer.parseInt( xField.getText() ), Integer.parseInt( yField.getText() ) );
                            partialSave.save( chooser.getSelectedFile().getPath() + ".jpg" );

                            break;
                    }
                }
            }
        }
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
                        break;
                    default:
                        partialSave = get( 0, 0, width, height );
                        partialSave.save( chooser.getSelectedFile().getPath() + ".jpg" );
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
            case "saveHighRes":
                saveHighRes();
                break;
            case "openFile":
                openFile();
                break;
            case "blur":
                blurFilter();
                break;
            case "threshold":
                thresholdFilter();
                break;
            case "gray":
                grayScaleFilter();
                break;
            case "invert":
                invertFilter();
                break;
            case "posterize":
                posterizeFilter();
                break;
            case "erode":
                erodeFilter();
                break;
            case "dialate":
                dilateFilter();
                break;
            case "border":
                enableBorders();
                break;
            case "simpleBrush":
                brush1();
                break;
            case "lineBrush":
                brush2();
                break;
            case "randomBrush":
                brush3();
                break;
            case "eraser":
                eraser();
                break;
            case "squareBackground":
                squareBackgroun();
                break;
        }
    }

    private void openFile()
    {
        PImage image = null;

        FileNameExtensionFilter filter = new FileNameExtensionFilter( "Only JPG, GIF & png Images", "jpg", "gif", "png", "tif" );

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter( filter );
        chooser.setDialogTitle( "Save file" );

        String[] extensionTypes = filter.getExtensions();

        int returnVal = chooser.showOpenDialog( this );
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            image = loadImage( chooser.getSelectedFile().getPath() );
            imageMode( CENTER );
            image( image, width / 2, height / 2, width, height );
        }
    }

    public void drawPencil()
    {
        if(mousePressed)
        {

            if(mouseButton == RIGHT)
            {
                delay( 100 );
                color = JColorChooser.showDialog( this, "Choose a Color", Color.BLACK );
                red = color.getRed();
                green = color.getGreen();
                blue = color.getBlue();
            }

            if (mouseButton == LEFT) 
            {
                println("Detected Mouse Left Click!");

                if(brush1 == true)
                {
                    simpleBrush();
                }
                if(brush2 == true) 
                {
                    lineBrush();
                }
                if(brush3 == true) 
                {
                    randomBrush();
                }
            }
        }
    }


    public void brush1()
    {
        brush1 = true;
        brush2 = false;
        brush3 = false;
    }

    public void brush2()
    {
        brush1 = false;
        brush2 = true;
        brush3 = false;
    }

    public void brush3()
    {
        brush1 = false;
        brush2 = false;
        brush3 = true;
    }

    public void lineBrush()
    {
        for(int i = 0; i < 20; i++)
        {
            int r = 10;
            stroke(red, green, blue);
            strokeWeight(1);
            line(mouseX + random(r), mouseY + random(r), pmouseX + random(r), pmouseY + random(r));
        }
    }

    public void simpleBrush()
    {
        stroke( red, green, blue );
        strokeWeight( 5 );
        line( mouseX, mouseY, pmouseX, pmouseY );
    }


    public void randomBrush() {
        float speed = abs(mouseX - pmouseX) + abs(mouseY - pmouseY);
        stroke(speed);
        fill(random(255), random(255), random(255), opacity);
        ellipse(mouseX, mouseY, speed, speed);
        }

    public void squareBackgroun(){
        noStroke();
        for (int i = 0; i < 700; i = i+(int)random(160, 200)) {
            for (int j = 0; j < 1200; j = j+(int)random(30,40) ) {
                fill(random(255),random(255), random(255),200);
                rect(j, i, 40, 200 );
            }
        }
    }

    public void enableBorders()
    {
        bordersEnabled = true;
        if(bordersEnabled == true)
        {
            borders();
        }
    }

    public void borders()
    {
        stroke( 0 );
        strokeWeight( 20 );
        line( 0, 0, width, 0 );
        line( width, 0, width, height );
        line( 0, height, width, height );
        line( 0, 0, 0, height );
    }


    public void eraser()
    {
        if(mousePressed)
        {
            if(mouseButton == LEFT)
            {
                noStroke();
                    fill( redBG, greenBG, blueBG );
                ellipse( pmouseX, pmouseY, 50, 50 );
            }
        }
    }
}
