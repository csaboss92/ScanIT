package csaboss.scanit.model;

import com.orm.dsl.Table;

@Table
public class Document {
    private Long id = null;
    private String title;
    private String text;


    public Document() {
    }

    public Document(Long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}