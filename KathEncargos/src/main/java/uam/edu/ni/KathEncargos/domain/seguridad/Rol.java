package uam.edu.ni.KathEncargos.domain.seguridad;

import org.openxava.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Rol {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 50, message = "El nombre del rol no puede exceder 50 caracteres")
    @Required
    private String nombre;

    @Column(length = 150)
    @Size(max = 150, message = "La descripción no puede exceder 150 caracteres")
    private String descripcion;

    @OneToMany(mappedBy = "rol") // Navegación hacia los usuarios
    private List<Usuario> usuarios;
}