package uam.edu.ni.KathEncargos.domain.menu;


import lombok.*;
import org.openxava.annotations.*;
import uam.edu.ni.KathEncargos.domain.catalogos.CategoriaGuarnicion;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity @Table(name = "Guarnicion")
@Getter @Setter
public class Guarnicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_guarnicion", nullable = false)
    private Long idGuarnicion;

    @Column(name ="nombre_guarnicion", length = 100, nullable = false)
    private String nombreGuarnicion;

    @Column(name = "precio", nullable = false)
    @Money
    private BigDecimal precio;

    @Column(name ="activo", nullable = false)
    private Boolean activo;

    //Relacion con categoria guarinicion: Una guarnicion pertenece a una categoria - Una categoria tiene muchas guarniciones
    @ManyToOne
    @JoinColumn(name = "id_categoriaGuarnicion", nullable = false)
    private CategoriaGuarnicion categoriaGuarnicion;



}
