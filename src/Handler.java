import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Handler {
    int daysForScanning;

    public Handler(String[] filename) throws IOException {

        for (String file : filename)
            readAndCreateDataStrutture(file);
    }

    public void readAndCreateDataStrutture(String fileName) throws IOException {
        Scanner in = new Scanner(new FileReader("./res/input/" + fileName));

        //general information on the problem
        String [] librariesInformation = in.nextLine().split(" ");
        int totalBooks = Integer.parseInt(librariesInformation [0]);
        int totalLibraries = Integer.parseInt(librariesInformation [1]);
        daysForScanning = Integer.parseInt(librariesInformation [2]);
        ArrayList< Library> libraries  = new ArrayList<>();

        // the score of each book
        int [] booksScore = Arrays.asList(in.nextLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();
        BookScore bookScore = new BookScore(booksScore);

        int libraryNumber = 0;
        for(int i = 0; i < totalLibraries; i++) {

            // get the library info
            String [] libraryInfo = in.nextLine().split(" ");
            int books = Integer.parseInt(libraryInfo [0]);
            int signup = Integer.parseInt(libraryInfo [1]);
            int ship = Integer.parseInt(libraryInfo [2]);

            // read all book name
            String [] booksName = in.nextLine().split(" ");
            HashMap<Integer, Integer> bookWithScore = new HashMap<>();

            // associate book with his score
            for(String book : booksName)
                bookWithScore.put(Integer.parseInt(book),bookScore.getScore(Integer.parseInt(book)));

            // sort book by value - first we want to take the book with the most score
            HashMap<Integer, Integer> sortedBookByScore = (HashMap<Integer, Integer>) MapUtil.sortByValue(bookWithScore);


            Library library = new Library(books, libraryNumber, signup, ship,  sortedBookByScore);
            libraries.add(library);
            libraryNumber++;
        }
        in.close();

        chooseTheLibraryToStart(libraries);
        writeOutput(libraries, fileName);
    }

    private void chooseTheLibraryToStart(ArrayList<Library> libraries){

        //sort the library ( based on each library score field ) to choose the best one to start
        Collections.sort(libraries);

        //remove the book that are already present on first library
        int i = 0;
        Library firstLibrary = null;
        for(Library library : libraries){
            if (i++ == 0){
                firstLibrary = library;
                continue;//skip the first library
            }
            library.getBooksAndScore().remove(firstLibrary.getBooksAndScore());
        }

        Collections.sort(libraries);
    }

    /**
     * write the output
     * the output file name is equal to the input
     * @param libraries
     * @param fileOutput
     */
    private void writeOutput(ArrayList<Library> libraries, String fileOutput) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedWriter writer = new BufferedWriter(new FileWriter("./res/output/" + fileOutput));
        output.append(libraries.size() + "\n");// the number of libraries sign up for scanning

       //traverse the library in the order as we took the book
        for (Library library : libraries){
            //name of library and the nu,ber of book
            output.append( library.getLibraryNumber() + " " + library.getBooksAndScore().size() + "\n");

            //write the book to send
            for (Object book : library.getBooksAndScore().keySet())
                output.append(book + " ");
            output.append("\n");
        }


        System.out.println("completed " + fileOutput + " !!" );
        writer.write(String.valueOf(output));
        writer.close();
    }
}
