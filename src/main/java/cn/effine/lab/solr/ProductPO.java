package cn.effine.lab.solr;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 */
public class ProductPO {

    @Field
    private int id;

    @Field
    private String name;

    @Field
    private String nameEN;

    @Field
    private String description;

    @Field
    private String descriptionEN;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEN() {
        return this.nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionEN() {
        return this.descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }
}
