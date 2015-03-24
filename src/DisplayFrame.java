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



    // Menus
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem save;
    private JMenuItem print;

    // Labels
    private JLabel title;
    private JLabel filters;


    public static void main(String[] args)
    {
        DisplayFrame displayFrame = new DisplayFrame("Random Art Generator");
    }


    /**
     * Construct a new DisplayFrame
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

        this.setJMenuBar( menuBar );
        menuBar.add( fileMenu );

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
        triangleButton = new JButton( "Triangles" );
        squareButton = new JButton("Squares");
        circleButton = new JButton( "Circles" );

        resetButton = new JButton( "Clear Canvas" );
        colorPicker = new JButton( "background Color" );
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
        save.setMnemonic( KeyEvent.VK_S );


        fileMenu.add( save );
        fileMenu.setMnemonic( KeyEvent.VK_F );

        print = new JMenuItem( "Print file" );
        print.setMnemonic( KeyEvent.VK_P );
        fileMenu.add( print );


        // Initiate Label
        title = new JLabel( "Random Art Generator" );
        title.setForeground( new Color( 0, 0, 0 ) );
        title.setFont( new Font( null, 2, 25 ) );

        filters = new JLabel( "Filters" );
        filters.setForeground( new Color( 0, 0, 0 ) );
        filters.setFont( new Font( null, 2, 20 ) );
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

        this.add( title );
        this.add( filters );


        processingPanel.add( randomArtProcessing );
    }


    /**
     * Set the layout for the components
     */
    private void setLayout()
    {
        setLayout( null );

        processingPanel.setBounds( getWidth() - 1030, 10, 1000, getHeight() - 500 );

        triangleButton.setBounds( 10, 100, 100, 25 );
        triangleButton.setBackground( new Color( 232, 177, 141 ) );

        squareButton.setBounds( 120, 100, 100, 25 );
        squareButton.setBackground( new Color( 232, 177, 141 ) );

        circleButton.setBounds( 230, 100, 100, 25 );
        circleButton.setBackground( new Color( 232, 177, 141 ) );

        colorPicker.setBounds( 10, 150, 120, 25 );
        colorPicker.setBackground( new Color( 232, 177, 141 ) );

        resetButton.setBounds( 10, getHeight() - 100, 150, 25 );

        borderButton.setBounds( 140, 150, 170, 25 );
        borderButton.setBackground( new Color( 232, 177, 141 ) );

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


        title.setBounds( 10, 10, 300, 100 );
        filters.setBounds( 10, 200, 100, 50 );
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
        dilateButton.setActionCommand( "dilate" );


        save.addActionListener( randomArtProcessing );
        save.setActionCommand( "save" );

        print.addActionListener( randomArtProcessing );
        print.setActionCommand( "print" );
    }
}


