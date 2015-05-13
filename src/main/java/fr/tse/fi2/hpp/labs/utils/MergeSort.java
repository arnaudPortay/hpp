package fr.tse.fi2.hpp.labs.utils;

public class MergeSort {

	
	public MergeSort(){
	}
	
	public int[] tri(int[] tab){
		int[] res = new int[tab.length];
		
		if (tab.length == 2)
		{
			if (tab[0]>tab[1])
			{
				res[0]=tab[1];
				res[1]=tab[0];
			}
		}
		else if (tab.length >2)
		{
			int[] tempGauche=null;
			int[] tempDroit=null;
			
			
			if (tab.length % 2 == 0)
			{
				tempDroit = new int[tab.length/2];
				tempGauche = new int[tab.length/2];
				
				for (int i=0;i<(tab.length/2);i++)
				{
					tempGauche[i]=tab[i];
					tempDroit[i]=tab[(tab.length/2)+i];
				}
			}
			
			String s = "";
			for (int i=0;i<tempGauche.length;i++)
				s=s+tempGauche[i]+" ";
			System.out.println("gauche "+s);
			
			String s2 = "";
			for (int i=0;i<tempDroit.length;i++)
				s2=s2+tempDroit[i]+" ";
			System.out.println("droit "+s2);
//			else
//			{
//				tempDroit = new int[(tab.length/2)+1];
//				tempGauche = new int[tab.length/2];
//				
//				for (int j=0;j<(tab.length/2)+1;j++)
//				{
//					if (j >(tab.length/2))
//					{
//						System.out.println(j);
//						tempGauche[j]=tab[j];
//						System.out.println("gauche"+tempGauche[j]);
//					}
//					tempDroit[j]=tab[(tab.length/2)+j];
//				}
//			}
							
			
			int[] resGauche = this.tri(tempGauche);
			int[] resDroit = this.tri(tempDroit);
			
		
			
		}
		String s3 = "";
		for (int i=0;i<res.length;i++)
			s3=s3+res[i]+" ";
		System.out.println("tableau "+s3);
		return res;
	}
	
	private int[] fusion(int[] gauche, int[] droit)
	{
		boolean fini=false;
		int[] res = new int[gauche.length+droit.length];
		while(!fini)
		{
			for (int i=0;i<gauche.length;i++)
			{
				if (gauche[i]<droit[i] && i<droit.length)
				{
					res[i]=gauche[i];
					
					
				}
			}
		}
		
		return res;
		
	}

}
