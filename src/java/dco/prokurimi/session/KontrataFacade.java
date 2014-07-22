/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dco.prokurimi.session;

import dco.prokurimi.entity.Furnitori;
import dco.prokurimi.entity.Kontrata;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author pirota
 */
@Stateless
public class KontrataFacade extends AbstractFacade<Kontrata> {
    @PersistenceContext(unitName = "ProkurimiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KontrataFacade() {
        super(Kontrata.class);
    }
    
    public void Create(Kontrata kontrata){
        em.persist(kontrata);
    }
    
    public void Update(Kontrata kontrata){
        
        
        
        System.out.println("ID-ja e kontrates qe po editohet osth: " + String.valueOf(kontrata.getId()));
        Kontrata kontrataPrejBazes = em.find(Kontrata.class, kontrata.getId());
        System.out.println("Numri kontrates ne baze: " + kontrataPrejBazes.getNumriKontrates());
        String numriKontrates = kontrata.getNumriKontrates();
        System.out.println("Numri ri i kontrates ne baze: " + kontrataPrejBazes.getNumriKontrates());
       
        em.merge(kontrata);
        //EntityTransaction entr = em.getTransaction();
        //entr.begin();
        
        //kontrataPrejBazes.setNumriKontrates(numriKontrates);
        //em.getTransaction().commit();
         
    }
    
    public List<Kontrata> krejtKontratat(){
        
        TypedQuery<Kontrata> query =
                em.createNamedQuery("Kontrata.findAll", Kontrata.class );
        
        List<Kontrata> krejtKontratat = query.getResultList();
        System.out.println("Madhesia e krejtKontratat osht: " + String.valueOf(krejtKontratat));
        
        return krejtKontratat;
    }

    /**
    public List<Kontrata> kontratatEFurnitorit(BigDecimal furnitori) {
        TypedQuery<Kontrata> query = 
                em.createQuery("SELECT k FROM Kontrata k WHERE k.furnitori.id = :furnitori", Kontrata.class);
        
        
        return query.setParameter("furnitori", furnitori).getResultList();
    }
    */
}
