package com.app.supawit.bcomprojectit;

/**
 * Created by froz on 2/23/2016.
 */
public class NewsItem
{
        private String headline;
        private String whName;
        private String score;
        private String docdate;
        //private String reporterName;



    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public String getDocdate() {
        return docdate;
    }

    public void setDocdate(String docdate) {
        this.docdate = docdate;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getHeadline() {
            return headline;
        }

    public void setHeadline(String headline) {
            this.headline = headline;
        }

    /*public String getReporterName() {
            return reporterName;
        }

    public void setReporterName(String reporterName) {
            this.reporterName = reporterName;
        }*/

    @Override
    public String toString() {
            return "[ headline=" + headline +
                    ", whname=" + whName +
                    ", score=" + score +
                    ", docdate=" +docdate+
                    "]";
    }
}
