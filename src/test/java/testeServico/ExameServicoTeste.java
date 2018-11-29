
package testeServico;

import consultoriodescorp.Exame;
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
import servico.ExameServico;
import static testeServico.Teste.container;

/**
 *
 * @author valeria
 */
public class ExameServicoTeste extends Teste{
    ExameServico exameServico;
    
    @Before
    public void setUp() throws NamingException {
        exameServico = (ExameServico) container.getContext().lookup("java:global/classes/ExameServico!servico.ExameServico");
    }

    @After
    public void tearDown() {
        exameServico = null;
    }
    
    @Test
    public void consultarExamePorNome(){
        assertNotNull( exameServico.getExame("Hemograma completo"));
    }
    
    @Test
    public void persistirExame(){
        Exame exame = exameServico.criar();
        exame.setNome("Colonoscopia");
        exameServico.salvar(exame);
    }
    
    @Test
    public void atualizarExame(){
        Exame exame = exameServico.consultarPorId(new Long(5));
        exame.setNome("Eletrocardiograma");
        exameServico.atualizar(exame);
        exame = exameServico.consultarPorId(new Long(5));
        assertEquals("Eletrocardiograma", exame.getNome()); 
    }
    
    @Test
    public void atualizarInvalido() {
        Exame exame = exameServico.consultarPorId(new Long(3));
        exame.setNome(null);
        try {
            exameServico.atualizar(exame);
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
    
}
