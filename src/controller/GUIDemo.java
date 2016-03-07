package controller;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
/**  Skeleton GUI for grid-based games. 
 *   GUI has a menu, a status area, and a 2d playing area.
 *   The GUI will display the game and handle mouse clicks, dispatching
 *   them to the appropriate button or cell of the board.
 *   The game displays a 10x10 grid of rose images.
 *   Clicking on a cell removes the rose and allows the background image
 *   to show through, except a few random cells that contain grapes.
 *   Right-clicking on a cell reveals a lilly.
 *   The move counter on the status bar increases with each click.
* @author jdalbey 
* @version 2014.10.4
*/
public class GUIDemo extends JFrame 
{
    /* Main components of the GUI */
    // DO NOT CHANGE THE FOLLOWING THREE LINES
    private JTable table;
    private JLabel myStatus = null;
    private JMenuBar menuBar;
    // The underlying data model
    private Object[][] myBoard; 
    private String[] columns = {"", "", "", "", "", "", "", "", "", "", };
    private int clickCount;  // Counter for mouse clicks
    // The images to be displayed
    private ImageIcon rose, grapes, lilly, background;
    /* Image dimensions, in pixels */
    private int kImageWidth = 65;
    private int kImageHeight = 43;

    /** Create a GUI. 
     *  Will use the System Look and Feel when possible.
     */
    public GUIDemo()
    {
        super();
        myBoard = new ImageIcon[10][10];
        try
        {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex)
        {
            System.err.println(ex);
        }
    }


    /** Place all the Swing widgets in the frame of this GUI.
     */
    public void layoutGUI()
    {
        setTitle("Swing Grid Demo");
        loadImages();
        newGame(); 
        table = new ImageJTable(myBoard, columns);
        
        // Define the layout manager that will control order of components
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        layoutMenus();
        
        // Create a panel for the status information
        JPanel statusPane = new JPanel();
        myStatus = new JLabel("###");
        statusPane.add(myStatus);
        statusPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(statusPane);

        /* Define the characteristics of the table that shows the game board    */
        // Set the dimensions for each column in the board to match the image 
        for (int col = 0; col < 10; col++)
        {
            TableColumn column = table.getColumnModel().getColumn(col);
            column.setMaxWidth(kImageWidth);
            column.setMinWidth(kImageWidth);
        }
        // remove editor makes table not editable
        table.setDefaultEditor(Object.class, null);  
        // define how cell selection works
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setCellSelectionEnabled(false);
        // other miscellaneous settings
        table.setRowHeight(kImageHeight);
        table.setOpaque(false);
        table.setShowGrid(false);
        table.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add a custom mouse listener that will handle player's clicks.
        table.addMouseListener(myMouseListener);
        // finally, add the table to the content pane
        getContentPane().add(table);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } // end layout GUI

    /* Setup the menubar and submenus */
    private void layoutMenus()
    {
        menuBar = new javax.swing.JMenuBar();
        JMenu mnuGame = new JMenu("Game");
        menuBar.add(mnuGame);
        JMenuItem mnuNew = new JMenuItem("New");
        mnuNew.setMnemonic('N');
        mnuNew.setAccelerator(
                KeyStroke.getKeyStroke('N', ActionEvent.ALT_MASK));
        mnuNew.addActionListener(new ActionListener()
        {
        // Anonymous inner classes are used here for brevity, but should be
        // named classes in production code.
            public void actionPerformed(ActionEvent e)
            {
                newGame();
                myStatus.setText("" + clickCount);
                repaint();                
            }
        });
        mnuGame.add(mnuNew);
        
        JMenuItem mnuQuit = new JMenuItem("Quit");
        mnuQuit.setMnemonic('Q');
        mnuQuit.setAccelerator(
                KeyStroke.getKeyStroke('Q', ActionEvent.ALT_MASK));
        mnuQuit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        mnuGame.add(mnuQuit);
        setJMenuBar(menuBar);        
    } // end layout Menus

    /* Listener to respond to mouse clicks on the table */
    private MouseAdapter myMouseListener = new MouseAdapter()
    {
        public void mouseReleased(MouseEvent ev)
        {
            // obtain the selected cell coordinates
            int col = table.getSelectedColumn();
            int row = table.getSelectedRow();
            // Is it a right mouse click?
            if (SwingUtilities.isRightMouseButton(ev)) 
            {
                row = (int) (ev.getPoint().getY()/kImageHeight);
                col = (int) (ev.getPoint().getX()/kImageWidth);
                // Show special image
                myBoard[row][col] = lilly;
            }
            // left mouse click
            else
            {
                // Randomly decide to show grapes or nothing
                if (2 < (Math.random()*10))
                {
                    myBoard[row][col] = null;
                }
                else
                {
                    myBoard[row][col] = grapes;
                }
                clickCount++;
                myStatus.setText("" + clickCount);
            }
            repaint();
        }
    };  // end mouse listener

    
    /* Load the images to be displayed */
    private void loadImages() 
    {
        // Only for the demo we load images from the internet, but it's
        // really slow ... your app must load images from the Jar in
        // which it's distributed, something like this:
        // Toolkit.getDefaultToolkit().getImage(this.getClass()
        //        .getResource("images/" + "bkgd.jpg")));
        final String kSite = "http://i.imgur.com//";
        try
        {
            Image one = ImageIO.read(new URL(kSite + "F4VnDdf.jpg"));
            Image two = ImageIO.read(new URL(kSite + "vqHZWUZ.jpg"));
            Image three = ImageIO.read(new URL(kSite + "nWpm4KG.jpg"));
            Image four = ImageIO.read(new URL(kSite + "nORa16t.jpg"));
            rose = new ImageIcon(one);
            grapes = new ImageIcon(two);
            lilly = new ImageIcon(three);
            background = new ImageIcon(four);
        }
        catch (IOException ex)
        {
            System.out.println("Couldn't read images. No network connection?");
        }
    }

    /* Start a new game by putting new values in the board */
    private void newGame()
    {
        Integer cellvalue = new Integer((int)(Math.random()*10));
        // Initialize every row
        for (int row = 0; row < 10; row++)
        {
            // Initialize every column
            for (int col = 0; col < 10; col++)
            {
                myBoard[row][col] = rose; //cellvalue;
            }
        }
        clickCount = 0;

    }
    
    /** Our custom JTable has special features for displaying images and 
     *  a background.
     */
    private class ImageJTable extends JTable
    {
        public ImageJTable(Object[][] rowData, Object[] columnNames)
        {
            super(rowData, columnNames);
        }
        //  Tell JTable it should expect each column to contain IconImages,
        //  and should select the corresponding renderer for displaying it.
        public Class getColumnClass(int column)
        {
            return ImageIcon.class;
        }
        //  Allow the background to be displayed
        public Component prepareRenderer(TableCellRenderer renderer, int row,
                int column)
        {
            Component component = super.prepareRenderer(renderer, row, column);
            // We want renderer component to be
            // transparent so background image is visible
            if (component instanceof JComponent)
            {
                ((JComponent) component).setOpaque(false);
            }
            return component;
        }

        // Override paint so as to show the table background
        public void paint(Graphics gfx)
        {
            // paint an image in the table background
            if (background != null)
            {
                //gfx.drawImage(background.getImage(), 0, 0, null, null);
            }
            // Now let the paint do its usual work
            super.paint(gfx);
        }
        
    } // end ImageJTable

    /** Local main to launch the GUI.
     * @param args command line arguments (none expected)
     */
    public static void main(String[] args)
    {
        // Create the GUI 
        GUIDemo frame = new GUIDemo();
        frame.layoutGUI();   // do the layout of widgets
               
        // Make the GUI visible and available for user interaction
        frame.pack();
        frame.setVisible(true);
    }
    
}  // end GUIDemo