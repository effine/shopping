/**
 * @author effine
 * @Date 2015年11月12日  下午11:13:31
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.model;

import java.io.Serializable;

public class SigninHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int uid;
    private String ip;
    private String signinTime;
    private int client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSigninTime() {
        return signinTime;
    }

    public void setSigninTime(String signinTime) {
        this.signinTime = signinTime;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

}
