/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dco.prokurimi.jsf;

import dco.prokurimi.entity.Furnitori;
import dco.prokurimi.entity.Kontrata;
import dco.prokurimi.session.FurnitoriFacade;
import dco.prokurimi.session.KontrataFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author pirota
 */
@ManagedBean(name="furnitoriController")
@ViewScoped
public class FurnitoriController {

    @EJB
    FurnitoriFacade furnitoriFacade;
    
    @EJB
    KontrataFacade kontrataFacade;
    
    private String emriFurnitorit;
    private String telefoni;
    private String emaili;
    
    private String furnitoriZgjedhur;
    private List<Furnitori> furnitortLista;
    
    private List<Kontrata> kontratatEFurnitorit;
    
            
    
    
    public FurnitoriController() {
    }
    
    public String shtoFurnitorin(){
        
        Furnitori furnitoriIRi = new Furnitori();
        furnitoriIRi.setEmriFurnitorit(emriFurnitorit);
        furnitoriIRi.setTel(telefoni);
        furnitoriIRi.setEmaili(emaili);
        
        furnitoriFacade.create(furnitoriIRi);
        
        emriFurnitorit = null;
        telefoni = null;
        emaili = null; 
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        context.addMessage(null, new FacesMessage("Furnitori u shtua me sukses"));
        
        
        return null;
    }

    public String getEmriFurnitorit() {
        return emriFurnitorit;
    }

    public void setEmriFurnitorit(String emriFurnitorit) {
        this.emriFurnitorit = emriFurnitorit;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(String telefoni) {
        this.telefoni = telefoni;
    }

    public String getEmaili() {
        return emaili;
    }

    public void setEmaili(String emaili) {
        this.emaili = emaili;
    }

    public List<Furnitori> getFurnitortLista() {
        
        furnitortLista = new ArrayList<Furnitori>();
        furnitortLista = furnitoriFacade.findAll();
        return furnitortLista;
    }

    public void setFurnitortLista(List<Furnitori> furnitortLista) {
        this.furnitortLista = furnitortLista;
    }

    public String getFurnitoriZgjedhur() {
        return furnitoriZgjedhur;
    }

    public void setFurnitoriZgjedhur(String furnitoriZgjedhur) {
        System.out.println("Te setFurnitoriZgjedhur u vendos vlera: " + furnitoriZgjedhur);
        this.furnitoriZgjedhur = furnitoriZgjedhur;
    }


    public List<Kontrata> getKontratatEFurnitorit() {
        
        System.out.println("Nuk pe kuptoj pse po hin te getKontratEFurnitorit ne start?!!");
        
        
        String  strFurnitori = getFurnitoriZgjedhur();
        
        if (strFurnitori != null) {
                    Furnitori furnZgjedhur = furnitoriFacade.gjejeSipasEmrit(strFurnitori);
                    System.out.println("Furnitori i zgjedhur e ka IDn - " + String.valueOf(furnZgjedhur.getId()));
                    
                    //BigDecimal idjaFurnitorit = furnZgjedhur.getId();
                    
                    kontratatEFurnitorit = new ArrayList<Kontrata>();
        List<Kontrata> krejtKontratat = kontrataFacade.findAll();
        System.out.println("Furnitori " + strFurnitori + " ka gjithsej " + String.valueOf(kontratatEFurnitorit.size()) + " kontrata ne sistem ...");
        
        for (int i = 0; i < krejtKontratat.size(); i ++ ){
            
            if (krejtKontratat.get(i).getFurnitori().getId() == furnZgjedhur.getId()){
                kontratatEFurnitorit.add(krejtKontratat.get(i));
            } else {
                System.out.println("Kjo kontrate nuk osht pjese e kushtit per shkak se id e furnitorit osht: " + String.valueOf(krejtKontratat.get(i).getFurnitori().getId()));
            }
        }

        //kontratatEFurnitorit = kontrataFacade.kontratatEFurnitorit(idjaFurnitorit);
        
        
        
        return kontratatEFurnitorit;
        } else {
            System.out.println("Vlera e strFurnitori osht null ... ");
            return null; 
        }

        
        
        
        
    }

    public void setKontratatEFurnitorit(List<Kontrata> kontratatEFurnitorit) {
        this.kontratatEFurnitorit = kontratatEFurnitorit;
    }
    
}
