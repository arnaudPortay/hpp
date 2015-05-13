package benchmarks;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import fr.tse.fi2.hpp.labs.utils.MergeSort;

@State(Scope.Thread)
public class BenchmarkSorting {
	
	private MergeSort m;
	private int[] tab;

	@Setup
	public void prepare(){
		m=new MergeSort();
		tab=m.tabVal(1000000);
	}
	
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(1)
	@Warmup(iterations=3)
	@Measurement(iterations=3)
	public void testMethod() {
		m.tri(tab);
	}
	

}
