package uam.edu.ni.KathEncargos.domain.menu;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@View(members =
        "nombre;" +
                "descripcion;" +
                "activo;" +
                "precio;" +
                "total;" +
                "guarniciones;" +
                "bebidas;" +
                "postres;"
)
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

    @Money
    @Column(name ="precio", nullable = false)
    private Double precio;   // precio base


    // ============================
    // RELACIONES
    // ============================

    // GUARNICIONES (muchas)
    @ManyToMany
    @JoinTable(
            name = "platillo_guarnicion",
            joinColumns = @JoinColumn(name = "id_platillo"),
            inverseJoinColumns = @JoinColumn(name = "id_guarnicion")
    )
    private Collection<Guarnicion> guarniciones;

    // BEBIDAS ? permitir varias
    @ManyToMany
    @JoinTable(
            name = "platillo_bebida",
            joinColumns = @JoinColumn(name = "id_platillo"),
            inverseJoinColumns = @JoinColumn(name = "id_bebida")
    )
    private Collection<Bebida> bebidas;

    // POSTRES ? permitir varios
    @ManyToMany
    @JoinTable(
            name = "platillo_postre",
            joinColumns = @JoinColumn(name = "id_platillo"),
            inverseJoinColumns = @JoinColumn(name = "id_postre")
    )
    private Collection<Postre> postres;


    // ============================
    //   C?LCULO DEL TOTAL
    // ============================

    @ReadOnly
    @Depends("precio, bebidas, postres, guarniciones")
    @Money
    public BigDecimal getTotal() {

        BigDecimal total = BigDecimal.ZERO;

        // precio base
        if (precio != null) {
            total = total.add(BigDecimal.valueOf(precio));
        }

        // BEBIDAS (varias)
        if (bebidas != null) {
            for (Bebida b : bebidas) {
                if (b != null && b.getPrecio() != null) {
                    total = total.add(b.getPrecio());
                }
            }
        }

        // POSTRES (varios)
        if (postres != null) {
            for (Postre p : postres) {
                if (p != null && p.getPrecio() != null) {
                    total = total.add(p.getPrecio());
                }
            }
        }

        // GUARNICIONES (varias)
        if (guarniciones != null) {
            for (Guarnicion g : guarniciones) {
                if (g != null && g.getPrecio() != null) {
                    total = total.add(g.getPrecio());
                }
            }
        }

        return total;
    }
}