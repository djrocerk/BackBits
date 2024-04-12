package Prueba.apibanco.service.impl;

import Prueba.apibanco.dto.EntradaDatosDTO;
import Prueba.apibanco.dto.SalidaDatosDTO;
import Prueba.apibanco.model.Cuenta;
import Prueba.apibanco.repository.CuentaRepository;
import Prueba.apibanco.repository.MovimientoRepository;
import Prueba.apibanco.service.IReporteService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class ReporteService implements IReporteService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public ReporteService(CuentaRepository cuentaRepository, MovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public SalidaDatosDTO generarReporte(EntradaDatosDTO entradaDatosDTO) throws ParseException {
        var idcliente = entradaDatosDTO.getId_cliente();
        var cuentas = cuentaRepository.findByClienteId(idcliente);

        LocalDate fechaInicial = stringToDate(String.valueOf(entradaDatosDTO.getFechaInicio()));
        Timestamp fechaInicioTimestamp = convertLocalDateToTimestamp(fechaInicial);

        LocalDate fechaFinal = stringToDate(String.valueOf(entradaDatosDTO.getFechaFinal()));
        Timestamp fechaFinTimestamp = convertLocalDateToTimestamp(fechaFinal);

        Double totalCreditos = movimientoRepository.calcularTotalcre(idcliente, fechaInicioTimestamp, fechaFinTimestamp);
        Double totalDebitos = movimientoRepository.calculartotaldeb(idcliente, fechaInicioTimestamp, fechaFinTimestamp);

        SalidaDatosDTO result = new SalidaDatosDTO();
        result.setId_cliente(idcliente);
        result.setCuentas(cuentas);
        result.setTotalcredito(totalCreditos);
        result.setTotaldebito(totalDebitos);

        return result;
    }

    private LocalDate stringToDate(String fecha) throws ParseException {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(fecha, inputFormat);
        return localDate;
    }

    private Timestamp convertLocalDateToTimestamp(LocalDate localDate) {
        return localDate == null ? null : Timestamp.valueOf(localDate.atStartOfDay());
    }


}
