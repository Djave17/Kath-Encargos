package uam.edu.ni.KathEncargos.domain.catalogos;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity @Table(name = "TipoPedido")
@Getter @Setter
public class TipoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_tipo_pedido", nullable = false)
    private Long idTipoPedido;

    @Column(name ="nombre_tipo", length = 100, nullable = false)
    private String nombreTipoPedido;

    @Column(name ="descripcion", length = 300, nullable = false)
    private String descripcionTipoPedido;

    @Column(name ="activo", nullable = false)
    private Boolean activo;

}
