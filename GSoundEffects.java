/**This is interface for sounds*/
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public interface GSoundEffects{
	static Clip st=null;
	/**
	 * When we collect the head, this method sound outs
	 */
	default void collectedEffect() {
		File sound = new File("sounds/dropEffect.wav");
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			
			Thread.sleep(clip.getMicrosecondLength() / 850);
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	/**
	 * When we start the game window, this method sound outs
	 */
	default void welcomeToGame() {
		File sound = new File("sounds/tadaa.wav");
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();

		} catch (Exception e) {
			e.getMessage();
		}
	}
	/**
	 * When we pass to play the game, this method sound outs
	 */
	default void whooEffect() {
		File sound = new File("sounds/whoo.wav");
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();

		} catch (Exception e) {
			e.getMessage();
		}
	}
	default void backMusic() {
		File sound = new File("sounds/mainSound.wav");
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();

		} catch (Exception e) {
			e.getMessage();
		}
	}
	
}
