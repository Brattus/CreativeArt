/**
 * Created by Ramin on 16.03.2015.
 */

import processing.core.*;
import g4p_controls.*;;

public class Gui extends PApplet
{
    int st;
    private PImage pg1;
    private PImage pg2;

    private PGraphics pGraphics;

    // Variable declarations
    GButton circle1;
    GButton square1;
    GButton triangle1;
    GButton clear1;
    GButton background1;
    GButton save1;
    GButton filter1;
    GButton filter2;
    GButton filter3;
    GButton filter4;
    GButton filter5;
    GButton filter6;
    GButton filter7;
    GButton import1;
    
    public void circle1_click1(GButton source, GEvent event) { //_CODE_:circle1:561316:
        println("Circle clicked at: " + millis());
        for(st = 0; st < 50; st++){
            noStroke ();
            fill (random (255), random (255), random(255), 170);
            ellipse (random (30, displayWidth-displayWidth/4), random (0, height), 60, 60);
        }
        new Gui();
    } //_CODE_:circle1:561316:

    public void button2_click1(GButton source, GEvent event) { //_CODE_:square1:656476:
        println("Square clicked at: " + millis());
        for(st = 0; st < 50; st++){
            noStroke ();
            fill (random (255), random (255), random(255), 170);
            rect(random(0,displayWidth-displayWidth/4), random(0,height), 60,60);
        }
        new Gui();
    } //_CODE_:square1:656476:



    public void button3_click1(GButton source, GEvent event) { //_CODE_:triangle1:298157:
        println("Triangle clicked at: " + millis());
        for(st = 0; st < 50; st++){
            noStroke ();
            fill (random (255), random (255), random(255), 200);
            triangle(random(0,displayWidth-displayWidth/4), random(0,height), random(0, (float) (width/1.7) ), random(0,height), (float) (width/3.2), height/2);
        }
        new Gui();
    } //_CODE_:triangle1:298157:

    public void button4_click1(GButton source, GEvent event) { //_CODE_:pause1:488433:
        println("Clear clicked at: " + millis());
        pGraphics.clear();
        pGraphics.beginDraw();
        pGraphics.background(255);
        image(pg1,displayWidth-displayWidth/4,displayHeight-displayHeight);
        pGraphics.endDraw();

        //pg2
        pGraphics.beginDraw();
        pGraphics.background(0);
        image(pg2,displayWidth-displayWidth/4,displayHeight);
        pGraphics.endDraw();

        new Gui();

    } //_CODE_:pause1:488433:

    public void button5_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
        println("Background clicked at: " + millis());
        pGraphics.beginDraw();
        pGraphics.background(255);
        image(pg2,displayWidth-displayWidth/4,displayHeight);
        pGraphics.endDraw();


        new Gui();
    } //_CODE_:resume1:284723:

    public void button6_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
        println("Save clicked at: " + millis());
        PImage partialSave = get(0,0,600,565);
        partialSave.save("art-#####.jpeg");
        new Gui();
    } //_CODE_:resume1:284723:

    //filter button
    public void button7_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
        println("Filer clicked at: " + millis());
        filter(BLUR,5);
        new Gui();
    } //_CODE_:resume1:284723:

    //filter2 button
    public void button8_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
        println("Filer2 clicked at: " + millis());
        filter(THRESHOLD);
        new Gui();
    } //_CODE_:resume1:284723:

    //filter3 button
    public void button9_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
        println("Filer3 clicked at: " + millis());
        filter(GRAY);
       new  Gui();
    } //_CODE_:resume1:284723:

    //filter4 button
    public void button10_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
        println("Filer4 clicked at: " + millis());
        filter(INVERT);
        new Gui();
    } //_CODE_:resume1:284723:

    //filter5 button
    public void button11_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
        println("Filer5 clicked at: " + millis());
        filter(POSTERIZE,3);
        new Gui();
    } //_CODE_:resume1:284723:

    //filter6 button
    public void button12_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
        println("Filer6 clicked at: " + millis());
        filter(ERODE);
        new Gui();
    } //_CODE_:resume1:284723:

    //filter7 button
    public void button13_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
        println("Filer7 clicked at: " + millis());
        filter(DILATE);
        new Gui();
    } //_CODE_:resume1:284723:

    //Import
    public void button14_click1(GButton source, GEvent event) { //_CODE_:resume1:284723:
        println("import1 clicked at: " + millis());
        selectInput( "Select an image", "imageChosen" );

        new Gui();
    } //_CODE_:resume1:284723:

    // Create all the GUI controls.
    public void createGUI(){
        G4P.messagesEnabled( false );
        G4P.setGlobalColorScheme( GCScheme.BLUE_SCHEME );
        G4P.setCursor(ARROW);
        if(frame != null)
        {
            frame.setTitle( "Random Art Generator" );
        }

        //Triangle button

        triangle1 = new GButton(this, (float) ((displayWidth - displayHeight)/(4.1)),(float) (displayHeight/2.9), 80, 30);
        triangle1.setText("Triangle");
        triangle1.setLocalColorScheme(GCScheme.CYAN_SCHEME);
        triangle1.addEventHandler(this, "button3_click1");

        //Circle button
        // displayWidth-400,displayHeight/3.8
        circle1 = new GButton(this, (float) (displayWidth-displayWidth/5.35), (float) (displayHeight/2.9), 80, 30);
        circle1.setText("Circle");
        circle1.setLocalColorScheme(GCScheme.CYAN_SCHEME);
        circle1.addEventHandler(this, "circle1_click1");

        //Square button
        square1 = new GButton(this, (float) (displayWidth-displayWidth/7.72),(float) (displayHeight/2.9), 80, 30);
        square1.setText("Square");
        square1.setLocalColorScheme(GCScheme.CYAN_SCHEME);
        square1.addEventHandler(this, "button2_click1");

        //Import
        import1 = new GButton(this,(float) ((displayWidth-displayWidth)/(13.85)), (float) (displayHeight/2.9), 80, 30);
        import1.setText("import");
        import1.setLocalColorScheme(GCScheme.CYAN_SCHEME);
        import1.addEventHandler(this, "button14_click1");

        //Clear Button
        clear1 = new GButton(this, (float) (displayWidth-displayWidth/4.1), (float) (displayHeight/1.1), 80, 30);
        clear1.setText("Clear");
        clear1.setLocalColorScheme(GCScheme.RED_SCHEME);
        clear1.addEventHandler(this, "button4_click1");

        //Background button
        background1 = new GButton(this, (float) (displayWidth-displayWidth/7.72), (float) (displayHeight/1.1), 80, 30);
        background1.setText("Background");
        background1.setLocalColorScheme(GCScheme.GREEN_SCHEME);
        background1.addEventHandler(this, "button5_click1");

        //Save button
        save1 = new GButton(this, (float) (displayWidth-displayWidth/13.85), (float) (displayHeight/1.1), 80, 30);
        save1.setText("Save");
        save1.setLocalColorScheme(GCScheme.GREEN_SCHEME);
        save1.addEventHandler(this, "button6_click1");


        //Filter
        filter1 = new GButton(this, (float) (displayWidth-displayWidth/4.1), (float) (displayHeight/2.1), 80, 30);
        filter1.setText("Blur");
        filter1.setLocalColorScheme(GCScheme.GREEN_SCHEME);
        filter1.addEventHandler(this, "button7_click1");

        //Filter2
        filter2 = new GButton(this, (float) (displayWidth-displayWidth/5.35), (float) (displayHeight/2.1), 80, 30);
        filter2.setText("Threshold");
        filter2.setLocalColorScheme(GCScheme.GREEN_SCHEME);
        filter2.addEventHandler(this, "button8_click1");

        //Filter3
        filter3 = new GButton(this, (float) (displayWidth-displayWidth/7.72), (float) (displayHeight/2.1), 80, 30);
        filter3.setText("Gray");
        filter3.setLocalColorScheme(GCScheme.GREEN_SCHEME);
        filter3.addEventHandler(this, "button9_click1");

        //Filter4
        filter4 = new GButton(this,(float) (displayWidth-displayWidth/13.85), (float) (displayHeight/2.1), 80, 30);
        filter4.setText("Invert");
        filter4.setLocalColorScheme(GCScheme.GREEN_SCHEME);
        filter4.addEventHandler(this, "button10_click1");

        //Filter5
        filter5 = new GButton(this, (float) (displayWidth-displayWidth/4.1), (float) (displayHeight/1.955), 80, 30);
        filter5.setText("Posterize");
        filter5.setLocalColorScheme(GCScheme.GREEN_SCHEME);
        filter5.addEventHandler(this, "button11_click1");

        //Filter6
        filter6 = new GButton(this, (float) (displayWidth-displayWidth/5.35), (float) (displayHeight/1.955), 80, 30);
        filter6.setText("Erode");
        filter6.setLocalColorScheme(GCScheme.GREEN_SCHEME);
        filter6.addEventHandler(this, "button12_click1");

        //Filter7
        filter7 = new GButton(this, (float) (displayWidth-displayWidth/7.72), (float) (displayHeight/1.955), 80, 30);
        filter7.setText("Dilate");
        filter7.setLocalColorScheme(GCScheme.GREEN_SCHEME);
        filter7.addEventHandler(this, "button13_click1");

    }
}
