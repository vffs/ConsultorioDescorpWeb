
package testeServico;

import consultoriodescorp.Funcionario;
import consultoriodescorp.Paciente;
import consultoriodescorp.Receita;
import consultoriodescorp.Remedio;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.naming.NamingException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import servico.FuncionarioServico;
import servico.PacienteServico;
import servico.ReceitaServico;
import servico.RemedioServico;

/**
 *
 * @author valeria
 */
public class ReceitaServicoTeste extends Teste{
    private ReceitaServico receitaServico;
    private RemedioServico remedioServico;
    private FuncionarioServico funcionarioServico;
    private PacienteServico pacienteServico;
    
    @Before
    public void setUp() throws NamingException {
        receitaServico = (ReceitaServico) container.getContext().lookup("java:global/classes/ReceitaServico!servico.ReceitaServico");
        remedioServico = (RemedioServico) container.getContext().lookup("java:global/classes/RemedioServico!servico.RemedioServico");
        funcionarioServico = (FuncionarioServico) container.getContext().lookup("java:global/classes/FuncionarioServico!servico.FuncionarioServico");
        pacienteServico = (PacienteServico) container.getContext().lookup("java:global/classes/PacienteServico!servico.PacienteServico");
    }

    @After
    public void tearDown() {
        receitaServico = null;
    }
    private Date getData(int dia, int mes, int ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.YEAR, ano);
        return calendar.getTime();
    }
    
    @Test
    public void persistirReceita(){
        Receita receita = receitaServico.criar();
        
        Remedio remedio = remedioServico.consultarPorId(new Long(5));
        receita.setRemedio(remedio);
        Funcionario medico = funcionarioServico.consultarPorId(new Long(10));
        receita.setMedico(medico);
        Paciente paciente = pacienteServico.consultarPorId(new Long(8));
        receita.setPaciente(paciente);
        receita.setDataReceita(getData(20,Calendar.DECEMBER,2018));
        receitaServico.salvar(receita);
    }
    @Test
    public void atualizarReceita(){
        Receita receita = receitaServico.consultarPorId(new Long(3));
        
        receita.setDataReceita(getData(15,Calendar.DECEMBER,2018));
        receitaServico.atualizar(receita);
        receita = receitaServico.consultarPorId(new Long(3));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dataReceita = dateFormat.format((Date) receita.getDataReceita());
        assertEquals("15-12-2018", dataReceita);
    }
    
}
