package com.example.demo.Controllers.EmailActions;

import java.util.Arrays;

public class MoveParameters {
    private int[] ids;
    private String fromFolder;
    private String toFolder;
    
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

    public String getToFolder() {
        return toFolder;
    }

    public void setToFolder(String toFolder) {
        this.toFolder = toFolder;
    }

    @Override
    public String toString() {
        return "MoveParameters [ids=" + Arrays.toString(ids) + ", fromFolder=" + fromFolder + ", toFolder=" + toFolder
                + "]";
    }
}
