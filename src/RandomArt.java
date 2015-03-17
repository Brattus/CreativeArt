/**
 * Created by Ramin on 16.03.2015.
 */

import processing.core.*;
import processing.opengl.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RandomArt extends PApplet implements ActionListener
{
    // Need G4P library

    PGraphics pg1;
    PGraphics pg2;
    PImage img;

    Gui gui;

    //fonts
    PFont myFont;
    PFont myFont1;

    int st;
    PGraphics pGraphics;

    public void setup(){

        size(1000, 700);

        background(255);

        noStroke();
    }

    @Override
    public void draw()
    {
    }


    public void gui(){
        pg1.beginDraw();
        pg1.background(200);
        image(pg1,displayWidth-displayWidth/4,displayHeight-displayHeight);
        pg1.endDraw();

        //Setting the font, texts size and text of the right window.
        myFont = createFont("Arial", 25);
        textFont(myFont);
        textAlign(CENTER, CENTER);
        fill(255);
        String s = "Welcome to Random Art Generator!";
        fill(255);
        text(s, displayWidth-displayWidth/4, displayHeight/40, displayWidth/4, displayHeight/12);  // Text wraps within text box

        //Font text
        textAlign(LEFT,CENTER);
        textSize(22);
        text("Choose filter:", (float) (displayWidth-displayWidth/4.1), (float) (displayHeight/2.55), 400,100);


        //Objects text
        textAlign(LEFT,CENTER);
        textSize(22);
        text("Choose Objects:", (float) (displayWidth-displayWidth/4.1), (float) (displayHeight/3.8), 400,100);

    }

    // Use this method to add additional statements
    // to customise the GUI controls

    void imageChosen( File f )
    {
        if( f.exists() )
        {
            img = loadImage( f.getAbsolutePath() );
        }
    }


    /**
     * Creates random triangles
     */
    public void createTriangles()
    {
        println( "Triangle clicked at: " + millis()/1000 + " s" );
        for(st = 0; st < 50; st++)
        {
           fill( random( 255 ), random( 255 ), random( 255 ), 200 );
            triangle( random ( 0, width ), random( 0, height ), random( 0, (float) ( width / 1.7 ) ), random( 0, height ), (float) ( width / 2 ), height / 2 );
        }
    }

    /**
     * Creates random squares
     */
    public void createSquares()
    {
        println("Square clicked at: " + millis());
        for(st = 0; st < 50; st++){
            noStroke ();
            fill (random (255), random (255), random(255), 170);
            rect(random(0,width), random(0,height), 60,60);
        }
    }

    /**
     * Creates random circles
     */
    public void createCircles()
    {
        println("Circle clicked at: " + millis());
        for(st = 0; st < 50; st++){
            noStroke ();
            fill (random (255), random (255), random(255), 170);
            ellipse (random (0, width), random (0, height), 60, 60);
        }
    }

    public void clearCanvas()
    {
        clear();
    }

        private void changeColor()
        {
            Color c = JColorChooser.showDialog(null, "Choose a Color", Color.BLACK);
             background( c.getRGB() );
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
        }
    }
}
