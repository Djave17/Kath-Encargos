package uam.edu.ni.KathEncargos.domain.menu;


import lombok.*;
import org.openxava.annotations.*;
import uam.edu.ni.KathEncargos.domain.catalogos.CategoriaFuerte;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "EstacionPrincipal")
@Setter @Getter
public class EstacionPrincipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_estacion", nullable = false)
    private Long idEstacionPrincipal;

    @Column(name ="nombre_estacion", length = 100, nullable = false)
    private String nombreEstacion;

    @Column(name ="descripcion", length = 300, nullable = false)
    private String descripcionEstacion;

    @Column(name ="activo", nullable = false)
    private Boolean activo;

    @Money
    @Column(name ="precio", nullable = false)
    private BigDecimal precio;

    //Foreing Key de categoria: Estación principal esta compuesta de una categoria fuerte
    @ManyToOne
    @JoinColumn(name = "id_categoriaFuerte", nullable = false)
    private CategoriaFuerte categoriaFuerte;
}
