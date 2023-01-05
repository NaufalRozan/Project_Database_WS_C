/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.ws.c.mantan;

import database.ws.c.mantan.exceptions.NonexistentEntityException;
import database.ws.c.mantan.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rozan
 */
public class C2020wsJpaController implements Serializable {

    public C2020wsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("database.ws.c_mantan_jar_0.0.1-SNAPSHOTPU");
    
    public C2020wsJpaController(){}

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(C2020ws c2020ws) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(c2020ws);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findC2020ws(c2020ws.getId()) != null) {
                throw new PreexistingEntityException("C2020ws " + c2020ws + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(C2020ws c2020ws) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            c2020ws = em.merge(c2020ws);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = c2020ws.getId();
                if (findC2020ws(id) == null) {
                    throw new NonexistentEntityException("The c2020ws with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            C2020ws c2020ws;
            try {
                c2020ws = em.getReference(C2020ws.class, id);
                c2020ws.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The c2020ws with id " + id + " no longer exists.", enfe);
            }
            em.remove(c2020ws);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<C2020ws> findC2020wsEntities() {
        return findC2020wsEntities(true, -1, -1);
    }

    public List<C2020ws> findC2020wsEntities(int maxResults, int firstResult) {
        return findC2020wsEntities(false, maxResults, firstResult);
    }

    private List<C2020ws> findC2020wsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(C2020ws.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public C2020ws findC2020ws(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(C2020ws.class, id);
        } finally {
            em.close();
        }
    }

    public int getC2020wsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<C2020ws> rt = cq.from(C2020ws.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
