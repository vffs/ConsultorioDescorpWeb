
package servico;

import consultoriodescorp.Remedio;
import javax.ejb.Stateless;
/**
 *
 * @author valeria
 */
@Stateless
public class RemedioServico extends Servico<Remedio> {
    
    public void salvar(Remedio remedio){
        entityManager.persist(remedio);
    }
    
    public void atualizar(Remedio remedio){
        entityManager.merge(remedio);
        entityManager.flush();
    }
    
    public void remover(Remedio remedio){
        entityManager.remove(remedio);
    }
    
    public Remedio criar() {
        return new Remedio();
    }
    
}
