package com.elegro.masterfinan.application;

import com.elegro.masterfinan.domain.service.CompraService;
import com.elegro.masterfinan.domain.service.PagoService;
import com.elegro.masterfinan.domain.service.TransaccionService;
import com.elegro.masterfinan.infraestructura.entity.Compra;
import com.elegro.masterfinan.infraestructura.entity.Pago;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    @Autowired
    ResponseApi response;
    @Autowired
    CompraService compraService;
    @Autowired
    TransaccionService transaccionService;
    @Autowired
    PagoService pagoService;

    @PostMapping("/registrarCompraFull")
    public Compra crearTransaccionCompra(@RequestBody Map<String, String> request){

        List<Transaccion> transacciones = new ArrayList<>();
        List<Pago> pagos = new ArrayList<>();

        //compra
        String estado_credito = request.get("estado_compra");
        LocalDate fecha_inicial = LocalDate.parse(request.get("fecha_inicial"));
        LocalDate fecha_final = LocalDate.parse(request.get("fecha_final"));
        Double saldo_pendiente  = Double.parseDouble(request.get("saldo_pendiente"));
        Long persona = Long.parseLong(request.get("persona"));
        String tipo_pago = request.get("tipo_pago");
        Double valor_compra = Double.parseDouble(request.get("valor_compra"));
        Compra compra = new Compra();
        compra.setEstadoCredito(estado_credito);
        compra.setValorCompra(valor_compra);
        compra.setSaldoPendiente(saldo_pendiente);
        compra.setTipoPago(tipo_pago);
        compra.setFechaInicial(fecha_inicial);
        compra.setFechaFinal(fecha_final);
        compra.setPersona(persona);

        Optional<Compra> optional = compraService.crear(compra);
        optional.map(_compra -> {
            compra.setId(_compra.getId());
            return null;
        });
        //crear la transaccion
        LocalDate fecha = LocalDate.parse(request.get("fecha"));
        LocalTime hora = LocalTime.parse(request.get("hora"));
        Long usuario = Long.parseLong(request.get("usuario"));
        String tipo_transaccion = request.get("tipo_transaccion");
        Double valor = Double.parseDouble(request.get("valor"));
        String estado = request.get("estado");
        Transaccion transaccion = new Transaccion();
        transaccion.setFecha(fecha);
        transaccion.setHora(hora);
        transaccion.setUsuario(usuario);
        transaccion.setTipoTransaccion(tipo_transaccion);
        transaccion.setValor(valor);
        transaccion.setEstado(estado);

        Optional<Transaccion> optional2 = transaccionService.crear(transaccion);
        optional2.map(_transaccion -> {
            transaccion.setId(_transaccion.getId());
            return null;
        });

        //pago realizado por la compra
        Pago pago = new Pago();
        pago.setCompra(compra.getId());
        pago.setTransaccion(transaccion.getId());
        pago.setMedioPago(request.get("medio_pago"));
        pagoService.crear(pago);
        pagos.add(pago);

        transacciones.add(transaccion);
        compra.setTransacciones(transacciones);
        compra.setPagos(pagos);
        return compra;
    }

    @PostMapping("/crear")
    public Optional<Compra> crear(@RequestBody Compra compra) {
        return compraService.crear(compra);
    }

    @GetMapping("/buscar")
    public Optional<Compra> buscar(@RequestParam String id){
        Integer _id = Integer.parseInt(id);
        return compraService.buscar(_id);
    }

    @PutMapping("/actualizar")
    public IResponseApi actualiza(@RequestBody Compra compra, @RequestParam String id){
        Integer _id = Integer.parseInt(id);
        if (compraService.actualiza(compra, _id))
        {
            response.setMessage("El proceso se completo con éxito");
            response.setSuccess(true);
        }else{
            response.setMessage("Error no se pueden actualizar, ha generado un error");
            response.setSuccess(true);
        }
        return response;
    }

    @DeleteMapping("/borrar")
    public IResponseApi borrar(@RequestBody Map<String, String> request){
        Integer _id = Integer.parseInt(request.get("id"));
        if (compraService.borrar(_id)) {
            response.setMessage("El proceso se completo con éxito");
            response.setSuccess(true);
        }else{
            response.setMessage("Error no se puede borrar, ha generado un error");
            response.setSuccess(false);
        }
        return response;
    }
}
