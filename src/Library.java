import java.util.HashMap;

public class Library implements Comparable<Library>{
    private int bookNumber;
    private int libraryNumber;
    private int signup;
    private int ship;
    private HashMap booksAndScore;
    // data for find the best library to choose
    private double libraryScore;

    public Library(int bookNumber, int libraryNumber ,int signup, int ship, HashMap booksAndScore) {
        this.bookNumber = bookNumber;
        this.libraryNumber = libraryNumber;
        this.signup = signup;
        this.ship = ship;
        this.booksAndScore = booksAndScore;
        this.libraryScore = getLibraryScore();
    }

    public String getLibraryNumber() {
        return libraryNumber + "";
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public int getSignup() {
        return signup;
    }

    public void setSignup(int signup) {
        this.signup = signup;
    }

    public int getShip() {
        return ship;
    }

    public void setShip(int ship) {
        this.ship = ship;
    }

    public HashMap getBooksAndScore() {
        return booksAndScore;
    }

    public void setBooksAndScore(HashMap booksAndScore) {
        this.booksAndScore = booksAndScore;
    }

    public int getBookSumScore(){
        Integer sum = 0;
        for (Object score : booksAndScore.values())
            sum += (Integer)score;

        return  sum;
    }
    public double getLibraryScore(){
        //less is better
        //idea -> get the sum of the book and divide it by the ship day

        //optimize for ( a, b, c, d) inputs
        //return  ( ((double)(getBookSumScore())/ ship) * signup);

        //optimize for f
        //return  ((double)(signup)/ getBookSumScore());

        //optmize for e
        return (double)(signup);
    }


    @Override
    public int compareTo(Library o) {
        return Double.compare(this.getLibraryScore(), o.getLibraryScore());
    }
}
