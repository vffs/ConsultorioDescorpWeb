
package bean;

import consultoriodescorp.Exame;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import servico.ExameServico;

/**
 *
 * @author valeria
 */
@RequestScoped
@Named
public class ExameBean extends Bean<Exame> implements Serializable{
    
    @Inject 
    private ExameServico exameServico;
    
    @Override
    protected void iniciarCampos() {
      setEntidade(exameServico.criar());
    }

    @Override
    protected boolean salvar(Exame entidade) throws ExcecaoNegocio {
        this.exameServico.salvar(entidade);
        return true;     
    }
    
}
