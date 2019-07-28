package proyecto.model.manager;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import proyecto.model.entities.CuentaCliente;
import proyecto.model.entities.TipoTransaccion;
import proyecto.model.entities.Transaccion;


/**
 * Session Bean implementation class ManagerClientes
 */
@Stateless
@LocalBean
public class ManagerClientes {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerClientes() {    	
    	
    }
    
     public List<CuentaCliente> findAllcuentaclientes(){
    	 System.out.println("-------hola enotro..al manager de cuentas-----");
    	String consulta = "select u from CuentaCliente u";
    	Query q = em.createQuery(consulta, CuentaCliente.class);    	
    	return q.getResultList();
    }
    
     public List<Transaccion> findAllTransacciones(int cuenta){
    	 String consulta = "select t from Transaccion t where nro_cuenta_cl="+ cuenta + "and id_tipo_transaccion=" + 3;
    	 Query q  = em.createQuery(consulta, Transaccion.class);
    	 return q.getResultList();
     }
     
     
     public List<Transaccion> findAllDep_Ret(int  cuenta){
    	 String consulta = "select t from Transaccion t where nro_cuenta_cl="+ cuenta+ "and id_tipo_transaccion<"+ 3;
    	 Query q = em.createQuery(consulta,Transaccion.class);
    	 return q.getResultList();
     }
     
     public List<Transaccion> SearchMovimientos(String fecchainicial, String tipomovimiento, int cuenta){
    	 if(!tipomovimiento.equals("General")) {
    	 String consulta = "select t from Transaccion  t where fecha_transaccion>='"+ fecchainicial
    	 +"' and id_tipo_transaccion=" + tipomovimiento+"and nro_cuenta_cl="+ cuenta;
    	 Query q = em.createQuery(consulta, Transaccion.class);
    	 return q.getResultList();
    	 }
    	 else {
    		 return findAllDep_Ret(cuenta);
    	 }
     }
     
     public void realizarTransferencia(int cuenta_origen, int cuenta_destino, int  cantidad) {
//    	 Date fecha_Sist = new Date();
//    	 SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
//    	 String fecha_sistema = formato.format(fecha_Sist);
//    	 transaccion.setFechaTransaccion(fe);

    	 Transaccion transaccion = new Transaccion();
    	 
    	 int saldo_actual_cnt_origen= ConsultarSaldo(cuenta_origen);
    	 System.out.println("cantidad es: "+cantidad+ "  saldo cuenta es: "+ saldo_actual_cnt_origen);
    	 
    	 int saldo_actual_cnt_destino = ConsultarSaldo(cuenta_destino);
    	 CuentaCliente cuenta =  findCuentaClienteByNroCuenta(cuenta_origen);
    	 TipoTransaccion tipotransaccion = findTipoTransaccion(3);
    	 
    	// insercion de datos en la tabla Transaccion
    	 transaccion.setCuentaCliente(cuenta);
    	 transaccion.setCuentaDestino(cuenta_destino);
    	 transaccion.setMontoTransaccion(new BigDecimal(cantidad));
    	 transaccion.setFechaTransaccion(new Date());
    	 transaccion.setTipoTransaccion(tipotransaccion);
    	 transaccion.setSaldoTransaccion( new BigDecimal( saldo_actual_cnt_origen-cantidad));
    	 em.persist(transaccion);
    	 
    	 //actulizamos el valor del saldo de las cuenta origen
    	 cuenta = findCuentaClienteByNroCuenta(cuenta_origen);
    	 cuenta.setSaldoCuenta(new BigDecimal(saldo_actual_cnt_origen-cantidad));
    	 em.merge(cuenta);
    	 
    	 //actualizamos el saldo de la cuenta destino
    	 cuenta = findCuentaClienteByNroCuenta(cuenta_destino);
    	 cuenta.setSaldoCuenta(new BigDecimal(saldo_actual_cnt_destino+cantidad) );
    	 em.merge(cuenta);	

    	 


    	 
    	 
    	 
     }
     
     public int ConsultarSaldo(int nro_cuenta) {
    	 int saldoactual=0;
    	 String consulta = "select cl from  CuentaCliente cl where nro_cuenta_cl=" + nro_cuenta;
    	 Query q = em.createQuery(consulta, CuentaCliente.class);
    	 List<CuentaCliente> list = q.getResultList();
    	 for (CuentaCliente c : list) {
    		 saldoactual= c.getSaldoCuenta().intValue();    // convertimos getsaldocuenta de Bigdecimal a int c0n intValue()
			 //System.out.println("impresion de consulta de saldo="+ saldoactual);
    	 }
    	    	 
    	 return saldoactual;
     }
     
       public CuentaCliente findCuentaClienteByNroCuenta(int nro_cuenta) {
    	return em.find(CuentaCliente.class, nro_cuenta);
    }
       
    public TipoTransaccion findTipoTransaccion(int n) {
    	return em.find(TipoTransaccion.class, n );
    }


}
