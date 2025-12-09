package uam.edu.ni.KathEncargos.report.actions;


import java.util.List;
import java.util.Map;
// EntityManager permite realizar consultas a la base de datos usando JPA
import javax.persistence.EntityManager;
// JasperReports ? tipos usados para enviar colecciones de objetos a un reporte
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// Accion base de OpenXava diseñada para ejecutar reportes Jasper reportes
import org.openxava.actions.JasperReportBaseAction;
// Función central para obtener el EntityManager de OpenXava
import org.openxava.jpa.XPersistence;
import uam.edu.ni.KathEncargos.domain.menu.Postre;

public class PrintPostresReportAction extends JasperReportBaseAction {

    /**
     * Obtiene la fuente de datos que JasperReports utilizará
     * para llenar el reporte. En este caso se consultan
     * todos los registros de Postre desde la base de datos.
     */
    @Override
    protected JRDataSource getDataSource() throws Exception {

        // Solicita un EntityManager gestionado por OpenXava
        EntityManager em = XPersistence.getManager();

        // Realiza una consulta JPA para recuperar todos los postres registrados
        List<Postre> postres = em.createQuery("from Postre", Postre.class).getResultList();

        // Convierte la lista en un datasource compatible con JasperReports
        return new JRBeanCollectionDataSource(postres);
    }

    /**
     * Devuelve el archivo JRXML que contiene la estructura visual
     * del reporte. Debe existir dentro de la carpeta /reports.
     */
    @Override
    protected String getJRXML() throws Exception {
        return "ReportePostres.jrxml";
    }

    /**
     * Permite pasar parámetros adicionales al reporte.
     * Como este reporte no los necesita, devolvemos null.
     */
    @Override
    protected Map getParameters() throws Exception {
        return null;
    }
}
