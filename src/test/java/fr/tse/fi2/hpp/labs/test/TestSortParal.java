package fr.tse.fi2.hpp.labs.test;

import java.util.concurrent.ForkJoinPool;

import org.junit.Assert;
import org.junit.Test;

import fr.tse.fi2.hpp.labs.utils.MergeSortParal;

public class TestSortParal {

	@Test
	public void test() {
		int[] tab = new int[4];
		tab[0]=5;
		tab[1]=2;
		tab[2]=4;
		tab[3]=1;
		
		int[] tabtri = new int[4];
		tab[0]=1;
		tab[1]=2;
		tab[2]=4;
		tab[3]=5;
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		MergeSortParal m = new MergeSortParal(tab);
		forkJoinPool.invoke(m);
		
		String s="patate";
		for (int i=0;i<m.getTab().length;i++)
		{
			s=""+m.getTab()[i];
		}
		
		System.out.println(s);
		
		Assert.assertArrayEquals(tabtri, m.getTab());
	}

}
