package uam.edu.ni.KathEncargos.domain.pedidos;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Money;
import uam.edu.ni.KathEncargos.domain.catalogos.EstadoPedido;
import uam.edu.ni.KathEncargos.domain.catalogos.TipoPedido;
import uam.edu.ni.KathEncargos.domain.seguridad.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Pedido")
@Getter @Setter

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Pedido", nullable = false)
    private Long idPedido;

    @Column(name = "fecha_hora_creacion", nullable = false)
    private LocalDateTime fechaHoraCreacion;

    @Column(name = "hora_estimada_retiro", nullable = false)
    private LocalDateTime horaEstimadaRetiro;

    @Column(name = "total_productos", nullable = false)
    private Integer totalProductos;

    @Column(name = "observaciones", length = 300)
    private String observaciones;

    @Money
    @Column(name = "total_pedido", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalPedido;

    @Money
    @Column(name = "monto_subto", precision = 10, scale = 2, nullable = false)
    private BigDecimal montoSubto;

    @Money
    @Column(name = "impuestos", precision = 10, scale = 2, nullable = false)
    private BigDecimal impuestos;

    @Money
    @Column(name = "monto_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal montoTotal;

    @ManyToOne
    @JoinColumn(name = "TipoPedido_id_tipo_pedido", nullable = false)
    private TipoPedido tipoPedido;

    @ManyToOne
    @JoinColumn(name = "EstadoPedido_id_estado", nullable = false)
    private EstadoPedido estadoPedido;

    @ManyToOne
    @JoinColumn(name = "Usuario_id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "Usuario_id_operador")
    private Usuario operador;

    @OneToMany(mappedBy = "pedido")
    private List<DetallePedido> detalles;

    @OneToMany(mappedBy = "pedido")
    private List<HistorialEstadoPedido> historial;

    @Transient
    public BigDecimal calcularSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        if (detalles != null) {
            for (DetallePedido d : detalles) {
                if (d.getSubtotal() != null) {
                    subtotal = subtotal.add(d.getSubtotal());
                }
            }
        }
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    @Transient
    public BigDecimal calcularImpuestos() {
        BigDecimal impuestoPorcentaje = new BigDecimal("0.15"); // 15% ejemplo
        return calcularSubtotal().multiply(impuestoPorcentaje).setScale(2, RoundingMode.HALF_UP);
    }

    @Transient
    public BigDecimal calcularTotal() {
        return calcularSubtotal().add(calcularImpuestos()).setScale(2, RoundingMode.HALF_UP);
    }

    @Transient
    public int contarProductos() {
        int total = 0;
        if (detalles != null) {
            for (DetallePedido d : detalles) {
                if (d.getCantidad() != null) {
                    total += d.getCantidad();
                }
            }
        }
        return total;
    }

}
