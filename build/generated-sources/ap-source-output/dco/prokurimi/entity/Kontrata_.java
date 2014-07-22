package dco.prokurimi.entity;

import dco.prokurimi.entity.Artikulli;
import dco.prokurimi.entity.Departamenti;
import dco.prokurimi.entity.Furnitori;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-02-08T15:51:45")
@StaticMetamodel(Kontrata.class)
public class Kontrata_ { 

    public static volatile SingularAttribute<Kontrata, BigDecimal> id;
    public static volatile SingularAttribute<Kontrata, Date> kohaArritjesPort;
    public static volatile SingularAttribute<Kontrata, Integer> pjesaMbeturPerPagese;
    public static volatile SingularAttribute<Kontrata, Boolean> kompletuar;
    public static volatile SingularAttribute<Kontrata, Integer> cmimiKontrates;
    public static volatile SingularAttribute<Kontrata, String> numriKontrates;
    public static volatile SingularAttribute<Kontrata, Furnitori> furnitori;
    public static volatile SingularAttribute<Kontrata, Integer> avansi;
    public static volatile SingularAttribute<Kontrata, Date> kohaRealeArritjesPort;
    public static volatile SingularAttribute<Kontrata, Integer> sasia;
    public static volatile SingularAttribute<Kontrata, Departamenti> departamenti;
    public static volatile SingularAttribute<Kontrata, Artikulli> artikulli;

}