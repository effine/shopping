package cn.effine.test;

import java.util.Objects;

/**
 * Created by effine on 2/8/17.
 */
public class EnumTest {

    public static void main(String[] args) {
        System.out.println(Channel.SMS);
        System.out.println(Channel.SMS.getId());
        System.out.println(Channel.SMS.getContent());
    }
}
