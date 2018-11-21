
package servico;

import consultoriodescorp.Funcionario;
import javax.ejb.Stateless;

/**
 *
 * @author valeria
 */
@Stateless
public class FuncionarioServico extends Servico<Funcionario>  {

    public void salvar(Funcionario funcionario){
        entityManager.persist(funcionario);
    }
    
    public void atualizar(Funcionario funcionario){
        entityManager.merge(funcionario);
        entityManager.flush();
    }
    
    public void remover(Funcionario funcionario){
        entityManager.remove(funcionario);
    }
    public Funcionario criar() {
        return new Funcionario();
    }
    
}
