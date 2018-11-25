
package testeServico;

import consultoriodescorp.Funcionario;
import consultoriodescorp.TipoFuncionario;
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import servico.FuncionarioServico;

/**
 *
 * @author valeria
 */
public class FuncionarioServicoTeste extends Teste {
     private FuncionarioServico funcionarioServico;
     @Before
    public void setUp() throws NamingException {
        funcionarioServico = (FuncionarioServico) container.getContext().lookup("java:global/classes/FuncionarioServico!servico.FuncionarioServico");
    }

    @After
    public void tearDown() {
        funcionarioServico = null;
    }
    @Test
    public void getFuncionarioPorId() {
        assertNotNull(funcionarioServico.consultarPorId(new Long(10)));
    }
     
    
    @Test
    public void presistirFuncionario(){
        Funcionario funcionario = funcionarioServico.criar();
        funcionario.setNome("Lorenzo Mateus");
        funcionario.setEmail("lorenzo@gmail.com");
        funcionario.setLogin("lorenzomateus"); 
        funcionario.setSenha("Lorenzo123");
        funcionario.setSexo("M");
        funcionario.setTipo(TipoFuncionario.MEDICO);
        funcionario.setEspecialidade("FISIOTERAPEUTA");
        funcionario.setEscolaridade("ENSINO SUPERIOR COMPLETO");
        funcionarioServico.salvar(funcionario);
        assertNotNull(funcionario.getId());
    }
    
   @Test
    public void atualizar() {
        Funcionario funcionario = funcionarioServico.consultarPorId(new Long(6));
        funcionario.setSenha("funcionario6"); 
        funcionarioServico.atualizar(funcionario);
        funcionario = funcionarioServico.consultarPorId(new Long(6));
        assertEquals("funcionario6", funcionario.getSenha());
    }
    
    @Test
    public void atualizarInvalido() {
        Funcionario funcionario = funcionarioServico.consultarPorId(new Long(7));
        funcionario.setLogin("jose luz"); //login com espaço
        try {
            funcionarioServico.atualizar(funcionario);
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
    } 
    

}
