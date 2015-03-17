import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Ramin on 16.03.2015.
 */
public class DisplayFrame extends JFrame
{
    private RandomArt randomArtProcessing;

    private JPanel processingPanel;

    // Buttons
    private JButton triangleButton;
    private JButton squareButton;
    private JButton circleButton;
    private JButton colorPicker;
    private JButton resetButton;

    // Menus
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem save;

    // Labels
    private JLabel title;


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
            this.setResizable( true );
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

        // Initiate buttons
        triangleButton = new JButton( "Triangles" );
        squareButton = new JButton("Squares");
        circleButton = new JButton( "Circles" );
        resetButton = new JButton( "Clear Canvas" );
        colorPicker = new JButton( "Color Picker" );

        save = new JMenuItem( "Save" );
        save.setMnemonic( KeyEvent.VK_S );

        fileMenu.add( save );
        fileMenu.setMnemonic( KeyEvent.VK_F );


        // Initiate Label
        title = new JLabel( "Random Art Generator" );
        title.setForeground( new Color( 0, 0, 0 ) );
        title.setFont( new Font( null, 2, 25 ) );
    }


    /**
     * Add the components to the frame
     */
    private void addComponents()
    {
        this.add( triangleButton );
        this.add( processingPanel );
        this.add( squareButton );
        this.add( circleButton );
        this.add( resetButton );
        this.add( colorPicker );



        this.add( title );

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
        squareButton.setBounds( 120, 100, 100, 25 );
        circleButton.setBounds( 230, 100, 100, 25 );
        colorPicker.setBounds( 10, 200, 120, 25 );
        resetButton.setBounds( 10, getHeight() - 100, 150, 25 );

        title.setBounds( 10, 10, 300, 100 );
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

        save.addActionListener( randomArtProcessing );
        save.setActionCommand( "save" );
    }
}


