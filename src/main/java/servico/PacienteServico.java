
package servico;

import consultoriodescorp.Paciente;
import consultoriodescorp.TipoPlanoSaude;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author valeria
 */
@Stateless
public class PacienteServico extends Servico<Paciente> {
     
    @PostConstruct
    public void init() {
        super.setClasse(Paciente.class);
    }
    
    public void salvar(Paciente paciente){
        entityManager.persist(paciente);
    }
    
    public void atualizar(Paciente paciente){
        entityManager.merge(paciente);
        entityManager.flush();
    }
    
    public void remover(Paciente paciente){
        paciente = entityManager.merge(paciente);
        entityManager.remove(paciente);
    }
    
    public Paciente criar() {
        return new Paciente();
    }
    
     public List<Paciente> getPacientePorPlano(TipoPlanoSaude tipo) {
        return super.getEntidades(Paciente.PACIENTE_POR_PLANO, new Object[] {tipo});
    }
}
