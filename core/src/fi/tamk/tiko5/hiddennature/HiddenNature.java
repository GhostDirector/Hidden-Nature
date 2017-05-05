package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public class HiddenNature extends Game {
    private SpriteBatch batch;
    final float WORLD_WIDTH = 800f;
    final float WORLD_HEIGHT = 480f;
    private Locale defaultLocale, fin, eng;
    private I18NBundle Localization;
    private boolean sound;
    private Preferences globalPrefs;
    GameScreen gameScreen;
    PauseMenu pauseMenu;
    LevelSelect levelSelect;
    MainMenu mainMenu;
    Credits credits;
    Settings settings;
    protected Music music, gameMusic;

    @Override
    public void dispose () {
        batch.dispose();
    }

	@Override
	public void create () {
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Menumusiikki.mp3"));
        music.setVolume(0.5f);
        music.setLooping(true);
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/Tasomusiikki.mp3"));
        gameMusic.setVolume(0.5f);
        gameMusic.setLooping(true);
        batch = new SpriteBatch();
        globalPrefs = Gdx.app.getPreferences("settings");
        defaultLocale = defaultLocale.getDefault();
        fin = new Locale("fi", "FI");
        eng = new Locale("en", "GB");
        int lang = globalPrefs.getInteger("localization", 1);
        int mute = globalPrefs.getInteger("sound", 1);
        switch (mute) {
            case 1: music.play();
                break;

            case 2: music.pause();
        }

        switch (lang){
            case 1: Localization = I18NBundle.createBundle(Gdx.files.internal("Localization"), defaultLocale);
                break;
            case 2: Localization = I18NBundle.createBundle(Gdx.files.internal("Localization"), eng);
                break;
            case 3: Localization = I18NBundle.createBundle(Gdx.files.internal("Localization"), fin);
                break;
        }
        levelSelect = new LevelSelect(this);
        mainMenu = new MainMenu(this);
        credits = new Credits(this);
        settings = new Settings(this);
        
        sound = true;
        mainMenu.selectScreen();
    }

    public void playMenuMusic(){
        if (globalPrefs.getInteger("sound", 1) == 1) {
            globalPrefs.putInteger("sound", 2);
            globalPrefs.flush();
            music.pause();
            sound = false;
        } else {
            globalPrefs.putInteger("sound", 1);
            globalPrefs.flush();
            music.play();
            sound = true;
        }
    }

    public Locale getFin(){
        return fin;
    }

    public Locale getEng(){
        return eng;
    }

    public boolean isSound(){
        return sound;
    }

    public I18NBundle getLocalization() {
        return Localization;
    }

    public void setLocalization(Locale lang) {
        Localization = I18NBundle.createBundle(Gdx.files.internal("Localization"), lang);
    }

	@Override
	public void render () {
		super.render();
	}
    
    public SpriteBatch getBatch() {
        return batch;
    }
    
    public float getWORLD_WIDTH(){
        return WORLD_WIDTH;
    }
    
    public float getWORLD_HEIGHT(){
        return WORLD_HEIGHT;
    }

}
