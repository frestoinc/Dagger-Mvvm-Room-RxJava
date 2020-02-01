package com.frestoinc.gojekassignment.data;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.frestoinc.gojekassignment.R;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by frestoinc on 01,February,2020 for GoJekAssignment.
 */
public class GithubModel {

  private static final String FIELD_ERROR = "Error";

  private long id;

  @BindingAdapter({"imagePath"})
  public static void getImage(ImageView imageView, String path) {
    Glide.with(imageView.getContext())
        .load(path)
        .centerCrop()
        .placeholder(R.drawable.ic_avatar)
        .error(R.drawable.ic_avatar)
        .fallback(R.drawable.ic_avatar)
        .apply(RequestOptions.circleCropTransform())
        .into(imageView);
  }

  @BindingAdapter({"color"})
  public static void parseColor(AppCompatTextView textView, String stringColor) {
    if (stringColor == null || stringColor.isEmpty()) {
      stringColor = "#FFFFFFFF";
    }
    int color = Color.parseColor(stringColor);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      textView.getCompoundDrawablesRelative()[0].setTint(color);
    } else {
      textView.getCompoundDrawablesRelative()[0].setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
  }

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

  public GithubModel() {
    //empty constructor
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
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

  public String getError() {
    return FIELD_ERROR;
  }

  public class GithubInnerModel {

    @SerializedName("href")
    private String href;

    @SerializedName("avatar")
    private String innerAvatar;

    @SerializedName("username")
    private String username;

    public GithubInnerModel() {
      //empty constructor
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
