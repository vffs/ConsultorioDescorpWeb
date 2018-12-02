package testeServico;

import consultoriodescorp.Funcionario;
import consultoriodescorp.MarcarConsulta;
import consultoriodescorp.Paciente;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.naming.NamingException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import servico.FuncionarioServico;
import servico.MarcarConsultaServico;
import servico.PacienteServico;

/**
 *
 * @author valeria
 */
public class MarcarConsultaServicoTeste extends Teste{
    MarcarConsultaServico marcarConsultaServico;
    private FuncionarioServico funcionarioServico;
    private PacienteServico pacienteServico;
    
    @Before
    public void setUp() throws NamingException {
        marcarConsultaServico = (MarcarConsultaServico) container.getContext().lookup("java:global/classes/MarcarConsultaServico!servico.MarcarConsultaServico");
        funcionarioServico = (FuncionarioServico) container.getContext().lookup("java:global/classes/FuncionarioServico!servico.FuncionarioServico");
        pacienteServico = (PacienteServico) container.getContext().lookup("java:global/classes/PacienteServico!servico.PacienteServico");
  
    }

    @After
    public void tearDown() {
        marcarConsultaServico = null;
    }
    @Test
    public void inserirConsulta(){
        MarcarConsulta marcarConsulta = marcarConsultaServico.criar();
        marcarConsulta.setDataConsuta(getData(20,Calendar.DECEMBER,2018));
        marcarConsulta.setHora("14:45");
        Paciente paciente = pacienteServico.consultarPorId(new Long(8));
        marcarConsulta.setPaciente(paciente);
        Funcionario funcionario = funcionarioServico.consultarPorId(new Long(10));
        marcarConsulta.adicionarMedico(funcionario);
        marcarConsultaServico.salvar(marcarConsulta);
        assertNotNull(marcarConsulta.getId());
    }
    
    @Test
    public void atualizarConsulta(){
        MarcarConsulta marcarConsulta = marcarConsultaServico.consultarPorId(new Long(3));
        marcarConsulta.setDataConsuta(getData(07,Calendar.JANUARY,2019));
        marcarConsultaServico.atualizar(marcarConsulta);
        marcarConsulta = marcarConsultaServico.consultarPorId(new Long(3));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dataConsulta = dateFormat.format((Date) marcarConsulta.getDataConsuta());
        assertEquals("07-01-2019", dataConsulta);
    }
    
    @Test
    public void removerConsulta(){
        MarcarConsulta marcarConsulta = marcarConsultaServico.consultarPorId(new Long(1));
        marcarConsultaServico.remover(marcarConsulta);
        marcarConsulta = marcarConsultaServico.consultarPorId(new Long(1));
        assertNull(marcarConsulta);      
    }
}
