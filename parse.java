public class parse { // Seed parsing method
	static int parseInt(String s) {
		try 
        { 
            Integer.parseInt(s); 
            return Integer.parseInt(s);
        }  
        catch (NumberFormatException e)  
        { 
            return s.hashCode();
        } 
	}
}
