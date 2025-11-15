package uam.edu.ni.KathEncargos.domain.menu;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Platillo")
@Getter @Setter
public class Platillo {

    @Id
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
    @OneToMany(mappedBy = "platillo", cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<Guarnicion> guarniciones;

    //Métodos de calculo de precio.




}
