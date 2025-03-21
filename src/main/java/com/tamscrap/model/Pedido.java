package com.tamscrap.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "precio")
	private double precio;

	@Column(name = "fecha")
	private LocalDateTime fechaCreacion;
	@Column(name = "estado")
	private String estado;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = true)
	@JsonIgnore
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<ProductosPedidos> productos;

	@Column(name = "direccion_envio")
	private String direccionEnvio;
	@Column(name = "metodo_pago")
	private String metodoPago;
	@Column(name = "nombre_comprador")
	private String nombreComprador;

	public Pedido() {
		productos = new HashSet<>();
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
		productos = new HashSet<>();
	}

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}

	public Pedido(Long id, double precio, LocalDateTime fechaCreacion, String estado, Cliente cliente,
			Set<ProductosPedidos> productos, String direccionEnvio, String metodoPago, String nombreComprador) {
		this.id = id;
		this.precio = precio;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.cliente = cliente;
		this.productos = productos;
		this.direccionEnvio = direccionEnvio;
		this.metodoPago = metodoPago;
		this.nombreComprador = nombreComprador;
	}

	public Pedido(LocalDateTime fechaCreacion, Cliente cliente) {
		this.fechaCreacion = fechaCreacion;
		this.cliente = cliente;
		this.productos = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ProductosPedidos> getProductos() {
		return productos;
	}

	public void setProductos(Set<ProductosPedidos> productos) {
		this.productos = productos;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public void addProducto(Producto producto, int cantidad) {
		ProductosPedidos productoPedido = new ProductosPedidos(producto, this, cantidad);
		if (productos.contains(productoPedido)) {
			productos.remove(productoPedido);
		}
		if (cantidad != 0) {
			productos.add(productoPedido);
		}
		producto.getPedidos().add(productoPedido);
	}

	public void addProducto2(Producto producto, int cantidad) {
		ProductosPedidos productoPedido = new ProductosPedidos(producto, this, cantidad);
		if (productos.contains(productoPedido)) {
			productos.remove(productoPedido);
		}
		if (cantidad != 0) {
			productos.add(productoPedido);
		}
	}

	public void removeProducto(Producto producto) {
		Iterator<ProductosPedidos> iterator = productos.iterator();
		while (iterator.hasNext()) {
			ProductosPedidos productoPedido = iterator.next();
			if (productoPedido.getPedido().equals(this) && productoPedido.getProducto().equals(producto)) {
				iterator.remove();
				productoPedido.getProducto().getPedidos().remove(productoPedido);
				productoPedido.setPedido(null);
				productoPedido.setProducto(null);
				productoPedido.setCantidad(0);
			}
		}
	}

	public void calcularPrecio() {
		precio = Math
				.round(productos.stream().mapToDouble(p -> p.getProducto().getPrecio() * p.getCantidad()).sum() * 100.0)
				/ 100.0;
	}

	public String imprimirProductos() {
		StringBuilder resultado = new StringBuilder("Productos del pedido " + id + "\n");
		if (productos.isEmpty()) {
			resultado.append("No hay productos en este pedido.");
		} else {
			for (ProductosPedidos productoPedido : productos) {
				Producto producto = productoPedido.getProducto();
				int cantidad = productoPedido.getCantidad();
				resultado.append(producto.getNombre()).append(" ---> Cantidad: ").append(cantidad)
						.append(" | Precio Unitario: ").append(producto.getPrecio()).append(" € | Total: ")
						.append(producto.getPrecio() * cantidad).append(" €\n");
			}
			resultado.append("\n\n");
		}
		return resultado.toString();
	}

	@Override
	public String toString() {
		return "Pedido{" + "id=" + id + ", direccionEnvio='" + direccionEnvio + '\'' + ", metodoPago='" + metodoPago
				+ '\'' + ", cliente=" + (cliente != null ? cliente.getId() : null) + ", productos=" + productos +

				'}';
	}

}
