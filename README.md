# Scentify

An Android app that recommends men's fragrances. The user answers four short questions — season, age, lifestyle and the type of scent they like — and Scentify filters a built-in catalogue of 52 fragrances down to the ones that match. Each result opens a detail screen with the fragrance's profile.

<img src="scentify_gif.gif" width="250" alt="Scentify demo">

Built in 2020–2021, during a year of self-study in Java and Android development before my first job as a developer.

---

## Features

- **Guided questionnaire** — four multiple-choice questions with a progress indicator and previous/next navigation. Every question has a *"Doesn't matter"* option.
- **Matching** — a fragrance is recommended only if it matches *all* answers: its seasons, its target age range, its lifestyle and its scent type. *"Doesn't matter"* acts as a wildcard for that criterion.
- **Results list** — the matching fragrances in a scrollable list, each with image, brand and name.
- **Detail screen** — the full profile of a fragrance: brand, name, seasons, age range, lifestyle and type.

## How it works

The app consists of four activities, connected with explicit `Intent`s:

| Activity | Responsibility |
|---|---|
| `MainActivity` | Home screen; starts the questionnaire. |
| `QuestionActivity` | Shows the questions one by one and collects the answers in a `HashMap<String, String>`, which is passed on as a serializable `Intent` extra. |
| `ResultActivity` | Holds the fragrance catalogue, filters it against the answers and shows the matches in a `ListView`. |
| `PerfumeActivity` | Detail screen for the selected fragrance, looked up by id. |

**Domain model.** `Perfume` (brand, name, seasons, age range, lifestyle, type, image) and `Question` (key, text, answer options) are plain Java classes. Age ranges use `android.util.Range`, so matching an age group is a simple `contains` check.

**List rendering.** Results are rendered by `PerfumeAdapter`, a custom `ArrayAdapter<Perfume>` that inflates a dedicated list-item layout per row.

**UI.** Custom backgrounds, buttons with pressed states (via touch listeners), a custom font (Proxima Nova) and density-specific drawables (ldpi to xxxhdpi).

## Tech stack

| | |
|---|---|
| Language | Java |
| Platform | Android (min SDK 21, target SDK 29) |
| UI | XML layouts · AndroidX AppCompat · Material Components · ConstraintLayout |
| Build | Gradle (Android Gradle Plugin 4.1) |
| IDE | Android Studio |

## Project structure

```
app/src/main/
├── java/com/example/perfumefinder/
│   ├── MainActivity.java        # home screen
│   ├── QuestionActivity.java    # questionnaire flow
│   ├── ResultActivity.java      # catalogue + matching + results list
│   ├── PerfumeActivity.java     # detail screen
│   ├── PerfumeAdapter.java      # custom ArrayAdapter for the results list
│   ├── Perfume.java             # domain model
│   └── Question.java            # domain model
└── res/
    ├── layout/                  # one layout per activity + list item
    ├── drawable*/               # fragrance images and UI assets per screen density
    └── font/                    # Proxima Nova
```

## Running the app

1. Clone the repository and open it in Android Studio.
2. Let Gradle sync.
3. Run the `app` configuration on an emulator or a device (Android 5.0 / API 21 or higher).

## Limitations

This was a learning project, and it shows in a few places I would design differently today:

- The catalogue is hard-coded in `ResultActivity`; it would belong in a database or a remote API.
- Matching is strict (all criteria must match) rather than scored, so some combinations of answers return no results.
- Button styling is handled with touch listeners per button instead of state-list drawables.
