package uam.edu.ni.KathEncargos.domain.catalogos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
* @Author David Sanchez
*
* */

@Entity @Table(name = "EstadoPedido")
@Getter
@Setter
public class EstadoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_estado_pedido", nullable = false)
    private Long idEstado;

    @Column(name ="nombre_estado", length = 100, nullable = false)
    private String nombreEstado;

    @Column(name ="descripcion", length = 300, nullable = false)
    private String descripcionEstado; //En preparacion, Cancelado, Entregado, Listo para retirar
}
