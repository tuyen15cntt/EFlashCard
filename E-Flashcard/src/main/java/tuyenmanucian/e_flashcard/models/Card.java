package tuyenmanucian.e_flashcard.models;

public class Card {
    private String word;
    private String essential;
    private String pronunciation;
    private String image;
    private String mean;

    @Override
    public String toString() {
        return "Card{" +
                "word='" + word + '\'' +
                ", essential='" + essential + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", image='" + image + '\'' +
                ", mean='" + mean + '\'' +
                '}';
    }


    public Card(String word, String mean, String essential,  String image, String pronunciation) {
        this.word = word;
        this.essential = essential;
        this.pronunciation = pronunciation;
        this.image = image;
        this.mean = mean;
    }

    public Card() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getEssential() {
        return essential;
    }

    public void setEssential(String essential) {
        this.essential = essential;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

}
