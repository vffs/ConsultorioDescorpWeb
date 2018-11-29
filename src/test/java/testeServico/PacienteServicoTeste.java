
package testeServico;

import consultoriodescorp.Paciente;
import consultoriodescorp.TipoPlanoSaude;
import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import servico.PacienteServico;

/**
 *
 * @author valeria
 */
public class PacienteServicoTeste extends Teste{
    private PacienteServico pacienteServico;
    
    @Before
    public void setUp() throws NamingException {
        pacienteServico = (PacienteServico) container.getContext().lookup("java:global/classes/PacienteServico!servico.PacienteServico");
    }

    @After
    public void tearDown() {
        pacienteServico = null;
    }
    
    @Test
    public void getPacientePorId() {
        assertNotNull(pacienteServico.consultarPorId(new Long(4)));
    }
    
    @Test
    public void persitirPaciente(){
        Paciente paciente = pacienteServico.criar();
        paciente.setNome("Tiago Benicio");
        paciente.setEmail("tiagobenicio@hotmail.com");
        paciente.setLogin("tiagobenicio");
        paciente.setSenha("Tiago123");
        paciente.setSexo("M");
        paciente.setPlano(TipoPlanoSaude.HAPVIDA);
        pacienteServico.salvar(paciente);
        assertNotNull(paciente.getId());
    }
    
    @Test
    public void atualizar() {
        Paciente paciente = pacienteServico.consultarPorId(new Long(2));
        paciente.setEmail("joaoaugusto@yahoo.com.br"); 
        pacienteServico.atualizar(paciente);
        paciente = pacienteServico.consultarPorId(new Long(2));
        assertEquals("joaoaugusto@yahoo.com.br", paciente.getEmail());
    }
  /* 
    @Test
    public void atualizarPacienteInvalido() {
        Paciente paciente = pacienteServico.consultarPorId(new Long(3));
        paciente.setLogin("Luiz Luz");// login com espaço
        try {
            pacienteServico.atualizar(paciente);
            assertTrue(false);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(startsWith("Não deve conter espaços.")));
                               
            }
        }
    } */
    
    @Test
    public void consultarPacientePorTipo(){
        assertEquals(2, pacienteServico.getPacientePorPlano(TipoPlanoSaude.UNIMED).size());
    }
    
    

}