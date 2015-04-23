package fr.tse.fi2.hpp.labs.queries.impl.lab3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ThreadWrite implements Runnable  {
	

	private BufferedWriter outputWriter;
	private BlockingQueue<Float> queue;
	private int id;
	private boolean poison;

	public ThreadWrite(BlockingQueue<Float> queue,int id){
		super();
		this.id=id;
		this.queue=queue;
		poison=false;
		try {
			outputWriter = new BufferedWriter(new FileWriter(new File(
					"result/query" + id + ".txt")));
		} catch (IOException e) {
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(!poison){
			try {
				writeLine(queue.take().toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		finish();
	}
	
	protected void writeLine(String line) {
		try {
			outputWriter.write(line);
			outputWriter.newLine();
		} catch (IOException e) {
		}

	}
	
	private void finish() {
		// Close writer
		try {
			outputWriter.flush();
			outputWriter.close();
		} catch (IOException e) {
		}
	}
	
	public void setPoison(boolean b){
		poison=b;
	}

}
