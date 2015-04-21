/**
 * Created by Ramin, Per-Olav, Ole-Martin and Knut Olav on 16.03.2015.
 *
 * DisplayFrame is responsible for the graphical user interface of the application.
 * It's responsible for he buttons, layout and listeners.
 * This class is an extension of JFrame.
 */

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.swing.JFrame;


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

    // Initializing menu variables
    private JMenuBar menuBar;
    private JMenu fileMenu;

    // Initializing File menu variables
    private JMenuItem save;
    private JMenuItem openFile;
    private JMenuItem saveHighres;

    // Initializing filter menu variables
    private JMenu filterMenu;
    private JMenuItem blur;
    private JMenuItem threshold;
    private JMenuItem grayScale;
    private JMenuItem invert;
    private JMenuItem posterize;
    private JMenuItem erode;
    private JMenuItem dialate;

    // Initializing help menu variables
    private JMenu helpMenu;
    private JMenuItem about;
    private JMenuItem sysDocumentation;

    // Initializing edit menu variables
    private JMenu editMenu;
    private JMenuItem undo;
    private JMenuItem redo;

    // Shortcuts for file menu
    private KeyStroke ctrlOKeyStroke = null;
    private KeyStroke ctrlSKeyStroke = null;
    private KeyStroke ctrlShiftSKeyStroke = null;

    // Shortcuts for edit menu
    private KeyStroke ctrlZKeystroke = null;
    private KeyStroke ctrlYKeyStroke = null;

    // Shortcuts for filter menu
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

    // Label used to set background picture in JFrame
    private JLabel backgroundLabel;


    /**
     * Constructs a new DisplayFrame object.
     *
     * @param title of the window.
     */
    public DisplayFrame(String title)
    {
        this.setTitle( title );
        initiateFrame();
        initiateComponents();

        addComponents();

        setLayout();

        addActionListeners();

    }

    /**
     * Initiate the Frame configurations
     */
    protected void initiateFrame()
    {
        // Initiate processing items
        randomArtProcessing = new RandomArt();
        randomArtProcessing.init();
        processingPanel = new JPanel();

        this.setSize( randomArtProcessing.width, randomArtProcessing.height );
        this.setMinimumSize( new Dimension( 1366, 835 ) );

        // Unused if. For later development.
        if(this != null)
        {
            this.setResizable( false );
        }
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

        // Initiate the Menu bar
        menuBar = new JMenuBar();

        this.setJMenuBar( menuBar );

        setVisible( true );
    }

    /**
     * Initiating the field components.
     */
    protected void initiateComponents()
    {
        ImageIcon appBG = new ImageIcon( "Application pics/background.jpg" );
        backgroundLabel = new JLabel( appBG );

        // Initiate shape buttons
        triangleButton = new JButton( "", new ImageIcon( "Buttons/triangles.png" ));
        squareButton = new JButton( "", new ImageIcon( "Buttons/squares.png" ) );
        circleButton = new JButton( "", new ImageIcon( "Buttons/circles.png" ) );

        resetButton = new JButton( "Start over" );
        colorPicker = new JButton( "Choose background color" );
        borderButton = new JButton( "Enable borders" );
        eraserButton = new JButton( "Eraser" );

        // File menu
        fileMenu = new JMenu( "File" );

        save = new JMenuItem( "Save" );
        ctrlSKeyStroke = KeyStroke.getKeyStroke( "control S" );
        save.setAccelerator( ctrlSKeyStroke );


        openFile = new JMenuItem( "Open file" );
        ctrlOKeyStroke = KeyStroke.getKeyStroke( "control O" );
        openFile.setAccelerator( ctrlOKeyStroke );


        saveHighres = new JMenuItem( "Save with custom resolution" );
        ctrlShiftSKeyStroke = KeyStroke.getKeyStroke( "control shift S" );
        saveHighres.setAccelerator( ctrlShiftSKeyStroke );

        // Edit menu
        editMenu = new JMenu( "Edit" );

        undo = new JMenuItem( "Undo" );
        ctrlZKeystroke = KeyStroke.getKeyStroke( "control Z" );
        undo.setAccelerator( ctrlZKeystroke );

        redo = new JMenuItem( "Redo" );
        ctrlYKeyStroke = KeyStroke.getKeyStroke( "control Y" );
        redo.setAccelerator( ctrlYKeyStroke );


        // Help menu
        helpMenu = new JMenu( "Help" );
        about = new JMenuItem( "About" );
        sysDocumentation = new JMenuItem( "Documentation" );


        // Filters menu.
        filterMenu = new JMenu( "Filters" );

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
        brushes.setForeground( new Color( 255, 255, 255 ) );
        brushes.setFont(new Font(null, 2, 20));

        shapes = new JLabel( "Shapes" );
        shapes.setForeground( new Color( 255, 255, 255 ) );
        shapes.setFont( new Font( null, 2, 20 ) );

        backgrounds = new JLabel( "Background options" );
        backgrounds.setForeground( new Color( 255, 255, 255 ) );
        backgrounds.setFont( new Font( null, 2, 20 ) );

        //Brush buttons
        simpleBrush = new JButton( "", new ImageIcon( "Buttons/simpleBrush.png" ) );

        //Brush generating lines from center to mouse position
        tunnelBrush = new JButton( "Tunnel brush", new ImageIcon( "Buttons/tunnelBrush.jpg" ) );

        //Generating a line of smaller lines.
        lineRoughBrush = new JButton( "", new ImageIcon( "Buttons/roughBrush.png" ) );

        //Brush making circles at mouse position.
        randomCirclesBrush = new JButton( "", new ImageIcon( "Buttons/randomCircleBrush.png" ) );

        //Random line generating brush.
        randomLineBrush = new JButton( "", new ImageIcon( "Buttons/lineBrush.png" ) );

        //Button making background of squares.
        ImageIcon imagesquareBackground = new ImageIcon( "Buttons/squareBackground.png" );
        squareBackground = new JButton( "", imagesquareBackground );


        // Button making text at random places
        ImageIcon imageRandomText = new ImageIcon( "Buttons/randomText.jpg" );
        randomTextButton = new JButton( "text", imageRandomText );
    }


    /**
     * Adding the field components to the JFrame
     */
    protected void addComponents()
    {
        this.add( processingPanel );
        processingPanel.add( randomArtProcessing );

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
        this.add (squareBackground);
        this.add( backgrounds );

        // Adding labels
        this.add( brushes );
        this.add( shapes );
        this.add( backgroundLabel );

        // File Menu
        menuBar.add( fileMenu );
        fileMenu.add( saveHighres );
        fileMenu.add( save );
        fileMenu.add( openFile );

        // Edit menu
        menuBar.add( editMenu );
        editMenu.add( undo );
        editMenu.add( redo );

        // Filter Menu
        menuBar.add( filterMenu );
        filterMenu.add( blur );
        filterMenu.add( threshold );
        filterMenu.add( grayScale );
        filterMenu.add( invert );
        filterMenu.add(posterize);
        filterMenu.add(erode);
        filterMenu.add(dialate);

        // Help menu
        menuBar.add( helpMenu );
        helpMenu.add( about );
        helpMenu.add( sysDocumentation );

        this.add( backgroundLabel );
    }


    /**
     * Sets the static position and size of the components within the JFrame.
     */
    protected void setLayout()
    {
        setLayout( null );

        backgroundLabel.setBounds( 0, 0, getWidth(), getHeight() );

        processingPanel.setBounds( 335, 0, 1024, 773 );

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
        brushes.setBounds(10, 410, 150, 48);
        brushes.setBackground(new Color(255, 255, 255));

        simpleBrush.setBounds(10, 450, 150, 48);
        simpleBrush.setBackground(new Color(255, 255, 255));

        lineRoughBrush.setBounds(170, 450, 150, 48);
        lineRoughBrush.setBackground(new Color(255, 255, 255));

        tunnelBrush.setBounds(170, 505, 150, 48);
        tunnelBrush.setBackground(new Color(255, 255, 255));

        randomCirclesBrush.setBounds(10, 560, 150, 48);
        randomCirclesBrush.setBackground(new Color(255, 255, 255));

        randomTextButton.setBounds(170, 560, 150, 48);
        randomTextButton.setBackground(new Color(255, 255, 255));

        randomLineBrush.setBounds(10, 505, 150, 48);
        randomLineBrush.setBackground(new Color(255, 255, 255));

        //Something special
        squareBackground.setBounds(10, 170, 315, 48);
        squareBackground.setBackground(new Color(232, 177, 141));


        backgrounds.setBounds(10, 58, 240, 48);
        backgrounds.setBackground(new Color(237, 177, 141));

        shapes.setBounds ( 10, 240, 300,50);
    }


    /**
     * Adding action listeners and action commands to the following field components in JFrame
     */
    protected void addActionListeners()
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

        // Edit menu
        undo.addActionListener( randomArtProcessing );
        undo.setActionCommand( "undo" );

        redo.addActionListener( randomArtProcessing );
        redo.setActionCommand( "redo" );

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

        // Help menu
        about.addActionListener( randomArtProcessing );
        about.setActionCommand( "about" );

        sysDocumentation.addActionListener( randomArtProcessing );
        sysDocumentation.setActionCommand( "sysdoc" );
    }
}


