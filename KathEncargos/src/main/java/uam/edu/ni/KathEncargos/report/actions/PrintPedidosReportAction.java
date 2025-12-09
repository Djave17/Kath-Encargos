package uam.edu.ni.KathEncargos.report.actions;

// Importaciones para listas y mapas
import java.util.List;
import java.util.Map;


import javax.persistence.EntityManager;
// JasperReports: DataSource usado para pasar colecciones de objetos al reporte
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// Clase base de OpenXava para acciones que generan reportes
import org.openxava.actions.JasperReportBaseAction;
// Utilidad de OpenXava para obtener el EntityManager gestionado
import org.openxava.jpa.XPersistence;
import uam.edu.ni.KathEncargos.domain.pedidos.Pedido;

public class PrintPedidosReportAction extends JasperReportBaseAction {

    /**
     * Provee los datos que JasperReports utilizará para llenar el reporte.
     * Consulta la base de datos para obtener todos los registros de Pedido.
     */
    @Override
    protected JRDataSource getDataSource() throws Exception {

        // Obtiene el EntityManager configurado por OpenXava
        EntityManager em = XPersistence.getManager();

        // Ejecuta una consulta JPA para obtener todos los pedidos existentes
        List<Pedido> pedidos = em
                .createQuery("from Pedido", Pedido.class)
                .getResultList();

        // Convierte la lista de pedidos en un datasource compatible con JasperReports
        return new JRBeanCollectionDataSource(pedidos);
    }

    /**
     * Define qué archivo JRXML se utilizará para generar el reporte.
     * El archivo debe existir dentro de la carpeta /reports del proyecto.
     */
    @Override
    protected String getJRXML() throws Exception {
        return "ReportePedidos.jrxml";  // archivo jrxml
    }

    /**
     * Parámetros adicionales para el reporte.
     * Actualmente no requerimos ninguno, así que retornamos null.
     */
    @Override
    protected Map getParameters() throws Exception {
        return null; // no usamos parámetros por ahora
    }
}
