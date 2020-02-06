# Dagger-Mvvm-Room-RxJava

### Sample application containing the most used trending libraries sucha s Dagger2, Retrofit2, MVVM architecture, Rooom and more.

This application will fetch JSON data from [Github trending api](https://github-trending-api.now.sh/repositories), saved in local database (Room) and displayed in RecyclerView.

Retrieved data can be sorted either by name or most stars.

A WorkManager will run in background to update the Room database by fetching the latest data on a 2 hour interval

# Todo

To enhance unit and instrument testing using TDD approach