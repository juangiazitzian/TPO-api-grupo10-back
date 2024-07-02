package com.example.uade.tpo.demo.entity;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.uade.tpo.demo.model.ViniloDTO;
import com.example.uade.tpo.demo.service.CarritoService;
import com.example.uade.tpo.demo.service.CarritoServiceImpl;
import com.example.uade.tpo.demo.service.DescuentoServiceImpl;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<ViniloDTO> cart;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Cuenta cuenta;
	
	@Column(nullable = false)
	private Date date;
	
	@Column(nullable = false)
	private boolean delivery;
	
	@Column
	private String adress;
	
	@Column (nullable = false)
	private Date deliveryDate;

	@Column(nullable = false)
	private boolean entregado;
	
	@Column(nullable = false)
	private double subtotal;
	
	@Column
	private String descuento;
	
	@Column
	private double descuentoOff;
	
	@Column(nullable = false)
	private double total;

	@Column(nullable = false)
	private String metodoPago;
	
	public Pedido() {
	}
	
    public Pedido(Cuenta cuenta, List<ViniloDTO> cart, boolean delivery, String adress, String descuento, double descuentoOff, String metodoPago) {
        this.cuenta = cuenta;
        this.cart = cart;
        this.date = new Date();
        this.delivery = delivery;
        this.adress = adress;
        this.deliveryDate = getDeliveryDate(delivery);
        this.entregado = false;
        this.subtotal = getSubtotal(this.cart);
        this.descuento = descuento;
        this.descuentoOff = descuentoOff;
        this.total = getTotal(this.subtotal, getDescuentoOff(this.subtotal, this.cuenta.getDescuentosUsados(), descuento, descuentoOff), delivery);
		this.metodoPago = metodoPago;
	}
    
    private Date getDeliveryDate(boolean delivery) {
    	if (delivery) {
    		Calendar calendario = Calendar.getInstance();
    		calendario.setTime(new Date());
    		calendario.add(Calendar.DAY_OF_YEAR, 2);
    		return calendario.getTime();
    	}
		
    	return new Date();
    }
    
    private double getSubtotal(List<ViniloDTO> cart) {
		double total = 0;
		for (ViniloDTO vinilo : cart) {
			total += (vinilo.getPrecio() * vinilo.getCantidad());
		}
		return total;
	}
    
    private double getDescuentoOff(double subtotal, List<String> descuentosUsados, String codDescuento, double descuentoOff) {
    	double descuento = 0;
    	if (descuentosUsados.contains(codDescuento) || codDescuento == null) {
    		return descuento;
    	}
    	return subtotal / 100 * descuentoOff;
    }
    
    private double getTotal(double subtotal, double descuento, boolean delivery) {
    	double total = subtotal - descuento;
    	if (delivery) {
    		total += 20;
    	}
    	return total;
    }

	public Long getId() {
		return id;
	}
	public List<ViniloDTO> getCart() {
		return cart;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public Date getDate() {
		return date;
	}
	public boolean isDelivery() {
		return delivery;
	}
	public String getAdress() {
		return adress;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public boolean isEntregado() {
		return entregado;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public String getDescuento() {
		return descuento;
	}
	public double getDescuentoOff() {
		return descuentoOff;
	}
	public double getTotal() {
		return total;
	}
	public String getMetodoPago() {
		return metodoPago;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setCart(List<ViniloDTO> cart) {
		this.cart = cart;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public void setDescuento(double descuentoOff) {
		this.descuentoOff = descuentoOff;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
}