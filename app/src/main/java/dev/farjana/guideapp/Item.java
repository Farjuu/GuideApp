package dev.farjana.guideapp;

public class Item {
    private String imageUrl;
    private String desc;
    private String imageName;
    private String siteURl;

    Item() {

    }

    public Item(String imageName, String desc, String imageUrl, String siteURl) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.siteURl = siteURl;
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getSiteURl() {
        return siteURl;
    }

    public void setSiteURl(String siteURl) {
        this.siteURl = siteURl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
