package com.example.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@AllArgsConstructor
@Embeddable
public class Info  implements Serializable {

    private String iden;

    private String status;

    private String date;

    private String link;


    private String urlImage;
    @Column(length = 100000)
    private String urlText;
    public Info() {
        this.iden=null;
        this.status=null;
        this.date=null;
        this.link=null;
        this.urlImage=null;
        this.urlText=null;
    }

    public String getIden(){
        return iden;
    }
    public void setIden(String iden){
        this.iden=iden;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }
    public String getLink(){
        return link;
    }
    public void setLink(String link){
        this.link=link;
    }
    public String getUrlImage(){
        return urlImage;
    }
    public void setUrlImage(String urlImage){
        this.urlImage=urlImage;
    }
    public String getUrlText(){
        return urlText;
    }
    public void setUrlText(String urlText){
        this.urlText=urlText;
    }


}
