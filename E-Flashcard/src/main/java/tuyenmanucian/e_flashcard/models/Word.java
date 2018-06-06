package tuyenmanucian.e_flashcard.models;

public class Word {
    private String vocabulary;
    private String mean;

    @Override
    public String toString() {
        return "Word{" +
                "vocabulary='" + vocabulary + '\'' +
                ", mean='" + mean + '\'' +
                '}';
    }

    public Word() {
    }

    public Word(String vocabulary, String mean) {
        this.vocabulary = vocabulary;
        this.mean = mean;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}
