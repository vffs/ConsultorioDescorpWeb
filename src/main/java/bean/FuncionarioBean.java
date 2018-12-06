
package bean;

import consultoriodescorp.Funcionario;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import servico.FuncionarioServico;

/**
 *
 * @author valeria
 */
@RequestScoped
@Named
public class FuncionarioBean extends Bean<Funcionario> implements Serializable {

    @Inject
    private FuncionarioServico funcionarioServico;

    @Override
    protected void iniciarCampos() {
        setEntidade(funcionarioServico.criar());
    }

    @Override
    protected boolean salvar(Funcionario entidade) throws ExcecaoNegocio {
       this.funcionarioServico.salvar(entidade);
       return true;
    }
    
    
    
    
}
