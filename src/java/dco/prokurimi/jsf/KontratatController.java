/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dco.prokurimi.jsf;

import dco.prokurimi.entity.Artikulli;
import dco.prokurimi.entity.Departamenti;
import dco.prokurimi.entity.Furnitori;
import dco.prokurimi.entity.Kontrata;
import dco.prokurimi.session.ArtikulliFacade;
import dco.prokurimi.session.DepartamentiFacade;
import dco.prokurimi.session.FurnitoriFacade;
import dco.prokurimi.session.KontrataFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author pirota
 */
@ManagedBean
@ViewScoped
public class KontratatController implements Serializable {
    
    @EJB
    KontrataFacade kontrataFacade;
    
    @EJB 
    FurnitoriFacade furnitoriFacade;
    
    @EJB
    ArtikulliFacade artikulliFacade;
    
    @EJB
    DepartamentiFacade departamentiFacade;
    
    private String numriKontrates;
    
    private List<Kontrata> krejtKontratat;
    private ArrayList<Kontrata> kontratatEFiltruara;
    private List<Furnitori> furnitoretPerEditRow;
    private String furnitori;
    private String artikulli;
    private String departamenti;
    
    private Date dataArritjes;
    
    private Date dataRealeArritjes;
    private List<Departamenti> departamentetObj;
    private List<String> departamentet;
    
    private String furnitoriSO;
    //private List<Furnitori> furnitoretObj;
    private List<Furnitori> furnitortSO;
    
    private String artikulliSO;
    private List<Artikulli> artikujtSO;
    
    private int sasia;
    private int avansi;
    
    private int cmimiKontrates;
    private int pjesaMbetur;
    
    private boolean permbyllur;

    /**
     * Creates a new instance of KontratatController
     */
    public KontratatController() {
    }
    
    @PostConstruct
    public void init(){
        
        krejtKontratat = new ArrayList<Kontrata>();
        krejtKontratat = kontrataFacade.krejtKontratat();
        
        departamentetObj = new ArrayList<Departamenti>();
        departamentetObj = departamentiFacade.findAll();
        
        
    }

    public List<Kontrata> getKrejtKontratat() {
        
        
        return krejtKontratat;
    }

    public void setKrejtKontratat(List<Kontrata> krejtKontratat) {
        this.krejtKontratat = krejtKontratat;
    }
    
    
    public List<Kontrata> teFiltruara(){
        
        kontratatEFiltruara = new ArrayList<Kontrata>();
        
        return kontratatEFiltruara;
    }
    
    public void onEdit(RowEditEvent event){
        System.out.println("Po editohet kontrata (objekti Kontrata nga eventi): " + ((Kontrata) event.getObject()).getNumriKontrates());
        Kontrata kontrataEdit = (Kontrata)event.getObject();
        
        System.out.println("Emri i furnitorit ne objektin e marur nga eventi: " + getFurnitori());
        System.out.println("ID-ja i furnitorit ne objektin e marur nga eventi: " + getFurnitori());
        String strFurnRi = getFurnitori();
        Furnitori furnitoriUpd = furnitoriFacade.gjejeSipasEmrit(strFurnRi);
        String strArtikulliEdit = getArtikulli();
        Artikulli artikulliUpd = artikulliFacade.gjejeSipasEmrit(strArtikulliEdit);
        Date dtArritjes = getDataArritjes();
        Date dtReal = getDataRealeArritjes();
        
        if (dtArritjes != null){
            System.out.println("Data Arritjes nuk osht null muj me bo update ");
            kontrataEdit.setKohaArritjesPort(dtArritjes);
        } else {
            System.out.println("Data Arritjes osht null nuk guxoj me bo update me bo update ");
        }
        
        if (dtReal != null){
            //System.out.println("Data Arritjes nuk osht null muj me bo update ");
            kontrataEdit.setKohaRealeArritjesPort(dtReal);
        } else {
            System.out.println("Data Arritjes osht null nuk guxoj me bo update me bo update ");
        }
        
        kontrataEdit.setFurnitori(furnitoriUpd);
        kontrataEdit.setArtikulli(artikulliUpd);
        
        //kontrataEdit.setKohaRealeArritjesPort(dtReal);
        //Artikulli artikulli = kontrataEdit.getArtikulli();
        //System.out.println(artikulli.getEmriArtikullit());
        
        
        
        kontrataFacade.Update(kontrataEdit);
         //event.getObject()

//System.out.println("Tu desht me nxon vleren qe po ndryshon" + ((Kontrata) event.getObject()).);
        
        //FacesMessage msg = new FacesMessage("Kontrata e edituar", ((Kontrata) event.getObject()).getNumriKontrates());
        
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
    
    public void onCancel(RowEditEvent event){
        FacesMessage msg = new FacesMessage("Anulohet editimi i ", ((Kontrata) event.getObject()).getNumriKontrates());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
    
    public String shtoKontrate(){
        
        Kontrata kontrataRe = new Kontrata();
        System.out.print("Artikulli osht: " + artikulliSO);
        
        Artikulli artikulliNeShtoKontrate = artikulliFacade.gjejeSipasEmrit(artikulliSO);
        System.out.print("Departamenti  osht: " + departamenti);
        Departamenti departamentiNeShtoKontrate = departamentiFacade.gjejeSipasEmrit(departamenti);
        System.out.print("Furnitori osht: " + furnitoriSO);
        Furnitori furnitoriNeShtoKontrate = furnitoriFacade.gjejeSipasEmrit(furnitoriSO);
        
        kontrataRe.setArtikulli(artikulliNeShtoKontrate);
        kontrataRe.setAvansi(avansi);
        kontrataRe.setCmimiKontrates(cmimiKontrates);
        kontrataRe.setDepartamenti(departamentiNeShtoKontrate);
        kontrataRe.setFurnitori(furnitoriNeShtoKontrate);
        kontrataRe.setKohaArritjesPort(dataArritjes);
        kontrataRe.setKohaRealeArritjesPort(dataRealeArritjes);
        kontrataRe.setNumriKontrates(numriKontrates);
        kontrataRe.setPjesaMbeturPerPagese(pjesaMbetur);
        kontrataRe.setSasia(sasia);
        
        kontrataFacade.Create(kontrataRe);
        
        artikulli = null;
        departamenti = null;
        furnitori = null;
        avansi = 0;
        cmimiKontrates = 0;
        dataArritjes = null;
        dataRealeArritjes = null;
        numriKontrates = null;
        pjesaMbetur = 0;
        sasia = 0;
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        context.addMessage(null, new FacesMessage("Kontrata eshte shtuar!"));
        
        
        
        return null;
    }
    
    
    
    public List<Furnitori> getFurnitort(){
        
        
        List<Furnitori> furnitoretPerEditRow = new ArrayList<Furnitori>();
        furnitoretPerEditRow = furnitoriFacade.findAll();
        
        return furnitoretPerEditRow;
        
    }

    public String getFurnitori() {
        return furnitori;
    }

    public void setFurnitori(String furnitori) {
        //System.out.println("Emri i furnitorit te setFurnitori osht: " + furnitori);
        this.furnitori = furnitori;
    }

    public List<Artikulli> getArtikujt(){
        
        
        List<Artikulli> artikujtPerEditRow = new ArrayList<Artikulli>();
        artikujtPerEditRow = artikulliFacade.findAll();
        
        return artikujtPerEditRow;
        
    }
    
    public String getArtikulli() {
        return artikulli;
    }

    public void setArtikulli(String artikulli) {
        this.artikulli = artikulli;
    }

    public Date getDataRealeArritjes() {
        return dataRealeArritjes;
    }

    public void setDataRealeArritjes(Date dataRealeArritjes) {
        this.dataRealeArritjes = dataRealeArritjes;
    }

    public Date getDataArritjes() {
        return dataArritjes;
    }

    public void setDataArritjes(Date dataArritjes) {
        System.out.println("Data Arritjes u vendos: " + String.valueOf(dataArritjes));
        this.dataArritjes = dataArritjes;
    }

    public String getDepartamenti() {
        return departamenti;
    }

    public void setDepartamenti(String departamenti) {
        this.departamenti = departamenti;
    }

    public List<Departamenti> getDepartamentetObj() {
        return departamentetObj;
    }

    public void setDepartamentetObj(List<Departamenti> departamentetObj) {
        this.departamentetObj = departamentetObj;
    }

    public List<String> getDepartamentet() {
        
        departamentet = new ArrayList<String>();
        List<Departamenti> departamentetObjPerListe = departamentiFacade.findAll();
        
        for (int i = 0; i < departamentetObjPerListe.size(); i++){
            departamentet.add(departamentetObjPerListe.get(i).getDepartamenti());
        }
        
        
        return departamentet;
    }

    public void setDepartamentet(List<String> departamentet) {
        this.departamentet = departamentet;
    }

    public String getFurnitoriSO() {
        return furnitoriSO;
    }

    public void setFurnitoriSO(String furnitoriSO) {
        this.furnitoriSO = furnitoriSO;
    }
    
    public void setFurnitort(List<Furnitori> furnitort){
        this.furnitortSO = furnitort;
    }
    
    public List<Furnitori> getFurnitortSO(){
        
        furnitortSO = new ArrayList<Furnitori>();
         furnitortSO = furnitoriFacade.findAll();
        
        //for (int i = 0; i < furnitoretObjPerListe.size(); i++){
          //  furnitortSO.add(furnitoretObjPerListe.get(i).getEmriFurnitorit());
        //}
        
        return furnitortSO;
        
    }

    public String getArtikulliSO() {
        return artikulliSO;
    }

    public void setArtikulliSO(String artikulliSO) {
        this.artikulliSO = artikulliSO;
    }

    public List<Artikulli> getArtikujtSO() {
        artikujtSO = new ArrayList<Artikulli>();
        artikujtSO = artikulliFacade.findAll();
        
        return artikujtSO;
    }

    public void setArtikujtSO(List<Artikulli> artikujtSO) {
        this.artikujtSO = artikujtSO;
    }

    public int getSasia() {
        return sasia;
    }

    public void setSasia(int sasia) {
        this.sasia = sasia;
    }

    public int getAvansi() {
        return avansi;
    }

    public void setAvansi(int avansi) {
        this.avansi = avansi;
    }

    public int getCmimiKontrates() {
        return cmimiKontrates;
    }

    public void setCmimiKontrates(int cmimiKontrates) {
        this.cmimiKontrates = cmimiKontrates;
    }

    public int getPjesaMbetur() {
        return pjesaMbetur;
    }

    public void setPjesaMbetur(int pjesaMbetur) {
        this.pjesaMbetur = pjesaMbetur;
    }

    public String getNumriKontrates() {
        return numriKontrates;
    }

    public void setNumriKontrates(String numriKontrates) {
        this.numriKontrates = numriKontrates;
    }

    public boolean isPermbyllur() {
        return permbyllur;
    }

    public void setPermbyllur(boolean permbyllur) {
        this.permbyllur = permbyllur;
    }
    
    
    
    

    
    
    

}
