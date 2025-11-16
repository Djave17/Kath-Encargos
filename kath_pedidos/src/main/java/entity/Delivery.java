package entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tblDelivery")
@Getter @Setter

public class Delivery {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name ="system-uuid", strategy = "uuid2")
    private String oid;

    @Column(length = 100)
    private String nombre_repartidor;

    private String direccion_entrega;

    private String telefono_contacto;
    private String hora_salida;
    private String hora_llegada;



}
