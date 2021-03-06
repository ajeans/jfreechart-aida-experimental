package hep.aida.jfree.plotter.listener;

import hep.aida.IDataPointSet;
import hep.aida.jfree.converter.DataPointSetConverter;
import hep.aida.jfree.dataset.DataPointSetAdapter;

import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;

/**
 * This is a listener for updating the plot graphics from a backing <code>IDataPointSet</code>.
 * 
 * @author Jeremy McCormick <jeremym@slac.stanford.edu>
 */
public class DataPointSetListener extends PlotListener<IDataPointSet> {

    DataPointSetAdapter adapter;

    DataPointSetListener(IDataPointSet object, JFreeChart chart, XYDataset dataset) {
        super(object, chart, dataset);
        adapter = (DataPointSetAdapter) dataset;
    }

    public void update() {

        // Configure the domain or x axis.
        DataPointSetConverter.configureDomainAxis(this.chart.getXYPlot().getDomainAxis(), this.adapter,
                DataPointSetConverter.MAX_POINTS);

        super.update();
    }
}
