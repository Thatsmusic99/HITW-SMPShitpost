package io.github.thatsmusic99.hitwshitpost.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinQuitMessages {
    // Main announcements - Aqua (&b) for regular text, Yellow (&e) for player names.
    // When leaving maybe use Dark Aqua (&3)
    public static List<String> FirstTime = new ArrayList<>(Arrays.asList(
            "&bWelcome, &e{player}&b, to the &6HITW SMP&b!",
            "&bLooks like a new human appeared! Welcome, &e{player}&b!",
            "&bA new challenger approaches: &e{player}&b!",
            "&bIt looks like the walls have pushed &e{player} &binto this place. Welcome!"
    ));

    public static List<String> Return = new ArrayList<>(Arrays.asList(
            "&bWelcome back, &e{player}&b!",
            "&bSHH! &e{player} &bis back! Everbody act normal!",
            "&e{player} &bis back from the dead!",
            "&bPetah, the &e{player} &bis here."
    ));

    public static List<String> Quit = new ArrayList<>(Arrays.asList(
            "&3See you later, &e{player}&3!",
            "&e{player} &3had to go.",
            "&3Bye, &e{player}&3! You'll be back right? ...Right?",
            "&3That's okay, &e{player}&3, take a break."
    ));

    public static List<String> DeathQuit = new ArrayList<>(Arrays.asList(
            "&e{player} &3couldn't take it anymore and &cRAGE QUIT&3!",
            "&3Looks like it was so much for &e{player} &3that they quit.",
            "&e{player} &3has quit due to a skill issue."
    ));

}
