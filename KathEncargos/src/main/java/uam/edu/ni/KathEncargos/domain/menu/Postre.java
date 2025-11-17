package uam.edu.ni.KathEncargos.domain.menu;


import lombok.*;
import org.openxava.annotations.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Postre")
@Getter @Setter
public class Postre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_bebida", nullable = false)
    private Long id;

    @Column(name ="nombre_bebida", length = 100, nullable = false)
    private String nombre;

    @Column(name ="descripcion", length = 300, nullable = false)
    private String descripcion;

    @Column(name ="activo", nullable = false)
    private Boolean activo;

    @Column(name ="precio", nullable = false)
    @Money
    private BigDecimal precio;

}
