package fr.tse.fi2.hpp.labs.queries.impl.lab2;

import java.util.concurrent.CountDownLatch;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.dispatcher.LoadFirstDispatcher;
import fr.tse.fi2.hpp.labs.main.MainNonStreaming;
import fr.tse.fi2.hpp.labs.queries.impl.lab1.NaiveAverage;


@State(Scope.Thread)
public class NonStreamingBenchmark {

	final static Logger logger = LoggerFactory
			.getLogger(MainNonStreaming.class);
	private NaiveAverage query;
	private LoadFirstDispatcher dispatch;
	
	
	@Setup
	public void prepare(){
		// Init query time measure
		QueryProcessorMeasure measure = new QueryProcessorMeasure();
		// Init dispatcher and load everything
		dispatch = new LoadFirstDispatcher(
				"src/main/resources/data/100k.csv");
		logger.info("Finished parsing");
		//Query to benchmark
		query = new NaiveAverage(measure);
		dispatch.registerQueryProcessor(query);
				
		/* ****************
		// Query processors

		List<AbstractQueryProcessor> processors = new ArrayList<>();
		//Declare query to benchmark here
		processors.add(new SumQueryThread(measure));
			
		// Register query processors
		for (AbstractQueryProcessor queryProcessor : processors) {
			dispatch.registerQueryProcessor(queryProcessor);
		}
		// Initialize the latch with the number of query processors
		CountDownLatch latch = new CountDownLatch(processors.size());
		// Set the latch for every processor
		for (AbstractQueryProcessor queryProcessor : processors) {
			queryProcessor.setLatch(latch);
		}*************************** */
	}
	
	
	@Benchmark
    @Fork(value = 2)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
	public boolean testMethod(){
		
		CountDownLatch latch = new CountDownLatch(1);
		query.setLatch(latch);
		Thread t = new Thread(query);
		t.setName("QP" + query.getId());
		t.start();
		dispatch.run();
		//logger.info("Finished Dispatching");
		try {
			latch.await();
		} catch (InterruptedException e) {
			logger.error("Error while waiting for the program to end", e);
		}

		
		return true;		
	}
	

	
}
