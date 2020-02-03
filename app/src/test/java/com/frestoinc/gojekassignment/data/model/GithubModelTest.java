package com.frestoinc.gojekassignment.data.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frestoinc on 03,February,2020 for GoJekAssignment.
 */
public class GithubModelTest {

    private String author = "author";

    private String name = "name";

    private String avatar = "avatar";

    private String url = "url";

    private String description = "description";

    private String language = "language";

    private String languageColor = "languageColor";

    private int stars = 1;

    private int forks = 2;

    private int currentPeriodStars = 3;

    private String href = "href";

    private String innerAvatar = "innerAvatar";

    private String username = "username";

    private final List<GithubModel.GithubInnerModel> githubList = new ArrayList<>();

    @Mock
    GithubModel githubModel;

    @Mock
    GithubModel.GithubInnerModel innerModel;

    @Mock
    List<GithubModel.GithubInnerModel> listModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(githubModel.getAuthor()).thenReturn(author);
        Mockito.when(githubModel.getName()).thenReturn(name);
        Mockito.when(githubModel.getAvatar()).thenReturn(avatar);
        Mockito.when(githubModel.getUrl()).thenReturn(url);
        Mockito.when(githubModel.getDescription()).thenReturn(description);
        Mockito.when(githubModel.getLanguage()).thenReturn(language);
        Mockito.when(githubModel.getLanguageColor()).thenReturn(languageColor);
        Mockito.when(githubModel.getStars()).thenReturn(stars);
        Mockito.when(githubModel.getForks()).thenReturn(forks);
        Mockito.when(githubModel.getCurrentPeriodStars()).thenReturn(currentPeriodStars);
        Mockito.when(innerModel.getHref()).thenReturn(href);
        Mockito.when(innerModel.getAvatar()).thenReturn(innerAvatar);
        Mockito.when(innerModel.getUsername()).thenReturn(username);

        Mockito.when(githubModel.getBuiltBy()).thenReturn(githubList);
    }

    @Test
    public void testAuthor() {
        Mockito.when(githubModel.getAuthor()).thenReturn(author);
        Assert.assertEquals("author", githubModel.getAuthor());
    }

    @Test
    public void testName() {
        Mockito.when(githubModel.getName()).thenReturn(name);
        Assert.assertEquals("name", githubModel.getName());
    }

    @Test
    public void testAvatar() {
        Mockito.when(githubModel.getAvatar()).thenReturn(avatar);
        Assert.assertEquals("avatar", githubModel.getAvatar());
    }

    @Test
    public void testUrl() {
        Mockito.when(githubModel.getUrl()).thenReturn(url);
        Assert.assertEquals("url", githubModel.getUrl());
    }

    @Test
    public void testDescription() {
        Mockito.when(githubModel.getDescription()).thenReturn(description);
        Assert.assertEquals("description", githubModel.getDescription());
    }

    @Test
    public void testLanguage() {
        Mockito.when(githubModel.getLanguage()).thenReturn(language);
        Assert.assertEquals("language", githubModel.getLanguage());
    }

    @Test
    public void testLanguageColor() {
        Mockito.when(githubModel.getLanguageColor()).thenReturn(languageColor);
        Assert.assertEquals("languageColor", githubModel.getLanguageColor());
    }

    @Test
    public void testStars() {
        Mockito.when(githubModel.getStars()).thenReturn(stars);
        Assert.assertEquals(1, githubModel.getStars());
    }

    @Test
    public void testForks() {
        Mockito.when(githubModel.getForks()).thenReturn(forks);
        Assert.assertEquals(2, githubModel.getForks());
    }

    @Test
    public void testCurrentPeriodStars() {
        Mockito.when(githubModel.getCurrentPeriodStars()).thenReturn(currentPeriodStars);
        Assert.assertEquals(3, githubModel.getCurrentPeriodStars());
    }

    @Test
    public void testHref() {
        Mockito.when(innerModel.getHref()).thenReturn(href);
        Assert.assertEquals("href", innerModel.getHref());
    }

    @Test
    public void testInnerAvatar() {
        Mockito.when(innerModel.getAvatar()).thenReturn(innerAvatar);
        Assert.assertEquals("innerAvatar", innerModel.getAvatar());
    }

    @Test
    public void testUsername() {
        Mockito.when(innerModel.getUsername()).thenReturn(username);
        Assert.assertEquals("username", innerModel.getUsername());
    }

    @Test
    public void testArrayInnerModel() {
        Mockito.when(githubModel.getBuiltBy()).thenReturn(githubList);
        Assert.assertEquals(new ArrayList<>(), githubModel.getBuiltBy());
    }

    @After
    public void tearDown() {
        githubModel = null;
        innerModel = null;
    }

}