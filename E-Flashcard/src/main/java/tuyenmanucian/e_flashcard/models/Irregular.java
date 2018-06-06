package tuyenmanucian.e_flashcard.models;

public class Irregular {
    private String infinitive;
    private String simplePast;
    private String pastParticiple;
    private String mean;

    public Irregular() {
    }

    public Irregular(String infinitive, String simplePast, String pastParticiple, String mean) {
        this.infinitive = infinitive;
        this.simplePast = simplePast;
        this.pastParticiple = pastParticiple;
        this.mean = mean;
    }

    public String getInfinitive() {
        return infinitive;
    }

    public void setInfinitive(String infinitive) {
        this.infinitive = infinitive;
    }

    public String getSimplePast() {
        return simplePast;
    }

    public void setSimplePast(String simplePast) {
        this.simplePast = simplePast;
    }

    public String getPastParticiple() {
        return pastParticiple;
    }

    public void setPastParticiple(String pastParticiple) {
        this.pastParticiple = pastParticiple;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    @Override
    public String toString() {
        return "Irregular{" +
                "infinitive='" + infinitive + '\'' +
                ", simplePast='" + simplePast + '\'' +
                ", pastParticiple='" + pastParticiple + '\'' +
                ", mean='" + mean + '\'' +
                '}';
    }
}
