import javafx.geometry.HorizontalDirection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
 import javax.swing.JFrame;
import javax.swing.JSlider;

/**
 * Created by Ramin, Per-Olav, Ole-Martin and Knut Olav on 16.03.2015.
 */
public class DisplayFrame extends JFrame
{
    // Initializing button variables
    public JButton eraserButton;
    // Initializing variables
    private RandomArt randomArtProcessing;
    private JPanel processingPanel;
    private JButton triangleButton;
    private JButton squareButton;
    private JButton circleButton;
    private JButton borderButton;
    private JButton colorPicker;
    private JButton resetButton;
    private JButton tunnelBrush;
    private JButton simpleBrush;
    private JButton lineRoughBrush;
    private JButton randomCirclesBrush;
    private JButton randomLineBrush;
    private JButton squareBackground;
    private JButton randomTextButton;
    private JButton undoButton;
    private JButton redoButton;

    // Initializing menu variables
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem save;
    private JMenuItem openFile;
    private JMenuItem saveHighres;

    // Filter menu
    private JMenu filterMenu;
    private JMenuItem blur;
    private JMenuItem threshold;
    private JMenuItem grayScale;
    private JMenuItem invert;
    private JMenuItem posterize;
    private JMenuItem erode;
    private JMenuItem dialate;

    // Shortcuts file menu
    private KeyStroke ctrlOKeyStroke = null;
    private KeyStroke ctrlSKeyStroke = null;
    private KeyStroke ctrlShiftSKeyStroke = null;
    private KeyStroke ctrlZKeystroke = null;
    private KeyStroke ctrlYKeyStroke = null;

    // Shortcuts filter menu
    private KeyStroke ctrl1 = null;
    private KeyStroke ctrl2 = null;
    private KeyStroke ctrl3 = null;
    private KeyStroke ctrl4 = null;
    private KeyStroke ctrl5 = null;
    private KeyStroke ctrl6 = null;
    private KeyStroke ctrl7 = null;

    // Labels
    private JLabel brushes;
    private JLabel shapes;
    private JLabel backgrounds;

    private JLabel backgroundLabel;


    //Display frame
    public DisplayFrame(String title) throws HeadlessException
    {
        this.setTitle( title );
        initiateFrame();
        initiateComponents();

        addComponents();

        setLayout();

        addActionListeners();
    }
    // Main method
    public static void main(String[] args)
    {
        DisplayFrame displayFrame = new DisplayFrame("Random Art Generator");
    }

    /**
     * Initiate the Frame configurations
     */
    private void initiateFrame()
    {
        // Initiate processing items
        randomArtProcessing = new RandomArt();
        randomArtProcessing.init();
        processingPanel = new JPanel();


        this.setSize( randomArtProcessing.width, randomArtProcessing.height );
        this.setMinimumSize( new Dimension( 1366, 830 ) );

        // Unused if. For later development.
        if(this != null)
        {
            this.setResizable( false );
        }
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

        // Initiate Menu items
        menuBar = new JMenuBar();
        menuBar.setSize( getWidth(), 100 );
        fileMenu = new JMenu( "File" );
        filterMenu = new JMenu( "Filters" );

        this.setJMenuBar( menuBar );
        menuBar.add(fileMenu);
        menuBar.add( filterMenu );

        setVisible( true );
    }

    /**
     * Initiate the field components
     */
    private void initiateComponents()
    {
        // Initiate shape buttons
        triangleButton = new JButton( "Triangle", new ImageIcon( "Buttons/triangle.png" ) );
        squareButton = new JButton( "", new ImageIcon( "Buttons/rectangle.png" ) );
        circleButton = new JButton( "", new ImageIcon( "Buttons/alt_circle.jpg" ) );

        resetButton = new JButton( "Start over" );
        colorPicker = new JButton( "Choose background color" );
        borderButton = new JButton( "Enable borders" );
        eraserButton = new JButton( "Eraser" );

        save = new JMenuItem( "Save" );
        ctrlSKeyStroke = KeyStroke.getKeyStroke( "control S" );
        save.setAccelerator( ctrlSKeyStroke );

        undoButton = new JButton( "Undo" );
        ctrlZKeystroke = KeyStroke.getKeyStroke( "control Z");

        redoButton = new JButton( "Redo" );
        ctrlYKeyStroke = KeyStroke.getKeyStroke( "control Y");
        //redoButton.setAccelerator(ctrlYKeyStroke);

        //Brush buttons
        ImageIcon imageSimpleBrush = new ImageIcon("Buttons/plainBrush.png");
        simpleBrush = new JButton( "", imageSimpleBrush);

        //Brush generating lines from center to mouse position
        ImageIcon imageTunnelBrush = new ImageIcon( "Buttons/tunnelBrush.jpg" );
        tunnelBrush = new JButton( "Tunnel brush", imageTunnelBrush );

        //Generating a line of smaller lines.
        ImageIcon imageRoughBrush = new ImageIcon ("Buttons/roughBrush.png");
        lineRoughBrush = new JButton( "", imageRoughBrush );

        //Brush making circles at mouse position.
        ImageIcon imageCircleBrush = new ImageIcon( "Buttons/randomCircleBrush.png" );
        randomCirclesBrush = new JButton( "", imageCircleBrush );

        //Random line generating brush.
        ImageIcon imageRandomLineBrushBrush = new ImageIcon( "Buttons/lineBrush.png" );
        randomLineBrush = new JButton ("", imageRandomLineBrushBrush);

        //Button making background of squares.
        ImageIcon imagesquareBackground = new ImageIcon ("Buttons/squareBackground.png");
        squareBackground = new JButton( "", imagesquareBackground);

        // Button making text at random places
        ImageIcon imageRandomText = new ImageIcon( "Buttons/randomText.jpg" );
        randomTextButton = new JButton( "text", imageRandomText );

        //Menubar buttons.
        fileMenu.add(save);
        fileMenu.setMnemonic( KeyEvent.VK_F );

        openFile = new JMenuItem( "Open file" );
        ctrlOKeyStroke = KeyStroke.getKeyStroke( "control O" );
        openFile.setAccelerator( ctrlOKeyStroke );

        fileMenu.add( openFile );

        saveHighres = new JMenuItem( "Save with different resolution" );
        ctrlShiftSKeyStroke = KeyStroke.getKeyStroke( "control shift S" );
        saveHighres.setAccelerator(ctrlShiftSKeyStroke);
        fileMenu.add( saveHighres );


        // Filters.
        blur = new JMenuItem( "Blur" );
        ctrl1 = KeyStroke.getKeyStroke( "control 1" );
        blur.setAccelerator(ctrl1);

        threshold = new JMenuItem( "Threshold" );
        ctrl2 = KeyStroke.getKeyStroke( "control 2" );
        threshold.setAccelerator( ctrl2 );

        grayScale = new JMenuItem( "Gray scale" );
        ctrl3 = KeyStroke.getKeyStroke( "control 3" );
        grayScale.setAccelerator( ctrl3 );

        invert = new JMenuItem( "Invert colors" );
        ctrl4 = KeyStroke.getKeyStroke( "control 4" );
        invert.setAccelerator( ctrl4 );

        posterize = new JMenuItem( "Posterize" );
        ctrl5 = KeyStroke.getKeyStroke( "control 5" );
        posterize.setAccelerator( ctrl5 );

        erode = new JMenuItem( "Erode" );
        ctrl6 = KeyStroke.getKeyStroke( "control 6" );
        erode.setAccelerator( ctrl6 );

        dialate = new JMenuItem( "Dialate" );
        ctrl7 = KeyStroke.getKeyStroke( "control 7" );
        dialate.setAccelerator( ctrl7 );

        // Initiate Labels
        brushes = new JLabel( "Brushes" );
        brushes.setForeground( new Color( 0, 0, 0 ) );
        brushes.setFont(new Font(null, 2, 20));

        shapes = new JLabel( "Shapes" );
        shapes.setForeground( new Color( 0, 0, 0 ) );
        shapes.setFont( new Font( null, 2, 20 ) );

        backgrounds = new JLabel( "Background Options");
        backgrounds.setForeground( new Color( 0, 0, 0));
        backgrounds.setFont( new Font( null, 2, 20));

        backgroundLabel = new JLabel( new ImageIcon( "Application pics/AppBG3.jpg" ) );
    }


    /**
     * Add the components to the frame
     */
    private void addComponents()
    {
        this.add( processingPanel );

        this.add( undoButton );
        this.add( redoButton );

        this.add( triangleButton );
        this.add( squareButton );
        this.add( circleButton );

        this.add( resetButton );
        this.add( colorPicker );
        this.add( borderButton );
        this.add( eraserButton );

        //Brush buttons
        this.add ( simpleBrush );
        this.add( lineRoughBrush );
        this.add( randomCirclesBrush );
        this.add ( randomLineBrush );
        this.add( tunnelBrush );
        this.add( randomTextButton );

        //something special
        this.add (squareBackground);
        this.add(backgrounds);
        
        // Filter Menu
        filterMenu.add( blur );
        filterMenu.add( threshold );
        filterMenu.add( grayScale );
        filterMenu.add( invert );
        filterMenu.add(posterize);
        filterMenu.add(erode);
        filterMenu.add(dialate);


        this.add(brushes);
        this.add( shapes );
        this.add( backgroundLabel );

        processingPanel.add( randomArtProcessing );
    }


    /**
     * Set the layout for the components
     */
    private void setLayout()
    {
        setLayout( null );
        backgroundLabel.setBounds( 0, 0, getWidth(), getHeight() );
        processingPanel.setBounds( 350, 45, 1009, 722 );

        triangleButton.setBounds( 10, 280, 100, 100 );
        //triangleButton.setBackground( new Color( 232, 177, 141 ) );

        squareButton.setBounds( 120, 280, 100, 100 );
        //squareButton.setBackground( new Color( 232, 177, 141 ) );

        circleButton.setBounds( 230, 280, 100, 100 );
        //circleButton.setBackground( new Color( 232, 177, 141 ) );

        colorPicker.setBounds( 10, 100, 315, 25 );
        //colorPicker.setBackground( new Color( 232, 177, 141 ) );

        resetButton.setBounds( 10, getHeight() - 88, 155, 25 );

        borderButton.setBounds( 10,135, 315, 25 );

        eraserButton.setBounds( 170, getHeight() - 88, 155, 25 );
        //borderButton.setBackground( new Color( 232, 177, 141 ) );

        //Brush buttons
        brushes.setBounds( 10, 410, 150, 48 );
        brushes.setBackground( new Color( 255, 255, 255 ) );

        simpleBrush.setBounds( 10, 450, 150, 48 );
        simpleBrush.setBackground( new Color( 255, 255, 255 ) );

        lineRoughBrush.setBounds( 170, 450, 150, 48 );
        lineRoughBrush.setBackground( new Color( 255, 255, 255 ) );

        tunnelBrush.setBounds( 170, 505, 150, 48 );
        tunnelBrush.setBackground( new Color( 255, 255, 255 ) );

        randomCirclesBrush.setBounds( 10, 560, 150, 48 );
        randomCirclesBrush.setBackground( new Color( 255, 255, 255 ) );

        randomTextButton.setBounds( 170, 560, 150, 48 );
        randomTextButton.setBackground( new Color( 255, 255, 255 ) );

        randomLineBrush.setBounds( 10, 505, 150, 48 );
        randomLineBrush.setBackground(new Color(255, 255, 255));

        //Something special
        squareBackground.setBounds( 10, 170, 315, 48 );
        squareBackground.setBackground( new Color( 232, 177, 141 ) );

        backgrounds.setBounds(10,58,240,48);
        backgrounds.setBackground(new Color(237, 177, 141));

        shapes.setBounds ( 10, 240, 300,50);

        undoButton.setBounds( 0, 0, 100, 50 );
        redoButton.setBounds( 110, 0, 100, 50 );



    }


    //Adding action listeners.
    private void addActionListeners()
    {
        triangleButton.addActionListener( randomArtProcessing );
        triangleButton.setActionCommand( "triangles" );
        triangleButton.setToolTipText( "Draw triangles towards center" );

        squareButton.addActionListener( randomArtProcessing );
        squareButton.setActionCommand( "squares" );
        squareButton.setToolTipText( "Draws squares with random positions and colors on screen" );
        squareButton.setIconTextGap( 10 );

        circleButton.addActionListener( randomArtProcessing );
        circleButton.setActionCommand( "circles" );
        circleButton.setToolTipText( "Draw circles in different location and colors" );

        resetButton.addActionListener( randomArtProcessing );
        resetButton.setActionCommand( "clear" );
        resetButton.setToolTipText( "Resets the drawing board" );

        eraserButton.addActionListener( randomArtProcessing );
        eraserButton.setActionCommand( "eraserEnabled" );
        eraserButton.setToolTipText( "Erase wherever on canvas" );

        colorPicker.addActionListener(randomArtProcessing);
        colorPicker.setActionCommand( "color" );
        colorPicker.setToolTipText( "Choose background color" );

        borderButton.addActionListener(randomArtProcessing);
        borderButton.setActionCommand( "border" );
        borderButton.setToolTipText( "Adds border to drawing board" );


        //Brush buttons
        simpleBrush.addActionListener( randomArtProcessing );
        simpleBrush.setActionCommand( "simpleBrushEnabled" );
        simpleBrush.setToolTipText( "Simple line" );

        lineRoughBrush.addActionListener( randomArtProcessing );
        lineRoughBrush.setActionCommand( "lineBrushEnabled" );
        lineRoughBrush.setToolTipText( "Rough line" );

        randomCirclesBrush.addActionListener( randomArtProcessing );
        randomCirclesBrush.setActionCommand( "randomCirclesBrushEnabled" );
        randomCirclesBrush.setToolTipText( "Random circles in different sizes and colors" );

        randomLineBrush.addActionListener( randomArtProcessing );
        randomLineBrush.setActionCommand( "randomLineBrushEnabled" );
        randomLineBrush.setToolTipText( "Random lines in different lenghts, directions, and colors" );

        tunnelBrush.addActionListener( randomArtProcessing );
        tunnelBrush.setActionCommand( "tunnelBrushEnabled" );
        tunnelBrush.setToolTipText( "Draws line where ever the mouse is towards center" );

        randomTextButton.addActionListener( randomArtProcessing );
        randomTextButton.setActionCommand( "randomText" );
        randomTextButton.setToolTipText( "Generate custom text in different locations and colors" );

        //SOMETHING SPECIAL
        squareBackground.addActionListener( randomArtProcessing );
        squareBackground.setActionCommand( "squareBackground" );
        squareBackground.setToolTipText( "Draws rectangles in different sizes and colors" );

        save.addActionListener( randomArtProcessing );
        save.setActionCommand( "save" );

        openFile.addActionListener( randomArtProcessing );
        openFile.setActionCommand( "openFile" );

        undoButton.addActionListener( randomArtProcessing );
        undoButton.setActionCommand( "undo" );

        redoButton.addActionListener( randomArtProcessing );
        redoButton.setActionCommand( "redo" );



        // Filter Memu
        blur.addActionListener( randomArtProcessing );
        blur.setActionCommand( "blur" );

        threshold.addActionListener( randomArtProcessing );
        threshold.setActionCommand( "threshold" );

        grayScale.addActionListener( randomArtProcessing );
        grayScale.setActionCommand( "gray" );

        invert.addActionListener( randomArtProcessing );
        invert.setActionCommand( "invert" );

        posterize.addActionListener( randomArtProcessing );
        posterize.setActionCommand( "posterize" );

        erode.addActionListener( randomArtProcessing );
        erode.setActionCommand( "erode" );

        dialate.addActionListener( randomArtProcessing );
        dialate.setActionCommand( "dialate" );
    
        saveHighres.addActionListener( randomArtProcessing );
        saveHighres.setActionCommand( "saveHighRes" );
    }
}


