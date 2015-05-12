package fr.tse.fi2.hpp.labs.queries.impl.lab5;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

import org.apache.commons.lang.RandomStringUtils;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

public class BloomFilter extends AbstractQueryProcessor {

	private final int k;
	private String[] salt;
	private BitSet bloomFilter;
	
	
	public BloomFilter(QueryProcessorMeasure measure){
		super(measure);

		k=10;
		bloomFilter = new BitSet(14378);
		salt = new String[k];
		for (int i=0;i<k;i++)
		{
			salt[i]=RandomStringUtils.random(10);
		}
		
		
		
	}

	@Override
	protected void process(DebsRecord record) 
	{
		// TODO Auto-generated method stub
		String str = record.getHack_license() + record.getDropoff_latitude() + record.getDropoff_longitude() + 
				record.getPickup_latitude() + record.getPickup_longitude();
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[] str2hash = new String[k];
		for (int i=0;i<10;i++)
		{
			str2hash[i]=salt[i]+str;
			try {
				md.update(str2hash[i].toString().getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] res = md.digest();
			BigInteger indiceB = new BigInteger(res);
			int indice = indiceB.intValue();
			if (indice<0) 
				indice=-indice;
			indice=indice %  14378;
			if (!bloomFilter.get(indice)) 
				bloomFilter.set(indice);
		}
		
	}
	
	public boolean contain(String plaque, float loD,float laD,float loA, float laA)
	{	
		String str = plaque +laA + loA + laD + loD;
		String[] str2hash = new String[k];
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (int i=0;i<10;i++)
		{
			str2hash[i]=salt[i]+str;
			try {
				md.update(str2hash.toString().getBytes("UTF-16"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] res = md.digest();
			BigInteger indiceB = new BigInteger(res);
			int indice = indiceB.intValue();
			
			if (indice<0) 
				indice=-indice;
			indice=indice %  14378;
			if (!bloomFilter.get(indice))
				return false;
		}
		return true;
	}

}
