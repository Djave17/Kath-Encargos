package uam.edu.ni.KathEncargos.report.actions;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.openxava.actions.JasperReportBaseAction;
import org.openxava.jpa.XPersistence;


import uam.edu.ni.KathEncargos.domain.seguridad.Usuario;

public class PrintUsuariosReportActions extends JasperReportBaseAction {

    @Override
    protected JRDataSource getDataSource() throws Exception {

        EntityManager em = XPersistence.getManager();

        // Obtener todos los usuarios
        List<Usuario> usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();
        // JRBeanCollectionDataSource convierte la lista en un datasource válido
        return new JRBeanCollectionDataSource(usuarios);
    }

    @Override
    protected String getJRXML() throws Exception { //Indica cuál archivo JRXML se va a usar para generar el reporte.
        return "ReporteUsuarios.jrxml"; // nombre del jrxml
    }

    //Permite pasar parámetros al reporte JasperReports.
            // Si el reporte no necesita parámetros, retornamos null.

    @Override
    protected Map getParameters() throws Exception {
        return null; // sin parámetros
    }
}
