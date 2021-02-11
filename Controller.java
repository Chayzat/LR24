package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.TextFieldTableCell;




public class Controller {
    @FXML
    private TableView tbl;
@FXML
private TableColumn<TextA, String> c1;
@FXML
private TextArea txt;
    private final int row = 10;
    private final String TXT = "Стагровин, вы красавец! - вскричал Петр\n Степанович почти в упоении,-\nзнаете ли, что вы красавец!\nВ вас всего дороже, то что вы иногда\n про это не знаете. О я вас изучил!\n Я на вас часто сбоку, из угла гляжу!\n В вас есть даже простодушие\n и наивность, знаете ли вы это\n" +
            "Еще есть, есть! Вы должно быть страдаете,\nи страдаете искренно, от того простодушия.\nЯ люблю красоту. Я нигилист, но\nлюблю красоту. Разве нигилисты красоту\nне любят? Они только идолов не любят,\nну, а я люблю идола! Вы мой идол!\nВы никого не оскорбляете, и вас все ненавидят,\nвы смотрите всем ровней, и вас все боятся, это хорошо.\n" +
            "К вам никто не подойдет вас потрепать по плечу.\nВы ужасный аристократ.";
    private int count = 0;
    ObservableList<TextA> items = FXCollections.observableArrayList();
    public void btn1_ch(ActionEvent actionEvent) {
        String[] arr = txt.getText().split(" ");
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].contains("$")) {
                String[] arrLetters = arr[i].split("");
                for (int j = 0; j < arrLetters.length; j++) {
                    if (arrLetters[j].equals("$")) {
                        arrLetters[j] = items.get(count).getWord();
                        count++;
                    }
                }
                arr[i] = "";
                for (int j = 0; j < arrLetters.length; j++) {
                    arr[i] += arrLetters[j];
                }
            }
        }
        for (int j = 0; j < arr.length; j++) {
            result += arr[j] + " ";
        }
        txt.setText(result);
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setWord("");
        }
        count = 0;
        tbl.refresh();
    }
    public void btn2_ch(ActionEvent actionEvent) {
        items.add(new TextA(""));
    }
    public void initialize() {
        tbl.itemsProperty().setValue(items);
        tbl.setEditable(true);
        for (int i =0; i < row; i++) {
            items.add(new TextA(""));
        }
        c1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getWord()));
        c1.setCellFactory(TextFieldTableCell.forTableColumn());
        c1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TextA, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TextA, String> event) {
                ((TextA) event.getTableView().getItems().get(event.getTablePosition().getRow())).setWord(event.getNewValue());
            }
        });
        txt.setText(TXT);
    }
}
