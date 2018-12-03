
package servico;
import consultoriodescorp.SolicitarExame;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author valeria
 */
@Stateless
public class SolicitarExameServico extends Servico<SolicitarExame> {
    
    @PostConstruct
    public void init() {
        super.setClasse(SolicitarExame.class);
    }
    
    public void salvar(SolicitarExame solicitar){
        entityManager.persist(solicitar);
    }
    
    public void atualizar(SolicitarExame solicitar){
        entityManager.merge(solicitar);
        entityManager.flush();
    }
    
    public void remover(SolicitarExame solicitar){
        solicitar = entityManager.merge(solicitar);
        entityManager.remove(solicitar);
    }
    
    public SolicitarExame criar() {
        return new SolicitarExame();
    }
}
