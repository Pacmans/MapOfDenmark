package Union;
import java.math.BigDecimal;

public class Union{
	
	    
	    private BigDecimal[][] points;

	    public Union(BigDecimal[][] p) {
	    	points = new BigDecimal[p.length][20];
	    	points = p;
	    }

	    public boolean connected(int p, int q) {
	        for (int i =2; i<20; i++){
	        	//hvis plads er tom (set alle connections igennem)
	        	if(points[p][i] == null) break; //breaks and returns false
	      
	        	//hvis der er en connection
	        	if (points[p][i].intValue() == q) return true; 
	        }
	        return false;
	    }

	    // merge components containing p and q
	    public void union(int p, int q) 
	    {
	    	//tjekker om de er conncected
	    	if (connected(p, q))								//-------- OBS ------ sikrer os ikke mod index out of bounds 
	    		return;
	    	
	    	boolean pFound = false, qFound = false;
	    	//gemmer information om den nye connection og laver connection
	    	for (int i=0; !pFound || !qFound; i++){
	    		if (!pFound && points[p][i] == null){
	    			points[p][i] = new BigDecimal(q);
	    			pFound = true;
	    		}
	    		
	    		if (!qFound && points[q][i] == null){
	    			points[q][i] = new BigDecimal(p);
	    			qFound = true;
	    		}  		
	    	}
	    }
	    
	    /**
	     * 
	     * @param i entry to be revised
	     * @param r reference to be removed
	     */
	    private void removeReference(int i, int r){
	    	for(int j = 2; j < points[i].length; j++){
	    		if(points[i][j].intValue()==r){
	    			points[i][j] = null;
	    			break;
	    		}
	    	}
	    }
	    
	    public void clean()
	    {
	    	for(int i = 0; i < points.length; i++){
	    		if (points[i][3] != null && points[i][4] == null){
	    			removeReference(points[i][2].intValue(), i);
	    			removeReference(points[i][3].intValue(), i);
	    			union(points[i][2].intValue(), points[i][3].intValue());
	    			points[i][2] = null;
	    			points[i][3] = null;
	    		}
	    		
	    	}
	    }
}
