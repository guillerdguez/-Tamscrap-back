package com.tamscrap.dto;

import com.tamscrap.model.ProductoPedidoId;
import com.tamscrap.model.ProductosPedidos;

public class ProductoPedidoDTO {
	private Long productoId;
	private int cantidad;

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ProductosPedidos toProductosPedidos(Long pedidoId) {
		ProductosPedidos productosPedidos = new ProductosPedidos();
		ProductoPedidoId id = new ProductoPedidoId(pedidoId, this.productoId);
		productosPedidos.setId(id);
		productosPedidos.setCantidad(this.cantidad);
		return productosPedidos;
	}

}
