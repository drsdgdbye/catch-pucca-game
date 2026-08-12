# Catch Pucca Game

![Catch Pucca Game](imgs/background.jpg)

A tiny mouse-chasing mini-game written in pure Java Swing. Try to catch Pucca, but she is fast: whenever you move your cursor onto her, she jumps to a random spot on the screen and taunts you. Only click on her before she escapes — then she is yours.

The catch is that for the first 5 seconds after she moves, she dodges instantly and the click does not register. Move slowly, aim well, and strike at the right moment to become her hero.

## How to Play

- **Hover** – Pucca jumps to a random place and prints a teasing phrase (`catch me, nerd!`, `miss again`, etc.).
- **Click** – if you press her before she escapes (outside the 5-second grace period), she switches to her winning pose and says `you're my hero!`.
- **Reset** – close and relaunch the game to start a new round.

## Requirements

- Java 8 or newer (Swing and `java.time` usage)
- No external dependencies, no build system required

## How to Run

Run from the project root (image paths are relative to the current directory):

```bash
javac -d out src/*.java
java -cp out Main
```

## Project Structure

```
catch-pucca-game/
├── src/
│   ├── Main.java          # Entry point; schedules GameWindow on the EDT
│   ├── GameWindow.java    # Main window, game setup and mouse-event logic
│   ├── PuccaButton.java   # The Pucca button; random repositioning and win state
│   └── PuccaSpeaker.java  # Text label with teasing/win phrases
└── imgs/
    ├── background.jpg     # 800x600 background image
    ├── pucca.png          # Default Pucca icon (100x73)
    └── pucca_win.png      # Winning-pose icon (100x90)
```

### Class overview

| Class           | Responsibility                                                                  |
|-----------------|---------------------------------------------------------------------------------|
| `Main`          | Entry point that launches `GameWindow` asynchronously via `EventQueue.invokeLater`. |
| `GameWindow`    | Initializes the `JFrame`, positions button and speaker, and wires up the mouse listeners. |
| `PuccaButton`   | `JButton` showing Pucca's image; placed at a random location, switches to the win image on click. |
| `PuccaSpeaker`  | `JLabel` that shows a random teasing phrase near the button and win/hello messages.   |

## Technical Details

- **Pure Swing GUI**: no `LayoutManager` — the frame uses a `null` layout and absolute positioning based on the background's 800x600 pixels.
- **Event handling**: a `MouseAdapter` on the button — `mouseEntered` triggers the dodge, `mouseClicked` (outside the grace period) triggers the win.
- **Grace-period mechanic**: `java.time.Instant` records when Pucca last moved; if the cursor enters within 5 seconds, she dodges again and the timer resets, so a quick click is impossible right after she lands.
- **Random placement**: `java.util.Random` picks an X anywhere in the window and a Y in the top half (`height / 2`).
- **No build tooling**: this is a plain IntelliJ IDEA project — no Maven, Gradle, or `pom.xml`. Compile and run with `javac`/`java` as shown above.