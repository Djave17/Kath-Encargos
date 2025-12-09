package uam.edu.ni.KathEncargos.report.actions;


import java.util.List;
import java.util.Map;
// Importación del EntityManager para manejar consultas a la BD
import javax.persistence.EntityManager;
// JasperReports: define el tipo de fuente de datos utilizada en reportes
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// Acción base para generar reportes en OpenXava
import org.openxava.actions.JasperReportBaseAction;
// Utilidad de OpenXava para acceder a JPA (EntityManager)
import org.openxava.jpa.XPersistence;
import uam.edu.ni.KathEncargos.domain.pedidos.Delivery;

public class PrintDeliveryReportAction extends JasperReportBaseAction {

    /**
     * Obtiene los datos que JasperReports usará para llenar el reporte.
     * Aquí se consulta la base de datos para obtener una lista de Deliveries.
     */
    @Override
    protected JRDataSource getDataSource() throws Exception {

        // Obtiene un EntityManager conectado al contexto de OpenXava
        EntityManager em = XPersistence.getManager();

        // Consulta todos los registros de Delivery desde la tabla correspondiente
        List<Delivery> deliveries = em.createQuery("from Delivery", Delivery.class).getResultList();

        // Convierte la lista en un datasource compatible con JasperReports
        return new JRBeanCollectionDataSource(deliveries);
    }

    /**
     * Indica el archivo JRXML que contiene el diseño del reporte.
     * Debe coincidir con el nombre del archivo ubicado en /reports.
     */
    @Override
    protected String getJRXML() throws Exception {
        return "ReporteDelivery.jrxml";
    }

    /**
     * Parámetros extra para enviar al reporte.
     * Este reporte no requiere parámetros adicionales, por eso se retorna null.
     */
    @Override
    protected Map getParameters() throws Exception {
        return null;
    }
}
