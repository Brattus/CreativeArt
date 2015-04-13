import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by RaminErDigg on 16.03.2015.
 */
public class DisplayFrame extends JFrame
{
    private RandomArt randomArtProcessing;

    private JPanel processingPanel;

    // Shape Buttons
    private JButton triangleButton;
    private JButton squareButton;
    private JButton circleButton;

    private JButton borderButton;
    private JButton colorPicker;
    private JButton resetButton;

    // Filter Buttons
    private JButton blurButton;
    private JButton thresholdButton;
    private JButton grayScaleButton;
    private JButton invertButton;
    private JButton posterizeButton;
    private JButton erodeButton;
    private JButton dilateButton;

    //Brush buttons
    private JButton simpleBrush;
    private JButton lineBrush;
    private JButton randomBrush;


    // Menus
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

    // Shortcuts
    private KeyStroke ctrlPKeyStroke = null;
    private KeyStroke ctrlSKeyStroke = null;

    // Labels
    private JLabel title;
    private JLabel filters;
    private JLabel shapes;


    /**
     * Construct a new DisplayFrame
     *
     * @throws HeadlessException
     */
    public DisplayFrame(String title) throws HeadlessException
    {
        this.setTitle( title );
        initiateFrame();
        initiateComponents();

        addComponents();

        setLayout();

        addActionListeners();


    }

    public static void main(String[] args)
    {
        DisplayFrame displayFrame = new DisplayFrame("Random Art Generator");
    }

    /**
     * Initiate the Frame configurations
     */
    private void initiateFrame()
    {
        this.setSize(1366 , 768 );
        this.setMinimumSize( new Dimension( 1366, 768 ) );

        this.setBackground( new Color( 116, 228, 74 ) );


        if(this != null)
        {
            this.setResizable( false );
        }
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

        // Initiate Menu items
        menuBar = new JMenuBar();
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
        // Initiate processing items
        randomArtProcessing = new RandomArt();
        randomArtProcessing.init();
        processingPanel = new JPanel();

        // Initiate shape buttons
        ImageIcon imageTriangle = new ImageIcon("Buttons/triangle.png");
        triangleButton = new JButton( "", imageTriangle );

        ImageIcon imageSquare = new ImageIcon("Buttons/rectangle.png");
        squareButton = new JButton("", imageSquare);

        ImageIcon imageCircle = new ImageIcon("Buttons/circle.png");
        circleButton = new JButton( "" , imageCircle);

        resetButton = new JButton( "Clear Canvas" );
        colorPicker = new JButton( "Choose background color" );
        borderButton = new JButton( "Enable/Disable borders" );

        // Initiate filter buttons
        blurButton = new JButton( "Blur" );
        thresholdButton = new JButton( "Threshold" );
        grayScaleButton = new JButton( "Gray Scale" );
        invertButton = new JButton( "Invert" );
        posterizeButton = new JButton( "Posterize" );
        erodeButton = new JButton( "Erode" );
        dilateButton = new JButton( "Dilate" );

        save = new JMenuItem( "Save" );
        ctrlSKeyStroke = KeyStroke.getKeyStroke( "control S" );
        save.setAccelerator( ctrlSKeyStroke );

        //Brush buttons
        ImageIcon imageSimpleBrush = new ImageIcon("Buttons/plainBrush.png");
        simpleBrush = new JButton( "", imageSimpleBrush);

        ImageIcon imageRoughBrush = new ImageIcon ("Buttons/roughBrush.png");
        lineBrush = new JButton( "", imageRoughBrush);

        ImageIcon imageCircleBrush = new ImageIcon ("Buttons/circleBrush.png");
        randomBrush = new JButton( "", imageCircleBrush);

        fileMenu.add( save );
        fileMenu.setMnemonic( KeyEvent.VK_F );
        
        openFile = new JMenuItem( "Open file" );
        ctrlPKeyStroke = KeyStroke.getKeyStroke( "control P" );
        openFile.setAccelerator( ctrlPKeyStroke );

        fileMenu.add( openFile );

        saveHighres = new JMenuItem( "Save with different resolution" );
        fileMenu.add( saveHighres );

        // Filter
        blur = new JMenuItem( "Blur" );
        threshold = new JMenuItem( "Threshold" );
        grayScale = new JMenuItem( "Gray scale" );
        invert = new JMenuItem( "Invert colors" );
        posterize = new JMenuItem( "Posterize" );
        erode = new JMenuItem( "Erode" );
        dialate = new JMenuItem( "Dialate" );


        // Initiate Label
        title = new JLabel( "Random Art Generator" );
        title.setForeground( new Color( 0, 0, 0 ) );
        title.setFont( new Font( null, 2, 25 ) );

        filters = new JLabel( "Brushes" );
        filters.setForeground( new Color( 0, 0, 0 ) );
        filters.setFont( new Font( null, 2, 20 ) );

        shapes = new JLabel( "Random shapes" );
        shapes.setForeground( new Color( 0, 0, 0 ) );
        shapes.setFont( new Font( null, 2, 20 ) );
    }


    /**
     * Add the components to the frame
     */
    private void addComponents()
    {
        this.add( processingPanel );

        this.add( triangleButton );
        this.add( squareButton );
        this.add( circleButton );

        this.add( resetButton );
        this.add( colorPicker );
        this.add( borderButton );

        // Add the filter buttons to the frame
        this.add( blurButton );
        this.add( thresholdButton );
        this.add( grayScaleButton );
        this.add( invertButton );
        this.add( posterizeButton );
        this.add( erodeButton );
        this.add( dilateButton );

        //Brush buttons
        this.add ( simpleBrush );
        this.add ( lineBrush );
        this.add ( randomBrush );
        
        // Filter Menu
        filterMenu.add( blur );
        filterMenu.add( threshold );
        filterMenu.add( grayScale );
        filterMenu.add( invert );
        filterMenu.add(posterize);
        filterMenu.add(erode);
        filterMenu.add(dialate);

        this.add( title );
        this.add( filters );
        this.add( shapes );


        processingPanel.add( randomArtProcessing );
    }


    /**
     * Set the layout for the components
     */
    private void setLayout()
    {
        setLayout( null );

        processingPanel.setBounds( getWidth() - 1030, 10, 1000, getHeight() - 500 );

        triangleButton.setBounds( 10, 250, 100, 100 );
        //triangleButton.setBackground( new Color( 232, 177, 141 ) );

        squareButton.setBounds( 120, 250, 100, 100 );
        //squareButton.setBackground( new Color( 232, 177, 141 ) );

        circleButton.setBounds( 230, 250, 100, 100 );
        //circleButton.setBackground( new Color( 232, 177, 141 ) );

        colorPicker.setBounds( 10, 100, 315, 25 );
        colorPicker.setBackground( new Color( 232, 177, 141 ) );

        resetButton.setBounds( 10, getHeight() - 100, 150, 25 );

        borderButton.setBounds( 10,135, 315, 25 );
        borderButton.setBackground( new Color( 232, 177, 141 ) );
/*
        blurButton.setBounds( 10, 250, 100, 25 );
        blurButton.setBackground( new Color( 0, 255, 0 ) );

        thresholdButton.setBounds( 10, 280, 100, 25 );
        thresholdButton.setBackground( new Color( 0, 255, 0 ) );

        grayScaleButton.setBounds( 10, 310, 100, 25 );
        grayScaleButton.setBackground( new Color( 0, 255, 0 ) );

        invertButton.setBounds( 10, 340, 100, 25 );
        invertButton.setBackground( new Color( 0, 255, 0 ) );

        posterizeButton.setBounds( 10, 370, 100, 25 );
        posterizeButton.setBackground( new Color( 0, 255, 0 ) );

        erodeButton.setBounds( 10, 400, 100, 25 );
        erodeButton.setBackground( new Color( 0, 255, 0 ) );

        dilateButton.setBounds( 10, 430, 100, 25 );
        dilateButton.setBackground( new Color( 0, 255, 0 ) );
*/
        //Brush buttons
        simpleBrush.setBounds(10, 500, 240, 48);
        simpleBrush.setBackground( new Color( 232, 177, 141));

        lineBrush.setBounds(10, 550, 240, 48);
        lineBrush.setBackground(new Color(232, 177, 141));

        randomBrush.setBounds(10, 600, 240, 48);
        randomBrush.setBackground(new Color(232, 177, 141));


        title.setBounds( 10, 10, 300, 100 );
        filters.setBounds( 10, 460, 100, 50 );
        shapes.setBounds ( 10, 210, 300,50);
    }

    private void addActionListeners()
    {
        triangleButton.addActionListener( randomArtProcessing );
        triangleButton.setActionCommand( "triangles" );

        squareButton.addActionListener( randomArtProcessing );
        squareButton.setActionCommand( "squares" );

        circleButton.addActionListener( randomArtProcessing );
        circleButton.setActionCommand( "circles" );

        resetButton.addActionListener( randomArtProcessing );
        resetButton.setActionCommand( "clear" );

        colorPicker.addActionListener( randomArtProcessing );
        colorPicker.setActionCommand( "color" );

        borderButton.addActionListener( randomArtProcessing );
        borderButton.setActionCommand( "border" );


        blurButton.addActionListener( randomArtProcessing );
        blurButton.setActionCommand( "blur" );

        thresholdButton.addActionListener( randomArtProcessing );
        thresholdButton.setActionCommand( "threshold" );

        grayScaleButton.addActionListener( randomArtProcessing );
        grayScaleButton.setActionCommand( "gray" );

        invertButton.addActionListener( randomArtProcessing );
        invertButton.setActionCommand( "invert" );

        posterizeButton.addActionListener( randomArtProcessing );
        posterizeButton.setActionCommand( "posterize" );

        erodeButton.addActionListener( randomArtProcessing );
        erodeButton.setActionCommand( "erode" );

        dilateButton.addActionListener( randomArtProcessing );
        dilateButton.setActionCommand( "dialate" );

        //Brush buttons
        simpleBrush.addActionListener( randomArtProcessing );
        simpleBrush.setActionCommand( "simpleBrush" );

        lineBrush.addActionListener( randomArtProcessing );
        lineBrush.setActionCommand( "lineBrush" );

        randomBrush.addActionListener( randomArtProcessing );
        randomBrush.setActionCommand( "randomBrush" );

        save.addActionListener( randomArtProcessing );
        save.setActionCommand( "save" );

        openFile.addActionListener( randomArtProcessing );
        openFile.setActionCommand( "openFile" );

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


