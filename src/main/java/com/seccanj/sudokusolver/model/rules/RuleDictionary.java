package com.seccanj.sudokusolver.model.rules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.seccanj.sudokusolver.model.Rule;

public class RuleDictionary implements Iterable<Rule> {

	private List<Rule> rules = new ArrayList<Rule>();
	
	public RuleDictionary() {
		//rules.add(new SingleHypothesis());
		
		rules.add(new SinglePositionInSquare());
		rules.add(new SinglePositionInRow());
		rules.add(new CandidateLinesInColumn());
		rules.add(new CandidateLinesInRow());
		rules.add(new HiddenPairsInRow());
		rules.add(new HiddenPairsInColumn());
	}

	@Override
	public Iterator<Rule> iterator() {
		return rules.iterator();
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	
}
