package uam.edu.ni.KathEncargos.domain.seguridad;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;
import org.openxava.annotations.Hidden;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@View(members =
        "Datos Personales[" +
                "   nombreUsuario; telefono; email; direccionReferencia;" +
                "]" +
                "Seguridad[" +
                "   contrasena; rol; activo;" +
                "]" +
                "Registro[" +
                "   fechaRegistro;" +
                "]")
public class Usuario {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre_usuario", length = 50, nullable = false)
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 50, message = "El nombre de usuario no puede exceder 50 caracteres")
    @Required
    private String nombreUsuario;

    @Column(length = 20)
    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
    @Pattern(regexp = "\\d{8}", message = "El teléfono debe tener 8 dígitos numéricos")
    private String telefono;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, max = 100, message = "La contraseña debe tener entre 4 y 100 caracteres")
    @Stereotype("PASSWORD")
    @Required
    private String contrasena;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Size(max = 100, message = "El correo electrónico no puede exceder 100 caracteres")
    @Required
    private String email;

    @Column(name = "direccion_referencia", length = 150)
    @Size(max = 150, message = "La dirección de referencia no puede exceder 150 caracteres")
    private String direccionReferencia;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    private Boolean activo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    @NotNull(message = "El rol es obligatorio")
    @DescriptionsList(descriptionProperties = "nombre")
    @Required
    private Rol rol;
}