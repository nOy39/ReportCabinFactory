package helpers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Refrigirators {

    private SimpleIntegerProperty id;

    private SimpleStringProperty modelBar;

    private SimpleStringProperty date;

    private SimpleStringProperty time;

    private SimpleStringProperty poliol_expand;

    private SimpleStringProperty iso_expand;

    public Refrigirators(int id, String modelBar, String date, String time, String pol_expand, String iso_expand) {
        this.id             =    new SimpleIntegerProperty(id);

        this.date           =    new SimpleStringProperty(date);
        this.time           =    new SimpleStringProperty(time);
        this.poliol_expand  =    new SimpleStringProperty(pol_expand);
        this.iso_expand     =    new SimpleStringProperty(iso_expand);

        if (modelBar.equals("3")) {
            modelBar = "BCD 1801";
        } if (modelBar.equals("4")) {
            modelBar = "BCD 2001";
        } if (modelBar.equals("5")) {
            modelBar = "BCD 1881";
        }
        this.modelBar       =    new SimpleStringProperty(modelBar);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getModelBar() {
        return modelBar.get();
    }

    public SimpleStringProperty modelBarProperty() {

        return modelBar;
    }

    public void setModelBar(String modelBar) {
        switch (Integer.parseInt(modelBar)) {
            case 3:
                this.modelBar.set("BCD 1801");
            case 4:
                this.modelBar.set("BCD 2001");
            case 5:
                this.modelBar.set("BCD 1881");
            default:
                this.modelBar.set(modelBar);
        }

    }

    public String getPoliol_expand() {
        return poliol_expand.get();
    }

    public SimpleStringProperty poliol_expandProperty() {
        return poliol_expand;
    }

    public void setPoliol_expand(String poliol_expand) {
        this.poliol_expand.set(poliol_expand);
    }

    public String getIso_expand() {
        return iso_expand.get();
    }

    public SimpleStringProperty iso_expandProperty() {
        return iso_expand;
    }

    public void setIso_expand(String iso_expand) {
        this.iso_expand.set(iso_expand);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    @Override
    public String toString() {
        return "Refrigirators{" +
                "modelBar=" + modelBar +
                ", poliol_expand=" + poliol_expand +
                ", iso_expand=" + iso_expand +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
