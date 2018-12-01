
package servico;

import consultoriodescorp.Remedio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
/**
 *
 * @author valeria
 */
@Stateless
public class RemedioServico extends Servico<Remedio> {
    @PostConstruct
    public void init() {
        super.setClasse(Remedio.class);
    }
    
    public void salvar(Remedio remedio){
        entityManager.persist(remedio);
    }
    
    public void atualizar(Remedio remedio){
        entityManager.merge(remedio);
        entityManager.flush();
    }
    
    public void remover(Remedio remedio){
        remedio = entityManager.merge(remedio);
        entityManager.remove(remedio);
    }
    
    public Remedio criar() {
        return new Remedio();
    }
    
    public List<Remedio> getRemedioPorTratamento(String remedio) {
        return super.getEntidades(Remedio.REMEDIO_POR_TRATAMENTO, new Object[] {remedio});
    }
     
    
}
