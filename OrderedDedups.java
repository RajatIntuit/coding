import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderedDedups {
	
	/*
	 * Algo: Populate LinkedHashMap with map <lowercase, first occurrence> of characters 
	 * in single pass. Join map by values for deduped string
	 * 
	 * Time Complexity: O(N) due to single pass
	 * Space Complexity: O(1) has map will have max 26 entries even for large strings
	 * 
	 * Test cases
	 * 1. mixed case: AbraCadABraAlakAzam
	 * 2. <empty>
	 * 3. long: abcdefghijklmnopqrstuvwxyzINFINITE 
	 * 4. alphanumeric: ABCabc123
	 * 5. spaces: abc ABC
	 * 6. special characters: a°b
	 * 7. already deduped: ABC
	 */
    public static String removeDuplicates(final String inputStr) { 
        Map<Character, Character> charOrderedMap = new LinkedHashMap<>();
        
        // Populate ordered map in single pass 
        for (int i = 0; i < inputStr.length(); i++) { 
        	if (charOrderedMap.size() == 26) {
        		// break if all 26 letters already populated to avoid traversing large string
        		break;
        	}
        	Character orig = inputStr.charAt(i);
        	Character lower = Character.toLowerCase(orig);
        	
        	// key: lowercase character, value: first lower or upper occurrence
        	if (!charOrderedMap.containsKey(lower)) {
        		charOrderedMap.put(lower, orig);
        	}
        }
        
        // Join all ordered unique characters map values
        String dedupStr = charOrderedMap.entrySet()
                .stream()
                .map(e -> e.getValue().toString())
                .collect(Collectors.joining());
        return dedupStr;
    } 
    
    /*
     * Usage: 
     * javac OrderedDedups.java
     * java OrderedDedups
     * <enter input string>
     */
    public static void main(String args[]) { 
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        
        // Allow only lower and upper case input strings
        if (str != null && !str.isEmpty() && str.matches("^[a-zA-Z]*$")) {
        	String dedupStr = removeDuplicates(str); 
            System.out.println(dedupStr);
        } else {
        	System.out.println("OrderedDedups accepts only uppercase and lowercase letters");
        }
    } 
}
