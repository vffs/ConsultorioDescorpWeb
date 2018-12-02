
package servico;

import consultoriodescorp.MarcarConsulta;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author valeria
 */
@Stateless
public class MarcarConsultaServico extends Servico<MarcarConsulta> {
    @PostConstruct
    public void init() {
        super.setClasse(MarcarConsulta.class);
    }
    
    public void salvar(MarcarConsulta marcar){
        entityManager.persist(marcar);
    }
    
    public void atualizar(MarcarConsulta marcar){
        entityManager.merge(marcar);
        entityManager.flush();
    }
    
    public void remover(MarcarConsulta marcar){
        marcar = entityManager.merge(marcar);
        entityManager.remove(marcar);
    }
    
    public MarcarConsulta criar() {
        return new MarcarConsulta();
    }
}
