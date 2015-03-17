/**
 * Created by Ramin on 16.03.2015.
 */

import javafx.stage.FileChooser;
import processing.core.*;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


import javax.print.*;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RandomArt extends PApplet implements ActionListener
{
    PGraphics pg1;
    PGraphics pg2;
    PImage img;

    int st;

    public void setup()
    {

        size( 1000, 680 );

        background( 255 );

        noStroke();
    }

    @Override
    public void draw()
    {
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
        clear();
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
     * Print the following saved file
     */
    public void printFile()
    {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter( "Only JPG, GIF & png Images", "jpg", "gif", "png", "tif" );
        chooser.setFileFilter( filter );
        chooser.setDialogTitle( "Choose file to print" );

        PrinterJob pj = PrinterJob.getPrinterJob();
        InputStream in = null;
        PrintJobWatcher watcher = null;


        int returnVal = chooser.showOpenDialog( this );
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            if(pj.printDialog())
            {

                try
                {
                    in = new FileInputStream( new File( chooser.getSelectedFile().getPath() ) );
                    System.out.println( chooser.getSelectedFile().getPath() );

                    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                    AttributeSet attributeSet = new HashAttributeSet();
                    attributeSet.add( new PrinterName( "NPI8DA48A", null ) );
                    PrintService service = PrintServiceLookup.lookupDefaultPrintService();

                    DocPrintJob job = service.createPrintJob();
                    Doc doc = new SimpleDoc( in, flavor, null );
                    watcher = new PrintJobWatcher( job );

                    job.print( doc, null );
                } catch(FileNotFoundException e)
                {
                    e.printStackTrace();
                } catch(PrintException e)
                {
                    e.printStackTrace();
                }
            }
            watcher.waitForDone();


        /*JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter( "Only JPG, GIF & png Images", "jpg", "gif", "png", "tif" );
        chooser.setFileFilter( filter );
        chooser.setDialogTitle( "Save file" );

        PrinterJob pj = PrinterJob.getPrinterJob();


        int returnVal = chooser.showOpenDialog( this );
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            if(pj.printDialog())
            {
                try
                {
                    pj.setJobName( chooser.getSelectedFile().getPath() );
                    pj.getJobName();
                    pj.print();
                } catch(PrinterException exc)
                {
                    System.out.println( exc );
                }
            }
        }*/
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
            case "print":
                printFile();
        }
    }

    class PrintJobWatcher
    {
        // true iff it is safe to close the print job's input stream
        boolean done = false;

        PrintJobWatcher(DocPrintJob job)
        {
            // Add a listener to the print job
            job.addPrintJobListener( new PrintJobAdapter()
            {
                public void printJobCanceled(PrintJobEvent pje)
                {
                    allDone();
                }

                public void printJobCompleted(PrintJobEvent pje)
                {
                    allDone();
                }

                public void printJobFailed(PrintJobEvent pje)
                {
                    allDone();
                }

                public void printJobNoMoreEvents(PrintJobEvent pje)
                {
                    allDone();
                }

                void allDone()
                {
                    synchronized(PrintJobWatcher.this)
                    {
                        done = true;
                        PrintJobWatcher.this.notify();
                    }
                }
            } );
        }

        public synchronized void waitForDone()
        {
            try
            {
                while(!done)
                {
                    wait();
                }
            } catch(InterruptedException e)
            {
            }
        }
    }
}
