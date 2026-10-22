/**
 * Author: Alexandros Panteli
 * Written: 05/10/2026
 * Last updated: 22/10/2026
 * Compilation command: javac -classpath .:stdlib.jar PasswordSecurity.java
 * Execution command: java -classpath .:stdlib.jar PasswordSecurity
 * 
 * To programma exei 5 sinolika litourgies:
 * 
 *  Leitourgia 1: Dimiourga ena tixaio kainourio password simfona me
 *  kapoies proipotheseis.
 * 
 *  Leitourgia 2: Elegxei an enas kodikos einai dinatos kai se periptosi
 *  pou den einai perigrafei ti tou lipei.
 * 
 *  Leitourgia 3: Ipologizei tous sinolikous pithanous sindiasmous kodikon
 *  me vasi to ti omades xaraktiron perilambanei enas kodikos.
 * 
 *  Leitourgia 4: Kanei ektimisi to xronou pou tha xreiastei gia
 *  na paraviastei enas kodikos.
 * 
 *  Leitourgia 5: Den tin ekana giati den ksero pos.
 * 
 *  Leitourgia 6: Dimiourga ena tixaio memorable passphrase
 *  me vasi kapoies proipotheseis xreisimopoiontas lekseis
 *  apo ena prokathorismeno pinaka String.
 * 
 */

public class PasswordSecurity{

    final static String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";

    final static String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    final static String DIGITS = "0123456789";
    
    final static String SPECIAL = "!@#$%^&*()-_=+[]{}|;:',.<>?/`~\"\\";

    /*/
        Dilono ena string me kathe xaraktira apo kathe omada.
    /*/

    public static String generatePassword (int length, boolean use_lowercase, boolean use_uppercase, boolean use_digits, boolean use_special_chars){

        int lengthNeeded = 0;
        if(use_lowercase) lengthNeeded++;
        if(use_uppercase) lengthNeeded++;
        if(use_digits) lengthNeeded++;
        if(use_special_chars) lengthNeeded++;

        /*/
            Ipologizo to elaxisto megethos pou xreiazetai to string.
        /*/

        if(lengthNeeded == 0){
            return "Error: At least one character type must be selected!";
        }

        if(length < lengthNeeded){
            return "Error: The length is too short to include all character types!";
        }

        /*/
            Elegxo an epilexthike toulaxisto enas xaraktiras kai an to
            megethos pou zitithike einai mikrotero apo to megethos pou
            xreiazete to password.
        /*/

        char[] password = new char[length];

        /*/
            Dilono enan adio pinaka char gia na dimiourgiso ton 
            kainourgio kodiko.
        /*/

        int index = 0;

        String allChars = "";
        if(use_lowercase){
            password[index++] = LOWERCASE.charAt((int)(LOWERCASE.length() * Math.random()));
            allChars += LOWERCASE;
        }

        if(use_uppercase){
            password[index++] = UPPERCASE.charAt((int)(UPPERCASE.length() * Math.random()));
            allChars += UPPERCASE;
        }

        if(use_digits){
            password[index++] = DIGITS.charAt((int)(DIGITS.length() * Math.random()));
            allChars += DIGITS;
        }

        if(use_special_chars){
            password[index++] = SPECIAL.charAt((int)(SPECIAL.length() * Math.random()));
            allChars += SPECIAL;
        }

        /*/
            Prota vazo enan xaraktira apo kathe omada xaraktiron pou
            epilextike sto String. Kai prostheto se enan pinaka.
        /*/

        for(int i = lengthNeeded; i < length; i ++){
            password[i] = allChars.charAt((int)(allChars.length() * Math.random()));
        }

        /*/
            Gemizo to ipolipo string me ta epitrepomena grammata simfona
            me tis proipotheseis.
        /*/

        for(int i = 0; i < length; i ++){
            int r = i + (int)((length - i) * Math.random());
            char temp = password[i];
            password[i] = password[r];
            password[r] = temp;
        }

        /*/
            Telos anakatevo ton kodiko kai ton epistrefo.
        /*/

        String generatedPassword = new String(password);
        return generatedPassword;

    }

    public static String enforceStrongPasswordRequirements (String password){
        
        Boolean atLeast8Char = true;
        Boolean hasLowerCase = false;
        Boolean hasUpperCase = false;
        Boolean hasDigit = false;
        Boolean hasSpecialChar = false;

        if(password.length() < 8) atLeast8Char = false;

        /*/
            Elegxo an o kodikos pou dothike einai toulaxisto 8 xaraktires.
        /*/

        for(int i = 0; i < password.length(); i ++){
            if(LOWERCASE.indexOf(password.charAt(i)) != -1) hasLowerCase = true;
            if(UPPERCASE.indexOf(password.charAt(i)) != -1) hasUpperCase = true;
            if(DIGITS.indexOf(password.charAt(i)) != -1) hasDigit = true;
            if(SPECIAL.indexOf(password.charAt(i)) != -1) hasSpecialChar = true;
        }

        /*/
            Elegxo an o kodikos pou dothike perilambanei enan xaraktira 
            apo kathe omada xaraktiron.
        /*/

        String message = "Your password is strong and meets all requirements!";

        if(!(atLeast8Char && hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar))
            message = "Your password is missing:";
        
        if(!atLeast8Char) message += "\n- Being at least 8 letters";
        if(!hasLowerCase) message += "\n- At least one lowercase letter";
        if(!hasUpperCase) message += "\n- At least one uppercase letter";
        if(!hasDigit) message += "\n- At least one digit";
        if(!hasSpecialChar) message += "\n- At least one special character";

        /*/
            Tipono ta katallila minimata analoga me to ti xreiazete o 
            kodikos prosvasis.
        /*/

        return message;

    }

    public static long calculatePossibilities (String password){

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

        /*/
            Elegxo poies omades xaraktiron perilambanei o kodikos.
        /*/

        int character_set_size = 0;

        if(hasLowerCase) character_set_size += LOWERCASE.length();
        if(hasUpperCase) character_set_size += UPPERCASE.length();
        if(hasDigit) character_set_size += DIGITS.length();
        if(hasSpecialChar) character_set_size += SPECIAL.length();
        
        /*/
            Dilono ena adeio string kai to gemizo me tis omades xaraktiron 
            pou perilambanei o kodikos.
        /*/
        
        long total_possibilities = (long)Math.pow(character_set_size, password.length());

        /*/
            Ipologizo kai epistrefo tis sinolikes dinatotites tou kodikou.
        /*/

        return total_possibilities;
    }

    public static double[] estimatePasswordCrackingTime (String password){

        long total_possibilities = calculatePossibilities(password);

        int guesses_per_second = 1000000;

        double time_in_seconds = (double) total_possibilities / guesses_per_second;

        double time_in_years = (double) time_in_seconds / (60 * 60 * 24 * 365.25);

        double[] time = {time_in_seconds, time_in_years};

        /*/
            Ipologizo ton ektimomeno xrono paraviasis me vasi
            ton tipo pou dothike stin odigia kai ton epistrefo
            se ena pinaka double apou stin proti thesi exei ton
            xrono se defterolepta kai stin defteri exei ton xrono
            se xronia.
        /*/

        return time;

    }

    /*/
        Dilono ton pinaka WORD_LIST.
    /*/
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

    public static String generatePassphrase (int wordCount, String separator, boolean capitalizeEach, boolean appendNumber){
        
        if(wordCount < 3) return "Passphrase must contain at least 3 words.";

        /*/
            Elegxo kai epistrefo to katallilo minima se periptosi pou
            to wordCount pou zitithike einai mikrotero tou 3 kai stin 
            periptosi afti tipono to katallilo minima.
        /*/
        
        String passPhrase = "";

        /*/
            Dilono ena adeio String gia na dimiourgiso to passPhrase.
        /*/

        for(int i = 1; i <= wordCount; i ++){
            String temp = WORD_LIST[(int)(Math.random() * WORD_LIST.length)];
            if(capitalizeEach) Character.toUpperCase(temp.charAt(0));
            passPhrase += temp;
            if(i < wordCount) passPhrase += separator;
        }

        /*/
            Dimiourgo to passphrase prosthetontas WordCount tixaies 
            lekseis apo ton pinaka WORD_LIST kai an den eimai stin 
            teleftaia epanalipsi prostheto episis to separator.
        /*/

        if(appendNumber) passPhrase += separator + (10 + (int)(Math.random() * 90));

        /*/
            Elegxo an zitithike na telionei to passphrase me arithmo
            kai an zitithike prostheto akoma ena separator kai sto 
            telos enan tixaio dipsifio arithmo.
        /*/

        return passPhrase;

        /*/
            Telos epistrefo to passphrase.
        /*/
    }

    public static String generatePassphrase (int wordCount) {

        /*/
            Dilono mia methodo me to idio onoma me tin proigoumeni
            etsi oste na kaleitai se periptosi pou dothei mono to
            wordCount san parametro. Stin periptosi afti to default
            separator = "-", to default capitalizeEach = true kai 
            to appendNumber = true.
        /*/
        
        return generatePassphrase(wordCount, "-", true, true);
    }

    public static void main(String[] args) {

        System.out.println("=== Part 1: Generate Password ===");
        String pwd = generatePassword(12, true, true, true, true);
        System.out.println("Generated (12 chars, all types): " + pwd);
        System.out.println(generatePassword(3, true, true, true, true));
        System.out.println(generatePassword(8, false, false, false, false));

        System.out.println("\n=== Part 2: Enforce Strong Password Requirements ===");
        System.out.println(enforceStrongPasswordRequirements("P@ssw0rd"));
        System.out.println(enforceStrongPasswordRequirements("password"));

        System.out.println("\n=== Part 3: Calculate Password Possibilities ===");
        long possibilities = calculatePossibilities("P@ssw0rd");
        System.out.println("Total possible combinations: " + possibilities);

        System.out.println("\n=== Part 4: Estimate Time to Crack ===");
        double[] times = estimatePasswordCrackingTime("P@ssw0rd");
        System.out.printf("Estimated time to crack: %.6f seconds (~%.8f years)%n",
        times[0], times[1]);

        /*/
            Den ekana tin leitourgia 5 giati den ksero pos na tin kano.
        /*/

        System.out.println("\n=== Part 6: Generate Memorable Passphrase ===");
        // 4 words, hyphen-separated, capitalised, with number (default style)
        System.out.println(generatePassphrase(4));
        // 3 words, space-separated, lowercase, no number
        System.out.println(generatePassphrase(3, " ", false, false));
        // 5 words, underscore-separated, capitalised, with number
        System.out.println(generatePassphrase(5, "_", true, true));
        // Error: too few words
        System.out.println(generatePassphrase(2));
    }
}