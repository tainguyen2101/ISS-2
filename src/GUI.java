import java.awt.*;
import javax.swing.*;

/**
 * GUI class.
 * 
 * @author Ford Nguyen
 */
public class GUI {

    private final JFrame myFrame;

    private final JTabbedPane myTabs;

    /**
     * Parameterless constructor.
     */
    public GUI() {
        myFrame = new JFrame("Console");
        myTabs = createTabs();
        setUp();
    }

    /**
     * test run.
     * @param args
     */
    public static void main(String[] args) {
        new GUI();
    }

    /**
     * set up myFrame
     * Close on exit
     * add Tabs pane
     * set size
     * set visible
     */
    private void setUp() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(myTabs);
        myFrame.setSize(new Dimension(800, 450));
        myFrame.setVisible(true);
    }

    /**
     * crete 5 tabs for 5 station.
     * @return JTabbedPanel have 5 JPanel
     */
    private JTabbedPane createTabs() {
        JTabbedPane theTabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        theTabs.addTab("Station 1", createPanel());
        theTabs.addTab("Station 2", createPanel());
        theTabs.addTab("Station 3", createPanel());
        theTabs.addTab("Station 4", createPanel());
        theTabs.addTab("Station 5", createPanel());
        return theTabs;
    }

    /**
     * create JPanel containing a buttons group on the right.
     * @return mainPanel.
     */
    private JPanel createPanel() {
        final JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createButtonPanel(), BorderLayout.EAST);
        return mainPanel;
    }

    /**
     * create new Button group with GridLayout(6 rows, 2 column).
     * @return buttonPanel.
     */
    private JPanel createButtonPanel() {
        final JPanel buttonPanel = new JPanel(new GridLayout(6,2));
        buttonPanel.add(new JButton("Bar"));
		buttonPanel.add(new JButton("Hum"));
		buttonPanel.add(new JButton("RainDay"));
		buttonPanel.add(new JButton("Temp"));
		buttonPanel.add(new JButton("Wind"));			
		buttonPanel.add(new JButton("2ND"));
		buttonPanel.add(new JButton("Forecast"));
		buttonPanel.add(new JButton("Graph"));
		buttonPanel.add(new JButton("Hi/Low"));
		buttonPanel.add(new JButton("RainYr")); 
		buttonPanel.add(new JButton("Alarm"));
		buttonPanel.add(new JButton("Done"));
        
        return buttonPanel;
    }
}