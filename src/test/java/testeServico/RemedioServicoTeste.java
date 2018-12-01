
package testeServico;

import consultoriodescorp.Remedio;
import java.util.List;
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
import servico.RemedioServico;

/**
 *
 * @author valeria
 */
public class RemedioServicoTeste extends Teste {
    private RemedioServico remedioServico;
    
    @Before
    public void setUp() throws NamingException {
        remedioServico = (RemedioServico) container.getContext().lookup("java:global/classes/RemedioServico!servico.RemedioServico");
    }

    @After
    public void tearDown() {
        remedioServico = null;
    }
    
    @Test
    public void persistirRemedio(){
        Remedio remedio = remedioServico.criar();
        
        remedio.setNomeRemedio("Multigripe");
        remedio.setTratamento("Gripe ou crises alérgicas");
        remedio.setDuracao("3 dias");
        remedioServico.salvar(remedio);
        assertNotNull(remedio.getId());
    }
    
    @Test
    public void atualizarRemedio(){
        Remedio remedio = remedioServico.consultarPorId(new Long(3));
        remedio.setNomeRemedio("Melxi");
        remedioServico.atualizar(remedio);
        remedio = remedioServico.consultarPorId(new Long(3));
        assertEquals("Melxi",remedio.getNomeRemedio());
    }
    
    @Test
    public void atualizarRemedioInvalido(){
         Remedio remedio = remedioServico.consultarPorId(new Long(2));
         remedio.setNomeRemedio(null);
        try {
            remedioServico.atualizar(remedio);
            assertTrue(false);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(startsWith("Não pode estar em branco")));
                               
            }        }
    }
    
    @Test
    public void consultarRemedioPorTratamento(){
        assertEquals(2, remedioServico.getRemedioPorTratamento("Pressão alta").size());
    }
   
    
    
}
