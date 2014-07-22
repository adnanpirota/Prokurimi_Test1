/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dco.prokurimi.session;

import dco.prokurimi.entity.Departamenti;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author pirota
 */
@Stateless
public class DepartamentiFacade extends AbstractFacade<Departamenti> {
    @PersistenceContext(unitName = "ProkurimiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentiFacade() {
        super(Departamenti.class);
    }
    
    public Departamenti gjejeSipasEmrit(String departamenti){
        
        TypedQuery<Departamenti> query = 
                em.createQuery("SELECT d FROM Departamenti d WHERE d.departamenti = :departamenti", Departamenti.class);
        
        return query.setParameter("departamenti", departamenti).getSingleResult();
    }
    
}
