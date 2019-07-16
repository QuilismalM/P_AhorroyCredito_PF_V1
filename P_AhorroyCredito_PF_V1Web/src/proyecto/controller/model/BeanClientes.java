package proyecto.controller.model;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto.model.entities.Transaccion;
import proyecto.model.manager.ManagerClientes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class BeanClientes implements Serializable {

	private static final long serialVersionUID = 1L;
		@EJB
	private ManagerClientes managercli;
	private List<Transaccion> listaCli;
	private List<Transaccion> list2;
	private Transaccion transaccion;
	private Date fechainicial;
	private String tipomovimiento;
	private int cuenta_origen;
	private int cuenta_destino;
	private int cantidad; 
	
	
	@PostConstruct
	public void inicializar() {
		listaCli= managercli.findAllTransaccion();
	}
	
	
	//	public List<Transaccion> Datos() {
//		listaCli=managercli.findAllTransaccion();
//		Transaccion tr= new Transaccion();
//		for (Transaccion transaccion : listaCli) {
//			if(transaccion.getTipoTransaccion().equals("3")) {
//				tr.setCuentaCliente(transaccion.getCuentaCliente());
//				tr.setCuentaDestino(transaccion.getCuentaDestino());
//				tr.setFechaTransaccion(transaccion.getFechaTransaccion());
//				tr.setIdTransaccion(transaccion.getIdTransaccion());
//				tr.setMontoTransaccion(transaccion.getMontoTransaccion());
//				tr.setTipoTransaccion(transaccion.getTipoTransaccion());
//				
//			}
//		}
//				
//		list2.add(tr);
//		
//		return list2;
//	}	
	
	
	
	
	public void ActionImprimir() {
		System.out.println("impirme origen "+ cuenta_origen);
		System.out.println("imprime destino "+ cuenta_destino);
		System.out.println("imprime cantidad"+ cantidad);
		
	}
	
	public void imprimirDatos() {
		SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/YYYY");
		
		System.out.println("fecha: "+ fecha.format(fechainicial));
		System.out.println("Tipo movimiento: "+ tipomovimiento);
		if(fechainicial.equals("01/07/2019"))
			System.out.println("si son iguales");
		else
			System.out.println("no entro en el if");
	}
		
	public void actionRealizarTransferecia() {
		try {
			managercli.realizarTransferencia(cuenta_origen, cuenta_destino, cantidad);
			transaccion = new Transaccion();
			JSFUtil.crearMensajeInfo("Transacción Realizada con éxito");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public String actionListenerMovimientos() {
		return "Movimientos";
	}
	
	public String actinoListenerTransferencia() {
		return "Transferencias";
	}
	
	public String actionListenerHome() {
			return "indexClientes";
	}
	
	
	
	public void actionBuscarporFecha() {
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/YYYY");
		String fecha = date.format(fechainicial);
		listaCli=managercli.SearchMovimientos(fecha, tipomovimiento);

	}
	
////////////////////////////////////// get y set ////////////////////////////////////////////////////	
	
	public Date getFechainicial() {
		return fechainicial;
	}


	public void setFechainicial(Date fechainicial) {
		this.fechainicial = fechainicial;
	}


	public String getTipomovimiento() {
		return tipomovimiento;
	}

	public void setTipomovimiento(String tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}	

	public int getCuenta_origen() {
		return cuenta_origen;
	}


	public void setCuenta_origen(int cuenta_origen) {
		this.cuenta_origen = cuenta_origen;
	}


	public int getCuenta_destino() {
		return cuenta_destino;
	}


	public void setCuenta_destino(int cuenta_destino) {
		this.cuenta_destino = cuenta_destino;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}
	
	public List<Transaccion> getListaCli() {
		return listaCli;
	}

	public void setListaCli(List<Transaccion> listaCli) {
		this.listaCli = listaCli;
	}

	public List<Transaccion> getList2() {
		return list2;
	}

	public void setList2(List<Transaccion> list2) {
		this.list2 = list2;
	}

}
