package fr.paquet.referentiel;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-18T08:11:21.207+0100")
@StaticMetamodel(Rap.class)
public class Rap_ {
	public static volatile SingularAttribute<Rap, Integer> id;
	public static volatile ListAttribute<Rap, Fonction> fonctions;
	public static volatile SingularAttribute<Rap, Referentiel> referentiel;
}
