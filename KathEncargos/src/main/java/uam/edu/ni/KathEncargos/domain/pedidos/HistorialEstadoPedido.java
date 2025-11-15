package uam.edu.ni.KathEncargos.domain.pedidos;

import lombok.Getter;
import lombok.Setter;
import uam.edu.ni.KathEncargos.domain.catalogos.EstadoPedido;
import uam.edu.ni.KathEncargos.domain.seguridad.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HistorialEstadoPedido")
@Getter @Setter

public class HistorialEstadoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial", nullable = false)
    private Long idHistorial;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "observacion", length = 300)
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "Pedido_id_Pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "EstadoPedido_id_estado", nullable = false)
    private EstadoPedido estadoPedido;

    @ManyToOne
    @JoinColumn(name = "Usuario_id_usuario", nullable = false)
    private Usuario operador;

}
