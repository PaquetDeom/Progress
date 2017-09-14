package fr.paquet.referentiel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-18T08:11:21.140+0100")
@StaticMetamodel(Activite.class)
public class Activite_ {
	public static volatile SingularAttribute<Activite, Integer> id;
	public static volatile SingularAttribute<Activite, String> intitule;
	public static volatile SingularAttribute<Activite, String> typeTache;
	public static volatile SingularAttribute<Activite, Fonction> fonction;
}
