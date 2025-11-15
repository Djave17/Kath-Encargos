package uam.edu.ni.KathEncargos.domain.seguridad;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Required;

import uam.edu.ni.KathEncargos.domain.seguridad.Usuario;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "roles" )
@Getter @Setter

public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id_rol;

    @Column(length = 50, nullable = false)
    private String nombre_rol;

    @Column(length = 150)
    private String descripcion;

    @OneToMany(mappedBy = "rol") // Navegación hacia los usuarios
    private List<Usuario> usuarios;



}