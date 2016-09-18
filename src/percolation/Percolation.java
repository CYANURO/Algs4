/**
 * 
 */
package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Cyanuro
 *
 */
public class Percolation {
	
	private WeightedQuickUnionUF unionFind;		
	private final boolean[][] percGrid;			
	private boolean hasOpen = false;			// site's state
	
	private final int gridSize;							
	private final int virtTopSiteIndex;			// virtual link to top row
	private final int virtBotSiteIndex;			// virtual link to bottom row
	
	
	public Percolation(int N) {
		
		gridSize = N;
		percGrid = new boolean[gridSize][gridSize];		// creates N-by-N grid, with all sites closed
		unionFind = new WeightedQuickUnionUF((gridSize * gridSize) + 2);
		
		virtTopSiteIndex = gridSize * gridSize;
		virtBotSiteIndex = (gridSize * gridSize) + 1;
			
		// linking all index top and bottom rows to their virtual sites
		for (int colJ = 1; colJ <= gridSize; colJ++) {
				
			unionFind.union(goToIndex(1, colJ), virtTopSiteIndex);
			unionFind.union(goToIndex(gridSize, colJ), virtBotSiteIndex);
				
		}		
	}
	
	/**
	 * Open sites, and use UnionFind to find neighboring opened sites  
	 * @param p = site (row, column) inside UnionFind array
	 * @param q = site (row +/- 1, column) inside UnionFind Array
	 */
	public void open(int rowI, int colJ) throws IndexOutOfBoundsException {
		
		checkOutOfBounds(rowI, colJ);
		
		// open site at (row, col) if not opened already
		if(!isOpen(rowI, colJ)) {
			
			hasOpen = true;
			percGrid[rowI - 1][colJ - 1] = true;
			
			final int p = goToIndex(rowI, colJ);
			
			int newRow = rowI - 1;					// previous row
			int newCol = colJ;
			int q;
			
			if(inBoundsAndOpen(newRow, newCol)) {
				
				q = goToIndex(newRow, newCol);
				unionFind.union(p, q);
			}
			
			
			newRow = rowI + 1;						// next row
			
			if(inBoundsAndOpen(newRow, newCol)) {
				
				q = goToIndex(newRow, newCol);
				unionFind.union(p, q);
			}
			
			newRow = rowI;
			newCol = colJ - 1;						// previous column
			
			if(inBoundsAndOpen(newRow, newCol)) {
				
				q = goToIndex(newRow, newCol);
				unionFind.union(p, q);
			}
			
			newCol = colJ + 1;						// new column
			
			if(inBoundsAndOpen(newRow, newCol)) {
				
				q = goToIndex(newRow, newCol);
				unionFind.union(p, q);
			}
		}
	}
	
	
	public boolean isOpen(int rowI, int colJ) throws IndexOutOfBoundsException {
		
		checkOutOfBounds(rowI, colJ);
		
		// check if site open
		return percGrid[rowI - 1][colJ - 1];
		
	}
	
	public boolean isFull(int rowI, int colJ) throws IndexOutOfBoundsException {
		
		checkOutOfBounds(rowI, colJ);
		
		// check if rowI, colJ are open sites
		if(!isOpen(rowI, colJ)) {
		
			return false;
		}
		
		/* check to see if rowI, colJ are full or connected to the top row */
		return unionFind.connected(goToIndex(rowI, colJ), virtTopSiteIndex);
		
	}
	
	public boolean percolates() {
		
		// check if top and bottom virtual sites are connected
		return hasOpen && unionFind.connected(virtTopSiteIndex, virtBotSiteIndex);
		
	}
	
	
	
	
	
	
	
	
	
	
	///////////////////////
	//  Utility methods	 //
	//////////////////////

	
	private boolean isInBounds(int limit) {
		
		return limit >= 1 && limit <= gridSize;
		
	}

	private int goToIndex(int row, int col) {
		
		return gridSize * (row - 1) + (col - 1);
		
	}
	
	private void checkOutOfBounds(int row, int col) {
		
		if(!isInBounds(row) || !isInBounds(col)) {
			// handling index out of bounds
			throw new IndexOutOfBoundsException(String.format(
					"Index %d or %d are out of bounds.", row, col));
			
		}
	}
	
	private boolean inBoundsAndOpen(int rowI, int colJ) {
		
		// check if both index in bounds, and if open site for percolation
		return isInBounds(rowI) && isInBounds(colJ) && isOpen(rowI, colJ);
		
	}
}
