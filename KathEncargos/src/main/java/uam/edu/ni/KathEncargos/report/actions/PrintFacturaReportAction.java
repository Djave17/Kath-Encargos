package uam.edu.ni.KathEncargos.report.actions;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.openxava.actions.JasperReportBaseAction;
import org.openxava.jpa.XPersistence;
import uam.edu.ni.KathEncargos.domain.facturacion.Factura;
import uam.edu.ni.KathEncargos.domain.pedidos.DetallePedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintFacturaReportAction extends JasperReportBaseAction {

    private Factura facturaActual;

    @Override
    protected JRDataSource getDataSource() throws Exception {
        Factura factura = cargarFactura();

        List<LineaFactura> lineas =
                factura.getPedido() != null && factura.getPedido().getDetalles() != null
                        ? factura.getPedido().getDetalles()
                        .stream()
                        .map(LineaFactura::new)
                        .collect(java.util.stream.Collectors.toList())
                        : Collections.emptyList();

        return new JRBeanCollectionDataSource(lineas);
    }

    @Override
    protected String getJRXML() throws Exception {
        return "Factura.jrxml";
    }

    @Override
    protected Map getParameters() throws Exception {
        Factura factura = cargarFactura();
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("numeroFactura", factura.getNumeroFactura());

        // Evita NullPointer si la fecha es null
        parametros.put("fechaEmision",
                factura.getFechaEmision() != null
                        ? Date.from(factura.getFechaEmision().atZone(ZoneId.systemDefault()).toInstant())
                        : null
        );

        parametros.put("cliente",
                factura.getCliente() != null ? factura.getCliente().getNombreUsuario() : "");

        parametros.put("correoCliente",
                factura.getCliente() != null ? factura.getCliente().getEmail() : "");

        parametros.put("direccionCliente",
                factura.getCliente() != null ? factura.getCliente().getDireccionReferencia() : "");

        parametros.put("pedidoId",
                factura.getPedido() != null ? factura.getPedido().getIdPedido() : null);

        // ? Aqui se reemplazó requireNonNullElse por ternario compatible con Java 8
        parametros.put("subtotalFactura",
                factura.getSubtotal() != null ? factura.getSubtotal() : BigDecimal.ZERO);

        parametros.put("impuestosFactura",
                factura.getImpuestos() != null ? factura.getImpuestos() : BigDecimal.ZERO);

        parametros.put("totalFactura",
                factura.getTotal() != null ? factura.getTotal() : BigDecimal.ZERO);

        return parametros;
    }

    private Factura cargarFactura() {
        if (facturaActual != null) return facturaActual;

        Long idFactura = (Long) getView().getValue("idFactura");
        if (idFactura == null) {
            throw new IllegalStateException("Debe seleccionar una factura para imprimir");
        }

        EntityManager em = XPersistence.getManager();
        facturaActual = em.find(Factura.class, idFactura);

        if (facturaActual == null) {
            throw new IllegalStateException("No se encontró la factura seleccionada");
        }

        return facturaActual;
    }

    public static class LineaFactura {
        private final String nombreProducto;
        private final Integer cantidad;
        private final BigDecimal precioUnitario;
        private final BigDecimal subtotal;

        public LineaFactura(DetallePedido detalle) {
            this.nombreProducto =
                    detalle.getPlatillo() != null ? detalle.getPlatillo().getNombre() : "";

            this.cantidad = detalle.getCantidad();
            this.precioUnitario = detalle.getPrecioUnitario();
            this.subtotal = detalle.getSubtotal();
        }

        public String getNombreProducto() { return nombreProducto; }

        public Integer getCantidad() { return cantidad; }

        public BigDecimal getPrecioUnitario() { return precioUnitario; }

        public BigDecimal getSubtotal() { return subtotal; }
    }
}
