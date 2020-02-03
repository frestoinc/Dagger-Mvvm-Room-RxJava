package com.frestoinc.gojekassignment.ui;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;

import com.frestoinc.gojekassignment.api.base.rx.TestSchedulerProvider;
import com.frestoinc.gojekassignment.data.AppDataManager;
import com.frestoinc.gojekassignment.data.model.GithubModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.model.Statement;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by frestoinc on 03,February,2020 for GoJekAssignment.
 */
@RunWith(JUnit4.class)
public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Rule
    public RxSchedulersOverrideRule schedulersOverrideRule = new RxSchedulersOverrideRule();

    @Mock
    public TestSchedulerProvider provider;

    @Mock
    public AppDataManager manager;

    public MainViewModel viewModel;

    @Mock
    Observer<AuthResource<List<GithubModel>>> observer;

    @Mock
    LifecycleOwner lifecycleOwner;
    Lifecycle lifecycle;

    List<GithubModel> list = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        viewModel = new MainViewModel();
        viewModel.getSource().observeForever(observer);
    }

    @After
    public void tearDown() {
        RxAndroidPlugins.reset();
        manager = null;
        viewModel = null;
    }

    @Test
    public void testNull() throws Exception {
        when(manager.getRepo()).thenReturn(null);
        assertNotNull(viewModel.getSource());
        assertTrue(viewModel.getSource().hasObservers());
    }

    @Test
    public void testOnlineRepo() throws Exception {
        when(manager.getRepo()).thenReturn(Single.just(list));
        viewModel.getOnlineRepo();
        verify(observer).onChanged(AuthResource.loading(null));
        verify(observer).onChanged(AuthResource.success(list));
    }

    @Test
    public void testError() throws Exception {
        when(manager.getRepo()).thenReturn(Single.error(new Throwable("test error")));
        viewModel.getOnlineRepo();
        verify(observer).onChanged(AuthResource.loading(null));
        verify(observer).onChanged(AuthResource.error("test error"));
    }

    public class RxSchedulersOverrideRule implements TestRule {
        @Override
        public Statement apply(final Statement base, Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    RxAndroidPlugins.reset();
                    RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

                    RxJavaPlugins.reset();
                    RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
                    RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
                    RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

                    base.evaluate();

                    RxAndroidPlugins.reset();
                    RxJavaPlugins.reset();
                }
            };
        }
    }


}