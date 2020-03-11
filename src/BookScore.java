/**
 * given the book, return the score
 */
public class BookScore {

    int[]  booksScore; // the second line of the input file

    public BookScore(int ... books) {
        booksScore = books;
    }

    public int getScore(int book){
        return booksScore[book];
    }
}
