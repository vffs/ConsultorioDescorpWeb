
package servico;

import consultoriodescorp.Paciente;
import javax.ejb.Stateless;

/**
 *
 * @author valeria
 */
@Stateless
public class PacienteServico extends Servico<Paciente> {
    
    public void salvar(Paciente paciente){
        entityManager.persist(paciente);
    }
    
    public void atualizar(Paciente paciente){
        entityManager.merge(paciente);
        entityManager.flush();
    }
    
    public void remover(Paciente paciente){
        entityManager.remove(paciente);
    }
    
    public Paciente criar() {
        return new Paciente();
    }
}
