package sg.edu.rp.c346.id22025566.problemstatementp08songs;

public class Song {

    private int _id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song (int _id, String title, String singers, int year, int stars){
        this._id=_id;
        this.title=title;
        this.singers=singers;
        this.year=year;
        this.stars=stars;
    }

    public int get_id(){
        return _id;
    }
    public String getTitle(){
        return title;
    }
    public String getSingers(){
        return singers;
    }
    public int getYear(){
        return year;
    }
    public int getStars(){
        return stars;
    }

    public String toString() {
        return "id: " + _id +
                "\n" + "Title: " + title + "\n" + "Singers: " + singers + "\n" + "Year: " + year + "\n" + "Stars: " + stars;

    }
}
