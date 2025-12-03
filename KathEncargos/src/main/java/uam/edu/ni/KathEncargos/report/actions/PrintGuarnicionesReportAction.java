package uam.edu.ni.KathEncargos.report.actions;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.openxava.actions.JasperReportBaseAction;
import org.openxava.jpa.XPersistence;

import uam.edu.ni.KathEncargos.domain.menu.Guarnicion;

public class PrintGuarnicionesReportAction extends JasperReportBaseAction {

    @Override
    protected JRDataSource getDataSource() throws Exception {
        EntityManager em = XPersistence.getManager();
        List<Guarnicion> guarniciones = em.createQuery("from Guarnicion", Guarnicion.class).getResultList();
        return new JRBeanCollectionDataSource(guarniciones);
    }

    @Override
    protected String getJRXML() throws Exception {
        return "ReporteGuarniciones.jrxml";
    }

    @Override
    protected Map getParameters() throws Exception {
        return null;
    }
}