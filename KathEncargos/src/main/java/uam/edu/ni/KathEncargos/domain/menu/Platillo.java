package uam.edu.ni.KathEncargos.domain.menu;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import org.openxava.annotations.Hidden;
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

    @Column(name ="precio", nullable = false)
    private Double precio;


    // Relaciones
    // UN platillo puede tener varias guarniciones
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


    //Métodos de calculo de precio.




}
