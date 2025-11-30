package uam.edu.ni.KathEncargos.domain.pedidos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.*;
import org.openxava.annotations.Hidden;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tblDelivery")
@Getter
@Setter
@View(members =
        "Datos del Repartidor[" +
                "   nombreRepartidor; telefonoContacto;" +
                "]" +
                "Entrega[" +
                "   direccionEntrega; horaSalida; horaLlegada;" +
                "]"
)
@Tab(properties =
        "nombreRepartidor, telefonoContacto, direccionEntrega, horaSalida, horaLlegada"
)
public class Delivery {

    @Id
    @Hidden
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String oid;

    @Column(name = "nombre_repartidor", length = 100, nullable = false)
    @NotBlank(message = "El nombre del repartidor es obligatorio")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    @Required
    private String nombreRepartidor;

    @Column(name = "direccion_entrega", length = 150, nullable = false)
    @NotBlank(message = "La dirección de entrega es obligatoria")
    @Size(max = 150, message = "La dirección no debe exceder 150 caracteres")
    @Required
    private String direccionEntrega;

    @Column(name = "telefono_contacto", length = 20)
    @Size(max = 20, message = "El teléfono no debe exceder 20 caracteres")
    @Required
    private String telefonoContacto;

    @Column(name = "hora_salida", length = 10)
    @Size(max = 10, message = "Formato de hora no válido")
    private String horaSalida;

    @Column(name = "hora_llegada", length = 10)
    @Size(max = 10, message = "Formato de hora no válido")
    private String horaLlegada;
}
