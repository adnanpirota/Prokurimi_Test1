/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dco.prokurimi.jsf;

import dco.prokurimi.entity.Artikulli;
import dco.prokurimi.entity.Kontrata;
import dco.prokurimi.session.ArtikulliFacade;
import dco.prokurimi.session.KontrataFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author pirota
 */
@ManagedBean(name="artikulliController")
@RequestScoped
public class ArtikulliController {

    @EJB
    ArtikulliFacade artikulliFacade;
    
    @EJB
    KontrataFacade kontrataFacade;
    
    private String emriArtikullit;
    private String pershkrimi;
    
    private String artikulliZgjedhur;
    
    private List<Artikulli> artikujtLista;
    
    private List<Kontrata> kontratatEArtikullit;
    
   
    
    
    public ArtikulliController() {
    }
    
    public String shtoArtikullin(){
        
        Artikulli artikulliIRi = new Artikulli();
        artikulliIRi.setEmriArtikullit(emriArtikullit);
        artikulliIRi.setPershkrimi(pershkrimi);
        
        artikulliFacade.create(artikulliIRi);
        
        emriArtikullit = null;
        pershkrimi = null;
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        context.addMessage(null, new FacesMessage("Artikulli u shtua me sukses"));
        
        return null;
        
    }

    public String getEmriArtikullit() {
        return emriArtikullit;
    }

    public void setEmriArtikullit(String emriArtikullit) {
        this.emriArtikullit = emriArtikullit;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public String getArtikulliZgjedhur() {
        return artikulliZgjedhur;
    }

    public void setArtikulliZgjedhur(String artikulliZgjedhur) {
        this.artikulliZgjedhur = artikulliZgjedhur;
    }

    public List<Artikulli> getArtikujtLista() {
        
        artikujtLista = new ArrayList<Artikulli>();
        artikujtLista = artikulliFacade.findAll();
        
        return artikujtLista;
        
        
    }

    public void setArtikujtLista(List<Artikulli> artikujtLista) {
        this.artikujtLista = artikujtLista;
    }

    public List<Kontrata> getKontratatEArtikullit() {
        String strArtikulli = getArtikulliZgjedhur();
        
        if (strArtikulli != null){
            Artikulli artZgjedhur = artikulliFacade.gjejeSipasEmrit(strArtikulli);
            
            kontratatEArtikullit = new ArrayList<Kontrata>();
            
            List<Kontrata> krejtKontratat = kontrataFacade.findAll();
            
            for (int i = 0; i < krejtKontratat.size(); i++){
                if (krejtKontratat.get(i).getArtikulli().getId() == artZgjedhur.getId()){
                    kontratatEArtikullit.add(krejtKontratat.get(i));
                } else {
                    
                }
            }
            
            return kontratatEArtikullit;
        } else {
            return null;
        }
    }

    public void setKontratatEArtikullit(List<Kontrata> kontratatEArtikullit) {
        this.kontratatEArtikullit = kontratatEArtikullit;
    }
    
}
