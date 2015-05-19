package fr.tse.fi2.hpp.labs.test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import org.junit.Assert;
import org.junit.Test;

import fr.tse.fi2.hpp.labs.utils.MergeSortParal;

public class TestSortParal {

	@Test
	public void test() {
		int nb=10000;	
		int[] tab = new int[nb];
		int[] tabtri = new int[nb];
		Random rdm = new Random();
		
		for (int i = 0; i < nb; i++){
			
			int randomNum = rdm.nextInt(i+1);
			tab[i]=(randomNum);
			tabtri[i] = tab[i];
	
		}
		Arrays.sort(tabtri);


		
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		MergeSortParal m = new MergeSortParal(tab);
		forkJoinPool.invoke(m);
		
		Assert.assertArrayEquals(tabtri, m.getTab());
	}

}
