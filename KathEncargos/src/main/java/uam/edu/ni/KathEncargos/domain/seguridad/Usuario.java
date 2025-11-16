package uam.edu.ni.KathEncargos.domain.seguridad;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre_usuario", length = 50, nullable = false)
    private String nombreUsuario;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100, nullable = false)
    @Stereotype("PASSWORD")
    private String contrasena;

    @Column(length = 100)
    private String email;

    @Column(name = "direccion_referencia", length = 150)
    private String direccionReferencia;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    @DescriptionsList(descriptionProperties = "nombre")
    private Rol rol;
}
