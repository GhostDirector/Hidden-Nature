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
    private Locale defaultLocale;
    private I18NBundle Localization;
    private boolean sound;
    private Preferences globalPrefs;


	@Override
	public void create () {
		batch = new SpriteBatch();
        defaultLocale = Locale.getDefault();
        globalPrefs = Gdx.app.getPreferences("settings");
        String lang = globalPrefs.getString("localization", "eng");
        if (lang.equalsIgnoreCase("eng")){
            Localization = I18NBundle.createBundle(Gdx.files.internal("Localization"), defaultLocale);
        } else {
            Localization = I18NBundle.createBundle(Gdx.files.internal("Localization_fi_FI"), defaultLocale);
        }
        setScreen(new MainMenu(this));
        sound = true;

	}
    
    public boolean isSound(){
        return sound;
    }
    
    public I18NBundle getLocalization() {
        return Localization;
    }

    public void setLocalization(String lang) {
        Localization = I18NBundle.createBundle(Gdx.files.internal(lang), defaultLocale);
    }
    
    @Override
    public void dispose () {
        batch.dispose();
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
