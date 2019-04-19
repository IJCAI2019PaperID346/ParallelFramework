package classification;

import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import dataStructure.atomicNodelist;

public class runTask0 implements Runnable
{
	private OWLOntologyManager manager1 = null;
	private OWLReasoner reasoner1 = null;
	private ArrayList<OWLClassAxiom> axioms1 = null;
	private int index1 = 0;
	private atomicNodelist list1 = null;
	
	
	public runTask0(OWLReasoner reasoner, OWLOntologyManager manager,/* OWLOntology ontology,*/ atomicNodelist list, ArrayList<OWLClassAxiom> axioms, int index)
	{
		this.reasoner1 = reasoner;
		this.manager1 = manager;
		list1 = list;
		//elementsNode1 = list.elementsNode;
		index1 = index;
		axioms1 = axioms;
	}

	@Override
	public void run() 
	{	
		if(index1 == 1)//superClass
		{
			list1.ExtractSuperClassAxioms(manager1, reasoner1, axioms1);
		}else if(index1 == 2)//subClass
		{
			list1.ExtractSubClassAxioms(manager1, reasoner1, axioms1);
		}else if(index1 == 3)//equivalentClass
		{
			list1.ExtractEquivalentClassAxioms(axioms1);
		}else if(index1 == 4)//disjointClass
		{
			list1.ExtractDisjointClassAxioms(axioms1);
		}
	}
	

}
