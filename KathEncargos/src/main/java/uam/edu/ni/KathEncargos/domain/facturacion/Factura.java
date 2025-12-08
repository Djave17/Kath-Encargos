package uam.edu.ni.KathEncargos.domain.facturacion;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Money;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.View;
import uam.edu.ni.KathEncargos.domain.pedidos.Pedido;
import uam.edu.ni.KathEncargos.domain.seguridad.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@View(members =
        "numeroFactura; fechaEmision;" +
                "pedido; cliente;" +
                "Totales [subtotal; impuestos; total]"
)
@Entity
@Table(name = "Factura")
@Getter
@Setter
public class Factura {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura", nullable = false)
    private Long idFactura;

    // Número de factura se genera automáticamente, por eso no es @Required ni nullable=false
    @ReadOnly // opcional, pero recomendable para que el usuario no lo cambie
    @Column(name = "numero_factura", length = 30, nullable = true, unique = true)
    private String numeroFactura;

    // También se genera automáticamente si viene null
    @Column(name = "fecha_emision", nullable = true)
    private LocalDateTime fechaEmision;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Usuario cliente;

    @OneToOne
    @JoinColumn(name = "id_pedido", unique = true)
    private Pedido pedido;

    @Money
    @Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Money
    @Column(name = "impuestos", precision = 10, scale = 2, nullable = false)
    private BigDecimal impuestos = BigDecimal.ZERO;

    @Money
    @Column(name = "total", precision = 10, scale = 2, nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @PrePersist
    @PreUpdate
    protected void prepararDatos() {

        // Si no hay fecha, ponemos la actual
        if (fechaEmision == null) {
            fechaEmision = LocalDateTime.now();
        }

        // Si no hay número de factura, lo generamos con timestamp
        if (numeroFactura == null || numeroFactura.isEmpty()) {
            numeroFactura = "FAC-" +
                    fechaEmision.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        }

        if (pedido != null) {

            // Si no han seleccionado cliente, se toma del pedido
            if (cliente == null) {
                cliente = pedido.getUsuario();
            }

            // Subtotal
            if (subtotal == null || subtotal.compareTo(BigDecimal.ZERO) == 0) {
                BigDecimal montoSubto = pedido.getMontoSubto();
                subtotal = (montoSubto != null) ? montoSubto : BigDecimal.ZERO;
            }

            // Impuestos
            if (impuestos == null || impuestos.compareTo(BigDecimal.ZERO) == 0) {
                BigDecimal imp = pedido.getImpuestos();
                impuestos = (imp != null) ? imp : BigDecimal.ZERO;
            }

            // Total
            if (total == null || total.compareTo(BigDecimal.ZERO) == 0) {
                BigDecimal montoTotal = pedido.getMontoTotal();
                total = (montoTotal != null) ? montoTotal : BigDecimal.ZERO;
            }
        }
    }
}
