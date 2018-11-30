
package servico;

import consultoriodescorp.Funcionario;
import consultoriodescorp.TipoFuncionario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author valeria
 */
@Stateless()
public class FuncionarioServico extends Servico<Funcionario>  {
    
    @PostConstruct
    public void init() {
        super.setClasse(Funcionario.class);
    }
      
    public void salvar(Funcionario funcionario){
        entityManager.persist(funcionario);
    }
    
    public void atualizar(Funcionario funcionario){
        entityManager.merge(funcionario);
        entityManager.flush();
    }
    
    public void remover(Funcionario funcionario){
        funcionario = entityManager.merge(funcionario);
        entityManager.remove(funcionario);
    }
    
    public Funcionario criar() {
        return new Funcionario();
    }
    
     public List<Funcionario> getFuncionarioPorTipo(TipoFuncionario tipo) {
        return super.getEntidades(Funcionario.FUNCIONARIO_POR_TIPO, new Object[] {tipo});
    }
    
    
}
