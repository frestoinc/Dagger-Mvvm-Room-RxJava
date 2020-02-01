package com.frestoinc.gojekassignment.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by frestoinc on 01,February,2020 for GoJekAssignment.
 */
public class GithubModel {

  @SerializedName("author")
  private String author;

  @SerializedName("name")
  private String name;

  @SerializedName("avatar")
  private String avatar;

  @SerializedName("url")
  private String url;

  @SerializedName("description")
  private String description;

  @SerializedName("language")
  private String language;

  @SerializedName("languageColor")
  private String languageColor;

  @SerializedName("stars")
  private int stars;

  @SerializedName("forks")
  private int forks;

  @SerializedName("currentPeriodStars")
  private int currentPeriodStars;

  @SerializedName("builtBy")
  private List<GithubInnerModel> builtBy;

  private boolean isExpanded = false;

  public GithubModel() {

  }

  public GithubModel(String author, String name, String avatar, String url, String description, String language, String languageColor, int stars, int forks, int currentPeriodStars, List<GithubInnerModel> builtBy) {
    this.author = author;
    this.name = name;
    this.avatar = avatar;
    this.url = url;
    this.description = description;
    this.language = language;
    this.languageColor = languageColor;
    this.stars = stars;
    this.forks = forks;
    this.currentPeriodStars = currentPeriodStars;
    this.builtBy = builtBy;
    this.isExpanded = false;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getLanguageColor() {
    return languageColor;
  }

  public void setLanguageColor(String languageColor) {
    this.languageColor = languageColor;
  }

  public int getStars() {
    return stars;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }

  public int getForks() {
    return forks;
  }

  public void setForks(int forks) {
    this.forks = forks;
  }

  public int getCurrentPeriodStars() {
    return currentPeriodStars;
  }

  public void setCurrentPeriodStars(int currentPeriodStars) {
    this.currentPeriodStars = currentPeriodStars;
  }

  public List<GithubInnerModel> getBuiltBy() {
    return builtBy;
  }

  public void setBuiltBy(List<GithubInnerModel> builtBy) {
    this.builtBy = builtBy;
  }

  public boolean isExpanded() {
    return isExpanded;
  }

  public void setExpanded(boolean expanded) {
    isExpanded = expanded;
  }

  public class GithubInnerModel {

    @SerializedName("href")
    private String href;

    @SerializedName("innerAvatar")
    private String innerAvatar;

    @SerializedName("username")
    private String username;

    public GithubInnerModel() {

    }

    public GithubInnerModel(String href, String innerAvatar, String username) {
      this.href = href;
      this.innerAvatar = innerAvatar;
      this.username = username;
    }

    public String getHref() {
      return href;
    }

    public void setHref(String href) {
      this.href = href;
    }

    public String getInnerAvatar() {
      return innerAvatar;
    }

    public void setInnerAvatar(String innerAvatar) {
      this.innerAvatar = innerAvatar;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }
  }
}
