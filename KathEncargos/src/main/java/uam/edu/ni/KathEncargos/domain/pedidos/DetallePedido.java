package uam.edu.ni.KathEncargos.domain.pedidos;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Money;
import uam.edu.ni.KathEncargos.domain.menu.Platillo;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "DetallePedido")
@Getter @Setter
public class DetallePedido {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detallePedido", nullable = false)
    private Long idDetallePedido;

    @NotNull
    @Min(1)
    @Column(name  = "cantidad", nullable = false)
    private Integer cantidad;

    @Money
    @Column(name= "precio_unitario", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioUnitario;

    @Money
    @Column(name = "subtotal", nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "Platillo_id_platillo", nullable = false)
    @NotNull
    private Platillo platillo;

    @ManyToOne
    @JoinColumn(name = "Pedido_id_pedido", nullable = false)
    private Pedido pedido;

    // Método para calcular precioUnitario y subtotal antes de persistir o actualizar
    @PrePersist
    @PreUpdate
    private void calcularPrecios() {
        if (this.platillo == null) {
            // Si no hay platillo, guardamos ceros seguros
            this.precioUnitario = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            this.subtotal = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            if (this.cantidad == null) this.cantidad = 1;
            return;
        }

        // Obtener precio calculado del platillo
        BigDecimal precioPlatillo;
        try {
            precioPlatillo = this.platillo.getTotal();
        } catch (Exception e) {
            precioPlatillo = BigDecimal.ZERO;
        }
        if (precioPlatillo == null) precioPlatillo = BigDecimal.ZERO;

        // validar cantidad
        if (this.cantidad == null || this.cantidad < 1) {
            this.cantidad = 1;
        }

        // asignar precioUnitario (con escala)
        this.precioUnitario = precioPlatillo.setScale(2, RoundingMode.HALF_UP);

        // calcular subtotal
        this.subtotal = this.precioUnitario.multiply(BigDecimal.valueOf(this.cantidad))
                .setScale(2, RoundingMode.HALF_UP);
    }
}

