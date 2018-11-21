
package servico;

import consultoriodescorp.Receita;
import javax.ejb.Stateless;
/**
 *
 * @author valeria
 */
@Stateless
public class ReceitaServico extends Servico<Receita> {
    
    public void salvar(Receita receita){
        entityManager.persist(receita);
    }
    
    public void atualizar(Receita receita){
        entityManager.merge(receita);
        entityManager.flush();
    }
    
    public void remover(Receita receita){
        entityManager.remove(receita);
    }
    
    public Receita criar() {
        return new Receita();
    }
}
