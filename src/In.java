import java.io.*;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: lindseydew
 * Date: 12/04/2012
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 */
public class In {
    private final String fileName;

    public In(String filename) {
       this.fileName = filename;
    }
            
    public LinkedList<String> readFile() throws FileNotFoundException {
        LinkedList<String> readFile = new LinkedList<String>();
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;

            //vertices
            int i = 0;
            //add condition not to be empty string
            while ((line = br.readLine()) != null ){
                readFile.add(line);
            }

            in.close();
        }
        catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return readFile;
    }


}
