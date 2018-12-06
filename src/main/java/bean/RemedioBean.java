
package bean;

import consultoriodescorp.Remedio;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import servico.RemedioServico;

/**
 *
 * @author valeria
 */
@RequestScoped
@Named
public class RemedioBean extends Bean<Remedio> implements Serializable {

    @Inject
    private RemedioServico remedioServico;
    
    @Override
    protected void iniciarCampos() {
       setEntidade(remedioServico.criar());
    }

    @Override
    protected boolean salvar(Remedio entidade) throws ExcecaoNegocio {
        this.remedioServico.salvar(entidade);
        return true;
    }
    
}
