package fr.tse.fi2.hpp.labs.queries.impl.lab3;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

public class SumQueryThread extends AbstractQueryProcessor {
	
	private float sumFares=0;
	public SumQueryThread(QueryProcessorMeasure measure) {
		super(measure);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void process(DebsRecord record) {
		// TODO Auto-generated method stub
		sumFares += record.getFare_amount();
		//this.sumList.add(sumFares);
	}

}
