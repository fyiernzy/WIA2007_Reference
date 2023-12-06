# Practical 6

Practical 6 is quite a challenge, mainly because it involves a lot of unseen setup, along with introducing new concepts. Sometimes, it doesn't show the full source code, so to ensure the app runs smoothly, you'll need to figure out the hidden parts of the code.

## Setup

Here are the basic dependencies for your `build.gradle` or `build.gradle.kts`:

```kotlin
implementation("androidx.appcompat:appcompat:1.3.0")
implementation("androidx.constraintlayout:constraintlayout:2.1.4")
implementation("com.google.android.material:material:1.3.0")
implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
```

If you're using Java, make sure to include `androidx.appcompat:appcompat:1.3.0` as it's crucial for extending `AppCompatActivity`.

### `AndroidManifest.xml`

Your `AndroidManifest.xml` should include this snippet:

```xml
<activity
    android:name=".MainActivity"
    android:exported="true"
    android:label="@string/app_name"
    android:theme="@style/Theme.AnimalLoverApp">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

In the latest Android Studio versions, `android:exported="true"` is necessary, or your activity won't show up. Regarding themes, I've used the following:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="Theme.AnimalLoverApp" parent="Theme.MaterialComponents.Light.DarkActionBar" />
</resources>
```

`Theme.MaterialComponents` is essential if you're using Material Components like MaterialButton and BottomNavigationBar, or you'll be greeted with a flood of error messages about mismatched styles.

## Fragment

### Import Statement in Fragment

```java
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
```

All fragment classes should extend `Fragment`. Above are the imports you should include for `AboutAppFragment`, `DogFragment`, `CatFragment`, and `HomeFragment`.

Note: If you don't have a class extending Fragment, you won't see the Fragment class in the `nav` folder. More on this in the `Navigation` section. The `AboutAppFragment`, `DogFragment`, and `CatFragment` should include the following code:

```java
@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.FRAGMENT, container, false);
}
```

Just replace `R.layout.FRAGMENT` with the appropriate layout ID.

**`AboutAppFragment.java`**

```java
public class AboutAppFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_app, container, false);
    }
}
```

**`CatFragment.java`**

```java
public class CatFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat, container, false);
    }
}
```

**`DogFragment.java`**

```java
public class DogFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dog, container, false);
    }
}
```

### Special Case: `HomeFragment`

`HomeFragment` is a bit different. It has two buttons in `fragment_home.xml` that need to navigate to other places, so we set up `View.OnClickListener`. `View.OnClickListener` is a functional interface with one abstract method: `onClick(View view)`. Here’s how we usually implement it:

```java
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        doSomething(view);
    }
});
```

Or, more neatly with a lambda expression:

```java
btn.setOnClickListener(v -> doSomething(v));
```

Here's how it looks in `HomeFragment`:

```java
public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button btnCat = view.findViewById(R.id.btnCat);
        btnCat.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.DestCat));

        Button btnDog = view.findViewById(R.id.btnDog);
        btnDog.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.NextToDog));
    }
}
```

`Navigation.findNavController(v).navigate(R.id.ROUTE)` breaks down as follows: `.findNavController(v)` retrieves a `NavController`, which acts as a manager or bridge for activity and fragment navigation. Then, `navigate(ROUTE)` takes us to the specific destination.

Note: There are different types of `NavController`, like `View.findNavController()`, `Fragment.findNavController()`, and `Activity.findNavController()`. It's crucial to use the right one, or you might get stuck

Reference: [Navigation.findNavController() Can't Find View](https://stackoverflow.com/questions/64441626/navigation-findnavcontroller-cant-find-view)

## `MainActivity.java`

```java
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);

        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.NHFMain);
        NavController navController = host.getNavController();

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}
```

Check this out: we're using a different method to get our `NavController` here! Instead of the typical `.findNavController()`, we're using `.getNavController()` from the host. This little twist is pretty cool and definitely worth pointing out.

Here's the breakdown:

```java
NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.NHFMain);
NavController navController = host.getNavController();

NavigationUI.setupWithNavController(bottomNavigationView, navController);
```

From this snippet, it's clear that instead of finding the `NavController` ourselves, we can use `.getNavController()` to grab it straight from the host. We're getting the `NavController` from the `NavHostFragment`, which is set up like this in the XML file:

```xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/NHFMain"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
    app:defaultNavHost="true"
    app:navGraph="@navigation/nav_animal_lover"/>
```

This is your `NavHostFragment`. According to the [documentation](https://developer.android.com/reference/androidx/navigation/fragment/NavHostFragment), the `NavHostFragment` provides a designated area in your layout for self-contained navigation. So, you can think of the `NavHostFragment` as a special zone where you can switch between fragments, making it a key player in your navigation setup.

We use `.getNavController()` to get the `NavController` from a `NavHostFragment`, as defined in the XML file. This sets up the BottomNavigationBar to enable navigation within the `NavHostFragment` when a user clicks on the BottomNavigationBar.

## Navigation

**`nav/nav_animal_lover.xml`**

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_animal_lover"
    app:startDestination="@id/DestHome">
    <fragment
        android:id="@+id/DestCat"
        android:name="com.example.animalloverapp.CatFragment"
        android:label="fragment_cat"
        tools:layout="@layout/fragment_cat" />
    <fragment
        android:id="@+id/DestDog"
        android:name="com.example.animalloverapp.DogFragment"
        android:label="fragment_dog"
        tools:layout="@layout/fragment_dog" />
    <fragment
        android:id="@+id/DestAboutApp"
        android:name="com.example.animalloverapp.AboutAppFragment"
        android:label="fragment_about_app"
        tools:layout="@layout/fragment_about_app" />
    <fragment
        android:id="@+id/DestHome"
        android:name="com.example.animalloverapp.HomeFragment"
        android:label="fragment_home"
        tools:layout="@layout/fragment_home" >
        <action
            android:id="@+id/NextToDog"
            app:destination="@id/DestDog" />
    </fragment>
</navigation>
```

If it's your first time using the visualization tools and you click the ![Alt text](image.png) button, but can't find the fragment you need, don't stress! Just make sure to include `android:name="com.example.<project_name>.<fragment_name>` in your XML. Also, double-check that you've created a Fragment class that extends `Fragment`, and that the filename matches `<fragment_name>`.

For everything else, just refer to the project. All the necessary info is right there.
