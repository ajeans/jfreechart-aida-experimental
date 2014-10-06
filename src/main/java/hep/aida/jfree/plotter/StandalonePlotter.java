package hep.aida.jfree.plotter;

import javax.swing.JFrame;

/**
 * This is an <code>IPlotter</code> implementation which will show plots in a <code>JFrame</code>.
 * 
 * @author Jeremy McCormick <jeremym@slac.stanford.edu>
 */
public class StandalonePlotter extends Plotter {

    JFrame frame;
    
    /**
     * Class constructor.
     */
    StandalonePlotter() {
        super();
    }
        
    /**
     * Show the regions of this plotter, first setting them up if necessary.
     */
    public void show() {
        plotRegions();
        if (this.frame == null)
            createFrame();     
        if (!frame.isVisible())
            frame.setVisible(true);
    }

    /**
     * This will hide the plotter's regions and set its frame to null.  
     */
    public void hide() {
        if (frame != null) {
            frame.setVisible(false);
        }
    }
          
    /**
     * Create the <tt>JFrame</tt> for the plotter.
     * This will only have an effect if the plotter is not embedded.
     */
    private void createFrame() {
        frame = new JFrame();
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
    }    
}
