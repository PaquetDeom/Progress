package fr.paquet.donneeTest;

import fr.paquet.sequence.Sequence;

public class SequenceTest {

	public static Sequence getSequence() throws Exception {

		Sequence sequence = new Sequence(ProgressionTest.getProgression(), "SequenceTest");

		sequence.setDebut(1);
		sequence.setFin(5);

		return sequence;
	}

}
