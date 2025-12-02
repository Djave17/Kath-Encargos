package uam.edu.ni.KathEncargos.domain.menu;

import lombok.*;
import org.openxava.annotations.Depends;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

@Entity
@Table(name = "Platillo")
@Getter @Setter
public class Platillo {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_platillo", nullable = false)
    private Long id;

    @Column(name ="nombre_platillo", length = 100, nullable = false)
    private String nombre;

    @Column(name ="descripcion", length = 300, nullable = false)
    private String descripcion;

    @Column(name ="activo", nullable = false)
    private Boolean activo;

    // Precio base del platillo
    @Money
    @Column(name ="precio", nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;


    // Relaciones
    @ManyToMany
    @JoinTable(
            name = "platillo_guarnicion",
            joinColumns = @JoinColumn(name = "id_platillo"),
            inverseJoinColumns = @JoinColumn(name = "id_guarnicion")
    )
    private Collection<Guarnicion> guarniciones;

    @ManyToOne
    @JoinColumn(name = "id_bebida")
    private Bebida bebida;

    @ManyToOne
    @JoinColumn(name = "id_postre")
    private Postre postre;


    // Cálculo del precio total del platillo con guarniciones, bebida y postre
    @Transient
    @Depends("precio, guarniciones, bebida, postre")
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;

        if (this.precio != null) {
            total = total.add(this.precio);
        }

        if (this.guarniciones != null) {
            for (Guarnicion g : this.guarniciones) {
                if (g != null && g.getPrecio() != null) {
                    total = total.add(g.getPrecio());
                }
            }
        }

        if (this.bebida != null && this.bebida.getPrecio() != null) {
            total = total.add(this.bebida.getPrecio());
        }

        if (this.postre != null && this.postre.getPrecio() != null) {
            total = total.add(this.postre.getPrecio());
        }

        // devolver con 2 decimales
        return total.setScale(2, RoundingMode.HALF_UP);
    }

}

