
package bean;

import consultoriodescorp.Paciente;
import consultoriodescorp.TipoPlanoSaude;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import servico.PacienteServico;

/**
 *
 * @author valeria
 */
@RequestScoped
@Named
public class Pacientebean extends Bean<Paciente> implements Serializable{
    @Inject
    private PacienteServico pacienteServico;
    
    @Override
    protected void iniciarCampos() {
         setEntidade(pacienteServico.criar());
    }

    @Override
    protected boolean salvar(Paciente entidade) throws ExcecaoNegocio {
        this.pacienteServico.salvar(entidade);
        return true;
    }
        

    
}
