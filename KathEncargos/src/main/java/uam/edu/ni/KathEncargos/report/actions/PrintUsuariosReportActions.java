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

        return new JRBeanCollectionDataSource(usuarios);
    }

    @Override
    protected String getJRXML() throws Exception {
        return "ReporteUsuarios.jrxml"; // nombre del jrxml
    }

    @Override
    protected Map getParameters() throws Exception {
        return null; // sin parámetros
    }
}
