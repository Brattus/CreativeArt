/**
 * Created by Ramin, Per-Olav, Ole-Martin and Knut Olav on 16.03.2015.
 *
 * RandomArt class is responsible for the drawing functions.
 */

import processing.core.PApplet;
import processing.core.PImage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


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
    int redBG = 255, greenBG = 255, blueBG = 255;

    /**
     * Setting up the processing window.
     */
    public void setup()
    {
        size( 1024, 768 );
        background( 255 );
        noStroke();
        undoRedo = new UndoRedo( 10 );
        frameRate( 60 );

    }

    /**
     * Drawing the objects on canvas. Updates the canvas for every loop.
     */
    @Override
    public void draw()
    {
        noStroke();
        time = millis() / 1000;


        drawPencil();
    }


    /**
     * When the mouse is leased, undoRedo calls to its method
     * takeSnapshot(), which take a snapshot of the canvas and stores it
     * in a arraylist.
     */
    public void mouseReleased()
    {
        // Save each line we draw to our stack of UNDOs
        undoRedo.takeSnapshot();
    }


    /**
     *
     */
    public void keyPressed()
    {
        // Remember if CTRL or SHIFT are pressed or not
        if(key == CODED)
        {
            if(keyCode == CONTROL)
            {
                controlDown = true;
            }
            if(keyCode == SHIFT)
            {
                shiftDown = true;
            }
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
     * Draws an amount of triangles of own choosing.
     */
    public void createTriangles()
    {
        JPanel inputPanel = new JPanel();
        inputPanel.add( new JLabel( "Number of triangles:" ) );
        inputPanel.add( Box.createHorizontalStrut( 2 ) ); // a spacer
        inputPanel.add( numberOftrianglesfField );


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
     * Draws an amount of Squares of own choosing.
     * The Squares are in different sizes and colors.
     */
    public void createSquares()
    {

        JPanel inputPanel = new JPanel();
        inputPanel.add( new JLabel( "Number of squares:" ) );
        inputPanel.add( Box.createHorizontalStrut( 2 ) ); // a spacer
        inputPanel.add( numberOfsquaresfField );

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
                    fill( random( 255 ), random( 255 ), random( 255 ), opacity );
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
     * Draws an amount of Circles of own choosing.
     * The Circles are in different sizes and colors.
     */
    public void createCircles()
    {

        JPanel inputPanel = new JPanel();
        inputPanel.add( new JLabel( "Number of circles:" ) );
        inputPanel.add( Box.createHorizontalStrut( 2 ) ); // a spacer
        inputPanel.add( numberOfellipsefField );


        int amount = 0;
        String text = "";

        int result = JOptionPane.showConfirmDialog( null, inputPanel, "Please Enter an amount of triangles", JOptionPane.OK_CANCEL_OPTION );


        if(result == JOptionPane.OK_OPTION)
        {
            try
            {
                text = numberOfellipsefField.getText();
                amount = Integer.parseInt( numberOfellipsefField.getText().toString() );

                if(amount > 2000)
                {
                    amount = 2000;
                }
                println( "Circle clicked at: " + millis() / 1000 + " s" );
                for(st = 0; st < amount; st++)
                {
                    int circles = (int) random( 400 );
                    stroke( stroke0 );
                    strokeWeight( stroke0 );
                    fill( random( 255 ), random( 255 ), random( 255 ), opacity );
                    ellipse( random( 0, width ), random( 0, height ), circles, circles );
                }
            } catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog( this, e.getMessage().substring( 18, e.getMessage().length() ) + " is not a numeric value. \n" +
                        "Amount must be a numeric value", "Input must be a numeric value", JOptionPane.ERROR_MESSAGE );

                numberOfellipsefField.setText( numberOfellipsefField.getText() );
                createCircles();
            }
        }
    }


    /**
     * The about menu method shows information about the application
     */
    protected void aboutMenu()
    {
        JOptionPane.showMessageDialog( this, "Team:\n" +
                "Ramin Esfandiari\n" +
                "Ole Martin Bratteberg\n" +
                "Per-Olav Eikrem\n" +
                "Knut-Olav Skaret", "About", JOptionPane.INFORMATION_MESSAGE );
    }


    /**
     * This is a link to a guide for the application
     */
    protected void systemDocumentation()
    {
        if(Desktop.isDesktopSupported())
        {
            try
            {
                File myFile = new File( "Documentation/SystemDocumentation.pdf" );
                Desktop.getDesktop().open( myFile );
            } catch(IOException ex)
            {
                // no application registered for PDFs
            }
        }
    }


    /**
     * Draws a blur filter on canvas
     */
    public void blurFilter()
    {
        println( "Blur effect clicked at: " + millis() / 1000 + " s" );
        filter( BLUR, 5 );
        redraw();
    }

    /**
     * Draws threshold filter on canvas
     */
    public void thresholdFilter()
    {
        println( "Threshold filter clicked at: " + millis() / 1000 + " s" );
        filter( THRESHOLD );
        redraw();
    }

    /**
     * Draws gray scale filter on canvas
     */
    public void grayScaleFilter()
    {
        println( "Gray scale filter clicked at: " + millis() / 1000 + " s" );
        filter( GRAY );
        redraw();
    }

    /**
     * Draws invert filter on canvas
     */
    public void invertFilter()
    {
        println( "Invert filter clicked at: " + millis() / 1000 + " s" );
        filter( INVERT );
        redraw();
    }

    /**
     * Draws posterize filter on canvas
     */
    public void posterizeFilter()
    {
        println( "Posterize filter clicked at: " + millis() / 1000 + " s" );
        filter( POSTERIZE, 3 );
        redraw();
    }

    /**
     * Draws erode filter on canvas
     */
    public void erodeFilter()
    {
        println( "Erode filter clicked at: " + millis() / 1000 + " s" );
        filter( ERODE );
        redraw();
    }

    /**
     * Draws dilate filter on canvas
     */
    public void dilateFilter()
    {
        println( "Dilate filter clicked at: " + millis() / 1000 + " s" );
        filter( DILATE );
        redraw();
    }

    /**
     * The method clears the canvas. Which means it removes all the objects drawn
     * and resets the canvas to default color.
     */
    public void clearCanvas()
    {
        int reply = JOptionPane.showConfirmDialog( null, "Are you sure you want to discard your masterpiece?", "Discard Masterpiece", JOptionPane.YES_NO_OPTION );
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
     * Changes the background color of the canvas, by using a color from the palette.
     */
    private void changeBackgroundColor()
    {
        Color c = JColorChooser.showDialog( null, "Choose a Color", Color.WHITE );
        redBG = c.getRed();
        greenBG = c.getGreen();
        blueBG = c.getBlue();

        background( redBG, greenBG, blueBG );
    }

    /**
     * This method saves the art with preferred resolution.
     * save first by name, then give the preferred resolution.
     */
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
     * Save the art with with the default resolution of the canvas.
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
     * Used for actionListening the buttons.
     *
     * @param e the event that is performed.
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
                changeBackgroundColor();
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
                break;
            case "about":
                aboutMenu();
                break;
            case "sysdoc":
                systemDocumentation();
                break;
        }
    }

    
    //Imports a file and draws it on canvas.
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

    /**
     * Draw line, wherever the mouse is, when left button is clicked. Just like a pencil.
     * If the right button on the mouse is clicked, the is a popup menu with colors to choose pencil
     * color.
     */
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

            if(mouseButton == LEFT)
            {
                println( "Detected Mouse Left Click!" );

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

    /**
     * Erasing where ever the mouse is, if the leftButton is clicked.
     */
    private void eraser()
    {
        strokeWeight( 20 );
        stroke( redBG, greenBG, blueBG );
        line( mouseX, mouseY, pmouseX, pmouseY );
    }


    /**
     * Enables the line brush variable and disables the other
     * related brush-variables.
     */
    public void lineBrushEnabled()
    {
        simpleBrushEnabled = false;
        lineBrushEnabled = true;
        randomLineBrushEnabled = false;
        randomCircleBrush = false;
        eraseEnabled = false;
        tunnelBrush = false;
    }


    /**
     * Enables the simple brush variable and disables the other
     * related brush-variables.
     */
    public void simpleBrushEnabled()
    {
        simpleBrushEnabled = true;
        lineBrushEnabled = false;
        randomLineBrushEnabled = false;
        randomCircleBrush = false;
        eraseEnabled = false;
        tunnelBrush = false;
    }

    /**
     * Enables the random-line brush variable and disables the other
     * related brush-variables.
     */
    public void randomLineBrushEnabled()
    {
        simpleBrushEnabled = false;
        lineBrushEnabled = false;
        randomLineBrushEnabled = true;
        randomCircleBrush = false;
        eraseEnabled = false;
        tunnelBrush = false;
    }

    /**
     * Enables the tunnel brush variable and disables the other
     * related brush-variables.
     */
    public void tunnelBrushEnabled()
    {
        tunnelBrush = true;
        simpleBrushEnabled = false;
        lineBrushEnabled = false;
        randomLineBrushEnabled = false;
        randomCircleBrush = false;
        eraseEnabled = false;
    }

    /**
     * Enables the eraser variable.
     */
    public void eraserEnabled()
    {
        eraseEnabled = true;
        simpleBrushEnabled = false;
        lineBrushEnabled = false;
        randomLineBrushEnabled = false;
        randomCircleBrush = false;
        tunnelBrush = false;
    }

    /**
     * Enables the random-circles brush variable and disables the other
     * related brush-variables.
     */
    public void randomCircleBrushEnabled()
    {
        randomCircleBrush = true;
        simpleBrushEnabled = false;
        lineBrushEnabled = false;
        randomLineBrushEnabled = false;
        eraseEnabled = false;
        tunnelBrush = false;
    }

    /**
     * Disables the eraser variable.
     */
    public void eraserDisabled()
    {
        eraseEnabled = false;
        simpleBrushEnabled = false;
        lineBrushEnabled = false;
        randomLineBrushEnabled = false;
    }

    /**
     * Draws a brush illustrating a real brush by drawing not straight lines.
     */
    public void lineBrush()
    {
        for(int i = 0; i < 20; i++)
        {
            int r = 10;
            stroke( red, green, blue );
            strokeWeight( 1 );
            line( mouseX + random( r ), mouseY + random( r ), pmouseX + random( r ), pmouseY + random( r ) );
        }
    }

    /**
     * Draws a line, wherever the mouse is.
     */
    public void simpleBrush()
    {

        stroke( red, green, blue );
        strokeWeight( 5 );
        line( mouseX, mouseY, pmouseX, pmouseY );


    }

    /**
     * Draws random circles with different colors wherever the mouse goes. If the speed of
     * the mouse increases, so does the size of the circles.
     */
    public void randomCirclesBrush()
    {
        float speed = abs( mouseX - pmouseX ) + abs( mouseY - pmouseY );
        stroke( 1 );
        strokeWeight( 1 );
        fill( random( 255 ), random( 255 ), random( 255 ), opacity );
        ellipse( mouseX, mouseY, speed, speed );
    }

    /**
     * Draws lines with different colors and lengths in every direction wherever
     * the mouse is.
     */
    public void randomLineBrush()
    {
        if(mousePressed == true)
        {
            strokeWeight( random( 10 ) );
            stroke( random( 255 ), random( 255 ), random( 255 ), opacity );
            line( mouseX, mouseY, random( 0, 1366 ), random( 0, 768 ) );
        }
    }

    /**
     * Draws squares with different sizes and colors, all over the canvas.
     * Illustrating a tv-program with no program.
     */
    public void squareBackground()
    {
        noStroke();
        for(int i = 0; i < 800; i = i + (int) random( 160, 210 ))
        {
            for(int j = 0; j < 1200; j = j + (int) random( 30, 40 ))
            {
                fill( random( 255 ), random( 255 ), random( 255 ), 210 );
                rect( j, i, 40, 210 );
            }
        }
    }

    /**
     * Enables the borders-variable and if the borders-variable is enabled
     * call the borders method.
     */
    public void enableBorders()
    {
        bordersEnabled = true;
        if(bordersEnabled == true)
        {
            borders();
        }
    }

    /**
     * Draws black border around the canvas.
     */
    public void borders()
    {
        stroke( 0 );
        strokeWeight( 20 );
        line( 0, 0, width, 0 );
        line( width, 0, width, height );
        line( 0, height, width, height );
        line( 0, 0, 0, height );
    }

    /**
     * Draws lines lines that points in center of canvas, wherever the mouse is.
     * Illustrating a tunnel-ish experience.
     */
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


    /**
     * Draws text on canvas. Choose the text you want to display and hoe many times.
     * The text will display in different sizes and colors.
     */
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
     * Undo/Redo class responsible for making the program undo or redo an action.
     */
    class UndoRedo
    {
        // Number of currently available undo and redo snapshots
        int undoSteps = 0, redoSteps = 0;
        ImgCollection images;

        /**
         * Constructing a UndoRedo object.
         *
         * @param stepsBackAndForth: How many steps back or forth it is possible to store.
         */
        UndoRedo(int stepsBackAndForth)
        {
            images = new ImgCollection( stepsBackAndForth );
        }

        /**
         * Takes a snapshot of the canvas to store.
         */
        public void takeSnapshot()
        {
            undoSteps = min( undoSteps + 1, images.amount - 1 );
            // each time we draw we disable redo
            redoSteps = 0;
            images.next();
            images.capture();
        }

        /**
         * Shows the previous drawing stored before the current drawing.
         */
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

        /**
         * Goes forth in the stored snapshots of canvas in the array and draws it on canvas.
         */
        public void redo()
        {
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
     * ImgCollection responsible for storing the snapshots taken of the canvas by the UndoRedo class.
     */
    class ImgCollection
    {
        int amount, current;
        PImage[] img;

        /**
         * Constructing a ImgCollection object.
         *
         * @param amountOfImages: The mount of images to store.
         */
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

        /**
         * Goes to the next picture stored in the array list.
         */
        void next()
        {
            current = ( current + 1 ) % amount;
        }

        /**
         * Goes to the previous picture stored in the array list.
         */
        void prev()
        {
            current = ( current - 1 + amount ) % amount;
        }

        /**
         * Takes a screenshot of the current version of canvas.
         */
        void capture()
        {
            img[ current ] = get();
        }

        /**
         * Show the current picture stored in the array.
         */
        void show()
        {
            image( img[ current ], 0, 0 );
        }
    }
}
