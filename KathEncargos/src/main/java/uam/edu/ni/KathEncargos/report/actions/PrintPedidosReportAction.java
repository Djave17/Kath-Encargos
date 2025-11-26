package uam.edu.ni.KathEncargos.report.actions;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.openxava.actions.JasperReportBaseAction;
import org.openxava.jpa.XPersistence;


import uam.edu.ni.KathEncargos.domain.pedidos.Pedido;

public class PrintPedidosReportAction extends JasperReportBaseAction {

    @Override
    protected JRDataSource getDataSource() throws Exception {

        EntityManager em = XPersistence.getManager();

        // Obtener todos los pedidos
        List<Pedido> pedidos = em
                .createQuery("from Pedido", Pedido.class)
                .getResultList();

        return new JRBeanCollectionDataSource(pedidos);
    }

    @Override
    protected String getJRXML() throws Exception {
        return "ReportePedidos.jrxml";  // archivo jrxml
    }

    @Override
    protected Map getParameters() throws Exception {
        return null; // no usamos parámetros por ahora
    }
}
