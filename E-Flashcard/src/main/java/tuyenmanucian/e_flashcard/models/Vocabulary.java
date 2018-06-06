package tuyenmanucian.e_flashcard.models;

/**
 * Created by TUYEN_MANUCIAN on 04-03-18.
 */

public class Vocabulary {
    private String word;
    private String pronunciation;
    private String mean;
    private int periodID;

    public Vocabulary(String word, String pronunciation, String mean, int periodID) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.mean = mean;
        this.periodID = periodID;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public int getPeriodID() {
        return periodID;
    }

    public void setPeriodID(int periodID) {
        this.periodID = periodID;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "word='" + word + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", mean='" + mean + '\'' +
                ", periodID=" + periodID +
                '}';
    }
}
