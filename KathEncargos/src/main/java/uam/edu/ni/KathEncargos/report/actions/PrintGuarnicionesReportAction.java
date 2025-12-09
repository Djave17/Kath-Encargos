package uam.edu.ni.KathEncargos.report.actions;

import java.util.List;
import java.util.Map;
// EntityManager permite ejecutar consultas a la base de datos con JPA
import javax.persistence.EntityManager;
// JasperReports: define el tipo de fuente de datos basado en listas de objetos
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// Acción base de OpenXava para reportes Jasper
import org.openxava.actions.JasperReportBaseAction;
// Utilidad de OpenXava para acceder al gestor de persistencia
import org.openxava.jpa.XPersistence;
import uam.edu.ni.KathEncargos.domain.menu.Guarnicion;

public class PrintGuarnicionesReportAction extends JasperReportBaseAction {

    /**
     * Obtiene los datos que se enviarán al reporte.
     * Consulta todas las guarniciones almacenadas en la base de datos.
     */
    @Override
    protected JRDataSource getDataSource() throws Exception {

        // Obtiene un EntityManager gestionado por OpenXava
        EntityManager em = XPersistence.getManager();

        // Consulta todos los registros de Guarnición
        List<Guarnicion> guarniciones = em.createQuery("from Guarnicion", Guarnicion.class).getResultList();

        // Convierte la lista en un datasource compatible con JasperReports
        return new JRBeanCollectionDataSource(guarniciones);
    }

    /**
     * Indica el archivo JRXML que contiene el diseño del reporte.
     * Este archivo debe estar ubicado en la carpeta /reports del proyecto.
     */
    @Override
    protected String getJRXML() throws Exception {
        return "ReporteGuarniciones.jrxml";
    }

    /**
     * Permite enviar parámetros adicionales al reporte,
     * pero en este caso no son necesarios, por lo que se retorna null.
     */
    @Override
    protected Map getParameters() throws Exception {
        return null;
    }
}
