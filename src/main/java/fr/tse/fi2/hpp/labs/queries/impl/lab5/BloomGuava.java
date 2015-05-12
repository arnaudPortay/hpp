package fr.tse.fi2.hpp.labs.queries.impl.lab5;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

public class BloomGuava extends AbstractQueryProcessor {

	public BloomGuava(QueryProcessorMeasure measure) {
		super(measure);
		// TODO Auto-generated constructor stub
		
		Funnel<DebsRecord> recordFunnel = new Funnel<DebsRecord>(){
			@Override
			public void funnel(DebsRecord record, PrimitiveSink into)
			{
				into.putString(record.getHack_license(), Charsets.UTF_8)
					.putFloat(record.getPickup_longitude())
					.putFloat(record.getPickup_latitude())
					.putFloat(record.getDropoff_longitude())
					.putFloat(record.getDropoff_latitude());
			}
		};
		
		BloomFilter<DebsRecord> filtre = new BloomFilter.create(recordFunnel,1000,0.01);
		
	}

	@Override
	protected void process(DebsRecord record) {
		// TODO Auto-generated method stub

	}
	

}
