package com.citi.prepaid.canon.utils;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.text.MaskFormatter;

import com.citi.prepaid.canon.dao.JDBCDDAByMemberDAO;
import com.citi.prepaid.canon.dao.MSSQLDDAByMemberDAO;

/**
 * TextInputDemo.java is a 1.4 application that uses these additional files:
 * SpringUtilities.java ...
 */
public class DDAFinder extends JPanel implements ActionListener, FocusListener
{

  private static final long serialVersionUID = -8860746158717601740L;
  JFormattedTextField memberId;
  boolean memberIdSet = false;
  Font regularFont, italicFont;
  JTextField ddaSerchDisplay;
  final static int GAP = 10;
  String URL ="jdbc:sqlserver://ppamwdcpdsql1b1.nam.nsroot.net;instanceName=ppamwdcpdsql1b1;databaseName=EcountCore";
  //String username = "b2c";
  //String password = "b2cstage";
  String username = "csa";
  String password = "E2_Hn3C!e8";


  public DDAFinder()
  {
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

    JPanel leftHalf = new JPanel()
    {
      private static final long serialVersionUID = 6065202457104443735L;

      // Don't allow us to stretch vertically.
      public Dimension getMaximumSize()
      {
        Dimension pref = getPreferredSize();
        return new Dimension(Integer.MAX_VALUE, pref.height);
      }
    };
    leftHalf.setLayout(new BoxLayout(leftHalf, BoxLayout.PAGE_AXIS));
    leftHalf.add(createEntryFields());
    leftHalf.add(createButtons());

    add(leftHalf);
    add(createMemberDisplay());
  }

  protected JComponent createButtons()
  {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

    JButton button = new JButton("Find DDA");
    button.addActionListener(this);
    panel.add(button);

    button = new JButton("Clear Member");
    button.addActionListener(this);
    button.setActionCommand("clear");
    panel.add(button);

    // Match the SpringLayout's gap, subtracting 5 to make
    // up for the default gap FlowLayout provides.
    panel.setBorder(BorderFactory.createEmptyBorder(0, 0, GAP - 5, GAP - 5));
    return panel;
  }

  /**
   * Called when the user clicks the button or presses Enter in a text field.
   */
  public void actionPerformed(ActionEvent e)
  {
    if ("clear".equals(e.getActionCommand()))
    {
      memberIdSet = false;
      memberId.setText("");
    } else
    {
      memberIdSet = true;
    }
    updateDisplays();
  }

  protected void updateDisplays()
  {
    ddaSerchDisplay.setText(formatDDA());
    if (memberIdSet)
    {
      ddaSerchDisplay.setFont(regularFont);
    } else
    {
      ddaSerchDisplay.setFont(italicFont);
    }
  }

  protected JComponent createMemberDisplay()
  {
    JPanel panel = new JPanel(new BorderLayout());
    ddaSerchDisplay = new JTextField();            
    regularFont = ddaSerchDisplay.getFont().deriveFont(Font.PLAIN, 16.0f);
    italicFont = regularFont.deriveFont(Font.ITALIC);
    updateDisplays();

    // Lay out the panel.
    panel.setBorder(BorderFactory.createEmptyBorder(GAP / 2, // top
        0, // left
        GAP / 2, // bottom
        0)); // right
    panel.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.LINE_START);
    panel.add(ddaSerchDisplay, BorderLayout.CENTER);
    panel.setPreferredSize(new Dimension(200, 150));

    return panel;
  }

  protected String formatDDA()
  {
    if (!memberIdSet)
      return "No Member ID set.";

    String dda = "";
    String empty = "";
   
    JDBCDDAByMemberDAO dao = new MSSQLDDAByMemberDAO();
    
    try
    {    
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      Connection conn = DriverManager.getConnection(URL,username, password);
      
      dao.setConnection(conn);
      
      dda = dao.get(memberId.getText());
      
      conn.close();
    }
    catch (Exception e)
    {
      dda = e.getMessage();
    }
   
    if ((dda == null) || empty.equals(dda))
    {
      dda = "Member ID Not Found";
    }

    StringBuffer sb = new StringBuffer();    
    sb.append(dda);

    return sb.toString();
  }

  // A convenience method for creating a MaskFormatter.
  protected MaskFormatter createFormatter(String s)
  {
    MaskFormatter formatter = null;
    try
    {
      formatter = new MaskFormatter(s);
      formatter.setPlaceholderCharacter('A');
    } catch (java.text.ParseException exc)
    {
      System.err.println("formatter is bad: " + exc.getMessage());
      System.exit(-1);
    }
    return formatter;
  }

  /**
   * Called when one of the fields gets the focus so that we can select the
   * focused field.
   */
  public void focusGained(FocusEvent e)
  {
    Component c = e.getComponent();
    if (c instanceof JFormattedTextField)
    {
      selectItLater(c);
    } else if (c instanceof JTextField)
    {
      ((JTextField) c).selectAll();
    }
  }

  // Workaround for formatted text field focus side effects.
  protected void selectItLater(Component c)
  {
    if (c instanceof JFormattedTextField)
    {
      final JFormattedTextField ftf = (JFormattedTextField) c;
      SwingUtilities.invokeLater(new Runnable()
      {
        public void run()
        {
          ftf.selectAll();
        }
      });
    }
  }

  // Needed for FocusListener interface.
  public void focusLost(FocusEvent e)
  {
  } // ignore

  protected JComponent createEntryFields()
  {
    JPanel panel = new JPanel(new SpringLayout());

    String[] labelStrings = { "Member ID" };

    JLabel[] labels = new JLabel[labelStrings.length];
    JComponent[] fields = new JComponent[labelStrings.length];
    int fieldNum = 0;

    // Create the text field and set it up.
    memberId = new JFormattedTextField(createFormatter("AAAAAAAA-AAAA-AAAA-AAAA-AAAAAAAAAAAA"));
    memberId.setColumns(25);
    fields[fieldNum++] = memberId;

    // Associate label/field pairs, add everything,
    // and lay it out.
    for (int i = 0; i < labelStrings.length; i++)
    {
      labels[i] = new JLabel(labelStrings[i], JLabel.TRAILING);
      labels[i].setLabelFor(fields[i]);
      panel.add(labels[i]);
      panel.add(fields[i]);

      // Add listeners to each field.
      JTextField tf = null;
      tf = (JTextField) fields[i];

      tf.addActionListener(this);
      tf.addFocusListener(this);
    }
    SpringUtilities.makeCompactGrid(panel, labelStrings.length, 2, GAP, GAP, // init
                                                                             // x,y
        GAP, GAP / 2);// xpad, ypad
    return panel;
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  private static void createAndShowGUI()
  {
    // Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);

    // Create and set up the window.
    JFrame frame = new JFrame("DDA Search");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create and set up the content pane.
    JComponent newContentPane = new DDAFinder();
    newContentPane.setOpaque(true); // content panes must be opaque
    frame.setContentPane(newContentPane);

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args)
  {
    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        createAndShowGUI();
      }
    });
  }
}

/**
 * A 1.4 file that provides utility methods for creating form- or grid-style
 * layouts with SpringLayout. These utilities are used by several programs, such
 * as SpringBox and SpringCompactGrid.
 */
class SpringUtilities
{
  /**
   * A debugging utility that prints to stdout the component's minimum,
   * preferred, and maximum sizes.
   */
  public static void printSizes(Component c)
  {
    System.out.println("minimumSize = " + c.getMinimumSize());
    System.out.println("preferredSize = " + c.getPreferredSize());
    System.out.println("maximumSize = " + c.getMaximumSize());
  }

  /**
   * Aligns the first <code>rows</code> * <code>cols</code> components of
   * <code>parent</code> in a grid. Each component is as big as the maximum
   * preferred width and height of the components. The parent is made just big
   * enough to fit them all.
   *
   * @param rows
   *          number of rows
   * @param cols
   *          number of columns
   * @param initialX
   *          x location to start the grid at
   * @param initialY
   *          y location to start the grid at
   * @param xPad
   *          x padding between cells
   * @param yPad
   *          y padding between cells
   */
  public static void makeGrid(Container parent, int rows, int cols,
      int initialX, int initialY, int xPad, int yPad)
  {
    SpringLayout layout;
    try
    {
      layout = (SpringLayout) parent.getLayout();
    } catch (ClassCastException exc)
    {
      System.err
          .println("The first argument to makeGrid must use SpringLayout.");
      return;
    }

    Spring xPadSpring = Spring.constant(xPad);
    Spring yPadSpring = Spring.constant(yPad);
    Spring initialXSpring = Spring.constant(initialX);
    Spring initialYSpring = Spring.constant(initialY);
    int max = rows * cols;

    // Calculate Springs that are the max of the width/height so that all
    // cells have the same size.
    Spring maxWidthSpring = layout.getConstraints(parent.getComponent(0))
        .getWidth();
    Spring maxHeightSpring = layout.getConstraints(parent.getComponent(0))
        .getWidth();
    for (int i = 1; i < max; i++)
    {
      SpringLayout.Constraints cons = layout.getConstraints(parent
          .getComponent(i));

      maxWidthSpring = Spring.max(maxWidthSpring, cons.getWidth());
      maxHeightSpring = Spring.max(maxHeightSpring, cons.getHeight());
    }

    // Apply the new width/height Spring. This forces all the
    // components to have the same size.
    for (int i = 0; i < max; i++)
    {
      SpringLayout.Constraints cons = layout.getConstraints(parent
          .getComponent(i));

      cons.setWidth(maxWidthSpring);
      cons.setHeight(maxHeightSpring);
    }

    // Then adjust the x/y constraints of all the cells so that they
    // are aligned in a grid.
    SpringLayout.Constraints lastCons = null;
    SpringLayout.Constraints lastRowCons = null;
    for (int i = 0; i < max; i++)
    {
      SpringLayout.Constraints cons = layout.getConstraints(parent
          .getComponent(i));
      if (i % cols == 0)
      { // start of new row
        lastRowCons = lastCons;
        cons.setX(initialXSpring);
      } else
      { // x position depends on previous component
        cons.setX(Spring.sum(lastCons.getConstraint(SpringLayout.EAST),
            xPadSpring));
      }

      if (i / cols == 0)
      { // first row
        cons.setY(initialYSpring);
      } else
      { // y position depends on previous row
        cons.setY(Spring.sum(lastRowCons.getConstraint(SpringLayout.SOUTH),
            yPadSpring));
      }
      lastCons = cons;
    }

    // Set the parent's size.
    SpringLayout.Constraints pCons = layout.getConstraints(parent);
    pCons.setConstraint(
        SpringLayout.SOUTH,
        Spring.sum(Spring.constant(yPad),
            lastCons.getConstraint(SpringLayout.SOUTH)));
    pCons.setConstraint(
        SpringLayout.EAST,
        Spring.sum(Spring.constant(xPad),
            lastCons.getConstraint(SpringLayout.EAST)));
  }

  /* Used by makeCompactGrid. */
  private static SpringLayout.Constraints getConstraintsForCell(int row,
      int col, Container parent, int cols)
  {
    SpringLayout layout = (SpringLayout) parent.getLayout();
    Component c = parent.getComponent(row * cols + col);
    return layout.getConstraints(c);
  }

  /**
   * Aligns the first <code>rows</code> * <code>cols</code> components of
   * <code>parent</code> in a grid. Each component in a column is as wide as the
   * maximum preferred width of the components in that column; height is
   * similarly determined for each row. The parent is made just big enough to
   * fit them all.
   *
   * @param rows
   *          number of rows
   * @param cols
   *          number of columns
   * @param initialX
   *          x location to start the grid at
   * @param initialY
   *          y location to start the grid at
   * @param xPad
   *          x padding between cells
   * @param yPad
   *          y padding between cells
   */
  public static void makeCompactGrid(Container parent, int rows, int cols,
      int initialX, int initialY, int xPad, int yPad)
  {
    SpringLayout layout;
    try
    {
      layout = (SpringLayout) parent.getLayout();
    } catch (ClassCastException exc)
    {
      System.err
          .println("The first argument to makeCompactGrid must use SpringLayout.");
      return;
    }

    // Align all cells in each column and make them the same width.
    Spring x = Spring.constant(initialX);
    for (int c = 0; c < cols; c++)
    {
      Spring width = Spring.constant(0);
      for (int r = 0; r < rows; r++)
      {
        width = Spring.max(width, getConstraintsForCell(r, c, parent, cols)
            .getWidth());
      }
      for (int r = 0; r < rows; r++)
      {
        SpringLayout.Constraints constraints = getConstraintsForCell(r, c,
            parent, cols);
        constraints.setX(x);
        constraints.setWidth(width);
      }
      x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
    }

    // Align all cells in each row and make them the same height.
    Spring y = Spring.constant(initialY);
    for (int r = 0; r < rows; r++)
    {
      Spring height = Spring.constant(0);
      for (int c = 0; c < cols; c++)
      {
        height = Spring.max(height, getConstraintsForCell(r, c, parent, cols)
            .getHeight());
      }
      for (int c = 0; c < cols; c++)
      {
        SpringLayout.Constraints constraints = getConstraintsForCell(r, c,
            parent, cols);
        constraints.setY(y);
        constraints.setHeight(height);
      }
      y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
    }

    // Set the parent's size.
    SpringLayout.Constraints pCons = layout.getConstraints(parent);
    pCons.setConstraint(SpringLayout.SOUTH, y);
    pCons.setConstraint(SpringLayout.EAST, x);
  }
}
