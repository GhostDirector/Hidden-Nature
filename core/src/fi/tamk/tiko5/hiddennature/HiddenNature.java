package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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


    @Override
    public void dispose () {
        batch.dispose();
    }

	@Override
	public void create () {
		batch = new SpriteBatch();
        globalPrefs = Gdx.app.getPreferences("settings");
        defaultLocale = defaultLocale.getDefault();
        fin = new Locale("fi", "FI");
        eng = new Locale("en", "GB");
        int lang = globalPrefs.getInteger("localization", 1);
        switch (lang){
            case 1: Localization = I18NBundle.createBundle(Gdx.files.internal("Localization"), defaultLocale);
                break;
            case 2: Localization = I18NBundle.createBundle(Gdx.files.internal("Localization"), eng);
                break;
            case 3: Localization = I18NBundle.createBundle(Gdx.files.internal("Localization"), fin);
                break;
        }

        setScreen(new MainMenu(this));
        sound = true;

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
