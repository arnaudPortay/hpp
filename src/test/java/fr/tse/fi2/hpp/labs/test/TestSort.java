package fr.tse.fi2.hpp.labs.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import fr.tse.fi2.hpp.labs.utils.MergeSort;

public class TestSort {

	@Test
	public void test() {
		MergeSort m = new MergeSort();
		int[] test = m.tabVal(10);
		int[] res=m.tri(test);
		int[] tri = new int[10];
		
		for (int i = 0; i < 10; i++){
			tri[i] = test[i];	
		}
		Arrays.sort(tri);
		
		Assert.assertArrayEquals(tri, res);
		
	}

}
