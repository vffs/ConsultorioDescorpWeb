
package servico;

import consultoriodescorp.Exame;
import static consultoriodescorp.Exame.EXAME_POR_NOME;
import javax.ejb.Stateless;

/**
 *
 * @author valeria
 */
@Stateless()
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
    public Exame getExame(String nome) {
        return super.getEntidade(EXAME_POR_NOME, new Object[]{nome});
    }
    
    public Exame criar() {
        return new Exame();
    }
    
}
