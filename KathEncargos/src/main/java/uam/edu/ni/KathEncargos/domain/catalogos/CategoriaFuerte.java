package uam.edu.ni.KathEncargos.domain.catalogos;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CategoriaFuerte")
@Getter @Setter
public class CategoriaFuerte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_categoriaFuerte", nullable = false)
    private Long idCategoriaFuerte;



    @Column(name ="nombre_categoria", length = 100, nullable = false)
    private String nombreCategoria;
}
