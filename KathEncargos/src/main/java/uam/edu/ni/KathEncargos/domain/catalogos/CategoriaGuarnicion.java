package uam.edu.ni.KathEncargos.domain.catalogos;

import lombok.AllArgsConstructor;
import lombok.*;


import javax.persistence.*;

@Entity
@Table(name = "CategoriaGuarnicion")
@Getter @Setter
public class CategoriaGuarnicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_categoriaGuarnicion", nullable = false)
    private Long idCategoriaGuarnicion;

    @Column(name ="nombre_categoria", length = 100, nullable = false)
    private String nombreCategoria;
}
