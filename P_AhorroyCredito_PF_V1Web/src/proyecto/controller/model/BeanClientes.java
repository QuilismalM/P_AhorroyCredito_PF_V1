package proyecto.controller.model;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto.model.entities.Transaccion;
import proyecto.model.manager.ManagerClientes;

import java.io.Serializable;
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
	private String fechainicial;
	private String tipomovimiento;
	private String cuenta_origen;
	private String cuenta_destino;
	private String cantidad; 
	
	
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
		System.out.println("fecha: "+ fechainicial);
		System.out.println("Tipo movimiento: "+ tipomovimiento);
		if(fechainicial.equals("01/07/2019"))
			System.out.println("si son iguales");
		else
			System.out.println("no entro en el if");
	}
		
	
	public String getFechainicial() {
		return fechainicial;
	}

	public void setFechainicial(String fechainicial) {
		this.fechainicial = fechainicial;
	}

	public String getTipomovimiento() {
		return tipomovimiento;
	}

	public void setTipomovimiento(String tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}

	public String getCuenta_origen() {
		return cuenta_origen;
	}

	public void setCuenta_origen(String cuenta_origen) {
		this.cuenta_origen = cuenta_origen;
	}

	public String getCuenta_destino() {
		return cuenta_destino;
	}

	public void setCuenta_destino(String cuenta_destino) {
		this.cuenta_destino = cuenta_destino;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
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
		listaCli=managercli.SearchMovimientos(fechainicial, tipomovimiento);

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
