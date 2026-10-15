public class PasswordSecurity{

    final static String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";

    final static String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    final static String DIGITS = "0123456789";
    
    final static String SPECIAL = "!@#$%^&*()-_=+[]{}|;:',.<>?/`~\"\\";

    public static String generate_password (int length, boolean use_lowercase, boolean use_uppercase, boolean use_digits, boolean use_special_chars){

        int lengthNeeded = 0;
        if(use_lowercase) lengthNeeded++;
        if(use_uppercase) lengthNeeded++;
        if(use_digits) lengthNeeded++;
        if(use_special_chars) lengthNeeded++;

        /*/
        Ελέγχω αν υπάρχει τουλάχιστον ένα κρητήριο και 
        αν τα διαφορετικά κρητήρια είναι περισσότερα 
        από μέγεθος που ζητήθηκε.
        /*/

        if(lengthNeeded == 0){
            return "Σφάλμα: Πρέπει να επιλεγεί τουλάχιστον ένας τύπος χαρακτήρων!";
        }

        if(length < lengthNeeded){
            return "Σφάλμα: Το μήκος είναι πολύ μικρό για να συμπεριλάβει όλους τους επιλεγμένους τύπους χαρακτήρων!";
        }

        char[] password = new char[length];
        
        /*/
        Δημιουργώ μια μεταβλητή τύπου StringBuilder (Για διευκόλυνση στην τροποποίησή του και 
        στο ανακάτεμμα) και προσθέτω αρχικά έναν χαρακτήρα από το κάθε κρητήριο.
        /*/

        int index = 0;

        String allChars = "";
        if(use_lowercase){
            password[index++] = LOWERCASE.charAt((int)((LOWERCASE.length() + 1)* Math.random()));
            allChars += LOWERCASE;
        }

        if(use_uppercase){
            password[index] = UPPERCASE.charAt((int)((UPPERCASE.length() + 1)* Math.random()));
            allChars += UPPERCASE;
        }

        if(use_digits){
            password[index++] = DIGITS.charAt((int)((DIGITS.length() + 1)* Math.random()));
            allChars += DIGITS;
        }

        if(use_special_chars){
            password[index++] = SPECIAL.charAt((int)((SPECIAL.length() + 1)* Math.random()));
            allChars += SPECIAL;
        }

        for(int i = lengthNeeded; i < length; i ++){
            password[index++] = allChars.charAt((int)((allChars.length() + 1) * Math.random()));
        }

        for(int i = 0; i < length; i ++){
            int r = i + (int)((length - i) * Math.random());
            char temp = password[i];
            password[i] = password[r];
            password[r] = temp;
        }

        /*/
        Ανακατεύω τον κωδικό που δημιούργησα και τον
        επιστρέφω.
        /*/

        String generatedPassword = new String(password);
        return generatedPassword;

    }

    public static String enforce_strong_password_requirements (String password){
        
        Boolean atLeast8Char = true;
        Boolean hasLowerCase = false;
        Boolean hasUpperCase = false;
        Boolean hasDigit = false;
        Boolean hasSpecialChar = false;

        if(password.length() < 8) atLeast8Char = false;

        for(int i = 0; i < password.length(); i ++){
            if(LOWERCASE.indexOf(password.charAt(i)) != -1) hasLowerCase = true;
            if(UPPERCASE.indexOf(password.charAt(i)) != -1) hasUpperCase = true;
            if(DIGITS.indexOf(password.charAt(i)) != -1) hasDigit = true;
            if(SPECIAL.indexOf(password.charAt(i)) != -1) hasSpecialChar = true;
        }

        String message = "O kodikos prosvasis sas einai isxyros\nkai plhrei oles tis apaithseis";

        if(!(atLeast8Char && hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar))
            message = "O kodikos prosvasis sas:";
        
        if(!atLeast8Char) message += "\n- Einai mikroteros apo 8 xarakthres";
        if(!hasLowerCase) message += "\n- Den periexei pezo gramma";
        if(!hasUpperCase) message += "\n- Den periexei kefalaio gramma";
        if(!hasDigit) message += "\n- Den periexei psifio";
        if(!hasSpecialChar) message += "\n- Den periexei eidiko xarakthra";

        return message;

    }

    public static long calculate_possibilities (String password){

        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for(int i = 0; i < password.length(); i ++){
            if(LOWERCASE.indexOf(password.charAt(i)) != -1) hasLowerCase = true;
            if(UPPERCASE.indexOf(password.charAt(i)) != -1) hasUpperCase = true;
            if(DIGITS.indexOf(password.charAt(i)) != -1) hasDigit = true;
            if(SPECIAL.indexOf(password.charAt(i)) != -1) hasSpecialChar = true;
        }

        int character_set_size = 0;

        if(hasLowerCase) character_set_size += LOWERCASE.length();
        if(hasUpperCase) character_set_size += UPPERCASE.length();
        if(hasDigit) character_set_size += DIGITS.length();
        if(hasSpecialChar) character_set_size += SPECIAL.length();

        int password_length = password.length();
        
        long total_possibilities = (long)Math.pow(character_set_size, password_length);

        return total_possibilities;
    }

    public static double[] estimate_password_cracking_time (String password){

        long total_possibilities = calculate_possibilities(password);

        int guesses_per_second = 1000000;

        double time_in_seconds = (double) total_possibilities / guesses_per_second;

        double time_in_years = (double) time_in_seconds / (60 * 60 * 24 * 365.25);

        double[] time = {time_in_seconds, time_in_years};

        return time;

    }

    // public static String dictionary_attack (String target_pasword, String dictionary_file)

    private static final String[] WORD_LIST = {
        "apple","brave","cloud","dance","eagle","flame","grace","house",
        "ivory","joker","kneel","lemon","mango","noble","ocean","piano",
        "queen","river","stone","tiger","ultra","vivid","waltz","xenon",
        "yacht","zebra","amber","blaze","crisp","drift","ember","frost",
        "gleam","haven","inlet","jewel","karma","lunar","maple","north",
        "orbit","pearl","quill","ridge","solar","torch","unity","vapor",
        "wheat","yield","acorn","birch","cedar","daisy","finch","glade",
        "holly","iris","junco","kelp","lilac","moose","newt","otter",
        "poppy","quail","robin","swift","tulip","vixen","wren","algae",
        "brook","clove","dunes","fjord","grove","heath","isle","jade",
        "knoll","marsh","opal","pine","quartz","reef","sage","thorn",
        "vale","willow","bison","crane","dove","egret","falcon","gecko",
        "heron","ibis","jaguar","kite","lynx","mink","raven","stoat",
        "tapir","vole","wombat","yak","antler","burrow","canopy","delta",
        "flint","gorge","harbor","jungle","lagoon","meadow","nimbus",
        "oasis","plateau","quasar","ravine","summit","tundra","valley",
        "abyss","basin","caldera","dune","fen","geyser","hammock","jetty",
        "levee","moraine","outcrop","quicksand","rift","scree","talus",
        "volcano","beacon","compass","dynamo","engine","forge","hammer",
        "iron","lantern","magnet","needle","piston","relay","signal",
        "timber","valve","winch","alloy","bolt","cable","diesel","filter",
        "gasket","hinge","impact","joint","kernel","lathe","module",
        "nozzle","pulley","rivet","spring","torque","weld","anchor",
        "brace","clasp","dowel","elbow","flange","gauge","hook","joist",
        "latch","mount","notch","pivot","ratchet","shim","zephyr","ash"
        };

    public static String generatePassphrase (int wordCount, String separator, boolean capitalzeEach, boolean appendNumber){
        
        if(wordCount < 3) return "Passphrase must contain at least 3 words.";

        String passPhrase = "";

        for(int i = 1; i <= wordCount; i ++){
            String temp = WORD_LIST[(int)(Math.random() * WORD_LIST.length)];
            if(capitalzeEach) Character.toUpperCase(temp.charAt(0));
            passPhrase += temp;
            if(i < wordCount) passPhrase += separator;
        }

        if(appendNumber) passPhrase += separator + (10 + (int)(Math.random() * 90));

        return passPhrase;
    }

    public static String generatePassphrase (int wordCount) {
        return generatePassphrase(wordCount, "-", true, true);
    }
    public static void main(String[] args){
        StdOut.println(generate_password(12, true, true, true, true));
    }
}