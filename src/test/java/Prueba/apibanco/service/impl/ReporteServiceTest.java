    package Prueba.apibanco.service.impl;

    import Prueba.apibanco.dto.EntradaDatosDTO;
    import Prueba.apibanco.dto.SalidaDatosDTO;
    import Prueba.apibanco.model.Cuenta;
    import Prueba.apibanco.repository.CuentaRepository;
    import Prueba.apibanco.repository.MovimientoRepository;
    import Prueba.apibanco.service.impl.ReporteService;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;

    import java.sql.Timestamp;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.mockito.Mockito.when;

    public class ReporteServiceTest {
        @Mock
        private CuentaRepository cuentaRepository;

        @Mock
        private MovimientoRepository movimientoRepository;

        @InjectMocks
        private ReporteService reporteService;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testGenerarReporte() throws Exception {
            // Mock de los datos de entrada
            EntradaDatosDTO entradaDatosDTO = new EntradaDatosDTO();
            entradaDatosDTO.setId_cliente(1);
            entradaDatosDTO.setFechaInicio(new Date());
            entradaDatosDTO.setFechaFinal(new Date());

            // Mock de cuentas asociadas al cliente
            List<Cuenta> cuentas = new ArrayList<>();
            Cuenta cuenta1 = new Cuenta();
            Cuenta cuenta2 = new Cuenta();
            cuentas.add(cuenta1);
            cuentas.add(cuenta2);

            // Mock de los totales de crédito y débito
            Double totalCredito = 0.0;
            Double totalDebito = 0.0;

            // Simular el comportamiento del repositorio
            when(cuentaRepository.findByClienteId(1)).thenReturn(cuentas);
            when(movimientoRepository.calcularTotalcre(1, Timestamp.valueOf("2024-01-01 00:00:00"), Timestamp.valueOf("2024-12-31 00:00:00"))).thenReturn(totalCredito);
            when(movimientoRepository.calculartotaldeb(1, Timestamp.valueOf("2024-01-01 00:00:00"), Timestamp.valueOf("2024-12-31 00:00:00"))).thenReturn(totalDebito);

            // Llamar al método del servicio
            SalidaDatosDTO resultado = reporteService.generarReporte(entradaDatosDTO);

            // Verificar que el resultado coincida con las expectativas
            assertEquals(2, resultado.getCuentas().size());
            assertEquals(totalCredito, resultado.getTotalcredito());
            assertEquals(totalDebito, resultado.getTotaldebito());
        }

    }
