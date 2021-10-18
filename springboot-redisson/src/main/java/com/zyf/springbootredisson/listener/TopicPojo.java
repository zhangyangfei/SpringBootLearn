package com.zyf.springbootredisson.listener;

public class TopicPojo {

    public TopicPojo() {
    }

    public TopicPojo(String setKey, int countEve) {
        this.setKey = setKey;
        this.countEve = countEve;
    }

    public TopicPojo(String setKey, int countAll, int countEve, int countSuccess, int countFailed, String errMsg) {
        this.setKey = setKey;
        this.countAll = countAll;
        this.countEve = countEve;
        this.countSuccess = countSuccess;
        this.countFailed = countFailed;
        this.errMsg = errMsg;
    }

    private String setKey;
    private int countAll;
    private int countEve;
    private int countSuccess;
    private int countFailed;
    private String errMsg;

    public String getSetKey() {
        return setKey;
    }

    public void setSetKey(String setKey) {
        this.setKey = setKey;
    }

    public int getCountAll() {
        return countAll;
    }

    public int getCountEve() {
        return countEve;
    }

    public void setCountEve(int countEve) {
        this.countEve = countEve;
    }

    public void setCountAll(int countAll) {
        this.countAll = countAll;
    }

    public int getCountSuccess() {
        return countSuccess;
    }

    public void setCountSuccess(int countSuccess) {
        this.countSuccess = countSuccess;
    }

    public int getCountFailed() {
        return countFailed;
    }

    public void setCountFailed(int countFailed) {
        this.countFailed = countFailed;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "TopicPojo{" +
                "setKey='" + setKey + '\'' +
                ", countAll=" + countAll +
                ", countEve=" + countEve +
                ", countSuccess=" + countSuccess +
                ", countFailed=" + countFailed +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
