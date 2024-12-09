package com.tamscrap.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARRITOS")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreCliente;

    @Column
    private String imagenUrl;

    @OneToOne(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cliente cliente;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CarritoProducto> productos = new HashSet<>();

    public Carrito() {}

    public Carrito(String nombreCliente, String imagenUrl) {
        this.nombreCliente = nombreCliente;
        this.imagenUrl = imagenUrl;
    }

    public Carrito(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    // Constructor completo
    public Carrito(Long id, String nombreCliente, String imagenUrl, Cliente cliente, Set<CarritoProducto> productos) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.imagenUrl = imagenUrl;
        this.cliente = cliente;
        this.productos = productos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<CarritoProducto> getProductos() {
        return productos;
    }

    public void setProductos(Set<CarritoProducto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto, int cantidad) {
        CarritoProducto carritoProducto = new CarritoProducto();
        carritoProducto.setProducto(producto);
        carritoProducto.setCarrito(this);
        carritoProducto.setCantidad(cantidad);
        productos.add(carritoProducto);
    }


    public void removeProducto(Producto producto) {
        productos.removeIf(carritoProducto -> carritoProducto.getProducto().equals(producto));
    }

    @Override
    public String toString() {
        return "Carrito [id=" + id + ", nombreCliente=" + nombreCliente + ", imagenUrl=" + imagenUrl + "]";
    }
}
