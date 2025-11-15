package uam.edu.ni.KathEncargos.domain.pedidos;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Money;
import uam.edu.ni.KathEncargos.domain.menu.Platillo;
import uam.edu.ni.KathEncargos.domain.seguridad.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DetallePedido")
@Getter @Setter

public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detallePedido", nullable = false)
    private Long idDetallePedido;

    @Column(name  = "cantidad", nullable = false)
    private Integer cantidad;

    @Money
    @Column(name= "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @Money
    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "Platillo_id_platillo", nullable = false)
    private Platillo platillo;

    @ManyToOne
    @JoinColumn(name = "Pedido_id_pedido", nullable = false)
    private Pedido pedido;

}
