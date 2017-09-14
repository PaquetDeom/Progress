package fr.paquet.evaluation;

import fr.paquet.sequence.Validation;

public class Evaluation {

	public Evaluation(Validation valid) {

		this();
		valid.addEvaluation(this);
	}

	public Evaluation() {

		super();
	}


}
