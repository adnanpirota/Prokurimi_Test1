/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dco.prokurimi.session;

import dco.prokurimi.entity.Furnitori;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author pirota
 */
@Stateless
public class FurnitoriFacade extends AbstractFacade<Furnitori> {
    @PersistenceContext(unitName = "ProkurimiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FurnitoriFacade() {
        super(Furnitori.class);
    }
    
    public void create(Furnitori furnitori){
        em.persist(furnitori);
    }
    
    public Furnitori gjejeSipasEmrit(String furnitori){
        
        TypedQuery<Furnitori> query = 
                em.createQuery("SELECT f FROM Furnitori f WHERE f.emriFurnitorit = :emertimi",Furnitori.class);
        
        return query.setParameter("emertimi", furnitori).getSingleResult();
    }
    
}
