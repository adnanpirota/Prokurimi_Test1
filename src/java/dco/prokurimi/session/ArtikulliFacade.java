/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dco.prokurimi.session;

import dco.prokurimi.entity.Artikulli;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author pirota
 */
@Stateless
public class ArtikulliFacade extends AbstractFacade<Artikulli> {
    @PersistenceContext(unitName = "ProkurimiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtikulliFacade() {
        super(Artikulli.class);
    }
    
    public void create(Artikulli artikulli){
        em.persist(artikulli);
    }
    
    public Artikulli gjejeSipasEmrit(String emri){
        
        TypedQuery<Artikulli> query = 
                em.createQuery("SELECT a FROM Artikulli a WHERE a.emriArtikullit = :emri", Artikulli.class);
        return query.setParameter("emri", emri).getSingleResult();
    }
    
}
