
package servico;

import consultoriodescorp.Exame;
import javax.ejb.Stateless;

/**
 *
 * @author valeria
 */
@Stateless
public class ExameServico extends Servico<Exame>{
    
    public void salvar(Exame exame){
        entityManager.persist(exame);
    }
    
    public void atualizar(Exame exame){
        entityManager.merge(exame);
        entityManager.flush();
    }
    
    public void remover(Exame exame){
        entityManager.remove(exame);
    }
    
    public Exame criar() {
        return new Exame();
    }
    
}
