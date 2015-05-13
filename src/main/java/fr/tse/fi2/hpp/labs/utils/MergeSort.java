package fr.tse.fi2.hpp.labs.utils;

import java.util.Arrays;
import java.util.Random;

public class MergeSort {

	
	public MergeSort(){
	}
	
	public int[] tri(int[] tab){
		int[] res = new int[tab.length];
		
		if (tab.length==1)
		{
			res[0]=tab[0];
		}
		else if (tab.length == 2)
		{
			if (tab[0]>tab[1])
			{
				res[0]=tab[1];
				res[1]=tab[0];
			}
			else
			{
				res[0]=tab[0];
				res[1]=tab[1];
			}
		}
		else if (tab.length >2)
		{
			int[] tempGauche=null;
			int[] tempDroit=null;
			
			if (tab.length<20)
			{
				for(int i=1;i<tab.length;i++)
	            {
	            int memory=tab[i];
	            int compt=i-1;
	            boolean marqueur;
	            do
	                {
	                marqueur=false;
	                if (tab[compt]>memory)
	                    {
	                	tab[compt+1]=tab[compt];
	                    compt--;
	                    marqueur=true;
	                    }
	                if (compt<0) marqueur=false;
	                }
	            while(marqueur);
	            tab[compt+1]=memory;
	            }
				
		        return tab;
			}
			
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
			else
			{
				tempDroit = new int[(tab.length/2)+1];
				tempGauche = new int[tab.length/2];
				
				for (int j=0;j<(tab.length/2)+1;j++)
				{
					if (j <(tab.length/2))
					{
						tempGauche[j]=tab[j];
					}
					tempDroit[j]=tab[(tab.length/2)+j];
				}
			}			
			
			int[] resGauche = this.tri(tempGauche);
			int[] resDroit = this.tri(tempDroit);
			
			System.arraycopy(resGauche, 0, res, 0, resGauche.length);
			System.arraycopy(resDroit, 0, res, resGauche.length, resDroit.length);
			Arrays.sort(res);		
			
		}
		return res;
	}
	
	public static int[] tabVal(int taille){
			
			int[] tab = new int[taille];
			Random rdm = new Random();
			
			for (int i = 0; i < taille; i++){
				
				int randomNum = rdm.nextInt(i+1);
				tab[i]=(randomNum);
		
			}
			return tab;
	}

}
