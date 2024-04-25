package playsoundTest;

import org.bihe.playSound.PlaySound;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import javax.swing.*;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PlaySoundTest {


    @Test
    public void playSoundSuccessfulTest() {
        PlaySound playSound = new PlaySound("TD.wav");
        assertDoesNotThrow(() -> {
            playSound.playSound();
        });
    }

    /*
    @Test
    public void playSoundExceptionTest() {
        PlaySound playSound = new PlaySound("T.wav");

        assertThrows(FileNotFoundException.class, () -> {
            try(MockedStatic mockedJoption = mockStatic(JOptionPane.class)) {
                    playSound.playSound();
            }
        });
    }*/
}
