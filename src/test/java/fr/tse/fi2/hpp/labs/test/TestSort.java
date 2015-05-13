package fr.tse.fi2.hpp.labs.test;

import org.junit.Assert;
import org.junit.Test;

import fr.tse.fi2.hpp.labs.utils.MergeSort;

public class TestSort {

	@Test
	public void test() {
//		int[] nonTri = new int[3];
//		int[] Tri = new int[3];
//		int[] res = new int[3];
//		nonTri[0]=2;
//		nonTri[1]=4;
//		nonTri[2]=1;
//		Tri[0]=1;
//		Tri[1]=2;
//		Tri[2]=4;
//		
		int[] nonTri = new int[4];
		int[] Tri = new int[4];
		int[] res = new int[4];
		nonTri[0]=4;
		nonTri[1]=2;
		nonTri[2]=5;
		nonTri[3]=1;
		Tri[0]=1;
		Tri[1]=2;
		Tri[2]=4;
		Tri[3]=5;
		
		MergeSort m = new MergeSort();
		res=m.tri(nonTri);
		Assert.assertArrayEquals(Tri, res);
		
	}

}
