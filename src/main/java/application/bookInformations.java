package application;

public class bookInformations {
    private final String bookTitle;
    private final String authorName;
    private final String yearofWriting;
    private final String time;
    private final int temp_index;
    private final String tmep_reward;
    private final String language;
    private final String publisher;
    private final int numberofPage;
    private final String year;
    private final String bookUrl;
    private final String destinationsPath;
    private final int ID;
    public bookInformations(int ID,String bookTitle,String autherName
                            ,String yearofWriting,String time,int temp_index
                            ,String temp_reward,String language,String publisher
                            ,int numberofPage,String year
                            ,String bookUrl,String destinationsPath){
        this.ID=ID;
        this.authorName=autherName;
        this.bookTitle=bookTitle;
        this.bookUrl=bookUrl;
        this.numberofPage=numberofPage;
        this.destinationsPath=destinationsPath;
        this.language=language;
        this.publisher=publisher;
        this.temp_index=temp_index;
        this.time=time;
        this.tmep_reward=temp_reward;
        this.year=year;
        this.yearofWriting=yearofWriting;
    }

    public int getID(){
        return ID;
    }
    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getYearofWriting() {
        return yearofWriting;
    }

    public String getTime() {
        return time;
    }

    public int getTemp_index() {
        return temp_index;
    }

    public String getTmep_reward() {
        return tmep_reward;
    }

    public String getLanguage() {
        return language;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getNumberofPage() {
        return numberofPage;
    }

    public String getYear() {
        return year;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public String getDestinationsPath() {
        return destinationsPath;
    }
}
