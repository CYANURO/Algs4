/**
 * 
 */
package percolation;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdOut;;


/**
 * @author Cyanuro
 *
 */
public class PercolationStats {

	private final int gridSizeN;
	private final int indepT;
	private final double[] thresholdCount;
	
	
	public PercolationStats(int N, int T) throws IllegalArgumentException {
		
		illegalArgumentCheck(N, T);
		
		thresholdCount = new double[T];
		gridSizeN = N;
		indepT = T;
		
		// perform T independent computational experiments on an N-by-N grid
		monteCarloSimulation();
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		printSimulation(200, 100);
		
		printSimulation(200, 100);
			
		printSimulation(2, 1000);
		
	}
	
	
	private void monteCarloSimulation() {
		
		final int gridSize = gridSizeN;
		final int computationT = indepT;
		final double totalSites = gridSize * gridSize;
		
		for(int i = 0; i < computationT; i++) {
			
			double openSites = 0;
			Percolation percolationGrid = new Percolation(gridSize);
			
			while(!percolationGrid.percolates()) {
				
				int randomRow = randomInt(gridSize);
				int randomCol = randomInt(gridSize);
				
				if(!percolationGrid.isOpen(randomRow, randomCol)) {
					
					percolationGrid.open(randomRow, randomCol);
					openSites++;
					
				}
			}
			
			// find and store threshold
			final double threshold = openSites / totalSites;
			thresholdCount[i] = threshold;
			
		}
	}
	
	public double mean() {
		
		return StdStats.mean(thresholdCount);
	}
	
	public double stddev() {
		
		return StdStats.stddev(thresholdCount);
	}
	
	public double confidenceLow() {
		
		// low end-point of 95% confidence threshold 
		return (mean() - (1.96 * stddev() / Math.sqrt(indepT)));
	}
	
	public double confidenceHigh() {
		
		// high end-point of 95% confidence threshold
		return (mean() + (1.96 * stddev() / Math.sqrt(indepT)));
	}
	
	
	
	
	
	
	

	///////////////////////
	//  Utility methods	 //
	//////////////////////
	
	
	private void illegalArgumentCheck(int N, int T) {
		
		if(N <= 0 || T <= 0) {
		
			throw new IllegalArgumentException("'N' or 'T' needs to be larger than zero.");
		}
	}
	
	private int randomInt(int N) {
	
		// random number between 0 and N
		return StdRandom.uniform(N) + 1;			// in case randomInt = 0, index not out of bounds
		
	}
	
	private static void printSimulation(int N, int T) {
		
		PercolationStats statsTest = new PercolationStats(N, T);
		
		// Testing simulation
		StdOut.println("PercolationStats of (" + N  + ", " + T + ")");
		StdOut.println("mean()\t\t = " + statsTest.mean());
		StdOut.println("stddev()\t = " + statsTest.stddev());
		StdOut.println("confidenceLow()\t = " + statsTest.confidenceLow());
		StdOut.println("confidenceHigh() = " + statsTest.confidenceHigh());
		StdOut.println();

	}
}

