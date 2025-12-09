package uam.edu.ni.KathEncargos.report.actions;

import java.util.List;
import javax.persistence.EntityManager;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.openxava.actions.JasperReportBaseAction;
import org.openxava.jpa.XPersistence;

import uam.edu.ni.KathEncargos.domain.menu.Bebida;

/**
 * Acción OpenXava para imprimir el reporte de bebidas.
 *
 * Esta clase obtiene la lista de bebidas desde la base de datos
 * y la pasa como datasource a JasperReports para generar un PDF
 * basado en el archivo ReporteBebidas.jrxml.
 */
public class PrintBebidasReportAction extends JasperReportBaseAction {

    /**
     * Fuente de datos del reporte.
     * Obtiene todas las bebidas registradas en la base de datos
     * y las convierte en un JRBeanCollectionDataSource para el .jrxml.
     */
    @Override
    protected JRDataSource getDataSource() throws Exception {
        EntityManager em = XPersistence.getManager();

        // Cargar todas las bebidas desde la entidad JPA
        List<Bebida> bebidas = em.createQuery("from Bebida", Bebida.class).getResultList();

        // Retornar la lista como datasource para Jasper
        return new JRBeanCollectionDataSource(bebidas);
    }

    /**
     * Define el archivo JRXML que Jasper utilizará para generar el reporte.
     */
    @Override
    protected String getJRXML() throws Exception {
        return "ReporteBebidas.jrxml";
    }

    /**
     * Parámetros opcionales del reporte.
     * Devuelve null porque este reporte no requiere parámetros externos.
     */
    @Override
    protected java.util.Map getParameters() throws Exception {
        return null;
    }
}
