package uam.edu.ni.KathEncargos.report.actions;

import java.util.List;
import java.util.Map;
// EntityManager permite ejecutar consultas JPA hacia la base de datos
import javax.persistence.EntityManager;
// Tipos de JasperReports para generar un DataSource basado en beans
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// Clase base de OpenXava para acciones que generan reportes Jasper
import org.openxava.actions.JasperReportBaseAction;
// Acceso al EntityManager administrado por OpenXava
import org.openxava.jpa.XPersistence;
import uam.edu.ni.KathEncargos.domain.pedidos.DetallePedido;

public class PrintDetallePedidoReportAction extends JasperReportBaseAction {

    /**
     * Este método proporciona los datos que JasperReports utilizará
     * para llenar el reporte.
     * Se consulta la base de datos para obtener todos los DetallePedido.
     */
    @Override
    protected JRDataSource getDataSource() throws Exception {

        // Obtiene un administrador de entidades de JPA a través de OpenXava
        EntityManager em = XPersistence.getManager();

        // Consulta todos los registros de DetallePedido en la base de datos
        List<DetallePedido> detalles = em.createQuery("from DetallePedido", DetallePedido.class).getResultList();

        // Convierte la lista obtenida en un datasource compatible con JasperReports
        return new JRBeanCollectionDataSource(detalles);
    }

    /**
     * Devuelve el nombre del archivo JRXML que contiene el diseño del reporte.
     * Debe existir dentro del folder /reports del proyecto.
     */
    @Override
    protected String getJRXML() throws Exception {
        return "ReporteDetallePedido.jrxml";
    }

    /**
     * Permite enviar parámetros adicionales al reporte.
     * Este reporte no necesita ninguno, por eso retornamos null.
     */
    @Override
    protected Map getParameters() throws Exception {
        return null;
    }
}
