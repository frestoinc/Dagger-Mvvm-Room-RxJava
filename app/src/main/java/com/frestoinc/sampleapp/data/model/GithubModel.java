package com.frestoinc.sampleapp.data.model;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.bumptech.glide.request.RequestOptions;
import com.frestoinc.sampleapp.R;
import com.frestoinc.sampleapp.di.module.GlideApp;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by frestoinc on 01,February,2020 for SampleApp.
 */
@Entity(tableName = "github", indices = {@Index(value = {"author"}, unique = true)})
public class GithubModel {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "author")
    @SerializedName("author")
    private String author;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "avatar")
    @SerializedName("avatar")
    private String avatar;

    @ColumnInfo(name = "url")
    @SerializedName("url")
    private String url;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String description;

    @ColumnInfo(name = "language")
    @SerializedName("language")
    private String language;

    @ColumnInfo(name = "languageColor")
    @SerializedName("languageColor")
    private String languageColor;

    @ColumnInfo(name = "stars")
    @SerializedName("stars")
    private int stars;

    @ColumnInfo(name = "forks")
    @SerializedName("forks")
    private int forks;

    @ColumnInfo(name = "currentPeriodStars")
    @SerializedName("currentPeriodStars")
    private int currentPeriodStars;

    @ColumnInfo(name = "builtBy")
    @SerializedName("builtBy")
    @TypeConverters(NestedConverter.class)
    private List<GithubInnerModel> builtBy;

    public GithubModel() {
        //empty constructor
    }

    /**
     * Parse color of return {@link GithubInnerModel #getAvatar()}.
     * Gets image through {@link com.bumptech.glide.annotation.GlideModule}
     * called binding through {@link com.frestoinc.sampleapp.ui.MainAdapter}.
     *
     * @param imageView the image view
     * @param path      the path
     */
    @BindingAdapter({"imagePath"})
    public static void getImage(ImageView imageView, String path) {
        GlideApp.with(imageView.getContext())
                .load(path)
                .centerCrop()
                .placeholder(R.drawable.ic_avatar)
                .error(R.drawable.ic_avatar)
                .fallback(R.drawable.ic_avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);
    }


    /**
     * Parse color of return {@link GithubModel #getLanguageColor()}.
     * called binding through {@link com.frestoinc.sampleapp.ui.MainAdapter}.
     *
     * @param textView    the text view
     * @param stringColor the string color
     */
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public class GithubInnerModel {

        @ColumnInfo(name = "href")
        @SerializedName("href")
        private String href;

        @ColumnInfo(name = "avatar")
        @SerializedName("avatar")
        private String avatar;

        @ColumnInfo(name = "username")
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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
