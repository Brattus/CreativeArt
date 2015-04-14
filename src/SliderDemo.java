/**
 * Created by Per-Olav on 14.04.2015.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * SliderDemo.java requires all the files in the images/doggy
 * directory.
 */
public class SliderDemo extends JPanel
        implements ActionListener,
        WindowListener,
        ChangeListener {
    //Set up animation parameters.
    static final int OPACITY_MIN = 0;
    static final int OPACITY_MAX = 255;
    static final int OPACITY_INIT = 170;    //initial frames per second
    int opacityNumber = 0;
    int NUM_OPACITY = 14;

    int delay;
    Timer timer;
    boolean frozen = false;

    //This label uses ImageIcon to show the doggy pictures.
    JLabel picture;

    public SliderDemo() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //Create the label.
        JLabel sliderLabel = new JLabel("Opacity", JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Create the slider.
        JSlider opacityNum = new JSlider(JSlider.HORIZONTAL,
                OPACITY_MIN, OPACITY_MAX, OPACITY_INIT);


        opacityNum.addChangeListener(this);

        //Turn on labels at major tick marks.

        opacityNum.setMajorTickSpacing(15);
        opacityNum.setMinorTickSpacing(5);
        opacityNum.setPaintTicks(true);
        opacityNum.setPaintLabels(true);
        opacityNum.setBorder(
                BorderFactory.createEmptyBorder(0, 0, 10, 0));
        Font font = new Font("Serif", Font.ITALIC, 15);
        opacityNum.setFont(font);


        //Put everything together.
        add(sliderLabel);
        add(opacityNum);
        //setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        //Set up a timer that calls this object's action handler.

    }

    /** Add a listener for window events. */
    void addWindowListener(Window w) {
        w.addWindowListener(this);
    }

    //React to window events.
    public void windowIconified(WindowEvent e) {
        stopAnimation();
    }
    public void windowDeiconified(WindowEvent e) {
        startAnimation();
    }
    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}

    /** Listen to the slider. */
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int fps = (int)source.getValue();
            if (fps == 0) {
                if (!frozen) stopAnimation();
            } else {
                delay = 1000 / fps;
                timer.setDelay(delay);
                timer.setInitialDelay(delay * 10);
                if (frozen) startAnimation();
            }
        }
    }

    public void startAnimation() {
        //Start (or restart) animating!
        timer.start();
        frozen = false;
    }

    public void stopAnimation() {
        //Stop the animating thread.
        timer.stop();
        frozen = true;
    }

    //Called when the Timer fires.
    public void actionPerformed(ActionEvent e) {
        //Advance the animation frame.
        if (opacityNumber == (NUM_OPACITY - 1)) {
            opacityNumber = 0;
        } else {
            opacityNumber++;
        }

        if ( opacityNumber ==(NUM_OPACITY - 1)
                || opacityNumber ==(NUM_OPACITY /2 - 1) ) {
            timer.restart();
        }
    }

    /** Update the label to display the image for the current frame. */


    /** Returns an ImageIcon, or null if the path was invalid. */


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SliderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SliderDemo animator = new SliderDemo();

        //Add content to the window.
        frame.add(animator, BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        animator.startAnimation();
    }

    public static void main(String[] args) {
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);


        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}