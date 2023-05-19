package com.example.demo.Controllers.EmailActions;

import java.util.Arrays;

public class DeleteParameters {
    private int[] ids;
    private String fromFolder;
    public int[] getIds() {
        return ids;
    }
    public void setIds(int[] ids) {
        this.ids = ids;
    }
    public String getFromFolder() {
        return fromFolder;
    }
    public void setFromFolder(String fromFolder) {
        this.fromFolder = fromFolder;
    }
    @Override
    public String toString() {
        return "DeleteParameters [ids=" + Arrays.toString(ids) + ", fromFolder=" + fromFolder + "]";
    }
}
