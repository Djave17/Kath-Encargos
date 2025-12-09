package uam.edu.ni.KathEncargos.domain.facturacion;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Money;
import org.openxava.annotations.View;
import uam.edu.ni.KathEncargos.domain.pedidos.Pedido;
import uam.edu.ni.KathEncargos.domain.seguridad.Usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Representa la factura asociada a un pedido.
 */
@Entity
@Table(name = "Factura")
@View(members =
        "numeroFactura, fechaEmision;" +
                "cliente;" +
                "pedido;" +
                "formaPago;" +
                "subtotal, impuestos, total;" +
                "notas;"
)
@Getter
@Setter
public class Factura {


    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura", nullable = false)
    private Long id;

    @Column(name = "numero_factura", length = 30, nullable = false, unique = true)
    private String numeroFactura;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;

    @Column(name = "forma_pago", length = 30, nullable = false)
    private String formaPago;

    @Money
    @Column(name = "subtotal", precision = 12, scale = 2, nullable = false)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Money
    @Column(name = "impuestos", precision = 12, scale = 2, nullable = false)
    private BigDecimal impuestos = BigDecimal.ZERO;

    @Money
    @Column(name = "total", precision = 12, scale = 2, nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "notas", length = 300)
    private String notas;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Usuario cliente;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_pedido", nullable = false, unique = true)
    private Pedido pedido;

    @PrePersist
    protected void onPersist() {
        if (fechaEmision == null) {
            fechaEmision = LocalDateTime.now();
        }

        if (numeroFactura == null || numeroFactura.trim().isEmpty()) {
            numeroFactura = generarNumeroFactura();
        }

        if (pedido != null) {
            if (pedido.getMontoSubto() != null) {
                subtotal = pedido.getMontoSubto();
            }
            if (pedido.getImpuestos() != null) {
                impuestos = pedido.getImpuestos();
            }
            if (pedido.getMontoTotal() != null) {
                total = pedido.getMontoTotal();
            }
        }
    }

    private String generarNumeroFactura() {
        return "FAC-" + System.currentTimeMillis();
    }
}