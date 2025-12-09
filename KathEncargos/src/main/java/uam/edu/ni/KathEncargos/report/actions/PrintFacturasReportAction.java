package uam.edu.ni.KathEncargos.report.actions;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.openxava.actions.JasperReportBaseAction;
import org.openxava.jpa.XPersistence;
import uam.edu.ni.KathEncargos.domain.facturacion.Factura;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

public class PrintFacturasReportAction extends JasperReportBaseAction {

    @Override
    protected JRDataSource getDataSource() throws Exception {
        EntityManager em = XPersistence.getManager();
        List<Factura> facturas = em.createQuery("from Factura", Factura.class).getResultList();
        return new JRBeanCollectionDataSource(facturas);
    }

    @Override
    protected String getJRXML() throws Exception {
        return "ReporteFacturas.jrxml";
    }

    @Override
    protected Map getParameters() throws Exception {
        return null;
    }
}