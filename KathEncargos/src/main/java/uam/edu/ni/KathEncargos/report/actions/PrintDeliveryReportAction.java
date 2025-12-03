package uam.edu.ni.KathEncargos.report.actions;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.openxava.actions.JasperReportBaseAction;
import org.openxava.jpa.XPersistence;

import uam.edu.ni.KathEncargos.domain.pedidos.Delivery;

public class PrintDeliveryReportAction extends JasperReportBaseAction {

    @Override
    protected JRDataSource getDataSource() throws Exception {
        EntityManager em = XPersistence.getManager();
        List<Delivery> deliveries = em.createQuery("from Delivery", Delivery.class).getResultList();
        return new JRBeanCollectionDataSource(deliveries);
    }

    @Override
    protected String getJRXML() throws Exception {
        return "ReporteDelivery.jrxml";
    }

    @Override
    protected Map getParameters() throws Exception {
        return null;
    }
}