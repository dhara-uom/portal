package org.dhara.portal.web.helper;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nipuni
 * Date: 8/11/13
 * Time: 8:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExperimentHelper {

    private String name;
    private String author;
    private Date updatedTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
