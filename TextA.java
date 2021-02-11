package sample;

import javafx.beans.property.SimpleStringProperty;

public class TextA {
    private SimpleStringProperty word;

    public TextA(String word) {
        this.word = new SimpleStringProperty(word);
    }
    public String getWord() {
        return word.get();
    }

    public void setWord(String word) {
        this.word.set(word);
    }
}
