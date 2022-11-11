public class LZW{
    String[] wb = new String[256];
    
    public LZW(){
      wb[0] = "he"; // just for testing
      wb[1] = "el";
      wb[2] = "ll";
      wb[3] = "lo";
      wb[4] = "o ";
      wb[5] = " he";
      wb[6] = "hell";
      wb[7] = "llo ";
      wb[8] = " hello";
      return;
    }

    public static void main(String[] args){
      LZW hi =  new LZW();
      String testString = "hello hello hello";
      for(int i = 0; i < testString.length(); i++){ // just for testing
        System.out.println(hi.vergleich(testString, i));
      }
    }

    private boolean matchWithSubString(String origin, String match){ // matches the main string with a sub string, with the same size as the main and retunrs true if they match
      return origin.matches(match.substring(0, origin.length()));
    }

    private boolean stringIsShorter(String shortString, String longString){ // returns true if the first string is shorter than the second one
      return shortString.length()<longString.length();
    }

    private int checkForStringPositionInStringArray(String inputString, String[] arrayToCheck){ // goes through the wb and reutrns the position (or -1 if nothing was found) of a string in the array with the same chars
        for( int i = 0; i < arrayToCheck.length; i++)
          if(!(arrayToCheck[i] == null) && !stringIsShorter(arrayToCheck[i], inputString) && matchWithSubString(inputString, arrayToCheck[i]))
            return i;
        return -1;
      }

    public int vergleich(String inputString, int position){ // returns the position of the longest matching string found in the wb, or -1 if none was found 
      int output = -1;
      String temp = String.valueOf(inputString.charAt(position));
      for(position++;inputString.length() > (position); position++){ // goes thourgh the rest of the String starting at the position and increases always by one
        temp += inputString.charAt(position);
        int currentCheck = checkForStringPositionInStringArray(temp, wb);
        if (currentCheck == -1)
          break;
        output = currentCheck;
      }
      return output;
    }
}