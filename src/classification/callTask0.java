package classification;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.semanticweb.owlapi.model.OWLClassAxiom;

import reasoning.OWLParser;

public class callTask0 implements Callable <ArrayList<OWLClassAxiom>>{
	
	private OWLParser parser = null;
	private int index = 0;
	//private OWLOntologyManager manager = null;
	//private OWLReasoner reasoner = null;

	
	public callTask0(OWLParser parser,int index) {
		//this.manager = OWLManager.createOWLOntologyManager();
		//this.reasoner = new Reasoner(ontology);
		this.parser = parser;
		this.index = index;
	}

	public ArrayList<OWLClassAxiom> call() 
	{	
		ArrayList<OWLClassAxiom> axioms = parser.getSuperClassAxiom();

		if(index == 1)//superClass
		{
			axioms = parser.getSuperClassAxiom();
			//return axioms;
		}else if(index == 2)//subClass
		{
			 axioms = parser.getSubClassAxiom();
			 //return axioms;
		}else if(index == 3)//equivalentClass
		{
			axioms = parser.getEquivalentClassAxiom();
			//return axioms;
		}else if(index == 4)//disjointClass
		{
			axioms = parser.getDisjointClassAxiom();
			//return axioms;
		}
		return axioms;
	}
}
