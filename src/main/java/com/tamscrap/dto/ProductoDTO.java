package com.tamscrap.dto;

import java.util.Set;

public class ProductoDTO {

    private Long id;
    private String nombre;
    private double precio;
    private String imagen;
    private boolean lettering;
    private boolean scrapbooking;
    private boolean oferta;
    private Integer descuento;
    private Double precioOriginal;   
    private boolean favorito;       
    private Set<ProductoPedidoDTO> pedidos;

    // Constructor vacío
    public ProductoDTO() {
    }

    // Constructor para inicializar desde Producto
    public ProductoDTO(Long id, String nombre, double precio, String imagen, boolean lettering,
                       boolean scrapbooking, boolean oferta, Integer descuento, Double precioOriginal,
                       boolean favorito, Set<ProductoPedidoDTO> pedidos) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.lettering = lettering;
        this.scrapbooking = scrapbooking;
        this.oferta = oferta;
        this.descuento = descuento;
        this.precioOriginal = precioOriginal;
        this.favorito = favorito;
        this.pedidos = pedidos;
    }

    // Constructor desde una instancia de Producto para facilidad de uso
    public ProductoDTO(com.tamscrap.model.Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.precio = producto.getPrecio();
        this.imagen = producto.getImagen();
        this.lettering = producto.isLettering();
        this.scrapbooking = producto.isScrapbooking();
        this.oferta = producto.isOferta();
        this.descuento = producto.getDescuento();
        this.precioOriginal = producto.getPrecioOriginal(); // Inicializamos precioOriginal
        this.favorito = producto.isFavorito();              // 
        // Se pueden mapear los pedidos si es necesario
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isLettering() {
        return lettering;
    }

    public void setLettering(boolean lettering) {
        this.lettering = lettering;
    }

    public boolean isScrapbooking() {
        return scrapbooking;
    }

    public void setScrapbooking(boolean scrapbooking) {
        this.scrapbooking = scrapbooking;
    }

    public boolean isOferta() {
        return oferta;
    }

    public void setOferta(boolean oferta) {
        this.oferta = oferta;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Double getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(Double precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public Set<ProductoPedidoDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<ProductoPedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "ProductoDTO [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", imagen=" + imagen
                + ", lettering=" + lettering + ", scrapbooking=" + scrapbooking + ", oferta=" + oferta
                + ", descuento=" + descuento + ", precioOriginal=" + precioOriginal + ", favorito=" + favorito
                + ", pedidos=" + pedidos + "]";
    }
}