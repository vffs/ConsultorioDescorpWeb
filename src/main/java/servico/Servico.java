
package servico;

import static javax.persistence.PersistenceContextType.TRANSACTION;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


public abstract class Servico<T> {

    @PersistenceContext(name = "consultorio", type = TRANSACTION)
    protected EntityManager entityManager;
    protected Class<T> classe;

    @TransactionAttribute(SUPPORTS)
    protected List<T> getEntidades(String nomeQuery) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);
        return query.getResultList();
    }

    @TransactionAttribute(SUPPORTS)
    protected List<T> getEntidades(String nomeQuery, Object[] parametros) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);

        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter(i++, parametro);
        }

        return query.getResultList();
    }

    @TransactionAttribute(SUPPORTS)
    protected T getEntidade(String nomeQuery, Object[] parametros) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);

        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter(i++, parametro);
        }

        return query.getSingleResult();
    }
    
}
