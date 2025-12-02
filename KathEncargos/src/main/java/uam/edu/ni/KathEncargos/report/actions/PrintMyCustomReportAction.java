package uam.edu.ni.KathEncargos.report.actions;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import org.openxava.actions.JasperReportBaseAction;

public class PrintMyCustomReportAction extends JasperReportBaseAction {

    @Override
    protected JRDataSource getDataSource() throws Exception {
        // Por ahora, reporte sin datos (solo texto, imágenes, etc. en el .jrxml)
        return new JREmptyDataSource();
    }

    @Override
    protected String getJRXML() throws Exception {
        // Nombre del archivo .jrxml que vas a crear
        return "MiPrimerReporte.jrxml";
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        // Sin parámetros por ahora
        return null;
    }
}
