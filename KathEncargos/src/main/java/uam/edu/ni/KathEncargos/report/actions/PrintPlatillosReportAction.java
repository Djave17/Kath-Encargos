package uam.edu.ni.KathEncargos.report.actions;

import java.util.List;
import java.util.Map;
// EntityManager permite ejecutar consultas JPA hacia la base de datos
import javax.persistence.EntityManager;
// JasperReports ? tipos usados para alimentar el reporte con colecciones de objetos
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// Acción base de OpenXava que habilita generación de reportes Jasper
import org.openxava.actions.JasperReportBaseAction;
// Utilidad de OpenXava para obtener la instancia del EntityManager
import org.openxava.jpa.XPersistence;
import uam.edu.ni.KathEncargos.domain.menu.Platillo;

public class PrintPlatillosReportAction extends JasperReportBaseAction {

    /**
     * Obtiene los datos que JasperReports usará para generar el reporte.
     * Aquí se consulta la base de datos para obtener todos los platillos registrados.
     */
    @Override
    protected JRDataSource getDataSource() throws Exception {

        // Recupera un EntityManager administrado por OpenXava
        EntityManager em = XPersistence.getManager();

        // Consulta todos los registros de Platillo usando JPA
        List<Platillo> platillos = em.createQuery("from Platillo", Platillo.class).getResultList();

        // Convierte la lista obtenida en un data source compatible con JasperReports
        return new JRBeanCollectionDataSource(platillos);
    }

    /**
     * Indica el nombre del archivo JRXML que contiene el diseño del reporte.
     * Debe existir dentro de la carpeta /reports del proyecto.
     */
    @Override
    protected String getJRXML() throws Exception {
        return "ReportePlatillos.jrxml";
    }

    /**
     * Permite pasar parámetros adicionales al reporte si fuera necesario.
     * Como en este caso no se requieren, se retorna null.
     */
    @Override
    protected Map getParameters() throws Exception {
        return null;
    }
}
