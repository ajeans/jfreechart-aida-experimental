package hep.aida.jfree;

import hep.aida.IAnalysisFactory;
import hep.aida.IAxisStyle;
import hep.aida.ICloud1D;
import hep.aida.IHistogramFactory;
import hep.aida.IPlotter;
import hep.aida.IPlotterFactory;
import hep.aida.IPlotterStyle;
import hep.aida.ITextStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

/**
 * @author Jeremy McCormick <jeremym@slac.stanford.edu>
 */
public class Cloud1DTest extends TestCase {

    IAnalysisFactory af;
    IPlotterFactory pf;
    IHistogramFactory hf;

    protected void setUp() {
        AnalysisFactory.register();
        af = IAnalysisFactory.create();
        pf = af.createPlotterFactory();
        hf = af.createHistogramFactory(null);
    }

    // Create a 1D cloud with random data
    private final ICloud1D cloud1D() {
        Random rand = new Random();
        ICloud1D c1d = hf.createCloud1D("c1d");
        for (int i = 0; i < 100000; i++) {
            c1d.fill(rand.nextDouble() * 100.);
        }
        return c1d;
    }

    public void testCloud1D() throws Exception {

        // Create plotter
        IPlotter plotter = pf.create();

        ICloud1D c1d = cloud1D();

        // Set labels for axes automatically based on title
        c1d.annotation().addItem("xAxisLabel", c1d.title() + " X");
        c1d.annotation().addItem("yAxisLabel", c1d.title() + " Y");

        // Create 3x3 regions for showing plots
        plotter.createRegion();

        IPlotterStyle pstyle = plotter.style();

        // data fill color
        // pstyle.dataStyle().fillStyle().setColor("white");
        pstyle.dataStyle().fillStyle().setVisible(false);

        pstyle.dataStyle().outlineStyle().setVisible(true);
        // pstyle.dataStyle().outlineStyle().setColor("black");
        // pstyle.dataStyle().outlineStyle().setVisible(false);

        pstyle.dataStyle().lineStyle().setVisible(false);

        // title style
        ITextStyle titleStyle = pstyle.titleStyle().textStyle();
        titleStyle.setBold(true);
        // titleStyle.setItalic(true);
        titleStyle.setFontSize(30.);
        titleStyle.setFont("Arial");
        titleStyle.setColor("black");

        // axis style
        List<IAxisStyle> axes = new ArrayList<IAxisStyle>();
        axes.add(pstyle.xAxisStyle());
        axes.add(pstyle.yAxisStyle());
        for (IAxisStyle axisStyle : axes) {
            axisStyle.labelStyle().setBold(true);
            // axisStyle.labelStyle().setItalic(true);
            axisStyle.labelStyle().setFont("Helvetica");
            axisStyle.labelStyle().setFontSize(15);
            axisStyle.labelStyle().setColor("black");
            axisStyle.lineStyle().setColor("black");
            axisStyle.lineStyle().setThickness(2);
            axisStyle.tickLabelStyle().setColor("black");
            axisStyle.tickLabelStyle().setFont("Helvetica");
            axisStyle.tickLabelStyle().setBold(true);
            axisStyle.tickLabelStyle().setFontSize(10);
        }

        // background color
        // pstyle.regionBoxStyle().backgroundStyle().setColor("white");

        // Plot histograms into regions
        plotter.region(0).plot(c1d);

        // Show time
        plotter.show();
        Thread.sleep(1000000); // Yeah, I know.
    }
}
