package fr.tse.fi2.hpp.labs.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveAction;


public class MergeSortParal extends RecursiveAction {

	private int[] tab;
	
	public MergeSortParal(int[] tab) {
		this.tab=tab;
	}

	@Override
	protected void compute() {
		
		if (tab.length>20)
		{
			System.out.println("rentré dans >20");
			
			
			//Create subtasks
            List<MergeSortParal> subtasks = new ArrayList<MergeSortParal>();
            
            subtasks.addAll(createSubtasks());

            for(MergeSortParal subtask : subtasks){
                subtask.fork();
            }
            
            for(MergeSortParal subtask : subtasks){
                subtask.join();
            }
                       	     		
            System.arraycopy(subtasks.get(0).getTab(), 0, tab, 0, subtasks.get(0).getTab().length);
            System.arraycopy(subtasks.get(1).getTab(), 0, tab, subtasks.get(0).getTab().length, subtasks.get(1).getTab().length);
            Arrays.sort(tab);            
        }
		else
		{
			System.out.println("<20");
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
                	tab[compt+1]=getTab()[compt];
                    compt--;
                    marqueur=true;
                    }
                if (compt<0) marqueur=false;
                }
            while(marqueur);
            tab[compt+1]=memory;
            }			
		}
		
		
	}

    private List<MergeSortParal> createSubtasks() {
        List<MergeSortParal> subtasks = new ArrayList<MergeSortParal>();
        int[] tempGauche=null;
		int[] tempDroit=null;
		
		if (getTab().length % 2 == 0)
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
		

        MergeSortParal subtask1 = new MergeSortParal(tempGauche);
        MergeSortParal subtask2 = new MergeSortParal(tempDroit);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }

	public int[] getTab() {
		return tab;
	}

	public void setTab(int[] tab) {
		this.tab = tab;
	}
}
