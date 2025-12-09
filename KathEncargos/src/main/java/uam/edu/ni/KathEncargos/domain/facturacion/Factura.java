package uam.edu.ni.KathEncargos.domain.facturacion;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;
import uam.edu.ni.KathEncargos.domain.pedidos.Pedido;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Random;

@Entity
@Table(name = "Factura")
@Getter @Setter
@View(members =
        "numero, fecha;" +
                "pedido;" +
                "totalPedido, impuestos, montoFinal"
)
public class Factura {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura", nullable = false)
    private Long idFactura;

    // n?mero de factura generado
    @Column(name = "numero", length = 10, nullable = false)
    @ReadOnly
    private String numero;

    @PrePersist
    private void generarNumeroFactura() {
        if (this.numero == null) {
            Random rnd = new Random();
            int n = rnd.nextInt(900) + 100; // genera 100-999
            this.numero = "F" + n;
        }
    }


    @Required
    @Column(name = "fecha", nullable = false)
    private java.time.LocalDate fecha = java.time.LocalDate.now();

    @ManyToOne
    @Required
    @DescriptionsList(descriptionProperties = "idPedido, fechaHoraCreacion, montoTotal")
    private Pedido pedido;


    @Money
    @ReadOnly
    @Depends("pedido")
    public BigDecimal getTotalPedido() {
        if (pedido == null || pedido.getMontoTotal() == null) return BigDecimal.ZERO;
        return pedido.getMontoTotal();
    }

    @Money
    @ReadOnly
    @Depends("totalPedido")
    public BigDecimal getImpuestos() {
        return getTotalPedido().multiply(new BigDecimal("0.15")); // 15%
    }

    @Money
    @ReadOnly
    @Depends("totalPedido, impuestos")
    public BigDecimal getMontoFinal() {
        return getTotalPedido().add(getImpuestos());
    }
}


