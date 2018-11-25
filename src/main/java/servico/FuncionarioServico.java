
package servico;

import consultoriodescorp.Funcionario;
import static consultoriodescorp.Funcionario.FUNCIONARIO_POR_TIPO;
import consultoriodescorp.TipoFuncionario;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

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
        entityManager.remove(funcionario);
    }
    
    public Funcionario criar() {
        return new Funcionario();
    }
    public Funcionario geTipo(TipoFuncionario tipo ) {
        return super.getEntidade(FUNCIONARIO_POR_TIPO, new Object[]{tipo});
    }
    
    
}
