
package testeServico;

import consultoriodescorp.Exame;
import consultoriodescorp.Funcionario;
import consultoriodescorp.Paciente;
import consultoriodescorp.SolicitarExame;
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
import servico.ExameServico;
import servico.FuncionarioServico;
import servico.PacienteServico;
import servico.SolicitarExameServico;
import static testeServico.Teste.container;

/**
 *
 * @author valeria
 */
public class SolicitarExameServicoTeste extends Teste{
    SolicitarExameServico solicitarExameServico;
     ExameServico exameServico;
    private FuncionarioServico funcionarioServico;
    private PacienteServico pacienteServico;
    
    @Before
    public void setUp() throws NamingException {
        solicitarExameServico = (SolicitarExameServico) container.getContext().lookup("java:global/classes/SolicitarExameServico!servico.SolicitarExameServico");
        funcionarioServico = (FuncionarioServico) container.getContext().lookup("java:global/classes/FuncionarioServico!servico.FuncionarioServico");
        pacienteServico = (PacienteServico) container.getContext().lookup("java:global/classes/PacienteServico!servico.PacienteServico");
        exameServico = (ExameServico) container.getContext().lookup("java:global/classes/ExameServico!servico.ExameServico");
  
    }

    @After
    public void tearDown() {
        solicitarExameServico = null;
    }
    
    @Test
    public void inserirSolicitaçaoExame(){
        SolicitarExame solicitarExame = solicitarExameServico.criar();
        
        solicitarExame.setDataExame(getData(20,Calendar.DECEMBER,2018));
        Exame exame = exameServico.consultarPorId(new Long(6));
        solicitarExame.setExame(exame);
        Paciente paciente = pacienteServico.consultarPorId(new Long(8));
        solicitarExame.setPaciente(paciente);
        Funcionario funcionario = funcionarioServico.consultarPorId(new Long(10));
        solicitarExame.setMedico(funcionario);
        solicitarExameServico.salvar(solicitarExame);
        assertNotNull(solicitarExame.getId());
    } 
    
    @Test
    public void atualizarSolicitaçãoExame(){
        SolicitarExame solicitarExame = solicitarExameServico.consultarPorId(new Long(2));
        solicitarExame.setDataExame(getData(04,Calendar.JANUARY,2019));
        solicitarExameServico.atualizar(solicitarExame);
        solicitarExame = solicitarExameServico.consultarPorId(new Long(2));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dataExame = dateFormat.format((Date) solicitarExame.getDataExame());
        assertEquals("04-01-2019", dataExame);
    }
    
    @Test
    public void removerSolicitacaoExame(){
        SolicitarExame solicitarExame = solicitarExameServico.consultarPorId(new Long(1));
        solicitarExameServico.remover(solicitarExame);
        solicitarExame = solicitarExameServico.consultarPorId(new Long(1));
        assertNull(solicitarExame);
    }
 
}
