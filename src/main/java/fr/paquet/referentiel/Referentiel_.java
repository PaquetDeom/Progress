package fr.paquet.referentiel;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-18T08:11:21.227+0100")
@StaticMetamodel(Referentiel.class)
public class Referentiel_ {
	public static volatile SingularAttribute<Referentiel, Integer> id;
	public static volatile SingularAttribute<Referentiel, String> diplome;
	public static volatile SingularAttribute<Referentiel, String> intitule;
	public static volatile SingularAttribute<Referentiel, Rap> rap;
	public static volatile ListAttribute<Referentiel, Capacite> capacites;
}
