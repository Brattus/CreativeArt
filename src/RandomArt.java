/**
 * Created by Ramin, Per-Olav, Ole-Martin and Knut Olav on 16.03.2015.
 */

import processing.core.PApplet;
import processing.core.PImage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;


public class RandomArt extends PApplet implements ActionListener
{
    Color color = null;
    float red = 0;
    float green = 0;
    float blue = 0;
    boolean bordersEnabled = false;
    long time = 0;
    int st;
    int stroke0 = 0;
    int stroke1 = 1;
    int stroke5 = 5;
    boolean simpleBrushEnabled, lineBrushEnabled, randomLineBrushEnabled, randomCircleBrush, tunnelBrush, eraseEnabled = false;
    int opacity = 170;

    // Variables for createTriangle
    JTextField numberOftrianglesfField = new JTextField( 15 );
    JTextField numberOfsquaresfField = new JTextField( 15 );
    JTextField numberOfellipsefField = new JTextField( 15 );


    // Variables for randomText()
    JTextField randomTextField = new JTextField( 15 );
    JTextField randomAmountField = new JTextField( 15 );

    // We need these to know if CTRL/SHIFT are pressed
    boolean controlDown = false;
    boolean shiftDown = false;
    UndoRedo undoRedo;

    // BG colors
    int redBG=255, greenBG =255, blueBG = 255;

    //Setting up the processing window.
    public void setup()
    {
        size(1009, 710);
        background( 255 );
        noStroke();
        undoRedo = new UndoRedo( 10 );
        frameRate( 60 );

    }

    //Processing draw function.
    @Override
    public void draw()
    {
        noStroke();
        time = millis() / 1000;


        drawPencil();
    }

    public void mouseReleased()
    {
        // Save each line we draw to our stack of UNDOs
        undoRedo.takeSnapshot();
    }

    public void keyPressed()
    {
        // Remember if CTRL or SHIFT are pressed or not
        if(key == CODED)
        {
            if(keyCode == CONTROL)
                controlDown = true;
            if(keyCode == SHIFT)
                shiftDown = true;
            return;
        }
        // Check if we pressed CTRL+Z or CTRL+SHIFT+Z
        if(controlDown)
        {
            if(keyCode == 'Z')
            {
                if(shiftDown)
                    undoRedo.redo();
                else
                    undoRedo.undo();
            }
            return;
        }
        // Check if we pressed the S key
        if(key == 's')
        {
            saveFrame( "image####.png" );
        }
    }

    public void keyReleased()
    {
        // Remember if CTRL or SHIFT are pressed or not
        if(key == CODED)
        {
            if(keyCode == CONTROL)
                controlDown = false;
            if(keyCode == SHIFT)
                shiftDown = false;
        }
    }

    /**
     * Creates random triangles
     */
    public void createTriangles()
    {
        JPanel inputPanel = new JPanel();
        inputPanel.add( new JLabel( "Number of triangles:" ) );
        inputPanel.add( Box.createHorizontalStrut( 2 ) ); // a spacer
        inputPanel.add(numberOftrianglesfField);


        int amount = 0;
        String text = "";

        int result = JOptionPane.showConfirmDialog( null, inputPanel, "Please Enter an amount of triangles", JOptionPane.OK_CANCEL_OPTION );


        if(result == JOptionPane.OK_OPTION)
        {
            try
            {
                text = numberOftrianglesfField.getText();
                amount = Integer.parseInt( numberOftrianglesfField.getText().toString() );

                if(amount > 2000)
                {
                    amount = 2000;
                }
                println( "Triangle clicked at: " + millis() / 1000 + " s" );
                for(st = 0; st < amount; st++)
                {
                    noStroke();
                    fill( random( 255 ), random( 255 ), random( 255 ), opacity );
                    triangle( random( 0, width ), random( 0, height ), random( 0, (float) ( width / 1.7 ) ), random( 0, height ), (float) ( width / 2 ), height / 2 );
                }
            } catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog( this, e.getMessage().substring( 18, e.getMessage().length() ) + " is not a numeric value. \n" +
                        "Amount must be a numeric value", "Input must be a numeric value", JOptionPane.ERROR_MESSAGE );

                numberOftrianglesfField.setText( numberOftrianglesfField.getText() );
                createTriangles();
            }
        }
    }

    /**
     * Creates random squares
     */
    public void createSquares()
    {

        JPanel inputPanel = new JPanel();
        inputPanel.add( new JLabel( "Number of squares:" ) );
        inputPanel.add( Box.createHorizontalStrut( 2 ) ); // a spacer
        inputPanel.add(numberOfsquaresfField);

        int amount = 0;
        String text = "";

        int result = JOptionPane.showConfirmDialog( null, inputPanel, "Please Enter an amount of squares", JOptionPane.OK_CANCEL_OPTION );


        if(result == JOptionPane.OK_OPTION)
        {
            try
            {
                text = numberOfsquaresfField.getText();
                amount = Integer.parseInt( numberOfsquaresfField.getText().toString() );
                if(amount > 2000)
                {
                    amount = 2000;
                }
                println( "Square clicked at: " + millis() );
                for(st = 0; st < amount; st++)
                {
                    int squares = (int) random( 400 );
                    noStroke();
                    // strokeWeight(stroke0);
                    fill(random(255), random(255), random(255), opacity);
                    rect( random( 0, width ), random( 0, height ), squares, squares );
                }
            } catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog( this, e.getMessage().substring( 18, e.getMessage().length() ) + " is not a numeric value. \n" +
                        "Amount must be a numeric value", "Input must be a numeric value", JOptionPane.ERROR_MESSAGE );

                numberOfsquaresfField.setText( numberOfsquaresfField.getText() );
                createSquares();
            }
        }
    }



    /**
     * Creates random circles
     */
    public void createCircles() {

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Number of circles:"));
        inputPanel.add(Box.createHorizontalStrut(2)); // a spacer
        inputPanel.add(numberOfellipsefField);


        int amount = 0;
        String text = "";

        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Please Enter an amount of triangles", JOptionPane.OK_CANCEL_OPTION);


        if (result == JOptionPane.OK_OPTION) {
            try {
                text = numberOfellipsefField.getText();
                amount = Integer.parseInt(numberOfellipsefField.getText().toString());

                if(amount > 2000)
                {
                    amount = 2000;
                }
                println( "Circle clicked at: " + millis() / 1000 + " s" );
                for(st = 0; st < amount; st++)
                {
                    int circles = (int) random( 400 );
                    stroke( stroke0 );
                    strokeWeight(stroke0);
                    fill(random(255), random( 255 ), random( 255 ), opacity );
                    ellipse( random( 0, width ), random( 0, height ),circles, circles );
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, e.getMessage().substring(18, e.getMessage().length()) + " is not a numeric value. \n" +
                        "Amount must be a numeric value", "Input must be a numeric value", JOptionPane.ERROR_MESSAGE);

                numberOfellipsefField.setText(numberOfellipsefField.getText());
                createCircles();
            }
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
        filter( ERODE );
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
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to discard your masterpiece?", "Discard Masterpiece", JOptionPane.YES_NO_OPTION );
        if(reply == JOptionPane.YES_OPTION)
        {
            clear();
            background( 255 );
            redBG = 255;
            greenBG = 255;
            blueBG = 255;
        }
    }

    /**
     * Change the background color, by using a color from the palette.
     */
    private void changeColor()
    {
        Color c = JColorChooser.showDialog( null, "Choose a Color", Color.WHITE );
        redBG = c.getRed();
        greenBG = c.getGreen();
        blueBG = c.getBlue();

        background(redBG, greenBG, blueBG);
    }

    //Function to save the art with preferred resolution.
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

        int result = JOptionPane.showConfirmDialog(null, myPanel, "Please select a resolution to save", JOptionPane.OK_CANCEL_OPTION );
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
        chooser.setFileFilter(filter );
        chooser.setDialogTitle( "Save file");

        String[] extensionTypes = filter.getExtensions();

        int returnVal = chooser.showSaveDialog(this);
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
            case "simpleBrushEnabled":
                simpleBrushEnabled();
                break;
            case "lineBrushEnabled":
                lineBrushEnabled();
                break;
            case "randomLineBrushEnabled":
                randomLineBrushEnabled();
                break;
            case "tunnelBrushEnabled":
                tunnelBrushEnabled();
                break;
            case "eraserEnabled":
                eraserEnabled();
                break;
            case "squareBackground":
                squareBackground();
                break;
            case "randomCirclesBrushEnabled":
                randomCircleBrushEnabled();
                break;
            case "randomText":
                randomText();
                break;
            case "undo":
                undoRedo.undo();
                break;
            case "redo":
                undoRedo.redo();
        }
    }

    
    //Imports a file into the processing window.
    private void openFile()
    {
        PImage image = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter( "Only JPG, GIF & png Images", "jpg", "gif", "png", "tif" );

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter( filter );
        chooser.setDialogTitle("Save file");

        String[] extensionTypes = filter.getExtensions();

        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            image = loadImage( chooser.getSelectedFile().getPath() );
            imageMode( CENTER );
            image( image, width / 2, height / 2, width, height );
        }
    }

    //The drawing function.
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
                //println("Detected Mouse Left Click!");

                if(simpleBrushEnabled == true)
                {
                    simpleBrush();
                }
                if(lineBrushEnabled == true)
                {
                    lineBrush();
                }
                if(randomLineBrushEnabled == true)
                {
                    randomLineBrush();
                }
                if(randomCircleBrush == true)
                {
                    randomCirclesBrush();
                }
                if(eraseEnabled == true)
                {
                    eraser();
                }
                if(tunnelBrush == true)
                {
                    tunnelVision();
                }
            }
        }
    }

    //The eraser.
    private void eraser()
    {
        strokeWeight( 20 );
        stroke( redBG, greenBG, blueBG );
        line( mouseX, mouseY, pmouseX, pmouseY );
    }

    //The line brush enabled.
    public void lineBrushEnabled()
    {
        simpleBrushEnabled = false;
        lineBrushEnabled = true;
        randomLineBrushEnabled = false;
        randomCircleBrush = false;
        eraseEnabled = false;
        tunnelBrush = false;
    }

    //The simple brush enabled.
    public void simpleBrushEnabled()
    {
        simpleBrushEnabled = true;
        lineBrushEnabled = false;
        randomLineBrushEnabled = false;
        randomCircleBrush = false;
        eraseEnabled = false;
        tunnelBrush = false;
    }

    //The random line brush enabled.
    public void randomLineBrushEnabled()
    {
        simpleBrushEnabled = false;
        lineBrushEnabled = false;
        randomLineBrushEnabled = true;
        randomCircleBrush = false;
        eraseEnabled = false;
        tunnelBrush = false;
    }

    //The tunnel brush enabled.
    public void tunnelBrushEnabled()
    {
        tunnelBrush = true;
        simpleBrushEnabled = false;
        lineBrushEnabled = false;
        randomLineBrushEnabled = false;
        randomCircleBrush = false;
        eraseEnabled = false;
    }

    //The eraser enabled.
    public void eraserEnabled()
    {
        eraseEnabled = true;
        simpleBrushEnabled = false;
        lineBrushEnabled = false;
        randomLineBrushEnabled = false;
        randomCircleBrush = false;
        tunnelBrush = false;
    }

    //The random circle brush enabled.
    public void randomCircleBrushEnabled()
    {
        randomCircleBrush = true;
        simpleBrushEnabled = false;
        lineBrushEnabled = false;
        randomLineBrushEnabled = false;
        eraseEnabled = false;
        tunnelBrush = false;
    }

    //Eraser disabled.
    public void eraserDisabled()
    {
        eraseEnabled = false;
        simpleBrushEnabled = false;
        lineBrushEnabled = false;
        randomLineBrushEnabled = false;
    }

    //The line brush function.
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

    //The simple brush function.
    public void simpleBrush()
    {
        stroke( red, green, blue );
        strokeWeight(5);
        line( mouseX, mouseY, pmouseX, pmouseY );
    }

    //The random circle brush function.
    public void randomCirclesBrush()
    {
        float speed = abs(mouseX - pmouseX) + abs(mouseY - pmouseY);
        stroke(1);
        strokeWeight(1);
        fill(random(255), random(255), random(255), opacity);
        ellipse(mouseX, mouseY, speed, speed);
        }

    //The random line brush function.
    public void randomLineBrush()
    {
        if(mousePressed == true)
        {
            strokeWeight( random( 10 ) );
            stroke( random( 255 ), random( 255 ), random( 255 ), opacity );
            line( mouseX, mouseY, random( 0, 1366 ), random( 0, 768 ) );
        }
    }

    //The square background function.
    public void squareBackground()
    {
        noStroke();
        for(int i = 0; i < 700; i = i + (int) random( 160, 200 ))
        {
            for(int j = 0; j < 1200; j = j + (int) random( 30, 40 ))
            {
                fill( random( 255 ), random( 255 ), random( 255 ), 200 );
                rect( j, i, 40, 200 );
            }
        }
    }

    //The enable borders.
    public void enableBorders()
    {
        bordersEnabled = true;
        if(bordersEnabled == true)
        {
            borders();
        }
    }

    //The border function.
    public void borders()
    {
        stroke( 0 );
        strokeWeight( 20 );
        line( 0, 0, width, 0 );
        line( width, 0, width, height );
        line( 0, height, width, height );
        line( 0, 0, 0, height );
    }

    //The tunnel vision function
    public void tunnelVision()
    {
        if(mousePressed)
        {
            if(mouseButton == LEFT)
            {
                stroke( random( 255 ), random( 255 ), random( 255 ) );
                strokeWeight( random( 10 ) );
                line( width / 2, height / 2, mouseX, mouseY );
            }
        }
    }


    public void randomText()
    {
        JPanel inputPanel = new JPanel();
        inputPanel.add( new JLabel( "Text:" ) );
        inputPanel.add( randomTextField );
        inputPanel.add( Box.createHorizontalStrut( 15 ) ); // a spacer
        inputPanel.add( new JLabel( "Amount:" ) );
        inputPanel.add( randomAmountField );

        int amount = 0;
        String text = "";

        int result = JOptionPane.showConfirmDialog( null, inputPanel, "Please Enter Text and amount Values", JOptionPane.OK_CANCEL_OPTION );
        if(result == JOptionPane.OK_OPTION)
        {
            try
            {
                text = randomTextField.getText();
                amount = Integer.parseInt( randomAmountField.getText().toString() );

                for(int i = 0; i < amount; i++)
                {
                    fill( random( 255 ), random( 255 ), random( 255 ) );
                    textSize( random( 10, 70 ) );
                    textMode( CENTER );
                    text( text, random( width - 200 ), random( height ) );
                }

            } catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog( this, e.getMessage().substring( 18, e.getMessage().length() ) + " is not a numeric value. \n" +
                        "Amount must be a numeric value", "Input must be a numeric value", JOptionPane.ERROR_MESSAGE );

                randomTextField.setText( randomTextField.getText() );
                randomText();
            }
        }
    }

    /**
     * Undo class
     */
    class UndoRedo
    {
        // Number of currently available undo and redo snapshots
        int undoSteps = 0, redoSteps = 0;
        ImgCollection images;

        UndoRedo(int stepsBackAndForth)
        {
            images = new ImgCollection( stepsBackAndForth );
        }

        public void takeSnapshot()
        {
            undoSteps = min( undoSteps + 1, images.amount - 1 );
            // each time we draw we disable redo
            redoSteps = 0;
            images.next();
            images.capture();
        }

        public void undo()
        {
            println( "Undo" );
            if(undoSteps > 0)
            {
                undoSteps--;
                redoSteps++;
                images.prev();
                images.show();
            }
        }

        public void redo() {
            println( "Redo" );
            if(redoSteps > 0)
            {
                undoSteps++;
                redoSteps--;
                images.next();
                images.show();
            }
        }
    }

    /**
     * Circle image collection class
     */
    class ImgCollection
    {
        int amount, current;
        PImage[] img;

        ImgCollection(int amountOfImages)
        {
            amount = amountOfImages;

            // Initialize all images as copies of the current display
            img = new PImage[ amount ];
            for(int i = 0; i < amount; i++)
            {
                img[ i ] = createImage( width, height, RGB );
                img[ i ] = get();
            }
        }

        void next()
        {
            current = ( current + 1 ) % amount;
        }

        void prev()
        {
            current = ( current - 1 + amount ) % amount;
        }

        void capture()
        {
            img[ current ] = get();
        }

        void show()
        {
            image( img[ current ], 0, 0 );
        }
    }


}
