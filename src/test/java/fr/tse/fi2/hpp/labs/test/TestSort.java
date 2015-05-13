package fr.tse.fi2.hpp.labs.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import fr.tse.fi2.hpp.labs.utils.MergeSort;

public class TestSort {

	@Test
	public void test() {
		MergeSort m = new MergeSort();
		int[] test = m.tabVal(20);
		int[] res=m.tri(test);
		int[] tri = test;
		Arrays.sort(tri);
		
		Assert.assertArrayEquals(tri, res);
		
	}

}
